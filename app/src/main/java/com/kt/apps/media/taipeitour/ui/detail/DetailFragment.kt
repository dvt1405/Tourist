package com.kt.apps.media.taipeitour.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.kt.apps.media.taipeitour.R
import com.kt.apps.media.taipeitour.base.BaseFragment
import com.kt.apps.media.taipeitour.data.models.TourDTO
import com.kt.apps.media.taipeitour.databinding.FragmentDetailBinding
import com.kt.apps.media.taipeitour.ui.MainViewModel
import com.kt.apps.media.taipeitour.utils.GlideApp

class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    private val mainViewModel by activityViewModels<MainViewModel>()

    override val layoutRes: Int
        get() = R.layout.fragment_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as? AppCompatActivity)?.setSupportActionBar(null)
    }

    override fun initView(savedInstanceState: Bundle?) {
        val item =
            arguments?.getParcelable<TourDTO>(EXTRA_ITEM) ?: savedInstanceState?.getParcelable(
                EXTRA_ITEM
            )
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.appbar.toolbar)
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.appbar.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        binding.item = item
        binding.urlTextView.setOnClickListener {
        }
    }

    override fun initAction(savedInstanceState: Bundle?) {
        GlideApp.with(this)
            .load(binding.item?.images?.firstOrNull()?.src)
            .into(binding.imageView)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(EXTRA_ITEM, binding.item)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.getParcelable<TourDTO>(EXTRA_ITEM)?.let {
            binding.item = it
        }
    }

    companion object {
        private const val EXTRA_ITEM = "extra:item"
        fun newInstance(item: TourDTO): DetailFragment {
            return DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_ITEM, item)
                }
            }
        }
    }
}