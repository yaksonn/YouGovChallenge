package com.example.yougovchallenge.ui.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.example.yougovchallenge.base.BaseActivity
import com.example.yougovchallenge.databinding.ActivitySplashBinding
import com.example.yougovchallenge.ui.main.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    private var displayTime: Long = 3000

    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)

    override fun prepareViews() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, displayTime)
    }
}