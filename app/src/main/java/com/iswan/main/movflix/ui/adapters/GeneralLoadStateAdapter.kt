package com.iswan.main.movflix.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iswan.main.movflix.databinding.MovieLoadStateBinding

class GeneralLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<GeneralLoadStateAdapter.MovieLoadStateViewHolder>() {

    inner class MovieLoadStateViewHolder(private val binding: MovieLoadStateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnLoadRetry.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                progressBar.isVisible = loadState is LoadState.Loading
                btnLoadRetry.isVisible = loadState !is LoadState.Loading
                tvLoadError.isVisible = loadState !is LoadState.Loading
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MovieLoadStateViewHolder {
        val binding =
            MovieLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieLoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

}