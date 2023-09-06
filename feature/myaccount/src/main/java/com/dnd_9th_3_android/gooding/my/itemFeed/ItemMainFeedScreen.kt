package com.dnd_9th_3_android.gooding.my.itemFeed

import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.dnd_9th_3_android.gooding.data.SampleFeedData
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.model.feed.Feed
import com.dnd_9th_3_android.gooding.model.feed.MyFeed

@Composable
fun ItemMainFeedScreen(
    feed : MyFeed,
    onDeleteView : (Boolean) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                top = dimensionResource(id = R.dimen.padding_12),
                bottom = dimensionResource(id = R.dimen.padding_12)
            )
    ) {
        // left image
        LeftBarLayout(feed.recordScore)

        // feed main
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            TopInfoLayout(timeData = feed.recordDate, onDelete = {
                // delete feed
                onDeleteView(true)
            })
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_15)))
            CenterFeedLayout(feed)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_12)))
            BottomFeedLayout(
                feed.title, feed.description,
                onMoreInfo = {
                    // go more info
                }
            )
        }
    }
}