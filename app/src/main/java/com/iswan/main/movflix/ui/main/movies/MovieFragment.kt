package com.iswan.main.movflix.ui.main.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.iswan.main.movflix.R
import com.iswan.main.movflix.data.models.MovieEntity
import com.iswan.main.movflix.databinding.FragmentMovieBinding
import com.iswan.main.movflix.ui.detail.movie.DetailMovieActivity
import com.iswan.main.movflix.utils.Utils

class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel : MoviesViewModel by viewModels()
    private val TAG = this.javaClass.simpleName
    private val utils: Utils = Utils()
    private val mAdapter: MovieAdapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            showLoading(true)
            viewModel.listMovie.observe(viewLifecycleOwner, {
                if (it.isNotEmpty()) {
                    showLoading(false)
                    updateRecyclerView(it)
                } else {
                    showLoading(false)
                    utils.showNotifSnackbar(binding.root, getString(R.string.error_occured))
                }
            })
        }
        showLoading(true)
    }

    private fun showLoading(state: Boolean) {
        with (binding) {
            when {
                state -> {
                    rvMovies.visibility = View.INVISIBLE
                    progressBarMovies.visibility = View.VISIBLE
                } else -> {
                    rvMovies.visibility = View.VISIBLE
                    progressBarMovies.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun updateRecyclerView(list: ArrayList<MovieEntity>) {
        mAdapter.setData(list)
        with (binding.rvMovies) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
        mAdapter.setOnItemClickCallback(object : MovieAdapter.IOnItemClickCallback {
            override fun onItemClick(movie: MovieEntity) {
                val intent = Intent(requireActivity(), DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_ID, movie.id)
                startActivity(intent)
            }
        })
    }

}