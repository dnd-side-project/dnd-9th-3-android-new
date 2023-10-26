package com.dnd_9th_3_android.gooding.record.viewModel

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordOptionViewModel @Inject constructor(
) : ViewModel() {
    private var _applicationState : ApplicationState? = null
    private val applicationState get() = _applicationState

    private var _screenWidth : Dp = 360.dp
    val screenWidth get() = _screenWidth
    private var _imageHeight : Dp = 180.dp
    val imageHeight get() = _imageHeight

    fun initAppState(
        appState : ApplicationState,
        appWidth : Dp, appHeight : Dp
    ){
        _applicationState = appState
        _screenWidth = appWidth
        _imageHeight = appHeight
    }

    fun goBackStage(){
        applicationState?.navController?.popBackStack()
    }

    fun goGalleryRecord(){
        applicationState?.navController?.navigate(ScreenRoot.RECORD_IMAGE_SELECT)
    }
    fun goMainRecord(){
        applicationState?.navController?.navigate(ScreenRoot.RECORD_MAIN)
    }

}