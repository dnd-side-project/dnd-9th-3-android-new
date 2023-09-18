package com.dnd_9th_3_android.gooding.search.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.search.component.SearchTextLayer

@Composable
fun SearchTopBar(
    backScreen : () -> Unit
) {
    val searchText = remember{
        mutableStateOf(TextFieldValue(""))
    }
    val visualCloseIcon = remember{
        mutableStateOf(false)
    }

    Row(
        Modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            Modifier
                .size(24.dp)
                .clickable { backScreen() }
        ) {
            Image(
                painterResource(id = R.drawable.arrow_back_24),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.width(11.dp))
        Box(
            Modifier
                .padding(vertical = 12.dp)
                .fillMaxSize()
        ){
            SearchTextLayer(
                hintText = "검색어를 입력하세요" ,
                visualCloseIcon =visualCloseIcon ,
                searchText = searchText,
                searchData = {
                    // search request
                }
            )
        }
        Spacer(modifier = Modifier.width(18.dp))
    }
}