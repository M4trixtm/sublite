package m4trixtm.sublite.features.subtitle.search

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

    private val searchQuery = Channel<String>()
    val subtitles: StateFlow<List<SearchSubtitleItem>?> = scope {
        searchQuery.transformToFlow { query ->
            emitAll(repository.search(query, {}, {}).mapToSubtitleList())
        }
    }

    fun search(query: String) = viewModelScope.launch {
        searchQuery.send(query)
    }

    private fun Flow<ApiResponse<Subtitle>>.mapToSubtitleList(): Flow<List<SearchSubtitleItem>> =
        map {
            it.data.map { data -> SearchSubtitleItem(data.attributes) }
        }
}