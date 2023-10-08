package com.dnd_9th_3_android.gooding.my.tabTop.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.my.subLayout.LevelScreen
import com.dnd_9th_3_android.gooding.my.subLayout.TopMenuScreen
import com.dnd_9th_3_android.gooding.my.subLayout.UserInfoScreen
import com.dnd_9th_3_android.gooding.my.viewModel.MyOptionViewModel


@Composable
fun MainTopScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            // main content (top menu)
            Spacer(modifier = Modifier.height(53.dp))
            TopMenuScreen()
            Spacer(modifier = Modifier.height(36.dp))
            LevelScreen(
                painterResource(id = R.drawable.level_icon),
                "LV1.초보 낭만러"
            )
            Spacer(modifier = Modifier.height(27.98.dp))
            UserInfoScreen()
            Spacer(modifier = Modifier.height(27.dp))
        }
    }
}