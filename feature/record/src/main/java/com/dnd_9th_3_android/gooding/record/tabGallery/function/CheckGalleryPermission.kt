package com.dnd_9th_3_android.gooding.record.tabGallery.function

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.content.ContextCompat

fun galleryPermissionCheck(
    context : Context,
    launcher: ManagedActivityResultLauncher<String, Boolean>,
    action : () -> Unit
){
    when(PackageManager.PERMISSION_GRANTED){
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_EXTERNAL_STORAGE,
        ),
        -> {
            action()
        }
        else -> {
            launcher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }
}