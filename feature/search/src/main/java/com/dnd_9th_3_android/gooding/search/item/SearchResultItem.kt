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
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.data.component.pretendardRegular
import com.dnd_9th_3_android.gooding.model.search.SearchLog

@Composable
fun SearchResultItem(
    searchLog: SearchLog,
    currentText : MutableState<TextFieldValue>,
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
        var indexList = searchLog.text.indexOfAll(currentText.value.text)
        val currentTextSize = currentText.value.text.length
        Text(
            text = buildAnnotatedString {
                if (indexList.isEmpty()) { append(searchLog.text) }
                else {
                    for (i in 0 until indexList.size){
                        withStyle(
                            SpanStyle(color = colorResource(id = R.color.secondary_1))
                        ){
                            append(searchLog.text.substring(indexList[i],indexList[i]+currentTextSize))
                        }
                        if (i == indexList.size-1){ //마지막
                            append(searchLog.text.substring(indexList[i]+currentTextSize))
                        }else{
                            append(searchLog.text.substring(indexList[i]+currentTextSize,indexList[i+1]))
                        }
                    }
                }
            },
            fontSize = 14.sp,
            fontFamily = pretendardRegular,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis // ... 처리
        )
    }
}

fun String.indexOfAll(str : String) : MutableList<Int>{
    var index = this.indexOf(str)
    val returnIndex = mutableListOf<Int>()

    while (index != -1){
        returnIndex.add(index)
        index = this.indexOf(str, index+1)
    }
    return returnIndex
}