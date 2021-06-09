package com.iswan.main.movflix.ui.main.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.iswan.main.movflix.R
import com.iswan.main.movflix.data.models.Movie
import com.iswan.main.movflix.databinding.ItemMovieBinding
import com.iswan.main.movflix.utils.Utils

class MoviePagingDataAdapter
    : PagingDataAdapter<Movie, MoviePagingDataAdapter.MovieViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem

        }
    }

    private var iOnItemClickCallback: IOnItemClickCallback? = null

    interface IOnItemClickCallback {
        fun onItemClick(movie: Movie)
    }

    fun setOnItemClickCallback(iOnItemClickCallback: IOnItemClickCallback) {
        this.iOnItemClickCallback = iOnItemClickCallback
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            with(binding) {
                tvTitle.text = movie.title
                val releaseDate =
                    itemView.resources.getString(R.string.release_date) + " : " + movie.releaseDate
                tvDate.text = releaseDate
                val score =
                    itemView.resources.getString(R.string.user_score) + " " + movie.voteAverage.toString()
                tvScore.text = score
                tvOverview.text = movie.overview
                Glide.with(itemView.context)
                    .load(Utils.getImagePath(1, movie.posterPath))
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_broken_image_black))
                    .into(ivBackdrop)
                itemView.setOnClickListener {
                    iOnItemClickCallback?.onItemClick(movie)
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