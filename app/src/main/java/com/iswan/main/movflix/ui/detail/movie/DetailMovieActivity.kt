package com.iswan.main.movflix.ui.detail.movie

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.iswan.main.movflix.R
import com.iswan.main.movflix.data.models.MovieDetailEntity
import com.iswan.main.movflix.data.models.concatName
import com.iswan.main.movflix.databinding.ActivityDetailMovieBinding
import com.iswan.main.movflix.ui.main.adapters.CompaniesAdapter
import com.iswan.main.movflix.utils.Utils
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var movieId: String
    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var companiesAdapter: CompaniesAdapter
    private lateinit var viewModel: DetailMovieViewModel
    private val utils: Utils = Utils()

    private val TAG = "DETAIL MOVIE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras!!
        val mId = extras.getInt(EXTRA_ID, 0)
        if (mId == 0) {
            showError()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f

        movieId = mId.toString()
        initViewModel()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showLoading(state: Boolean) {
        with (binding) {
            when {
                state -> { frameLoading.visibility = View.VISIBLE }
                else -> { frameLoading.visibility = View.INVISIBLE }
            }
        }
    }

    private fun showError() {
        showLoading(false)
        utils.showNotifSnackbar(binding.root, getString(R.string.error_occured))
        finish()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(DetailMovieViewModel::class.java)
        viewModel.movie.observe(this, {
            lifecycleScope.launch {
                delay(500L)
                showLoading(false)
            }
            initView(it)
        })
        viewModel.getMovie(movieId)
    }

    private fun initView(movie: MovieDetailEntity) {
        with (binding) {
            Glide.with(this@DetailMovieActivity)
                .load(utils.getImagePath(2, movie.backdropPath.toString()))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_broken_image_black))
                .into(ivBackdrop)

            tvTitle.text = movie.title.toString()

            val releaseDate = getString(R.string.release_date) + " " + movie.releaseDate.toString()
            tvDate.text = releaseDate

            val genres = getString(R.string.genres) + " " + movie.genres?.concatName()
            tvGenres.text = genres
            tvScore.text = movie.voteAverage.toString()

            val duration = getString(R.string.duration) + " " + movie.runtime.toString() + " min"
            tvDuration.text = duration

            tvTagline.text = movie.tagline.toString()
            tvOverview.text = movie.overview.toString()

            tvStatus.text = movie.status.toString()
            tvLanguage.text = movie.originalLanguage.toString()
            tvBudget.text = utils.currencyString(movie.budget)
            tvRevenue.text = utils.currencyString(movie.revenue)

            tvHomepage.text = movie.homepage.toString()
        }

        companiesAdapter = CompaniesAdapter()
        companiesAdapter.setData(movie.productionCompanies!!)
        val layout = LinearLayoutManager(this@DetailMovieActivity, LinearLayoutManager.HORIZONTAL, false)
        with (binding) {
            rvCompanies.layoutManager = layout
            rvCompanies.setHasFixedSize(true)
            rvCompanies.adapter = companiesAdapter
        }
    }

}