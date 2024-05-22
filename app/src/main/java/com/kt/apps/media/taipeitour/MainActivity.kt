package com.kt.apps.media.taipeitour

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.kt.apps.media.taipeitour.base.BaseActivity
import com.kt.apps.media.taipeitour.databinding.ActivityMainBinding
import com.kt.apps.media.taipeitour.ui.MainFragment
import com.kt.apps.media.taipeitour.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mainViewModel by viewModels<MainViewModel>()
    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main, MainFragment())
                .commit()
        }
        mainViewModel.getALlTour()
    }

    override fun initAction(savedInstanceState: Bundle?) {
    }
}