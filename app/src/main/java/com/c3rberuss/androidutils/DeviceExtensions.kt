package com.c3rberuss.androidutils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources

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