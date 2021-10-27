package com.c3rberuss.androidutils

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment

val Context.screenWidth: Int get() = resources.displayMetrics.widthPixels

val Context.screenHeight: Int get() = resources.displayMetrics.heightPixels

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Context.isPhone: Boolean
    get() = (this.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL

val Context.isTablet: Boolean
    get() = !this.isPhone

val Context.isLandscape: Boolean
    get() = this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

val Context.isPortrait: Boolean
    get() = this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

fun disableDarkMode() = AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
fun enableDarkMode() = AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
fun enableBothUiModes() =
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_UNSPECIFIED)

fun AppCompatActivity.disableRotation() {
    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED
}

fun AppCompatActivity.enableRotation() {
    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
}

fun Fragment.disableRotation() {
    requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED
}

fun Fragment.enableRotation() {
    requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
}