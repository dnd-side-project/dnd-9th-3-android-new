package com.dnd_9th_3_android.gooding.data.state

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberApplicationState(
    bottomBarState : MutableState<Boolean> = mutableStateOf(false),
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(bottomBarState,navController,scaffoldState,coroutineScope){
    ApplicationState(
        bottomBarState,
        navController,
        scaffoldState,
        coroutineScope
    )
}