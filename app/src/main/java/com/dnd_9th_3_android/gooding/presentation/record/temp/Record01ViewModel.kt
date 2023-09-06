package com.dnd_9th_3_android.gooding.presentation.record.temp

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnd_9th_3_android.gooding.data.model.search.RequestUploadFeed1
import com.dnd_9th_3_android.gooding.presentation.gallery.GalleryFileUiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class RecordGalleryItem {

    data class File(
        val galleryFileUiData: GalleryFileUiData
    ) : RecordGalleryItem()

    object AddButton : RecordGalleryItem()
}


@HiltViewModel
class Record01ViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedFiles = MutableLiveData<List<RecordGalleryItem>>()
    val selectedFiles: LiveData<List<RecordGalleryItem>>
        get() = _selectedFiles

    val title: MutableLiveData<String> = MutableLiveData()
    val description: MutableLiveData<String> = MutableLiveData()
    val recordDate: MutableLiveData<String> = MutableLiveData()
    val placeTitle: MutableLiveData<String> = MutableLiveData()
    val placeLatitude: MutableLiveData<Double> = MutableLiveData()
    val placeLongitude: MutableLiveData<Double> = MutableLiveData()
    val recordOpen: MutableLiveData<String> = MutableLiveData()
    val interestType: MutableLiveData<String> = MutableLiveData()
    val recordScore: MutableLiveData<Int> = MutableLiveData()

    private fun setSelectedFiles(selectedFiles: List<GalleryFileUiData>) {
        val addButtons: List<RecordGalleryItem> =
            if (selectedFiles.size >= 5) emptyList() else listOf(RecordGalleryItem.AddButton)
        _selectedFiles.value =
            selectedFiles.map { RecordGalleryItem.File(it) } + addButtons
    }

    fun removeGalleryFileUiData(item: RecordGalleryItem) {
        val newList = _selectedFiles.value.orEmpty().toMutableList().apply {
            remove(item)
        }
        newList.firstOrNull()?.let {
            if (it is RecordGalleryItem.File) {
                it.galleryFileUiData.selectedNumber = 1
            }
        }

        if (newList.any { it !is RecordGalleryItem.AddButton }) {
            newList.add(RecordGalleryItem.AddButton)
        }

        _selectedFiles.value = newList
    }

    fun onSetFeedData() {
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

            }
                .onSuccess {
                    Log.d("Record01ViewModel", "onSetFeedData: $it")
                }
                .onFailure {
                    it.printStackTrace()
                    Log.e("Record01ViewModel", "onSetFeedData: $it", )
                }
        }
    }
}