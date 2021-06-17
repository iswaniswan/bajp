package com.iswan.main.movflix.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.iswan.main.movflix.R
import com.iswan.main.movflix.data.models.Season
import com.iswan.main.movflix.databinding.ItemTvSeasonBinding
import com.iswan.main.movflix.utils.Utils

class SeasonsAdapter:
    RecyclerView.Adapter<SeasonsAdapter.SeasonsViewHolder>() {

    private var seasons = listOf<Season>()

    fun setData(seasons: List<Season>) {
        this.seasons = seasons
        this.notifyDataSetChanged()
    }

    inner class SeasonsViewHolder(private val binding: ItemTvSeasonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(season: Season) {
            with (binding) {
                Glide.with(itemView.context)
                    .load(Utils.getImagePath(1, season.posterPath))
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_broken_image_black))
                    .into(ivBackdrop)
                tvYear.text = Utils.yearStr(season.airDate)
                tvSeason.text = season.name
                val episode = season.episodeCount.toString() + " " + itemView.resources.getString(R.string.episodes)
                tvEpisodes.text = episode
                tvOverview.text = season.overview
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonsViewHolder {
        val binding: ItemTvSeasonBinding = ItemTvSeasonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeasonsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeasonsViewHolder, position: Int) {
        val season = seasons[position]
        holder.bind(season)
    }

    override fun getItemCount(): Int = seasons.size
}