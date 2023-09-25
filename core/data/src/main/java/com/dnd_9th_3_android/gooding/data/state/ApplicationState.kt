package com.dnd_9th_3_android.gooding.data.state

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Stable
class ApplicationState(
    val bottomBarState : MutableState<Boolean>,
    val navController: NavHostController,
    val scaffoldState: ScaffoldState,
    val coroutineScope: CoroutineScope
) {

    fun showSnackBar(message : String){
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(message)
        }
    }
}