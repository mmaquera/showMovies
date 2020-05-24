package com.mmaquera.showmovies.ui.main.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmaquera.showmovies.databinding.FragmentMainBinding
import com.mmaquera.showmovies.framework.VerticalSpaceItemDecoration
import com.mmaquera.showmovies.ui.main.MainActivity
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding
    private val adapter by lazy { MoviesAdapter(viewModel.getGlideSettings()) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        setupRecycler()
        setupObservers()
        viewModel.getMovies()
    }

    private fun setupRecycler(){
        with(binding){
            recyclerMovies.layoutManager = LinearLayoutManager(activity)
            recyclerMovies.addItemDecoration(VerticalSpaceItemDecoration(4))
            recyclerMovies.adapter = adapter
        }
    }

    private fun setupObservers(){
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            adapter.items = it
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
    }
}
