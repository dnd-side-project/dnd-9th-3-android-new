package com.dnd_9th_3_android.gooding.feed.viewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.data.state.rememberApplicationState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FeedOptionViewModel @Inject constructor(

): ViewModel(){
    private var _applicationState : ApplicationState? = null
    val applicationState get() = _applicationState

    fun initAppState(appState : ApplicationState){
        _applicationState = appState
    }

    fun naviToSearch(){
        applicationState?.navController?.navigate(ScreenRoot.MAIN_SEARCH)
    }
}