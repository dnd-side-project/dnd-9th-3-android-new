package com.dnd_9th_3_android.gooding.data.dataRecord.domain

import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import com.dnd_9th_3_android.gooding.data.state.ApplicationState

interface RecordStateRepository {

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

}