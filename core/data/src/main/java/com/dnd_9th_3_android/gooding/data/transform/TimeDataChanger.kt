package com.dnd_9th_3_android.gooding.data.transform

import android.annotation.SuppressLint
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint
class TimeDataChanger {
    @SuppressLint("SimpleDateFormat")
    fun getLastText(createTime: String): String {
        val uploadTimeStamp = createTime.split("-", "T", ":", ".")
        val timeData = uploadTimeStamp[0] + "-" + uploadTimeStamp[1] + "-" + uploadTimeStamp[2] +
                " " + uploadTimeStamp[3] + ":" + uploadTimeStamp[4] + ":" + uploadTimeStamp[5].chunked(2)[0]
        val timeString = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeData)?.time
        val today = Calendar.getInstance().time.time
        return if (timeString != null) {
            val calDate = (today - timeString)
            if ((calDate / (60 * 60 * 24 * 1000)) >= 1) { //1일 이상
                (calDate / (60 * 60 * 24 * 1000)).toInt().toString() + " 일 전"
            } else if ((calDate / (60 * 60 * 1000)) >= 1) { //1시간 이상
                (calDate / (60 * 60 * 1000)).toInt().toString() + " 시간 전"
            } else if ((calDate / (60 * 1000)) >= 1) { //분 단위
                (calDate / (60 * 1000)).toInt().toString() + " 분 전"
            } else {
                "몇초 전"
            }
        } else { "" }
    }

    fun getRecordText(createTime: String) : String {
        val uploadTimeStamp  = createTime.split("-","T",":",".")
        val calendar = Calendar.getInstance()
        calendar.set(
            uploadTimeStamp[0].toInt(),uploadTimeStamp[1].toInt()-1,uploadTimeStamp[2].toInt()
        )

        val day = when(calendar.get(Calendar.DAY_OF_WEEK)){
            1-> "일"
            2-> "월"
            3-> "화"
            4-> "수"
            5-> "목"
            6-> "금"
            7-> "토"
            else->""
        }
        val month = if (uploadTimeStamp[1][0].toString()=="0"){uploadTimeStamp[1][1]}
                    else uploadTimeStamp[1]
        return "${month}월 ${uploadTimeStamp[2]}일($day)"
    }
}
