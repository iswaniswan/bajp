package com.iswan.main.movflix.ui.detail.tvshow

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.iswan.main.movflix.R
import com.iswan.main.movflix.data.models.TvShowDetailEntity
import com.iswan.main.movflix.data.models.concatName
import com.iswan.main.movflix.databinding.ActivityDetailTvBinding
import com.iswan.main.movflix.ui.detail.movie.DetailMovieActivity
import com.iswan.main.movflix.ui.main.adapters.CompaniesAdapter
import com.iswan.main.movflix.ui.main.adapters.SeasonsAdapter
import com.iswan.main.movflix.utils.Utils
import kotlinx.coroutines.*

class DetailTvActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var tvId : String
    private lateinit var binding: ActivityDetailTvBinding
    private lateinit var companiesAdapter: CompaniesAdapter
    private lateinit var seasonsAdapter: SeasonsAdapter
    private lateinit var viewModel: DetailTvViewModel

    private val utils: Utils = Utils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras!!
        val mId = extras.getInt(DetailMovieActivity.EXTRA_ID, 0)
        if (mId == 0) {
            showError()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f

        tvId = mId.toString()
        initViewModel()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showError() {
        showLoading(false)
        utils.showNotifSnackbar(binding.root, getString(R.string.error_occured))
        finish()
    }


    private fun showLoading(state: Boolean) {
        with (binding) {
            when {
                state -> { frameLoading.visibility = View.VISIBLE }
                else -> { frameLoading.visibility = View.INVISIBLE }
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(DetailTvViewModel::class.java)
        viewModel.tvShow.observe(this, {
            lifecycleScope.launch {
                delay(500L)
                showLoading(false)
            }
            initView(it)
        })
        viewModel.getTvShow(tvId)
    }

    private fun initView(tv: TvShowDetailEntity) {
        with (binding) {
            Glide.with(this@DetailTvActivity)
                .load(utils.getImagePath(2, tv.posterPath.toString()))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_broken_image_black))
                .into(ivBackdrop)
            tvTitle.text = tv.name.toString()

            val genres = getString(R.string.genres) + " " + tv.genres?.concatName()
            tvGenres.text = genres
            tvScore.text = tv.voteAverage.toString()

            val duration = getString(R.string.duration) + " " + tv.episodeRunTime.toString() + " min"
            tvDuration.text = duration

            tvTagline.text = tv.tagline.toString()
            tvOverview.text = tv.overview.toString()

            tvStatus.text = tv.status.toString()
            tvLanguage.text = tv.originalLanguage.toString()

            tvHomepage.text = tv.homepage.toString()
        }

        seasonsAdapter = SeasonsAdapter()
        seasonsAdapter.setData(tv.seasons)
        with (binding) {
            rvSeasons.layoutManager = LinearLayoutManager(this@DetailTvActivity, LinearLayoutManager.HORIZONTAL, false)
            rvSeasons.setHasFixedSize(true)
            rvSeasons.adapter = seasonsAdapter
        }

        companiesAdapter = CompaniesAdapter()
        companiesAdapter.setData(tv.productionCompanies)
        with (binding) {
            rvCompanies.layoutManager = LinearLayoutManager(this@DetailTvActivity, LinearLayoutManager.HORIZONTAL, false)
            rvCompanies.setHasFixedSize(true)
            rvCompanies.adapter = companiesAdapter
        }
    }




}