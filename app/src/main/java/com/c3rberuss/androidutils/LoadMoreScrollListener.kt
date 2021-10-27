package com.c3rberuss.androidutils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

abstract class RecyclerViewLoadMoreScroll : RecyclerView.OnScrollListener {

    private var visibleThreshold = 5
    private var lastVisibleItem: Int = 0
    private var totalItemCount: Int = 0
    private var mLayoutManager: RecyclerView.LayoutManager

    abstract fun isLoading(): Boolean
    abstract fun isLastPage(): Boolean
    abstract fun loadMoreItems()

    constructor(layoutManager: LinearLayoutManager, visibleThreshold: Int = 5) {
        this.mLayoutManager = layoutManager
        this.visibleThreshold = visibleThreshold
    }

    constructor(layoutManager: GridLayoutManager, visibleThreshold: Int = 5) {
        this.mLayoutManager = layoutManager
        this.visibleThreshold = visibleThreshold
        this.visibleThreshold *= layoutManager.spanCount
    }

    constructor(layoutManager: StaggeredGridLayoutManager, visibleThreshold: Int = 5) {
        this.mLayoutManager = layoutManager
        this.visibleThreshold = visibleThreshold
        this.visibleThreshold *= layoutManager.spanCount
    }


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy <= 0) return

        totalItemCount = mLayoutManager.itemCount

        val firstVisibleItemPosition = when (mLayoutManager) {
            is StaggeredGridLayoutManager -> {
                val lastVisibleItemPositions =
                    (mLayoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
                // get maximum element within the list
                lastVisibleItem = getLastVisibleItem(lastVisibleItemPositions)
                0
            }
            is GridLayoutManager -> {
                lastVisibleItem =
                    (mLayoutManager as GridLayoutManager).findLastVisibleItemPosition()

                (mLayoutManager as GridLayoutManager).findFirstVisibleItemPosition()
            }
            is LinearLayoutManager -> {
                lastVisibleItem =
                    (mLayoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                (mLayoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            }
            else -> 0
        }

        val visibleItemCount = mLayoutManager.childCount
        val totalItemCount = mLayoutManager.itemCount

        if (!isLastPage() && !isLoading() && (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0)) {
            loadMoreItems()
        }
    }

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }
}