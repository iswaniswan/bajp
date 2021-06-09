package com.iswan.main.movflix.ui.main.movies

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.iswan.main.movflix.R
import com.iswan.main.movflix.data.models.Movie
import com.iswan.main.movflix.databinding.FragmentMovieBinding
import com.iswan.main.movflix.ui.detail.movie.DetailMovieActivity
import com.iswan.main.movflix.ui.main.adapters.MovieLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment() : Fragment() {

    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = ""
    }

    private lateinit var binding: FragmentMovieBinding
    private lateinit var movieAdapter : MoviePagingDataAdapter
    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MoviePagingDataAdapter()

        with(binding.rvMovies) {
            setHasFixedSize(true)

            adapter = movieAdapter.withLoadStateHeaderAndFooter(
                header = MovieLoadStateAdapter { movieAdapter.retry() },
                footer = MovieLoadStateAdapter { movieAdapter.retry() }
            )

            binding.btnLoadRetry.setOnClickListener {
                movieAdapter.retry()
            }

        }

        movieAdapter.setOnItemClickCallback(object : MoviePagingDataAdapter.IOnItemClickCallback {
            override fun onItemClick(movie: Movie) {
                val intent = Intent(requireActivity(), DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_ID, movie.id)
                startActivity(intent)
            }
        })

        movieAdapter.addLoadStateListener { loadState ->
            binding.apply {
                val refresh = loadState.source.refresh

                val empty = refresh is LoadState.NotLoading
                        && movieAdapter.itemCount == 0

                val notFound = refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached && empty

                val initialization = refresh is LoadState.Loading

                binding.apply {
                    rvMovies.isVisible = !empty
                    progressBarMovies.isVisible = initialization
                    tvLoadError.isVisible = refresh is LoadState.Error
                    btnLoadRetry.isVisible = refresh is LoadState.Error
                    tvNotFound.isVisible = notFound
                }
            }
        }

        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        search(query)

        lifecycleScope.launch {
            movieAdapter.loadStateFlow
                .distinctUntilChangedBy { it.source.refresh }
                .filter { it.source.refresh is LoadState.NotLoading }
                .collect { binding.rvMovies.scrollToPosition(0) }
        }

        setHasOptionsMenu(true)
    }

    private var searchJob: Job? = null

    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = viewLifecycleOwner.lifecycleScope.launch {
            viewModel.search(query).collectLatest {
                movieAdapter.submitData(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.top_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    binding.rvMovies.scrollToPosition(0)
                    search(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = true
        })
    }

}