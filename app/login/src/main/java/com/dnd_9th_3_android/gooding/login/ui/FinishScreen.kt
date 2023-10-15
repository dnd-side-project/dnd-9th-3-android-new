package com.dnd_9th_3_android.gooding.login.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.login.viewModel.LoginViewModel

@Composable
fun FinishScreen(
    onStepChange : (Int) -> Unit,
    loginViewModel : LoginViewModel
) {
    if (loginViewModel.getUserName()!=""
        && loginViewModel.getUserName().isNotEmpty()
        && loginViewModel.getCheckCategorySize()>=3
    ){ onStepChange(3) }
    else{ onStepChange(5) }

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(97.dp))
        Image(
            painter = painterResource(id = R.drawable.finish_ment),
            contentDescription = null,
            modifier = Modifier
                .padding(
                    start = 18.dp,
                    top = 36.dp
                )
                .width(278.dp)
                .height(54.dp)
        )
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(
                    bottom = 88.4.dp,
                    start = 0.53.dp
                )
            , contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.finish_image),
                contentDescription = null,
                modifier = Modifier
                    .width(284.53.dp)
                    .height(263.68.dp)
            )
        }
    }

}