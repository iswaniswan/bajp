package com.iswan.main.movflix.ui.fragments.tvshows

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.iswan.main.movflix.R
import com.iswan.main.movflix.data.models.TvShow
import com.iswan.main.movflix.databinding.FragmentTvshowBinding
import com.iswan.main.movflix.ui.adapters.GeneralLoadStateAdapter
import com.iswan.main.movflix.ui.detail.movie.DetailMovieActivity
import com.iswan.main.movflix.ui.detail.tvshow.DetailTvActivity
import com.iswan.main.movflix.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TvshowsFragment : Fragment() {

    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = ""
    }

    private lateinit var binding: FragmentTvshowBinding
    private lateinit var tvAdapter: TvShowPagingDataAdapter
    private lateinit var searchView: SearchView
    private val viewModel: TvShowsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvshowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        val isMainActivity =
            requireActivity()::class.java.simpleName == MainActivity::class.java.simpleName

        if (isMainActivity) {
            val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
            search(query)
            setHasOptionsMenu(true)
        } else {
            getFavourites()
        }

        binding.swipeLayout.setOnRefreshListener(swipeListener(isMainActivity))

    }

    private fun swipeListener(state: Boolean) = object : SwipeRefreshLayout.OnRefreshListener {
        override fun onRefresh() {
            if (state) {
                Log.d(TAG, "onRefresh: LAYOUT SWIPE")
                (activity as MainActivity).backArrow()
                search("")
                searchView.apply {
                    setQuery("", false)
                    isIconified = true
                    isIconified = true
                    onActionViewCollapsed()
                }
            }
            binding.swipeLayout.isRefreshing = false
        }
    }

    private val TAG = "TvShowsFragment"

    private fun getFavourites() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getFavourites().collectLatest {
                tvAdapter.submitData(it)
            }
        }
    }

    private fun initAdapter() {
        tvAdapter = TvShowPagingDataAdapter()

        with(binding.rvTvshow) {
            setHasFixedSize(true)

            adapter = tvAdapter.withLoadStateHeaderAndFooter(
                header = GeneralLoadStateAdapter { tvAdapter.retry() },
                footer = GeneralLoadStateAdapter { tvAdapter.retry() }
            )

            binding.btnLoadRetry.setOnClickListener {
                tvAdapter.retry()
            }

        }

        tvAdapter.setOnItemClickCallback(object : TvShowPagingDataAdapter.IOnItemClickCallback {
            override fun onItemClick(tvShow: TvShow) {
                val intent = Intent(requireActivity(), DetailTvActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_ID, tvShow.id)
                startActivity(intent)
            }
        })

        tvAdapter.addLoadStateListener { loadState ->
            binding.apply {
                val refresh = loadState.source.refresh

                val empty = refresh is LoadState.NotLoading
                        && tvAdapter.itemCount == 0

                val notFound = refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached && empty

                val initialization = refresh is LoadState.Loading

                binding.apply {
                    rvTvshow.isVisible = !empty && refresh is LoadState.NotLoading
                    progressBarMovies.isVisible = initialization
                    tvLoadError.isVisible = refresh is LoadState.Error
                    btnLoadRetry.isVisible = refresh is LoadState.Error
                    tvNotFound.isVisible = notFound
                }
            }
        }
    }

    private var searchJob: Job? = null

    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = viewLifecycleOwner.lifecycleScope.launch {
            viewModel.search(query).collectLatest {
                tvAdapter.submitData(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    binding.rvTvshow.scrollToPosition(0)
                    search(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = true
        })
    }
}