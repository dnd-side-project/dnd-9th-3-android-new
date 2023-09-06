package com.dnd_9th_3_android.gooding.presentation.util

import android.content.res.Resources

fun Float.fromDpToPx(): Int =
    (this * Resources.getSystem().displayMetrics.density).toInt()