package com.dnd_9th_3_android.gooding.search.ui

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.dnd_9th_3_android.gooding.model.search.SearchLog
import com.dnd_9th_3_android.gooding.search.item.PopularItem
import com.dnd_9th_3_android.gooding.search.item.SearchResultItem

@Composable
fun SearchResultScreen(
    currentText : MutableState<TextFieldValue>,
    searchResultListState : SnapshotStateList<SearchLog>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
        ,verticalArrangement = Arrangement.spacedBy(16.dp),
    ){
        itemsIndexed(searchResultListState){index,item ->
            SearchResultItem(
                searchLog = item,
                currentText,
                clickData = {

                }
            )
        }
    }
}