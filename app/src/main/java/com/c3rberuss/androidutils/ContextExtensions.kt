package com.c3rberuss.androidutils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.c3rberuss.androidutils.adapters.ViewTypes
import com.c3rberuss.androidutils.adapters.BaseListAdapter


fun Context.getCompatDrawable(@DrawableRes drawable: Int): Drawable? {
    return ContextCompat.getDrawable(this, drawable)
}

fun Context.getInteger(@IntegerRes integer: Int): Int {
    return this.resources.getInteger(integer)
}

fun Context.getDimension(@DimenRes dimen: Int): Float {
    return this.resources.getDimension(dimen)
}

fun Context.getCompatColor(@ColorRes color: Int): Int {
    return ContextCompat.getColor(this, color)
}

fun <T, VH : RecyclerView.ViewHolder> Context.gridLayoutManager(
    @IntegerRes columns: Int,
    adapter: BaseListAdapter<T, VH>
): GridLayoutManager {

    val columnsValue = this.resources.getInteger(columns)

    return GridLayoutManager(this, columnsValue).apply {
        this.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when {
                    adapter.getItemViewType(position) == ViewTypes.EMPTY -> columnsValue
                    else -> 1
                }
            }
        }
    }
}