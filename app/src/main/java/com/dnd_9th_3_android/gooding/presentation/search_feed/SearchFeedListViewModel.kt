package com.dnd_9th_3_android.gooding.presentation.search_feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dnd_9th_3_android.gooding.data.model.search.PopularKeywordData
import com.dnd_9th_3_android.gooding.data.model.search.RecentKeywordData
import com.dnd_9th_3_android.gooding.data.model.search.SearchFeedData
import com.dnd_9th_3_android.gooding.data.model.search.toEntity
import com.dnd_9th_3_android.gooding.data.repository.search.SearchFeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFeedListViewModel @Inject constructor(
    private val searchFeedRepository: SearchFeedRepository
): ViewModel() {
    sealed class UiState {
        object SearchFeedListFailed : UiState()

        data class SearchFeedListSuccess(
            val searchFeedList: List<SearchFeedData>,
        ) : UiState()

        object AddRecentKeywordListFailed : UiState()

        data class AddRecentKeywordListSuccess(
            val recentKeywordList: List<RecentKeywordData>,
        ) : UiState()

        object GetRecentKeywordListFailed : UiState()

        data class GetRecentKeywordListSuccess(
            val recentKeywordList: List<RecentKeywordData>,
        ) : UiState()

        object DeleteRecentKeywordListFailed : UiState()

        object DeleteRecentKeywordListSuccess: UiState()

        object GetPopularKeywordListFailed : UiState()

        data class GetPopularKeywordListSuccess(
            val popularKeywordList: List<PopularKeywordData>,
        ) : UiState()

        object Idle : UiState()
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _recentKeywordList = MutableLiveData<List<RecentKeywordData>>()
    val recentKeywordList: LiveData<List<RecentKeywordData>> = _recentKeywordList

    private val _popularKeywordList = MutableLiveData<List<PopularKeywordData>>()
    val popularKeywordList: LiveData<List<PopularKeywordData>> = _popularKeywordList

    private val _searchFeedList = MutableLiveData<PagingData<SearchFeedData>>()
    val searchFeedList: LiveData<PagingData<SearchFeedData>> = _searchFeedList

    val searchKeyword: MutableLiveData<String> = MutableLiveData()

    private val _emptyStateMessage =
        MutableLiveData("getApplication<Application>().getString(R.string.no_place_found)")
    val emptyStateMessage: LiveData<String>
        get() = _emptyStateMessage

    init {
        getRecentKeywordList()
        getPopularKeywordList()
    }

    fun getRecentKeywordList() {
        viewModelScope.launch {
            kotlin.runCatching {
                searchFeedRepository.getRecentKeywordList()
            }
                .onSuccess {
                    recentKeywordList.value?.toMutableList()?.addAll(
                        it.result.map {
                            it.toEntity()
                        }
                    )
                }
                .onFailure {
                    it.printStackTrace()
                }
        }
    }

    fun getPopularKeywordList() {
        viewModelScope.launch {
            kotlin.runCatching {
                searchFeedRepository.getPopularKeywordList()
            }
                .onSuccess {
                    popularKeywordList.value?.toMutableList()?.addAll(
                        it.result.map {
                            it.toEntity()
                        }
                    )
                }
                .onFailure {
                    it.printStackTrace()
                }
        }
    }

    fun searchPlace(query: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                searchFeedRepository.searchFeedRepository(query)
                    .cachedIn(viewModelScope)
                    .collect {
                        _searchFeedList.value = it
                    }
            }
                .onSuccess {
//                    _uiState.value = UiState.SearchFeedListSuccess(searchFeedList.value.)
                }
                .onFailure {
                    _uiState.value = UiState.SearchFeedListFailed
                    it.printStackTrace()
                }
        }
    }

    fun addRecentKeywordList() {
        viewModelScope.launch {
            kotlin.runCatching {
                searchFeedRepository.addRecentKeywordList()
            }
                .onSuccess {
//                    _recentKeywordList.value?.toMutableList()?.addAll()
                    _uiState.value = UiState.AddRecentKeywordListSuccess(
                        _recentKeywordList.value.orEmpty()
                    )
                }
                .onFailure {
                    _uiState.value = UiState.DeleteRecentKeywordListFailed
                    it.printStackTrace()
                }
        }
    }

    fun deleteRecentKeywordList() {
        viewModelScope.launch {
            kotlin.runCatching {
                searchFeedRepository.deleteRecentKeywordList()
            }
                .onSuccess {
                    _recentKeywordList.value?.toMutableList()?.clear()
                    _uiState.value = UiState.DeleteRecentKeywordListSuccess
                }
                .onFailure {
                    _uiState.value = UiState.DeleteRecentKeywordListFailed
                    it.printStackTrace()
                }
        }
    }
}