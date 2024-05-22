package com.kt.apps.media.taipeitour.ui.detail

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.kt.apps.media.taipeitour.R
import com.kt.apps.media.taipeitour.base.BaseFragment
import com.kt.apps.media.taipeitour.data.models.TourDTO
import com.kt.apps.media.taipeitour.databinding.FragmentDetailBinding
import com.kt.apps.media.taipeitour.ui.MainViewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    private val mainViewModel by activityViewModels<MainViewModel>()

    override val layoutRes: Int
        get() = R.layout.fragment_detail

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun initAction(savedInstanceState: Bundle?) {
    }

    companion object {
        fun newInstance(item: TourDTO): DetailFragment {
            return DetailFragment()
        }
    }
}