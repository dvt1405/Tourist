package com.kt.apps.media.taipeitour.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import com.kt.apps.media.taipeitour.R
import com.kt.apps.media.taipeitour.base.BaseFragment
import com.kt.apps.media.taipeitour.databinding.FragmentMainBinding
import androidx.fragment.app.activityViewModels
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.kt.apps.media.taipeitour.ui.detail.DetailFragment
import com.kt.apps.media.taipeitour.ui.selectlanguage.SelectLanguageFragment
import com.kt.skeleton.KunSkeleton

class MainFragment : BaseFragment<FragmentMainBinding>() {
    private val mainViewModel by activityViewModels<MainViewModel>()
    private val adapter by lazy {
        MainAdapter()
    }

    private val skeleton by lazy {
        KunSkeleton.bind(binding.recyclerView)
            .layoutItem(R.layout.item_tour_loading)
            .adapter(adapter)
            .itemCount(10)
            .runLayoutAnimation(true)
            .build()
    }

    override val layoutRes: Int
        get() = R.layout.fragment_main

    override fun initView(savedInstanceState: Bundle?) {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            mainViewModel.getALlTour()
        }
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_select_language, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.action_language) {
                    SelectLanguageFragment().show(
                        parentFragmentManager,
                        SelectLanguageFragment::class.java.name
                    )
                }
                return true
            }
        }, viewLifecycleOwner)
        binding.recyclerView.addItemDecoration(
            MaterialDividerItemDecoration(
                requireContext(),
                MaterialDividerItemDecoration.VERTICAL
            )
        )
    }


    override fun initAction(savedInstanceState: Bundle?) {
        binding.recyclerView.adapter = adapter
        adapter.itemClickListener = { item, _ ->
            mainViewModel.getDetailTour(item)
            parentFragmentManager.beginTransaction()
                .setCustomAnimations(
                    com.kt.skeleton.R.anim.grow_from_bottom,
                    com.kt.skeleton.R.anim.fade_out,
                    0,
                    com.kt.skeleton.R.anim.shrink_from_top
                )
                .replace(R.id.main, DetailFragment.newInstance(item))
                .addToBackStack(DetailFragment::class.java.name)
                .commit()
        }
        mainViewModel.mainUIState.observe(viewLifecycleOwner) {
            handleUIState(it)
        }
    }

    private fun handleUIState(uiModel: MainUIModel) {
        when (uiModel) {
            is MainUIModel.Loading -> {
                skeleton.run()
            }

            is MainUIModel.Success -> {
                skeleton.hide {
                    adapter.onRefresh(uiModel.data)
                }
            }

            is MainUIModel.Error -> {
                // handle error
            }
        }
    }
}