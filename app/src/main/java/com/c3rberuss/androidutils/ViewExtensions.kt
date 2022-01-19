package com.c3rberuss.androidutils

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.constraintlayout.widget.ConstraintLayout

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.showThisAndHide(viewToHide : View?){
    this.visible()
    viewToHide?.gone()
}

fun View.showThisAndHide(viewsToHide : List<View?>){
    this.visible()
    viewsToHide.forEach { view -> view?.gone() }
}

fun View.hideThisAndShow(viewsToHide : List<View?>){
    this.gone()
    viewsToHide.forEach { view -> view?.visible() }
}

@SuppressLint("UNCHECKED_CAST")
fun<T: MarginLayoutParams> View.setWidth(width: Int){
    this.layoutParams = (this.layoutParams as T).apply {
        this.width = width.px
    }
}

@SuppressLint("UNCHECKED_CAST")
fun<T: MarginLayoutParams> View.setHeight(height: Int){
    this.layoutParams = (this.layoutParams as T).apply {
        this.height = height.px
    }
}

@SuppressLint("UNCHECKED_CAST")
fun<T: MarginLayoutParams> View.setSize(width: Int, height: Int){
    this.layoutParams = (this.layoutParams as T).apply {
        this.width = width.px
        this.height = height.px
    }
}

fun View.setHorizontalBias(value: Float){
    this.layoutParams = (this.layoutParams as ConstraintLayout.LayoutParams).apply {
        horizontalBias = value
    }
}

fun View.setVerticalBias(value: Float){
    this.layoutParams = (this.layoutParams as ConstraintLayout.LayoutParams).apply {
        verticalBias = value
    }
}