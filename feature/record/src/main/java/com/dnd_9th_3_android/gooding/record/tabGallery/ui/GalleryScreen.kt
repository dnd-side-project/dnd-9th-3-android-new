package com.dnd_9th_3_android.gooding.record.tabGallery

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.DropdownMenu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.record.tabGallery.component.FolderListLayer
import com.dnd_9th_3_android.gooding.record.tabGallery.component.GalleryTopLayer
import com.dnd_9th_3_android.gooding.record.tabGallery.function.galleryPermissionCheck
import com.dnd_9th_3_android.gooding.record.tabGallery.item.GalleryItemContent
import com.dnd_9th_3_android.gooding.record.tabGallery.item.function.getSelectedImageFromBackStack
import com.dnd_9th_3_android.gooding.record.viewModel.RecordViewModel

@Composable
fun GalleryScreen(
    viewModel: RecordViewModel
) {
    val rememberView = remember{ mutableStateOf(false) }
    val isDropDownMenuExpanded = remember { mutableStateOf(false) }
    // 권한 얻기
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){ isGranted : Boolean ->
        rememberView.value = isGranted
    }
    SideEffect{
        galleryPermissionCheck(
            context = viewModel.recordStateRepository.getContext(),
            launcher,
            action = {
                rememberView.value = true
            }
        )
    }
    val pagingItems = viewModel.customGalleryPhotoList.collectAsLazyPagingItems()

    if (rememberView.value) { // 권한 허용 시 이미지 불러오기
        LaunchedEffect(viewModel.currentFolder.value) {
            viewModel.getGalleryPagingImages()
            viewModel.getFolder()
        }
    }

    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.blue_gray_7))
    ) {
        GalleryTopLayer(
            prevStep = {
                viewModel.recordStateRepository.goBackState()
            },
            nextStep = {
                viewModel.recordStateRepository.goNextStep("mainRecordScreen")
            },
            currentDirectory  = viewModel.currentFolder.value,
            setCurrentDirectory = { folder ->
                viewModel.setCurrentFolder(folder)
            },
            isDropDownMenuExpanded
        )

        Box {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(3),
            ) {
                items(pagingItems.itemCount) { index ->
                    pagingItems[index]?.let { galleryImage ->
                        GalleryItemContent(galleryImage, viewModel)
                    }
                }
            }
            if (isDropDownMenuExpanded.value) {
                DropdownMenu(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.blue_gray_7)),
                    expanded = isDropDownMenuExpanded.value,
                    onDismissRequest = { isDropDownMenuExpanded.value = false }
                ) {
                    FolderListLayer(
                        viewModel.folders,
                        selectedFolder = { folder ->
                            viewModel.setCurrentFolder(folder)
                            isDropDownMenuExpanded.value = false
                        }
                    )
                }
            }
        }
    }
}