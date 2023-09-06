package com.dnd_9th_3_android.gooding.login.ui

import android.util.Log
import androidx.navigation.NavHostController
import com.dnd_9th_3_android.gooding.core.data.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.login.component.CategoryBox
//import com.dnd_9th_3_android.gooding.login.type.CategoryListType
import com.dnd_9th_3_android.gooding.login.viewModel.LoginViewModel
import com.dnd_9th_3_android.gooding.login.viewModel.OnBoardingObject

@Composable
fun CheckCategoryScreen(
    navController: NavHostController,
    onStepChange : (Int) -> Unit,
    loginViewModel : LoginViewModel = hiltViewModel()
) {
    onStepChange(0)

    if (OnBoardingObject.checkCategoryCount>=3){
        onStepChange(1)
    }else{
        onStepChange(0)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_133)))

        Image(
            painter = painterResource(id = R.drawable.category_ment),
            contentDescription = null,
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.top_padding))
                .width(dimensionResource(id = R.dimen.size_248))
                .padding(
                    start = dimensionResource(id = R.dimen.padding_18)
                )
        )

    }

    Column(
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.padding_12),
                end = dimensionResource(id = R.dimen.padding_12),
                top = dimensionResource(id = R.dimen.padding_40)
            )
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ){

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_97)))
        val size = OnBoardingObject.categoryList.size
        val oneWidth = LocalConfiguration.current.screenWidthDp.dp / 3  //- padding

        LazyVerticalGrid(
            columns = GridCells.Fixed(size/3),
            content = {
                items(size){ index->
                    Box(
                        Modifier.padding(dimensionResource(id = R.dimen.padding_6)),
                        contentAlignment = Alignment.Center
                    ){
                        CategoryBox(OnBoardingObject.categoryList[index],oneWidth, clickData = {
                            if (OnBoardingObject.categoryList[index].selected){
                                OnBoardingObject.checkCategoryCount-=1
                                if(OnBoardingObject.checkCategoryCount<3){
                                    onStepChange(0)
                                }
                            }else{
                                OnBoardingObject.checkCategoryCount+=1
                                if(OnBoardingObject.checkCategoryCount>=3){
                                    onStepChange(1)
                                }
                            }
                            OnBoardingObject.categoryList[index].selected = !OnBoardingObject.categoryList[index].selected
                        })
                    }
                }
            }
        )
    }


}