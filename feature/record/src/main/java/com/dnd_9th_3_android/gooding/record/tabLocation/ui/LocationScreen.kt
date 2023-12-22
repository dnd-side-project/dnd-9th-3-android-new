package com.dnd_9th_3_android.gooding.record.tabLocation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.dnd_9th_3_android.gooding.record.tabLocation.component.LocationSearchLayer
import com.dnd_9th_3_android.gooding.record.tabLocation.component.LocationTopLayer

@Composable
fun LocationScreen(

) {

    val searchQuery = remember { mutableStateOf(TextFieldValue("")) }

    Column{

        LocationTopLayer(
            backState = {

            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        LocationSearchLayer(
            searchQuery
        )

    }
}