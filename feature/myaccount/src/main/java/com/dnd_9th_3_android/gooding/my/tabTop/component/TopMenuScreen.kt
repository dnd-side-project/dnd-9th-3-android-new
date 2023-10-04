package com.dnd_9th_3_android.gooding.my.subLayout

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.data.root.ScreenRoot
import com.dnd_9th_3_android.gooding.my.viewModel.MyOptionViewModel

// my main top menu
@SuppressLint("RestrictedApi")
@Composable
fun TopMenuScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(end = 17.dp)
    ){
        Text(
            text = "마이 굳잉",
            modifier = Modifier.align(Alignment.Center),
            color = Color.White,
            fontSize = dimensionResource(R.dimen.main_text_sp).value.sp,
            fontFamily = pretendardBold
        )
        Box(
            Modifier.align(Alignment.CenterEnd)
                .size(24.dp)

        ) {
            Image(
                painter = painterResource(id = R.drawable.settings),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

    }
}
