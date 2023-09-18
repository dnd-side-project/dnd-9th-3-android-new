package com.dnd_9th_3_android.gooding.search.item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.dnd_9th_3_android.gooding.core.data.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp

@Composable
fun SearchResultItem(
    resultText : String,
    currentText : TextFieldValue,
    clickData : () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ){
        Image(
            painter = painterResource(id = R.drawable.search_16),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        // 문자열 분할
        val textList = resultText.split(currentText.text)

        Text(
            text = buildAnnotatedString {
            }
        )
    }
}