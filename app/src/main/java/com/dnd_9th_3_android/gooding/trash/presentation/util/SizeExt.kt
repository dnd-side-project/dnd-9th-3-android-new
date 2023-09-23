package com.dnd_9th_3_android.gooding.trash.presentation.util

import android.content.res.Resources

fun Float.fromDpToPx(): Int =
    (this * Resources.getSystem().displayMetrics.density).toInt()