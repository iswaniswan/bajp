package com.iswan.main.movflix.ui.favourite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iswan.main.movflix.R
import com.iswan.main.movflix.databinding.ActivityFavouriteBinding
import com.iswan.main.movflix.ui.adapters.SectionPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavouriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        with(binding) {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }

        supportActionBar?.let {
            it.title = getString(R.string.favourites)
            it.elevation = 0f
            it.setDisplayHomeAsUpEnabled(true)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}