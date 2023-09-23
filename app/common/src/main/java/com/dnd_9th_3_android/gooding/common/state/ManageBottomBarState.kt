package com.dnd_9th_3_android.gooding.common.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavBackStackEntry
import com.dnd_9th_3_android.gooding.common.root.ScreenRoot

@Composable
fun ManageBottomBarState(
    navBackStackEntry: NavBackStackEntry?,
    bottomBarState : MutableState<Boolean>
) {
    when (navBackStackEntry?.destination?.route){
        ScreenRoot.MAIN_FEED,ScreenRoot.MAIN_MY ,
        ->{
            bottomBarState.value = true
        }
        ScreenRoot.MAIN_RECORD,ScreenRoot.MAIN_SEARCH,
        -> {
            bottomBarState.value = false
        }
    }
}