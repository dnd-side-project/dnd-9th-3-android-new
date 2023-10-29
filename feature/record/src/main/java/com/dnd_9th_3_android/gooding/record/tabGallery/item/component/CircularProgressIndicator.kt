package com.dnd_9th_3_android.gooding.record.tabGallery.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.dnd_9th_3_android.gooding.core.data.R

@Composable
fun CircularProgressIndicator(
    fraction: Float = 0.5f
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.blue_gray_7)),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.material.CircularProgressIndicator(
            color = colorResource(id = R.color.secondary_1),
            modifier = Modifier.fillMaxSize(fraction)
        )

    }
}