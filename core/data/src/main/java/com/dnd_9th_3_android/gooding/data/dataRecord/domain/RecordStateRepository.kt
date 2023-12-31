package com.dnd_9th_3_android.gooding.data.dataRecord.domain

import android.content.Context
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import com.dnd_9th_3_android.gooding.data.state.ApplicationState

interface RecordStateRepository {
    var navController: NavHostController?
    var appState: ApplicationState?
    var appWidth : Dp
    var imageHeight : Dp
    fun goBackState()

    fun goNextStep(destination : String)

    fun goPrevStep()

    fun setState(
        state: ApplicationState,
        navi : NavHostController
    )

    fun resetState()

    fun setSize(
        height : Dp, width : Dp
    )

    fun getContext() : Context
}