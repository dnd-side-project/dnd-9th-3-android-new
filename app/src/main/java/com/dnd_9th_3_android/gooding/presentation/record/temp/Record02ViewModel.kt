package com.dnd_9th_3_android.gooding.presentation.record.temp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnd_9th_3_android.gooding.data.model.search.RequestUploadFeed1
import com.dnd_9th_3_android.gooding.data.repository.feed.UploadFeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Record02ViewModel @Inject constructor(
    private val uploadFeedRepository: UploadFeedRepository
): ViewModel() {

    sealed class UiState {
        object UploadFeedFailed : UiState()

        object UploadFeedSuccess: UiState()

        object Idle : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    val title: MutableLiveData<String> = MutableLiveData()
    val description: MutableLiveData<String> = MutableLiveData()
    val recordDate: MutableLiveData<String> = MutableLiveData()
    val placeTitle: MutableLiveData<String> = MutableLiveData()
    val placeLatitude: MutableLiveData<Double> = MutableLiveData()
    val placeLongitude: MutableLiveData<Double> = MutableLiveData()
    val recordOpen: MutableLiveData<String> = MutableLiveData()
    val interestType: MutableLiveData<String> = MutableLiveData()
    val recordScore: MutableLiveData<Int> = MutableLiveData()

    fun uploadFeed() {
        val title = title.value
        val description = description.value
        val recordDate = recordDate.value
        val placeTitle = placeTitle.value
        val placeLatitude = placeLatitude.value
        val placeLongitude = placeLongitude.value
        val recordOpen = recordOpen.value
        val interestType = interestType.value
        val recordScore = recordScore.value

        val request = RequestUploadFeed1(
            title = title.orEmpty(),
            description = description.orEmpty(),
            recordDate = recordDate.orEmpty(),
            placeTitle = placeTitle.orEmpty(),
            placeLatitude = placeLatitude,
            placeLongitude = placeLongitude,
            recordOpen = recordOpen.orEmpty(),
            interestType = interestType.orEmpty(),
            recordScore = recordScore!!
        )

        viewModelScope.launch {
            kotlin.runCatching {
//                uploadFeedRepository.uploadFeed(
//                    RequestUploadFeed(
//                    thumbnail = "",
//                    thumbnailDirectory = "",
//                    images = listOf(),
//                    videos = listOf(),
//                    oauthId = "",
//                    uploadRequest = UploadRequest(
//                        title = "",
//                        description = "",
//                        recordDate = "",
//                        placeTitle = "",
//                        placeLatitude = 0.0,
//                        placeLongitude = 0.0,
//                        recordOpen = 1,
//                        recordScore = ""
//                    )
//                )
            }
                .onSuccess {
                    _uiState.value = UiState.UploadFeedSuccess
                    Log.d("Record02ViewModel", "uploadFeed: $it")
                }
                .onFailure {
                    _uiState.value = UiState.UploadFeedFailed
                    it.printStackTrace()
                    Log.e("Record02ViewModel", "uploadFeed: $it", )
                }
        }
    }

    companion object {
        const val KEY_UPLOAD_FEED_DATA = "uploadFeedData"
    }
}