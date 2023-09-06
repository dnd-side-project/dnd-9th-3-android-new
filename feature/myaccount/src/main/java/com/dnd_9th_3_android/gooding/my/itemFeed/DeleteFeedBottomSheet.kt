package com.dnd_9th_3_android.gooding.my.itemFeed

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.dnd_9th_3_android.gooding.core.data.R
import com.dnd_9th_3_android.gooding.data.contentLayout.BoxText
import com.dnd_9th_3_android.gooding.data.contentLayout.pretendardBold
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialog
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialogProperties

@Composable
fun DeleteFeedBottomSheet(
    feedId : Int,
    onClose : () -> Unit,
    onDelete : () -> Unit
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
                    topStart = dimensionResource(id = R.dimen.padding_24),
                    topEnd = dimensionResource(id = R.dimen.padding_24)
                ),
            )
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_36))
        ) {
            Text(
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.padding_18),
                ),
                text = "삭제하시겠습니까?",
                fontSize = dimensionResource(id = R.dimen.text_16_sp).value.sp,
                fontFamily = pretendardBold,
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.padding_24))
                    .clickable {
                        onClose()
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.close_button),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = dimensionResource(id = R.dimen.padding_13)),
                    contentDescription = null
                )
            }
        }
        Text(
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_18)),
            text = "삭제된 이미지와 글은 복구가 불가능합니다.",
            fontSize = dimensionResource(id = R.dimen.text_16_sp).value.sp,
            fontFamily = pretendardBold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_36)))
        Box(modifier = Modifier.clickable { 
            onDelete()
            onClose()
        }){
            BoxText(
                borderColor = listOf(colorResource(id = R.color.blue_gray_3),colorResource(id = R.color.blue_gray_3)),
                borderShape = RoundedCornerShape(dimensionResource(id = R.dimen.corner_8)),
                borderBackground = Color.Transparent ,
                text = "삭제하기",
                fontSize = dimensionResource(id = R.dimen.text_16_sp).value.sp,
                fontColor = Color.White ,
                hoPadding = dimensionResource(id = R.dimen.padding_11_5),
                verPadding = dimensionResource(id = R.dimen.zero)
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_28)))
    }
}