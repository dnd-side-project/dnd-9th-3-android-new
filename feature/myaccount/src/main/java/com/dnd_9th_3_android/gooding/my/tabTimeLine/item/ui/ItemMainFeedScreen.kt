package com.dnd_9th_3_android.gooding.my.itemFeed

import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dnd_9th_3_android.gooding.api.myApi.entity.MyRecordEntity
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.model.feed.MyFeed
import com.dnd_9th_3_android.gooding.my.viewModel.MyOptionViewModel

@Composable
fun ItemMainFeedScreen(
    feed : MyRecordEntity,
    todayViewModel: MyOptionViewModel = hiltViewModel(),
) {
    // is delete view ?
    val showDeleteView = todayViewModel.myAccountState?.showDeleteView.let{ deleteView ->
        deleteView?: remember { mutableStateOf(-1) }
    }

    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 14.dp)
    ) {
        // left image
        LeftBarLayout(feed.recordScore)
        
        Spacer(modifier = Modifier.width(16.dp))

        // feed main
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(end = 18.dp)
        ) {
            TopInfoLayout(
                timeData = feed.recordDate,
                onDelete = {
                // delete feed
                showDeleteView.value = feed.id
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            CenterFeedLayout(feed)
            Spacer(modifier = Modifier.height(12.dp))
            BottomFeedLayout(
                feed.title,
                feed.description,
                onMoreInfo = {
                    // go more info
                }
            )
        }
    }
}