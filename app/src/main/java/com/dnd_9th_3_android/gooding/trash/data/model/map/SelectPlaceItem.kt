package com.dnd_9th_3_android.gooding.trash.data.model.map

// 장소 선택 데이터
data class SelectPlaceItem(
    val placeName: String,           // 장소명
    val loadAddress: String,         // 지번 주소
    val randAddress: String,         // 도로명 주소
    var selectPlace: Boolean = false, // 장소 선택 유무
)