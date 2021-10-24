package com.c3rberuss.androidutils.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<T, VH : RecyclerView.ViewHolder>(diffUtil: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, RecyclerView.ViewHolder>(diffUtil) {

    private var _isEmpty: Boolean = false
    val isEmpty: Boolean get() = _isEmpty

    abstract fun createDataViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun createEmptyViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun createLoadingViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    abstract fun bindDataViewHolder(holder: VH, position: Int)

    abstract fun bindEmptyViewHolder(holder: RecyclerView.ViewHolder, position: Int)

    abstract fun bindLoadingViewHolder(holder: RecyclerView.ViewHolder, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewTypes.EMPTY -> createEmptyViewHolder(parent)
            ViewTypes.LOADING -> createLoadingViewHolder(parent)
            else -> createDataViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (getItemViewType(position)) {
            ViewTypes.NORMAL -> bindDataViewHolder(holder as VH, position)
            ViewTypes.LOADING -> bindLoadingViewHolder(holder, position)
            else -> bindEmptyViewHolder(holder, position)
        }

    override fun getItemViewType(position: Int): Int {
        return when {
            itemCount == 1 && _isEmpty -> ViewTypes.EMPTY
            else -> ViewTypes.NORMAL
        }
    }

    override fun getItemCount(): Int {
        return if (super.getItemCount() > 0) {
            _isEmpty = false
            super.getItemCount()
        } else {
            _isEmpty = true
            1
        }
    }

}