package com.iswan.main.movflix.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iswan.main.movflix.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        title = getString(R.string.about)
    }
}