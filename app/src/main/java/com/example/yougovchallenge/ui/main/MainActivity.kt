package com.example.yougovchallenge.ui.main

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.yougovchallenge.R
import com.example.yougovchallenge.base.BaseActivity
import com.example.yougovchallenge.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onSupportNavigateUp() =
        getNavController().navigateUp() || super.onSupportNavigateUp()

    private fun getNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        return navHostFragment.navController
    }
}