package com.dnd_9th_3_android.gooding.record.viewModel

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dnd_9th_3_android.gooding.data.dataRecord.domain.ImageRepository
import com.dnd_9th_3_android.gooding.data.dataRecord.domain.RecordStateRepository
import com.dnd_9th_3_android.gooding.data.dataRecord.remote.GalleryPagingSource
import com.dnd_9th_3_android.gooding.data.dataRecord.remote.GalleryPagingSource.Companion.PAGING_SIZE
import com.dnd_9th_3_android.gooding.data.di.DispatcherModule
import com.dnd_9th_3_android.gooding.data.state.ApplicationState
import com.dnd_9th_3_android.gooding.model.record.GalleryImage
import com.dnd_9th_3_android.gooding.model.record.ImageFolder
import com.dnd_9th_3_android.gooding.record.state.RecordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
    val recordStateRepository: RecordStateRepository,
    @DispatcherModule.IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _customGalleryPhotoList =
        MutableStateFlow<PagingData<GalleryImage>>(PagingData.empty())
    val customGalleryPhotoList: StateFlow<PagingData<GalleryImage>> =
        _customGalleryPhotoList.asStateFlow()

    // 폴더 리스트
    private val _folders = mutableStateListOf<Pair<String, ImageFolder?>>()
    val folders get() = _folders

    // 현재 폴더
    private val _currentFolder = mutableStateOf<Pair<String, ImageFolder?>>("최근 항목" to null)
    val currentFolder: State<Pair<String, ImageFolder?>> = _currentFolder

    // 선택 이미지 리스트
    private val _selectedImages = mutableStateListOf<GalleryImage>()
    val selectedImages: SnapshotStateList<GalleryImage> = _selectedImages

    // paging 처리 함수
    fun getGalleryPagingImages() = viewModelScope.launch {
        _customGalleryPhotoList.value = PagingData.empty()
        Pager(
            config = PagingConfig(
                pageSize = PAGING_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                GalleryPagingSource(
                    imageRepo = imageRepository,
                    currentLocation = currentFolder.value.second?.uriPath
                )
            }
        ).flow.cachedIn(viewModelScope).collectLatest {
            _customGalleryPhotoList.value = it
        }
    }

    fun setCurrentFolder(location: Pair<String, ImageFolder?>) {
        _currentFolder.value = location
    }

    fun setFirstFolder() {
        _currentFolder.value = folders[0]
    }


    fun getSelectNumber(image: GalleryImage): Int {
        val index = _selectedImages.indexOf(image)
        return index + 1
    }

    fun getFolder() {
        _folders.clear()
        imageRepository.getFolderList().map {
            _folders.add(it.name to it)
        }
    }

    fun addSelectedImage(image: GalleryImage) {
        _selectedImages.add(image)
    }

    fun addSelectedImageList(secondScreenResult: List<GalleryImage>?) {
        secondScreenResult?.let { _selectedImages.addAll(it) }
    }

    fun removeSelectedImage(id: Long) {
        val removeImage = _selectedImages.find { it.id == id }
        removeImage?.let {
            _selectedImages.remove(removeImage)
        }
    }

    fun selectedImageSize(): Int = selectedImages.size

    fun initState(
        appState: ApplicationState,
        recordNavi : NavHostController,
        height: Dp,
        width: Dp
    ) {
        recordStateRepository.setState(appState,recordNavi)
        recordStateRepository.setSize(height, width)
    }

    fun goMain(){
        recordStateRepository.goBackState()
    }

    fun prevStep() {
        recordStateRepository.goPrevStep()
    }

    fun nextStep(step: String) {
        recordStateRepository.goNextStep(step)
    }

    fun getContext() = recordStateRepository.getContext()

    fun getImageHeight() = recordStateRepository.imageHeight
}