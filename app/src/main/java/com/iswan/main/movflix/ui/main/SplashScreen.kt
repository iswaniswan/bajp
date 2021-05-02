package com.iswan.main.movflix.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.iswan.main.movflix.R
import com.iswan.main.movflix.databinding.ActivitySplashScreenBinding

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {

    private lateinit var binding : ActivitySplashScreenBinding
    private val timeout: Long = 3000

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
        }
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        val topAnim = AnimationUtils.loadAnimation(this, R.anim.drop_in_top)
        val bottomAnim = AnimationUtils.loadAnimation(this, R.anim.drop_in_bottom)
        val appVersion = getString(R.string.version) + " " + getString(R.string.app_version)

        with (binding) {
            logo.animation = topAnim
            textTitle.animation = bottomAnim
            textApp.text = appVersion
            textApp.animation = bottomAnim
        }

        Handler(Looper.myLooper() ?: return).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, timeout)
    }
}