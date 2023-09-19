package com.dnd_9th_3_android.gooding.data.sampleData

import com.dnd_9th_3_android.gooding.model.feed.Feed

object SampleFeedData {
    val sampleThumb = listOf(
        "https://media.w3.org/2010/05/sintel/poster.png",
    )
    private fun sampleRandomImage(id:Int) : String = "https://picsum.photos/id/$id/360/685"

    val sampleFeedList = arrayListOf(
        Feed(
            "나의 첫 굳이데이 기록",
            "rem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the Lorem Ipsum has been ",
            10,
            "광안리 해수욕장",
            listOf(
                sampleRandomImage(1),
                sampleRandomImage(2),
                sampleRandomImage(3)
            ),
            "2023-01-14T14:49:09.230Z",
            SampleUserData.sampleUserData[0]

        ),
        Feed(
            "나의 두번째 굳이데이 기록",
            "rem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the ",
            80,
            "광안리 해수욕장",
            listOf(
                sampleRandomImage(20),
                sampleRandomImage(15),
                sampleRandomImage(20),
                sampleRandomImage(21)
            ),
            "2023-12-15T14:49:09.230Z",
            SampleUserData.sampleUserData[1]

        )
    )
}