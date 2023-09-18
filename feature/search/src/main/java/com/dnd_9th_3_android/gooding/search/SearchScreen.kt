package com.dnd_9th_3_android.gooding.search

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dnd_9th_3_android.gooding.core.data.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import com.dnd_9th_3_android.gooding.model.search.SearchLogData
import com.dnd_9th_3_android.gooding.search.ui.RecentlySearchScreen
import com.dnd_9th_3_android.gooding.search.ui.SearchTopBar

@Composable
fun SearchScreen(){
    val searchLogListState = remember { mutableStateListOf<SearchLogData>() }
    Column(
        Modifier
            .background(colorResource(id = R.color.blue_gray_7))
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(39.dp))
        SearchTopBar(backScreen = {

        })
        Spacer(modifier = Modifier.height(16.dp))

        if (searchLogListState.isNotEmpty()) {
            RecentlySearchScreen(searchLogListState)
        }


    }
}


@Composable
@Preview
fun SearchPreview(){
    SearchScreen()
}