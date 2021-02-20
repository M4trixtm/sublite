package m4trixtm.sublite.features.subtitle.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import m4trixtm.sublite.core.extension.transformToFlow
import m4trixtm.sublite.core.platform.viewmodel.BaseViewModel
import m4trixtm.sublite.features.common.ApiResponse
import m4trixtm.sublite.features.common.PaginationApiResponse
import m4trixtm.sublite.features.common.repository.ShowRepository
import m4trixtm.sublite.features.common.repository.SubtitleRepository
import m4trixtm.sublite.features.show.entity.Show
import m4trixtm.sublite.features.show.home.HomeShowItem
import m4trixtm.sublite.features.subtitle.entity.Subtitle
import javax.inject.Inject

//TODO handle UIStates in a better way:)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val subtitleRepository: SubtitleRepository,
    private val showRepository: ShowRepository
) : BaseViewModel() {

    companion object {
        const val languagesKey: String = "lang"
        const val typeKey: String = "type"
    }

    private val mostDownloadSignal = Channel<HashMap<String, String?>>()
    private val latestSignal = Channel<HashMap<String, String?>>()
    private val popularSignal = Channel<HashMap<String, String?>>()

    val mostDownloadedSubtitles: StateFlow<List<HomeSubtitleItem>?> = scope {
        mostDownloadSignal.transformToFlow { query ->
            emitAll(
                subtitleRepository.getMostDownloaded(
                    languages = query[languagesKey],
                    type = query[typeKey],
                    onError = {},
                    onSuccess = {}
                )
                    .mapToSubtitleList()
            )
        }
    }

    val latestSubtitles: StateFlow<List<HomeSubtitleItem>?> = scope {
        latestSignal.transformToFlow { query ->
            emitAll(
                subtitleRepository.getLatestSubtitles(
                    languages = query[languagesKey],
                    type = query[typeKey],
                    onError = {},
                    onSuccess = {}
                ).mapToSubtitleList()
            )
        }
    }

    val popularShows: StateFlow<List<HomeShowItem>?> = scope {
        popularSignal.transformToFlow { query ->
            emitAll(
                showRepository.getPopularFeatures(
                    languages = query[languagesKey],
                    type = query[typeKey],
                    onError = {},
                    onSuccess = {}
                ).mapToShowList()
            )
        }
    }

    fun loadHomePage() {
        //TODO load user preferred language and types instead of putting null
        viewModelScope.launch {
            mostDownloadSignal.send(
                hashMapOf(
                    languagesKey to null,
                    typeKey to null
                )
            )

            latestSignal.send(
                hashMapOf(
                    languagesKey to null,
                    typeKey to null
                )
            )

            popularSignal.send(
                hashMapOf(
                    languagesKey to null,
                    typeKey to null
                )
            )
        }
    }

    private val _selectedSubtitle = MutableStateFlow<Subtitle?>(null)
    val selectedSubtitle: StateFlow<Subtitle?> get() = _selectedSubtitle

    private fun Flow<PaginationApiResponse<Subtitle>>.mapToSubtitleList(): Flow<List<HomeSubtitleItem>> =
        map {
            it.data.map { data ->
                HomeSubtitleItem(data.attributes) { item ->
                    _selectedSubtitle.value = item
                }
            }
        }

    private val _selectedShow = MutableStateFlow<Show?>(null)
    val selectedShow: StateFlow<Show?> get() = _selectedShow

    private fun Flow<ApiResponse<Show>>.mapToShowList(): Flow<List<HomeShowItem>> = map {
        it.data.map { data ->
            HomeShowItem(data.attributes) { item ->
                _selectedShow.value = item
            }
        }
    }
}