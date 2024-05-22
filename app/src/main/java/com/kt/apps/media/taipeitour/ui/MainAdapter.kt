package com.kt.apps.media.taipeitour.ui

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kt.apps.media.taipeitour.R
import com.kt.apps.media.taipeitour.base.BaseAdapter
import com.kt.apps.media.taipeitour.data.models.TourDTO
import com.kt.apps.media.taipeitour.databinding.ItemTourBinding

class MainAdapter() : BaseAdapter<TourDTO, ItemTourBinding>() {

    override val layoutRes: Int
        get() = R.layout.item_tour

    override fun onBindItem(item: TourDTO, binding: ItemTourBinding, position: Int) {
        binding.item = item
        if (item.images.isNotEmpty()) {
            Glide.with(binding.root)
                .load(item.images.first().src)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(com.kt.skeleton.R.drawable.gradient_first_corners)
                .into(binding.itemLogo)
        } else {
            Glide.with(binding.root)
                .load(com.kt.skeleton.R.drawable.gradient_first_corners)
                .into(binding.itemLogo)
        }

    }

    override fun onViewRecycled(holder: BaseViewHolder<TourDTO, ItemTourBinding>) {
        super.onViewRecycled(holder)
    }
}