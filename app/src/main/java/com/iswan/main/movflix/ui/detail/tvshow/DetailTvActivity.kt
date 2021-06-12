package com.iswan.main.movflix.ui.detail.tvshow

import android.os.Bundle
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
import com.iswan.main.movflix.data.models.TvShowDetail
import com.iswan.main.movflix.data.models.concatName
import com.iswan.main.movflix.data.vo.Resource
import com.iswan.main.movflix.databinding.ActivityDetailTvBinding
import com.iswan.main.movflix.ui.adapters.CompaniesAdapter
import com.iswan.main.movflix.ui.adapters.SeasonsAdapter
import com.iswan.main.movflix.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class DetailTvActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private val viewModel: DetailTvViewModel by viewModels()
    private lateinit var binding: ActivityDetailTvBinding
    private lateinit var companiesAdapter: CompaniesAdapter
    private lateinit var seasonsAdapter: SeasonsAdapter
    private var tvShow: TvShowDetail? = null
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mId = intent.extras?.getString(EXTRA_ID)
        if (mId == null || mId.isEmpty()) {
            showError()
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            elevation = 0f
            title = getString(R.string.tvshow_detail)
            setBackgroundDrawable(ContextCompat.getDrawable(
                this@DetailTvActivity, R.drawable.bg_secondary))
        }

        viewModel.getTvShow(mId.toString())
        viewModel.tvShow.observe(this, {
            when (it) {
                is Resource.Success -> {
                    tvShow = it.data
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
        tvShow?.let {
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
                this@DetailTvActivity, R.drawable.ic_favourite_custom
            )
        } else {
            actionFavourite?.icon = ContextCompat.getDrawable(
                this@DetailTvActivity, R.drawable.ic_favourite_border_custom
            )
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
        return if (tvShow != null) {
            tvShow?.let {
                viewModel.insertUpdateFavourite(it)
            }
            true
        } else false
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showError() {
        Utils.showNotifSnackbar(binding.root, getString(R.string.error_occured))
        finish()
    }

    private fun populateView() {
        tvShow?.isFavourite?.let {
            setFavouriteState(it)
        }
        with(binding) {
            Glide.with(this@DetailTvActivity)
                .load(Utils.getImagePath(2, tvShow?.posterPath.toString()))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_broken_image_black))
                .into(ivBackdrop)
            tvTitle.text = tvShow?.name
            val genres = getString(R.string.genres) + " " + tvShow?.genres?.concatName()
            tvGenres.text = genres
            tvScore.text = tvShow?.voteAverage.toString()
            val duration =
                getString(R.string.duration) + " " + tvShow?.episodeRunTime.toString() + " min"
            tvDuration.text = duration
            tvTagline.text = tvShow?.tagline
            tvOverview.text = tvShow?.overview
            tvStatus.text = tvShow?.status
            tvLanguage.text = tvShow?.originalLanguage
            tvHomepage.text = tvShow?.homepage
        }

        seasonsAdapter = SeasonsAdapter()
        tvShow?.seasons?.let { seasonsAdapter.setData(it) }

        with(binding) {
            rvSeasons.layoutManager =
                LinearLayoutManager(this@DetailTvActivity, LinearLayoutManager.HORIZONTAL, false)
            rvSeasons.setHasFixedSize(true)
            rvSeasons.adapter = seasonsAdapter
        }

        companiesAdapter = CompaniesAdapter()
        tvShow?.productionCompanies?.let { companiesAdapter.setData(it) }

        with(binding) {
            rvCompanies.layoutManager =
                LinearLayoutManager(this@DetailTvActivity, LinearLayoutManager.HORIZONTAL, false)
            rvCompanies.setHasFixedSize(true)
            rvCompanies.adapter = companiesAdapter
        }
    }
}