package com.dnd_9th_3_android.gooding.record.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun rememberRecordState(
    subject: MutableState<String> = mutableStateOf(""),
    comment: MutableState<String> = mutableStateOf(""),
    recordDate: MutableState<String> = mutableStateOf(""),
    recordPlace: MutableState<String> = mutableStateOf(""),
    recordCategory: MutableState<Int> = mutableStateOf(0),
    toggleState: MutableState<Boolean> = mutableStateOf(false)
) = remember(subject, comment, recordDate, recordPlace, recordCategory,toggleState) {
    RecordState(
        subject,
        comment,
        recordDate,
        recordPlace,
        recordCategory,
        toggleState
    )
}