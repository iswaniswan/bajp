package com.iswan.main.movflix.ui.detail.movie

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.iswan.main.movflix.R
import com.iswan.main.movflix.data.models.MovieDetail
import com.iswan.main.movflix.data.models.concatName
import com.iswan.main.movflix.data.vo.Resource
import com.iswan.main.movflix.databinding.ActivityDetailMovieBinding
import com.iswan.main.movflix.ui.adapters.CompaniesAdapter
import com.iswan.main.movflix.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private val viewModel: DetailMovieViewModel by viewModels()
    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var companiesAdapter: CompaniesAdapter
    private var movie: MovieDetail? = null
    private var menu: Menu? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mId = intent.extras?.getString(EXTRA_ID)
        if (mId == null || mId.isEmpty()) {
            finish()
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            elevation = 0f
            title = getString(R.string.movie_detail)
            setBackgroundDrawable(ContextCompat.getDrawable(
                this@DetailMovieActivity, R.drawable.bg_secondary))
        }

        viewModel.getMovie(mId.toString())
        viewModel.movie.observe(this, {
            when (it) {
                is Resource.Success -> {
                    movie = it.data
                    populateView()
                    binding.frameLoading.isVisible = false
                }
                is Resource.Loading -> binding.frameLoading.isVisible = true

                is Resource.Error -> {
                    binding.frameLoading.isVisible = false
                    Utils.showNotifSnackbar(binding.root, getString(R.string.error_occured))
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        this.menu = menu
        movie?.let {
            setFavouriteState(it.isFavourite)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    private fun setFavouriteState(state: Boolean) {
        if (menu == null) {
            invalidateOptionsMenu()
            return
        }
        val actionFavourite = menu?.findItem(R.id.action_favourite)
        if (state) {
            actionFavourite?.icon = ContextCompat.getDrawable(
                this@DetailMovieActivity, R.drawable.ic_favourite_custom)
        } else {
            actionFavourite?.icon = ContextCompat.getDrawable(
                this@DetailMovieActivity, R.drawable.ic_favourite_border_custom)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favourite -> {
                toggleFavourite()
            }
            else -> false
        }
    }

    private fun toggleFavourite(): Boolean {
        return if (movie != null) {
            movie?.let {
                viewModel.insertUpdateFavourite(it)
            }
            true
        } else false
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun populateView() {
        movie?.isFavourite?.let {
            setFavouriteState(it)
        }
        with(binding) {
            Glide.with(this@DetailMovieActivity)
                .load(Utils.getImagePath(2, movie?.posterPath.toString()))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_broken_image_black))
                .into(ivBackdrop)
            tvTitle.text = movie?.title
            val releaseDate = getString(R.string.release_date) + " " + movie?.releaseDate
            tvDate.text = releaseDate
            val genres = getString(R.string.genres) + " " + movie?.genres?.concatName()
            tvGenres.text = genres
            tvScore.text = movie?.voteAverage.toString()
            val duration = getString(R.string.duration) + " " + movie?.runtime.toString() + " min"
            tvDuration.text = duration
            tvTagline.text = movie?.tagline
            tvOverview.text = movie?.overview
            tvStatus.text = movie?.status
            tvLanguage.text = movie?.originalLanguage
            tvBudget.text = movie?.budget?.let { Utils.currencyString(it) }
            tvRevenue.text = movie?.revenue?.let { Utils.currencyString(it) }
            tvHomepage.text = movie?.homepage
        }

        movie?.let {
            companiesAdapter = CompaniesAdapter()
            companiesAdapter.setData(it.productionCompanies)
        }

        with(binding.rvCompanies) {
            layoutManager = LinearLayoutManager(
                this@DetailMovieActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = companiesAdapter
        }
    }

}