package com.dnd_9th_3_android.gooding.my.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnd_9th_3_android.gooding.data.sampleData.SampleRecordData
import com.dnd_9th_3_android.gooding.api.RetrofitUtil
import com.dnd_9th_3_android.gooding.model.feed.MyFeed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CurrentTimeLineViewModel @Inject constructor(): ViewModel() {
    val currentTimeLine = mutableStateListOf<MyFeed>()
    // if prev != current -> load
    var prevDate = ""
    var loadData = "firstLoad"
    fun setCurrentTimeLine(userId:Int, recordDate : String){
        if(prevDate!=loadData) {
            viewModelScope.launch {

                RetrofitUtil.userApiService?.getMyDateRecords(userId, recordDate.toInt())
                    ?.enqueue(object : Callback<ArrayList<MyFeed>> {
                        override fun onResponse(
                            call: Call<ArrayList<MyFeed>>,
                            response: Response<ArrayList<MyFeed>>
                        ) {
                            if (response.isSuccessful) {
                                val dataList = response.body() ?: emptyList()
                                currentTimeLine.clear()

                                // 샘플 데이터 추가
                                if (SampleRecordData.sampleRecordData!=null && recordDate=="202308"){
                                    currentTimeLine.add(SampleRecordData.sampleRecordData!!)
                                    SampleRecordData.sampleRecordData = null
                                }
                                //

                                currentTimeLine.addAll(dataList)
                                Log.d("dataSEt", dataList.toString())
                            } else {
                                Log.d("fail 22", response.errorBody()!!.string())
                            }
                        }

                        override fun onFailure(call: Call<ArrayList<MyFeed>>, t: Throwable) {
                            Log.d("fail data", "load fail $t")
                        }

                    })

            }
            prevDate = loadData
        }
    }
}