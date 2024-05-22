package com.kt.apps.media.taipeitour.ui

import android.os.Bundle
import com.kt.apps.media.taipeitour.R
import com.kt.apps.media.taipeitour.base.BaseFragment
import com.kt.apps.media.taipeitour.databinding.FragmentMainBinding
import androidx.fragment.app.activityViewModels

class MainFragment : BaseFragment<FragmentMainBinding>() {
    private val mainViewModel by activityViewModels<MainViewModel>()
    override val layoutRes: Int
        get() = R.layout.fragment_main

    override fun initView(savedInstanceState: Bundle?) {
        binding.btnTest.setOnClickListener {
            mainViewModel.getALlTour()
        }
    }

    override fun initAction(savedInstanceState: Bundle?) {
    }
}