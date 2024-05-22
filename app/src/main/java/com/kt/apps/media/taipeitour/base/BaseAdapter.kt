package com.kt.apps.media.taipeitour.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VB : ViewDataBinding>() :
    RecyclerView.Adapter<BaseAdapter.BaseViewHolder<T, VB>>() {
    protected val itemList by lazy {
        mutableListOf<T>()
    }
    var itemClickListener: ((T, Int) -> Unit)? = null
    abstract val layoutRes: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T, VB> {
        val binding = DataBindingUtil.inflate<VB>(
            LayoutInflater.from(parent.context),
            layoutRes,
            parent,
            false
        )
        return object : BaseViewHolder<T, VB>(binding) {
            override fun onBind(item: T) {
                onBindItem(item, binding, adapterPosition)
                binding.root.setOnClickListener {
                    itemClickListener?.invoke(item, adapterPosition)
                }
            }

        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T, VB>, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    abstract fun onBindItem(item: T, binding: VB, position: Int)

    fun onRefresh(list: List<T>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

    abstract class BaseViewHolder<T, VB : ViewDataBinding>(itemBinding: VB) :
        RecyclerView.ViewHolder(itemBinding.root) {
        abstract fun onBind(item: T)
    }
}