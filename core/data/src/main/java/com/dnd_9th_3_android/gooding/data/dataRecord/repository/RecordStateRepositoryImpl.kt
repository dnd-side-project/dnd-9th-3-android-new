package com.dnd_9th_3_android.gooding.data.dataRecord.repository

import android.content.Context
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dnd_9th_3_android.gooding.data.dataRecord.domain.RecordStateRepository
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RecordStateRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
)
    : RecordStateRepository {
    override var navController: NavHostController? = null // record navi
    override var appState: ApplicationState? = null
    override var appWidth = 360.dp
    override var imageHeight = 180.dp

    override fun goBackState() {
        appState?.navController?.popBackStack()
    }

    override fun goNextStep(destination : String) {
        navController?.navigate(destination)
    }


    override fun goPrevStep() {
        navController?.popBackStack()
    }

    override fun setState(
        state: ApplicationState,
        navi : NavHostController
    ) {
        appState = state
        navController = navi
    }

    override fun resetState() {
        appState = null
        navController = null
    }

    override fun setSize(height: Dp, width: Dp) {
        imageHeight = height
        appWidth = width
    }

    override fun getContext(): Context = context
}