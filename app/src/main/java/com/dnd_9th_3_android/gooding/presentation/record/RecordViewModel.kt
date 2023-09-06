package com.dnd_9th_3_android.gooding.presentation.record

import android.net.Uri
import android.os.Parcelable
import androidx.compose.runtime.MutableState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dnd_9th_3_android.gooding.data.repository.record.RecordRepository
import com.dnd_9th_3_android.gooding.presentation.gallery.GalleryFileUiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val recordRepository: RecordRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _imageListFlow = MutableStateFlow<List<Uri>>(emptyList())
    val imageListFlow: StateFlow<List<Uri>> = _imageListFlow.asStateFlow()
    var location  =  MutableStateFlow<String>("장소를 설정해주세요. (선택)")
    init {
        val initialSelectedFiles = savedStateHandle.get<Array<Parcelable>>(KEY_SELECTED_FILES).orEmpty().toList().map { Uri.parse((it as GalleryFileUiData).mediaData) }
        viewModelScope.launch {
            _imageListFlow.emit(initialSelectedFiles)
        }
    }

    companion object {
        const val KEY_SELECTED_FILES = "selectedFiles"
    }
}