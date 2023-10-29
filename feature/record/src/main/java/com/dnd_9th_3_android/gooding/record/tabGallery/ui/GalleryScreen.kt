package com.dnd_9th_3_android.gooding.record.tabGallery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.record.tabGallery.component.GalleryTopLayer
import com.dnd_9th_3_android.gooding.record.tabGallery.item.GalleryItemContent
import com.dnd_9th_3_android.gooding.record.tabGallery.item.function.getSelectedImageFromBackStack
import com.dnd_9th_3_android.gooding.record.viewModel.RecordViewModel

@Composable
fun GalleryScreen(
    viewModel: RecordViewModel,
    navi : NavHostController
) {
    val pagingItems = viewModel.customGalleryPhotoList.collectAsLazyPagingItems()

    LaunchedEffect(viewModel.currentFolder.value) {
        viewModel.getGalleryPagingImages()
    }
    // 이전 선택 뷰와 폴더 로드
    LaunchedEffect(Unit) {
        val secondScreenResult =
            getSelectedImageFromBackStack(navi.previousBackStackEntry)
        viewModel.addSelectedImageList(secondScreenResult)
        viewModel.getFolder()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.blue_gray_7))
    ) {
        GalleryTopLayer(
            viewModel.currentFolder.value.first,
            prevStep = {
                viewModel.recordStateRepository.goBackState()
            },
            nextStep = {
                viewModel.recordStateRepository.goNextStep("mainRecordScreen")
            }
        )

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
    }
}