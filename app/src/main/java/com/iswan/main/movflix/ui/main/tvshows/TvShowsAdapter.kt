package com.iswan.main.movflix.ui.main.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.iswan.main.movflix.R
import com.iswan.main.movflix.data.models.TvShowEntity
import com.iswan.main.movflix.databinding.ItemMovieBinding
import com.iswan.main.movflix.utils.Utils

class TvShowsAdapter: RecyclerView.Adapter<TvShowsAdapter.TvShowViewHolder>() {

    private val utils: Utils = Utils()

    private var iOnItemClickCallback: IOnItemClickCallback? = null

    interface IOnItemClickCallback {
        fun onItemClick(tvs: TvShowEntity)
    }

    fun setOnItemClickCallback(callback: IOnItemClickCallback) {
        this.iOnItemClickCallback = callback
    }

    private val listMovie = ArrayList<TvShowEntity>()

    fun setData(tvs: ArrayList<TvShowEntity>) {
        this.listMovie.clear()
        this.listMovie.addAll(tvs)
        this.notifyDataSetChanged()
    }

    inner class TvShowViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvs: TvShowEntity) {
            with (binding) {
                tvTitle.text = tvs.name
                val firstAirDate = itemView.resources.getString(R.string.first_air_date) + " : " + tvs.firstAirDate
                tvDate.text = firstAirDate
                val score = itemView.resources.getString(R.string.user_score) + " " + tvs.voteAverage.toString()
                tvScore.text = score
                tvOverview.text = tvs.overview
                Glide.with(itemView.context)
                    .load(utils.getImagePath(1, tvs.posterPath.toString()))
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_broken_image_black))
                    .into(ivBackdrop)
                itemView.setOnClickListener {
                    iOnItemClickCallback?.onItemClick(tvs)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding: ItemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(
            parent.context), parent, false
        )
        return TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvs = listMovie[position]
        holder.bind(tvs)
    }

    override fun getItemCount(): Int = listMovie.size


}