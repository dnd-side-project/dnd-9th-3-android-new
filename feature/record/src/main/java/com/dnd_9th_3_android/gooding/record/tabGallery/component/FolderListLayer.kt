package com.dnd_9th_3_android.gooding.record.tabGallery.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import com.dnd_9th_3_android.gooding.model.record.ImageFolder
import com.dnd_9th_3_android.gooding.record.tabGallery.component.folderItem.ItemFolder

@Composable
fun FolderListLayer(
    folderList : SnapshotStateList<Pair<String, ImageFolder?>>,
    selectedFolder : (Pair<String, ImageFolder?>) -> Unit
) {
    folderList.map{ folder ->
        DropdownMenuItem(onClick = {
            selectedFolder(folder)
        }) {
            ItemFolder(folder = folder)
        }
    }
}