package com.c3rberuss.androidutils.base_views

import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

open class BaseFragment : Fragment(), CoroutineScope {

    private lateinit var _job: Job
    val job: Job get() = _job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + _job

    override fun onStart() {
        super.onStart()
        _job = SupervisorJob()
    }

    override fun onStop() {
        _job.cancel()
        super.onStop()
    }
}