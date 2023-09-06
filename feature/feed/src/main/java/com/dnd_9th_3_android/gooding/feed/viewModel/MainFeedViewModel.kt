package com.dnd_9th_3_android.gooding.feed.viewModel

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dnd_9th_3_android.gooding.api.RetrofitUtil
import com.dnd_9th_3_android.gooding.api.UserApiService
import com.dnd_9th_3_android.gooding.api.UserInfo
import com.dnd_9th_3_android.gooding.data.SampleFeedDataList
import com.dnd_9th_3_android.gooding.model.feed.Feed
import com.dnd_9th_3_android.gooding.model.feed.GetMainFeed
import com.dnd_9th_3_android.gooding.model.feed.GetMainFeedList
import com.dnd_9th_3_android.gooding.model.user.AccessToken
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainFeedViewModel @Inject constructor(): ViewModel(){
    var feedList = mutableStateListOf<GetMainFeed>()
    var currentFeed = MutableLiveData<GetMainFeed>()
    var roadFeed = true
    fun initFeedData(){
        if (roadFeed) {
            feedList.addAll(SampleFeedDataList.sampleFeedList)
//            UserInfo.myData?.id?.let {
//                RetrofitUtil.userApiService
//                    .getUserFeedFromId(
//                        userId = it,
//                        interestCodes = UserInfo.myData!!.onboardings,
//                        page = 1,
//                        size = 10
//                    ).enqueue(object : Callback<GetMainFeedList> {
//                        override fun onResponse(
//                            call: Call<GetMainFeedList>,
//                            response: Response<GetMainFeedList>
//                        ) {
//                            if (response.isSuccessful) {
//                                val dataList = if (response.body() == null) {
//                                    arrayListOf()
//                                } else {
//                                    response.body()!!.content
//                                }
//                                feedList.addAll(dataList)
//                            }
//                        }
//                        override fun onFailure(call: Call<GetMainFeedList>, t: Throwable) {}
//                    })
//            }
            roadFeed = false
        }
    }

    fun setCurrentFeed(feed : GetMainFeed){
        currentFeed.value = feed
    }

    fun setRomantic(per : Int){
        currentFeed.value?.recordScore = per
    }
}