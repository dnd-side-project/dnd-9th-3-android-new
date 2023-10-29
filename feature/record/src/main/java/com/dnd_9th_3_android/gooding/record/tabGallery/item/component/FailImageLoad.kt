package com.dnd_9th_3_android.gooding.record.tabGallery.item

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.core.data.R

@Composable
fun FailImageLoad() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_error_outline_24),
            contentDescription = "Icon Error",
            modifier = Modifier.size(16.dp),
            tint = Color.White,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "지원하지 않는\n파일 형식입니다.",
            fontSize = 8.sp,
            textAlign = TextAlign.Center,
        )
    }
}