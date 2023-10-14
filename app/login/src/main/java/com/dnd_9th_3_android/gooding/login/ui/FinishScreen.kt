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
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.login.viewModel.LoginViewModel
import com.dnd_9th_3_android.gooding.login.viewModel.OnBoardingObject

@Composable
fun FinishScreen(
    navController: NavHostController,
    onStepChange : (Int) -> Unit,
    loginViewModel : LoginViewModel = hiltViewModel()
) {
    if (OnBoardingObject.userName!="" && OnBoardingObject.userName.length>0){
        onStepChange(3)
    }else{
        onStepChange(5)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.top_padding)))
        Image(
            painter = painterResource(id = R.drawable.finish_ment),
            contentDescription = null,
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.padding_18),
                    top = dimensionResource(id = R.dimen.padding_36)
                )
                .width(dimensionResource(id = R.dimen.size_278))
                .height(dimensionResource(id = R.dimen.size_54))
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_107_96)))
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
            , contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.finish_image),
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
            )
        }
    }

}