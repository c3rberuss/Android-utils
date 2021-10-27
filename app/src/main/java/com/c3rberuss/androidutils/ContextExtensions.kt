package com.c3rberuss.androidutils

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


fun Context.getCompatDrawable(@DrawableRes drawable: Int): Drawable? {
    return ContextCompat.getDrawable(this, drawable)
}

fun Fragment.getCompatDrawable(@DrawableRes drawable: Int): Drawable? {
    return ContextCompat.getDrawable(requireContext(), drawable)
}

fun AppCompatActivity.getCompatDrawable(@DrawableRes drawable: Int): Drawable? {
    return ContextCompat.getDrawable(this, drawable)
}

fun Context.getInteger(@IntegerRes integer: Int): Int {
    return this.resources.getInteger(integer)
}

fun Fragment.getInteger(@IntegerRes integer: Int): Int {
    return this.resources.getInteger(integer)
}

fun AppCompatActivity.getInteger(@IntegerRes integer: Int): Int {
    return this.resources.getInteger(integer)
}

fun Context.getDimension(@DimenRes dimen: Int): Float {
    return this.resources.getDimension(dimen)
}

fun Fragment.getDimension(@DimenRes dimen: Int): Float {
    return this.resources.getDimension(dimen)
}

fun AppCompatActivity.getDimension(@DimenRes dimen: Int): Float {
    return this.resources.getDimension(dimen)
}

fun Context.getCompatColor(@ColorRes color: Int): Int {
    return ContextCompat.getColor(this, color)
}

fun Fragment.getCompatColor(@ColorRes color: Int): Int {
    return ContextCompat.getColor(requireContext(), color)
}

fun AppCompatActivity.getCompatColor(@ColorRes color: Int): Int {
    return ContextCompat.getColor(this, color)
}

fun Context.gridLayoutManager(
    @IntegerRes columns: Int,
    orientation: Int = RecyclerView.VERTICAL,
    isReverse: Boolean = false
): GridLayoutManager {
    return GridLayoutManager(this, getInteger(columns), orientation, isReverse)
}

fun Fragment.gridLayoutManager(
    @IntegerRes columns: Int,
    orientation: Int = RecyclerView.VERTICAL,
    isReverse: Boolean = false
): GridLayoutManager {
    return GridLayoutManager(requireContext(), getInteger(columns), orientation, isReverse)
}

fun AppCompatActivity.gridLayoutManager(
    @IntegerRes columns: Int,
    orientation: Int = RecyclerView.VERTICAL,
    isReverse: Boolean = false
): GridLayoutManager {
    return GridLayoutManager(this, getInteger(columns), orientation, isReverse)
}

fun Context.linearLayoutManager(
    orientation: Int = RecyclerView.VERTICAL,
    isReverse: Boolean = false
): LinearLayoutManager {
    return LinearLayoutManager(this, orientation, isReverse)
}

fun Fragment.linearLayoutManager(
    orientation: Int = RecyclerView.VERTICAL,
    isReverse: Boolean = false
): LinearLayoutManager {
    return LinearLayoutManager(requireContext(), orientation, isReverse)
}

fun AppCompatActivity.linearLayoutManager(
    orientation: Int = RecyclerView.VERTICAL,
    isReverse: Boolean = false
): LinearLayoutManager {
    return LinearLayoutManager(this, orientation, isReverse)
}

fun AppCompatActivity.openURL(url: String) {
    try {
        val uri = Uri.parse(url.trim())
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    } catch (e: Exception) {
        Log.e("openUrl", "${e.message}")
    }
}

fun Fragment.openURL(url: String) {
    try {
        val uri = Uri.parse(url.trim())
        val intent = Intent(Intent.ACTION_VIEW, uri)
        requireActivity().startActivity(intent)
    } catch (e: Exception) {
        Log.e("openUrl", "${e.message}")
    }
}