package com.mmaquera.showmovies.ui.main.view

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mmaquera.showmovies.R
import com.mmaquera.showmovies.databinding.FragmentMainBinding
import com.mmaquera.showmovies.framework.VerticalSpaceItemDecoration
import com.mmaquera.showmovies.ui.main.listener.DebouncingQueryTextListener
import com.mmaquera.showmovies.ui.main.listener.FilterInterface
import com.mmaquera.showmovies.ui.main.MainViewModel
import com.mmaquera.showmovies.ui.main.listener.MoviesInterface
import com.mmaquera.showmovies.ui.main.adapter.FilterAdapter
import com.mmaquera.showmovies.ui.main.adapter.MoviesAdapter
import javax.inject.Inject

class MainFragment : Fragment(),
    MoviesInterface,
    FilterInterface {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding
    private val moviesAdapter by lazy {
        MoviesAdapter(
            viewModel.getGlideSettings(),
            this
        )
    }
    private lateinit var moviesLayoutManager: LinearLayoutManager
    private val filterAdapter by lazy { FilterAdapter(this) }
    private lateinit var searchView: SearchView
    private var orderBy: String = "title.asc"
    private var page: Int = 1
    private lateinit var navController: NavController

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        navController = Navigation.findNavController(view)
        setupRecycler()
        setupObservers()
        setupEvents()
        binding.swipeRefresh.isRefreshing = true
        viewModel.getFilters()
        viewModel.getMovies(page, orderBy)
    }

    private fun setupRecycler() {
        with(binding) {
            moviesLayoutManager = LinearLayoutManager(activity)
            recyclerMovies.layoutManager = moviesLayoutManager
            recyclerMovies.addItemDecoration(VerticalSpaceItemDecoration(4))
            recyclerMovies.adapter = moviesAdapter

            recyclerFilter.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            recyclerFilter.adapter = filterAdapter
        }
    }

    private fun setupObservers() {
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            moviesAdapter.replace(it)
            binding.swipeRefresh.isRefreshing = false
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            binding.swipeRefresh.isRefreshing = false
        })
        viewModel.filters.observe(viewLifecycleOwner, Observer {
            filterAdapter.items = it
        })
    }

    private fun setupEvents() {
        with(binding) {
            swipeRefresh.setOnRefreshListener {
                val query = searchView.query.toString()
                if (query.isEmpty())
                    viewModel.getMovies(1, orderBy)
                else
                    viewModel.getMoviesFilter(title = query, orderBy = orderBy, page = 1)
            }

            recyclerMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        val visibleItemCount = moviesLayoutManager.childCount
                        val pastVisibleItem =
                            moviesLayoutManager.findFirstCompletelyVisibleItemPosition()
                        val total = moviesAdapter.itemCount

                        if ((visibleItemCount + pastVisibleItem) >= total){
                            page++
                            viewModel.getMovies(page, orderBy)
                        }
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_movies, menu)
        searchView = menu.findItem(R.id.menu_search).actionView as SearchView

        searchView.setOnQueryTextListener(
            DebouncingQueryTextListener(
                lifecycle
            ) { query ->
                query?.let {
                    if (it.isEmpty())
                        viewModel.getMovies(1, orderBy)
                    else
                        viewModel.getMoviesFilter(title = it, orderBy = orderBy, page = 1)
                }
            })

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onMovieClick(id: Int) {
        MainFragmentDirections.actionMainFragmentToMovieActivity(id)
            .also { navController.navigate(it) }
    }

    override fun onFilterClick(id: String, position: Int) {
        orderBy = id
        page = 1
        filterAdapter.updateList(position)
        binding.recyclerMovies.smoothScrollToPosition(0)

        val query = searchView.query.toString()
        if (query.isEmpty())
            viewModel.getMovies(page, orderBy)
        else
            viewModel.getMoviesFilter(title = query, orderBy = orderBy, page = page)

    }
}
