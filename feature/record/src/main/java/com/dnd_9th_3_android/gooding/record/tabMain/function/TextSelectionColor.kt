package com.dnd_9th_3_android.gooding.record.tabMain.function

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun textSelectionColor() = TextSelectionColors(
    handleColor = Color(0XFF3CEFA3),
    backgroundColor = Color(0xFF282932)
)
