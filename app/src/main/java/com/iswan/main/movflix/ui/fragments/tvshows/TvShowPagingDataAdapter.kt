package com.iswan.main.movflix.ui.fragments.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.iswan.main.movflix.R
import com.iswan.main.movflix.data.models.Movie
import com.iswan.main.movflix.data.models.TvShow
import com.iswan.main.movflix.databinding.ItemMovieBinding
import com.iswan.main.movflix.utils.Utils

class TvShowPagingDataAdapter
    : PagingDataAdapter<TvShow, TvShowPagingDataAdapter.MovieViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
                oldItem == newItem

        }
    }

    private var iOnItemClickCallback: IOnItemClickCallback? = null

    interface IOnItemClickCallback {
        fun onItemClick(tvShow: TvShow)
    }

    fun setOnItemClickCallback(iOnItemClickCallback: IOnItemClickCallback) {
        this.iOnItemClickCallback = iOnItemClickCallback
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: TvShow) {
            with(binding) {
                tvTitle.text = tvShow.name
                val firstAirDate =
                    itemView.resources.getString(R.string.first_air_date) + " : " + tvShow.firstAirDate
                tvDate.text = firstAirDate
                val score =
                    itemView.resources.getString(R.string.user_score) + " " + tvShow.voteAverage.toString()
                tvScore.text = score
                tvOverview.text = tvShow.overview
                Glide.with(itemView.context)
                    .load(Utils.getImagePath(1, tvShow.posterPath))
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_broken_image_black))
                    .into(ivBackdrop)
                itemView.setOnClickListener {
                    iOnItemClickCallback?.onItemClick(tvShow)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bind(currentItem)
    }
}