package com.c3rberuss.androidutils.base_views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class BaseActivity : AppCompatActivity(), CoroutineScope {
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