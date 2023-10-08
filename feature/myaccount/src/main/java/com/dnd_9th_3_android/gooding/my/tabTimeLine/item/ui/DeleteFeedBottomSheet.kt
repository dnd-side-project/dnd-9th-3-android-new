package com.dnd_9th_3_android.gooding.my.itemFeed

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.component.BoxText
import com.dnd_9th_3_android.gooding.data.component.pretendardBold
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialog
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialogProperties

@Composable
fun DeleteFeedBottomSheet(
    feedId : Int,
    onClose : () -> Unit,
    onDelete : (Int) -> Unit
) {

    BottomSheetDialog(
        onDismissRequest = {
            onClose()
        },
        properties = BottomSheetDialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
    }
    Column(
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.blue_gray_6),
                shape = RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp
                ),
            )
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Spacer(modifier = Modifier.height(36.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    start = 18.dp,
                    end = 13.dp
                )
        ){
            Column(modifier = Modifier.align(Alignment.CenterStart)) {
                Text(
                    text = "삭제하시겠습니까?",
                    fontSize = 16.sp,
                    fontFamily = pretendardBold,
                    color = Color.White
                )
                Text(
                    text = "삭제된 이미지와 글은 복구가 불가능합니다.",
                    fontSize = 16.sp,
                    fontFamily = pretendardBold,
                    color = Color.White
                )
            }

            Box(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.TopEnd)
                    .clickable {
                        onClose()
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.close_my),
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = null
                )
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        Box(modifier = Modifier
            .clickable {
                onDelete(feedId)
                onClose()
            }
        ){
            BoxText(
                borderColor = listOf(colorResource(id = R.color.blue_gray_3),colorResource(id = R.color.blue_gray_3)),
                borderShape = RoundedCornerShape(8.dp),
                borderBackground = Color.Transparent ,
                text = "삭제하기",
                fontSize = 16.sp,
                fontColor = Color.White ,
                hoPadding = 11.5.dp,
                verPadding = 0.dp,
                (-0.25).sp
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
    }
}