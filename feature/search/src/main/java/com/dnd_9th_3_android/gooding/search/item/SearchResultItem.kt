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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.data.contentLayout.pretendardRegular
import com.dnd_9th_3_android.gooding.model.search.SearchLog

@Composable
fun SearchResultItem(
    searchLog: SearchLog,
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
        var prevIndex = 0
        var index = searchLog.text.indexOf(currentText.text)
        val currentTextSize = currentText.text.length
        Text(
            text = buildAnnotatedString {
                while (true) {
                    // 비검색 문자열 (이전 인덱스 ~ 현재 인덱스)
                    append(searchLog.text.substring(prevIndex, index))
                    // 검색 문자열  (현재 인덱스 ~ 검색 글자 수)
                    withStyle(
                        SpanStyle(color = colorResource(id = R.color.secondary_1))
                    ){
                        append(searchLog.text.substring(index, index + currentTextSize))
                    }
                    // 이전 인덱스 = 현재 인덱스
                    prevIndex = index
                    // 현재 인덱스 = 다음 검색 글자
                    index = searchLog.text.indexOf(currentText.text, index + 1)
                    if (index == -1) {
                        append(searchLog.text.substring(prevIndex))
                        break
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