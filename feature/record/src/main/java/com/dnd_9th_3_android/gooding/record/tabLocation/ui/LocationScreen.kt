package com.dnd_9th_3_android.gooding.record.tabLocation.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.record.state.RecordState
import com.dnd_9th_3_android.gooding.record.tabLocation.component.LocationSearchLayer
import com.dnd_9th_3_android.gooding.record.tabLocation.component.LocationTopLayer
import com.dnd_9th_3_android.gooding.record.viewModel.RecordViewModel

@Composable
fun LocationScreen(
    recordState: RecordState,
    viewModel: RecordViewModel = hiltViewModel()
) {

    BackHandler(
        enabled = true,
        onBack = {
            // 삭제 바텀 sheet
            viewModel.prevStep()
        }
    )

    val searchQuery = remember { mutableStateOf(TextFieldValue("")) }

    Column{

        LocationTopLayer(
            backState = {

            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        LocationSearchLayer(
            searchQuery
        )

    }
}