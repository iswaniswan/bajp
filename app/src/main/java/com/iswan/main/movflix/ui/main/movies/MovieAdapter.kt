package com.iswan.main.movflix.ui.main.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.iswan.main.movflix.R
import com.iswan.main.movflix.data.models.Movie
import com.iswan.main.movflix.databinding.ItemMovieBinding
import com.iswan.main.movflix.utils.Utils

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val utils: Utils = Utils()

    private var iOnItemClickCallback: IOnItemClickCallback? = null

    interface IOnItemClickCallback {
        fun onItemClick(movie: Movie)
    }

    fun setOnItemClickCallback(callback: IOnItemClickCallback) {
        this.iOnItemClickCallback = callback
    }

    private val listMovie = ArrayList<Movie>()

    fun setData(movies: ArrayList<Movie>) {
        this.listMovie.clear()
        this.listMovie.addAll(movies)
        this.notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with (binding) {
                tvTitle.text = movie.title
                val releaseDate = itemView.resources.getString(R.string.release_date) + " : " + movie.releaseDate
                tvDate.text = releaseDate
                val score = itemView.resources.getString(R.string.user_score) + " " + movie.voteAverage.toString()
                tvScore.text = score
                tvOverview.text = movie.overview
                Glide.with(itemView.context)
                    .load(utils.getImagePath(1, movie.posterPath))
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_broken_image_black))
                    .into(ivBackdrop)
                itemView.setOnClickListener {
                    iOnItemClickCallback?.onItemClick(movie)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: ItemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(
            parent.context), parent, false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovie.size


}