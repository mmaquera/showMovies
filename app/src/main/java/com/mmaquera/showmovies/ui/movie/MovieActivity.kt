package com.mmaquera.showmovies.ui.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmaquera.showmovies.application.AppApplication
import com.mmaquera.showmovies.databinding.ActivityMovieBinding
import com.mmaquera.showmovies.di.subcomponents.MovieComponent
import com.mmaquera.showmovies.extensions.openWithGlide
import com.mmaquera.showmovies.ui.movie.adapter.CastAdapter
import com.mmaquera.showmovies.ui.movie.adapter.ReviewAdapter
import com.mmaquera.showmovies.ui.movie.model.MovieModel
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MovieViewModel
    private lateinit var binding : ActivityMovieBinding
    private lateinit var movieComponent: MovieComponent
    private val args: MovieActivityArgs by navArgs()

    private val castAdapter by lazy {
        CastAdapter(
            viewModel.getGlideSettings()
        )
    }
    private val reviewAdapter by lazy { ReviewAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        movieComponent = (application as AppApplication).appComponent.movieComponent().create()
        movieComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)
        setupRecycler()
        setupObservers()
        viewModel.getMovie(args.id)
    }

    private fun setupObservers(){
        viewModel.movie.observe(this, Observer { showData(it) })

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun setupRecycler(){
        with(binding){
            recyclerCast.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            recyclerCast.adapter = castAdapter

            recyclerReview.layoutManager = LinearLayoutManager(applicationContext)
            recyclerReview.adapter = reviewAdapter
            recyclerReview.isNestedScrollingEnabled = false
        }
    }

    private fun showData(movie: MovieModel){
        with(binding){

            toolbar.title = movie.title

            textTitle.text = movie.title
            movie.image?.let{
                imageMovie.openWithGlide(viewModel.getGlideSettings().invoke(it, "w500"))
            }

            castAdapter.items = movie.cast
            reviewAdapter.items = movie.reviews

            textSummary.text = movie.summary
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
