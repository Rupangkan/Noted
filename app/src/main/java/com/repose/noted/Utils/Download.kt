package com.repose.noted.Utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri

class Download {
    fun downloadFile(
        context: Context,
        fileName: String,
//        fileExtension: String,
        destinationDirectory: String?,
        url: String?
    ): Long {
        val downloadmanager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri: Uri = Uri.parse(url)
        val request = DownloadManager.Request(uri)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalFilesDir(
            context,
            destinationDirectory,
            fileName
        )
        return downloadmanager.enqueue(request)
    }
}