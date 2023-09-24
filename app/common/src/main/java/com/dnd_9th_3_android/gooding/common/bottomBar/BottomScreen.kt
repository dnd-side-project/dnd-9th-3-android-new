package com.dnd_9th_3_android.gooding.common.bottomBar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dnd_9th_3_android.gooding.common.root.ScreenRoot.MAIN_FEED
import com.dnd_9th_3_android.gooding.common.root.ScreenRoot.MAIN_MY
import com.dnd_9th_3_android.gooding.common.root.ScreenRoot.MAIN_RECORD
import com.dnd_9th_3_android.gooding.core.data.R

// 각 바텀 스크린 아이템
sealed class BottomScreen(
    val route : String,
    @StringRes val stringResId : Int,
    @DrawableRes val drawableResId : Int ,
    @DrawableRes val selectedDrawableResId : Int ,
) {
    object Feed:
        BottomScreen(
            MAIN_FEED,
            R.string.feed_screen_text,
            R.drawable.bottom_feed,
            R.drawable.bottom_selected_feed
        )
    object Record:
        BottomScreen(
            MAIN_RECORD,
            R.string.record_screen_text,
            R.drawable.bottom_record,
            R.drawable.bottom_selected_record
        )
    object MyAccount:
        BottomScreen(
            MAIN_MY,
            R.string.my_account_screen_text,
            R.drawable.bottom_my_account,
            R.drawable.bottom_selected_my_account
        )


    companion object {
        val BOTTOM_NAV_ITEMS = listOf(
            Feed,
            Record,
            MyAccount
        )
    }

}