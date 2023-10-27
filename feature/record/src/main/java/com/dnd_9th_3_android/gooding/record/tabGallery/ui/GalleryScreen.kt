package com.dnd_9th_3_android.gooding.record.tabGallery

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.record.tabGallery.component.GalleryTopLayer
import com.dnd_9th_3_android.gooding.record.viewModel.RecordViewModel

@Composable
fun GalleryScreen(
    viewModel: RecordViewModel
) {
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

    }
}