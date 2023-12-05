package com.dnd_9th_3_android.gooding.record.tabMain

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.record.viewModel.RecordViewModel
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.record.state.RecordState
import com.dnd_9th_3_android.gooding.record.tabMain.component.ImageLayer
import com.dnd_9th_3_android.gooding.record.tabMain.component.MainLayer

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainRecordScreen(
    recordState : RecordState,
    viewModel: RecordViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    val clickLocationState = remember { mutableStateOf(false) }
    val currentLocationState = remember { mutableStateOf("카테고리를 선택해주세요.(선택)") }

    Box(
        modifier = Modifier
            .padding(top = 56.dp)
            .fillMaxSize()
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) {
                focusManager.clearFocus()
            }
    ) {
        CompositionLocalProvider(LocalOverscrollConfiguration.provides(null)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(36.dp))
                ImageLayer()
                Spacer(modifier = Modifier.height(36.dp))
                MainLayer(recordState,focusManager)
            }
        }
    }
}