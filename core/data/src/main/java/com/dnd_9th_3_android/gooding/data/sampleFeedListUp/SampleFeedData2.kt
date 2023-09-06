package com.dnd_9th_3_android.gooding.data

import com.dnd_9th_3_android.gooding.data.sampleFeedListUp.SubSampleData
import com.dnd_9th_3_android.gooding.model.feed.GetMainFeed

//GetMainFeed
// 메인 피드 리스트업
object SampleFeedDataList{

    val sampleFeedList = arrayListOf(
        GetMainFeed(
            recordId = 1,
            title = "서울나들이",
            description ="오늘은 지영이랑 나의 첫 굳이데이를 즐겼다! 하루종일 걷고 마무리는 와인으루~~ 맛있는 것도 잔뜩 먹구 보람찬 하루를 보냈다 ㅎㅎㅎ",
            placeTitle ="서울 영등포구 여의도동",
            placeLatitude =0,
            placeLongitude =0,
            interestType ="",
            recordScore =0,
            user = SubSampleData.user1,
            files = SubSampleData.file1
        ),
        GetMainFeed(
            recordId = 2,
            title = "무계획 제주 여행",
            description ="여자친구랑 태어나서 처음으로 아무런 계획없이 여행을 와봤다ㅎㅎ.. 여유있게 즐기는 즉석 여행도 꽤 좋은거 같다!",
            placeTitle ="제주특별자치도 서귀포시 색달동",
            placeLatitude =0,
            placeLongitude =0,
            interestType ="",
            recordScore =0,
            user = SubSampleData.user2,
            files = SubSampleData.file2
        ),
        GetMainFeed(
            recordId = 3,
            title = "혼자 굳이데이 즐기기~",
            description ="혼자서 카페투어도 하고, 여유있는 시간을 보냈다. 그동안 정신없었는데 간만에 휴가를 즐겨서 좋았다ㅎㅎ 굳이데이 최고",
            placeTitle ="서울 종로구 인사동",
            placeLatitude =0,
            placeLongitude =0,
            interestType ="",
            recordScore =0,
            user = SubSampleData.user3,
            files = SubSampleData.file3
        ),
        GetMainFeed(
            recordId = 4,
            title = "런닝하다 찍은 야경",
            description ="목적지 없는 런닝을 왔다! 운동도 하고 굳이데이 경험도 한 것 같아서 뿌듯했다 ㅎㅎ",
            placeTitle ="서울 영등포구 여의도동",
            placeLatitude =0,
            placeLongitude =0,
            interestType ="",
            recordScore =0,
            user = SubSampleData.user4,
            files = SubSampleData.file4
        )
    )
}