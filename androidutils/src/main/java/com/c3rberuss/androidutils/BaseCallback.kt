package com.c3rberuss.androidutils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class BaseCallback<T>(private val clickAction: (T, Any?) -> Unit) : Parcelable {
    fun onClick(value: T, vararg extras: Any?) = clickAction(value, extras)
}