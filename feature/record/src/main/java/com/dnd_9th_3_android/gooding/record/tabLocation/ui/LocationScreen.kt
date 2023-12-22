package com.dnd_9th_3_android.gooding.record.tabLocation.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.core.data.R
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
            viewModel.prevStep()
        }
    )

    val searchQuery = remember { mutableStateOf(TextFieldValue("")) }

    Column{

        LocationTopLayer(
            backState = {
                viewModel.prevStep()
            }
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = colorResource(id = R.color.blue_gray_6))
        )
        Spacer(modifier = Modifier.height(8.dp))

        LocationSearchLayer(
            query = searchQuery
        )

    }
}