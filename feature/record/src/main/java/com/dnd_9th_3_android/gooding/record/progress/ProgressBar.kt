package com.dnd_9th_3_android.gooding.record.progress

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.Divider
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.dnd_9th_3_android.gooding.core.data.R

@Composable
fun ProgressBar(
    backStage: () -> Unit,
    progressState: State<Dp>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(97.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(
                    start = 11.dp,
                    bottom = 18.dp
                )
                .align(Alignment.BottomStart)
                .clickable {
                    backStage()
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow_back_24),
                modifier = Modifier.size(24.dp),
                contentDescription = null
            )
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .align(Alignment.BottomCenter)
                .background(
                    color = colorResource(id = R.color.blue_gray_5)
                )
        ) {
            Box(
                modifier = Modifier
                    .width(progressState.value)
                    .height(4.dp)
                    .background(colorResource(id = R.color.secondary_1)),
            )
        }
    }
}

@Preview
@Composable
fun PreviewProgressBar() {
    ProgressBar(
        backStage = {

        },
        progressState = mutableStateOf(20.dp)
    )
}