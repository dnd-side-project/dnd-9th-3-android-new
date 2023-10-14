package com.dnd_9th_3_android.gooding.login.ui

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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.login.component.CategoryBox
//import com.dnd_9th_3_android.gooding.login.type.CategoryListType
import com.dnd_9th_3_android.gooding.login.viewModel.LoginViewModel
import com.dnd_9th_3_android.gooding.login.viewModel.OnBoardingObject
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.dnd_9th_3_android.gooding.data.component.pretendardRegular

@Composable
fun CheckCategoryScreen(
    onStepChange : (Int) -> Unit,
    loginViewModel : LoginViewModel = hiltViewModel()
) {
    // check state
    if (loginViewModel.getCheckCategorySize()>=3){ onStepChange(1) }
    else{ onStepChange(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(133.dp))
        Text(
            text =  "관심있는 활동 카테고리를\n선택해주세요.",
            fontSize = 18.sp,
            fontFamily = pretendardBold,
            color = Color.White,
            modifier = Modifier.padding(start = 18.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text =  "관심있는 카테고리를 3가지 이상 선택해주세요.",
            fontSize = 14.sp,
            fontFamily = pretendardRegular,
            color = Color.White,
            modifier = Modifier.padding(start = 18.dp)
        )
        
        Box(modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, bottom = 91.dp)
            .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            val size = loginViewModel.getCategoryListSize()
            val categoryList = loginViewModel.getCategoryList()
            val oneWidth = LocalConfiguration.current.screenWidthDp.dp / 3  //- padding

            LazyVerticalGrid(
                columns = GridCells.Fixed(size/3),
                content = {
                    items(size){ index->
                        Box(
                            Modifier.padding(6.dp),
                            contentAlignment = Alignment.Center
                        ){
                            CategoryBox(categoryList[index],oneWidth, clickData = {
                                if (categoryList[index].selected){
                                    loginViewModel.minusCheckCategory()
                                    if(loginViewModel.getCheckCategorySize()<3){
                                        onStepChange(0)
                                    }
                                }else{
                                    loginViewModel.plusCheckCategory()
                                    if(loginViewModel.getCheckCategorySize()>=3){
                                        onStepChange(1)
                                    }
                                }
                                loginViewModel.setCategoryState(index)
                            })
                        }
                    }
                }
            )
        }
    }
}