package com.dnd_9th_3_android.gooding.record.tabGallery

import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.DropdownMenu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot
import com.dnd_9th_3_android.gooding.record.tabGallery.component.FolderListLayer
import com.dnd_9th_3_android.gooding.record.tabGallery.component.GalleryTopLayer
import com.dnd_9th_3_android.gooding.record.tabGallery.component.MessagePreventSelectImageLayer
import com.dnd_9th_3_android.gooding.record.tabGallery.function.BottomNotyMessage
import com.dnd_9th_3_android.gooding.record.tabGallery.function.galleryPermissionCheck
import com.dnd_9th_3_android.gooding.record.tabGallery.item.GalleryItemContent
import com.dnd_9th_3_android.gooding.record.tabGallery.item.function.getSelectedImageFromBackStack
import com.dnd_9th_3_android.gooding.record.viewModel.RecordViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GalleryScreen(
    viewModel: RecordViewModel = hiltViewModel()
) {
     //뒤로가기 제어 -> 메인으로 이동
    BackHandler(
        enabled = true,
        onBack = {
            viewModel.goMain()
        }
    )
    val rememberView = remember{ mutableStateOf(false) }
    val isDropDownMenuExpanded = remember { mutableStateOf(false) }
    val isPreventSelectMessage = remember { mutableStateOf(false) }
    // 권한 얻기
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){ isGranted : Boolean ->
        rememberView.value = isGranted
    }
    SideEffect{
        galleryPermissionCheck(
            context = viewModel.getContext(),
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
        }
        // 한 번만 실행
        LaunchedEffect(Unit){
            viewModel.getFolder()
        }
    }

    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.blue_gray_7))
    ) {
        GalleryTopLayer(
            prevStep = {
                viewModel.goMain()
            },
            nextStep = {
                viewModel.nextStep(ScreenRoot.SUB_RECORD)
            },
            isDropDownMenuExpanded = isDropDownMenuExpanded,
            viewModel = viewModel
        )

        Box (Modifier.fillMaxSize()){
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(3),
            ) {
                items(pagingItems.itemCount) { index ->
                    pagingItems[index]?.let { galleryImage ->
                        GalleryItemContent(galleryImage, isPreventSelectMessage,viewModel)
                    }
                }
            }

            // 경고 메시지
            if (isPreventSelectMessage.value) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    MessagePreventSelectImageLayer()
                    rememberCoroutineScope().launch {
                        delay(1000)
                        isPreventSelectMessage.value = false
                    }

                }
            }

            // bottom 메시지
            Box( modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(60.dp)
                .background(colorResource(id = R.color.record_noty_box_color_80))
                .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                BottomNotyMessage(selected = viewModel.selectedImageSize())
            }

            // dropdown 메뉴
            Box(modifier = Modifier.align(Alignment.BottomCenter)){
                val ratio = viewModel.getImageHeight() / 180.dp
                if (isDropDownMenuExpanded.value) {
                    DropdownMenu(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(660.dp * ratio)
                            .background(colorResource(id = R.color.blue_gray_7)),
                        expanded = isDropDownMenuExpanded.value,
                        onDismissRequest = { isDropDownMenuExpanded.value = false },
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
}