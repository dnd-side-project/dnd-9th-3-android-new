package com.dnd_9th_3_android.gooding.feed.viewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.data.state.FeedPagerState
import com.dnd_9th_3_android.gooding.data.state.rememberApplicationState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject


@HiltViewModel
class FeedOptionViewModel @Inject constructor(

): ViewModel(){
    private var _applicationState : ApplicationState? = null
    val applicationState get() = _applicationState

    private var _pagerStage : FeedPagerState? = null
    val pagerState get() = _pagerStage

    fun initAppState(appState : ApplicationState){
        _applicationState = appState
    }

    fun initPagerState(feedPagerState: FeedPagerState){
        _pagerStage = feedPagerState
    }

    fun naviToSearch(){
        applicationState?.navController?.navigate(ScreenRoot.MAIN_SEARCH)
    }
}