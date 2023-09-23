package com.dnd_9th_3_android.gooding.trash.presentation.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.dnd_9th_3_android.gooding.data.model.gallery.toUiData
import com.dnd_9th_3_android.gooding.data.repository.gallery.GalleryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val galleryRepository: GalleryRepository
) : ViewModel() {

    sealed class UiState {
        object GetGalleryFileListSuccess : UiState()

        object GetGalleryFileListFailed : UiState()

        object Idle : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val selectedAlbumName: Channel<String> = Channel()
    private val galleryLoadEvent = selectedAlbumName.receiveAsFlow()

    private val _selectedItems = mutableListOf<GalleryFileUiData>()
    val selectedItems: List<GalleryFileUiData>
        get() = _selectedItems.toList() // 방어적 복사

    val imagePagingList: Flow<PagingData<GalleryFileUiData>> =
        galleryLoadEvent.flatMapLatest { albumName ->
            galleryRepository.getGalleryPagingList(albumName)
                .map {
                    it.map {
                        it.toUiData()
                    }
                }
        }
            .cachedIn(viewModelScope)

    init {
        updateAlbumName("")
    }

    fun getAlbumList(): List<GalleryAlbumUiData> {
        val uiDataList = galleryRepository.getAlbumList()
            .map {
                GalleryAlbumUiData(
                    thumbnail = it.thumbnail,
                    folderName = it.folderName,
                    folderFileCount = it.folderFileCount,
                )
            }

        return uiDataList
    }

    fun updateAlbumName(selectedAlbumName: String) {
        viewModelScope.launch {
            this@GalleryViewModel.selectedAlbumName.send(selectedAlbumName)
        }
    }

    fun addGalleryList(photoPathList: List<String>) {
//        _imageList.value = _imageList.value + photoPathList
    }

    fun deletePhoto(position: Int) {
//        _imageList.value = _imageList.value
//            ?.map {
//                it
//            }
//            .apply {
//                removeAt(position)
//            }
    }

    fun selectedGalleryImageItem(galleryFileUiData: GalleryFileUiData): Boolean {
        val isAlreadySelected = selectedItems.any { it.id == galleryFileUiData.id }

        return if (isAlreadySelected) {
            val prevSelectedNumber = galleryFileUiData.selectedNumber
            galleryFileUiData.isSelected = false
            galleryFileUiData.selectedNumber = -1

            _selectedItems.remove(galleryFileUiData)

            selectedItems.forEach {
                if (it.selectedNumber > prevSelectedNumber) {
                    it.selectedNumber = it.selectedNumber - 1
                }
            }
            true
        } else {
            if (selectedItems.size >= MAX_SELECT_IMAGE_COUNT) {
                false
            } else {
                galleryFileUiData.isSelected = true
                galleryFileUiData.selectedNumber = selectedItems.size + 1

                _selectedItems.add(galleryFileUiData)
                true
            }
        }
    }

    fun resetSelectedItems() {
        _selectedItems.clear()
    }
}