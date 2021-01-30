package m4trixtm.sublite.features.subtitle.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import m4trixtm.sublite.core.extension.transformToFlow
import m4trixtm.sublite.core.platform.viewmodel.BaseViewModel
import m4trixtm.sublite.features.ApiResponse
import m4trixtm.sublite.features.subtitle.SubtitleRepository
import m4trixtm.sublite.features.subtitle.entity.Subtitle
import javax.inject.Inject

@HiltViewModel
class SearchSubtitleViewModel @Inject constructor(private val repository: SubtitleRepository) :
    BaseViewModel() {

    val loading = MutableLiveData(false)
    private val searchQuery = Channel<String>()
    val subtitles: StateFlow<List<SearchSubtitleItem>?> = scope {
        searchQuery.transformToFlow { query ->
            emitAll(repository.search(query, onSuccess = { loading(false) }, onError = { loading(false) }).mapToSubtitleList())
        }
    }

    private val _clickedItem = MutableStateFlow<Subtitle?>(null)
    val clickedItem: StateFlow<Subtitle?> get() = _clickedItem

    fun search(query: String) {
        loading()
        viewModelScope.launch {
            searchQuery.send(query)
        }
    }

    private fun Flow<ApiResponse<Subtitle>>.mapToSubtitleList(): Flow<List<SearchSubtitleItem>> =
        map {
            it.data.map { data ->
                SearchSubtitleItem(data.attributes) { item ->
                    _clickedItem.value = item
                }
            }
        }

    private fun loading(isLoading: Boolean = true) {
        this.loading.value = isLoading
    }
}