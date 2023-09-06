package com.dnd_9th_3_android.gooding.my.subLayout

import android.annotation.SuppressLint
import android.view.View
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.contentLayout.pretendardBold
import com.dnd_9th_3_android.gooding.my.BottomNaviLocator
import com.google.android.material.bottomnavigation.BottomNavigationView

// my main top menu
@SuppressLint("RestrictedApi")
@Composable
fun TopMenuScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ){
        Text(
            text = "마이 굳잉",
            modifier = Modifier.align(Alignment.Center),
            color = Color.White,
            fontSize = dimensionResource(R.dimen.main_text_sp).value.sp,
            fontFamily = pretendardBold
        )
        Image(
            painter = painterResource(id = R.drawable.settings),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = dimensionResource(id = R.dimen.padding_17))
                .size(dimensionResource(id = R.dimen.icon_size))
                .clickable {
                    navController.navigate("settingScreen")
                }
        )

    }
}
