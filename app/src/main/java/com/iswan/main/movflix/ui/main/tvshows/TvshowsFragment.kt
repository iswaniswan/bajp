package com.iswan.main.movflix.ui.main.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.iswan.main.movflix.databinding.FragmentTvshowBinding
import com.iswan.main.movflix.ui.detail.tvshow.DetailTvActivity
import com.iswan.main.movflix.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvshowsFragment : Fragment() {

    private lateinit var binding: FragmentTvshowBinding
    private val viewModel: TvShowsViewModel by viewModels()
//    private val mAdapter: TvShowsAdapter = TvShowsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvshowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            showLoading(true)
        }
        showLoading(true)
    }

    private fun showLoading(state: Boolean) {
        with (binding) {
            when {
                state -> {
                    rvTvshow.visibility = View.INVISIBLE
                    progressBarTvshows.visibility = View.VISIBLE
                } else -> {
                    rvTvshow.visibility = View.VISIBLE
                    progressBarTvshows.visibility = View.INVISIBLE
            }
            }
        }
    }

//    private fun updateRecyclerView(list: ArrayList<TvShow>) {
//        mAdapter.setData(list)
//        with (binding.rvTvshow) {
//            layoutManager = LinearLayoutManager(context)
//            setHasFixedSize(true)
//            adapter = mAdapter
//        }
//        mAdapter.setOnItemClickCallback(object : TvShowsAdapter.IOnItemClickCallback {
//            override fun onItemClick(tvs: TvShow) {
//                val intent = Intent(requireActivity(), DetailTvActivity::class.java)
//                intent.putExtra(DetailTvActivity.EXTRA_ID, tvs.id)
//                startActivity(intent)
//            }
//        })
//    }


}