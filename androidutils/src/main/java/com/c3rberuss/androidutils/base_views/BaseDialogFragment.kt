package com.c3rberuss.androidutils.base_views

import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment

open class BaseDialogFragment : DialogFragment() {
    override fun onResume() {
        super.onResume()

        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }
}