package com.c3rberuss.androidutils

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

val Context.downloadManager: DownloadManager
    get() {
        return getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    }

suspend fun String.getPublicUrl(): String? {
    val bucket = FirebaseStorage.getInstance()
    return bucket.reference.child(this).downloadUrl.await()?.toString()
}

fun String.downloadFile(context: Context, filename: String): Long {

    val request: DownloadManager.Request = DownloadManager.Request(Uri.parse(this))

    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

    request.setDestinationInExternalFilesDir(
        context,
        Environment.DIRECTORY_DOWNLOADS,
        filename
    )

    return context.downloadManager.enqueue(request)
}

fun buildDownloadCompleteReceiver(activity: AppCompatActivity): BroadcastReceiver {
    return object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {

            intent ?: return

            if (intent.action == DownloadManager.ACTION_DOWNLOAD_COMPLETE) {
                intent.extras?.let {
                    try {
                        val downloadId = it.getLong(DownloadManager.EXTRA_DOWNLOAD_ID)
                        val uri: Uri = activity.downloadManager.getUriForDownloadedFile(downloadId)

                        //opening it
                        val intentToOpenFile = Intent(Intent.ACTION_VIEW, uri)
                        intentToOpenFile.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        context.startActivity(intentToOpenFile)

                        activity.unregisterReceiver(this)

                    } catch (e: Exception) {
                        //Do something
                        activity.unregisterReceiver(this)
                    }
                }
            }
        }
    }
}

fun Fragment.registerDownloadsListener() {
    val onDownloadCompleteReceiver =
        buildDownloadCompleteReceiver(requireActivity() as AppCompatActivity)

    requireActivity().registerReceiver(
        onDownloadCompleteReceiver,
        IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
    )
}

fun AppCompatActivity.registerDownloadListener() {
    val onDownloadCompleteReceiver =
        buildDownloadCompleteReceiver(this)

    registerReceiver(
        onDownloadCompleteReceiver,
        IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
    )
}