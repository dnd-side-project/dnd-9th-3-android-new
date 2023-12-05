package com.dnd_9th_3_android.gooding.record.tabMain

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.record.viewModel.RecordViewModel

@Composable
fun MainRecordScreen(
    viewModel: RecordViewModel = hiltViewModel()
) {
    Log.d("124oi1u24o12u401",viewModel.selectedImages.toList().toString())
}