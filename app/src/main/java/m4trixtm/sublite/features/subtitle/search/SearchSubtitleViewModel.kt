package m4trixtm.sublite.features.subtitle.search

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import m4trixtm.sublite.core.extension.transformToFlow
import m4trixtm.sublite.core.platform.viewmodel.BaseViewModel
import m4trixtm.sublite.features.ApiResponse
import m4trixtm.sublite.features.common.repository.SubtitleRepository
import m4trixtm.sublite.features.language.Language
import m4trixtm.sublite.features.language.LanguageItem
import m4trixtm.sublite.features.language.LanguageRepository
import m4trixtm.sublite.features.language.LanguageResponse
import m4trixtm.sublite.features.subtitle.entity.Subtitle
import javax.inject.Inject

@HiltViewModel
class SearchSubtitleViewModel @Inject constructor(
    private val repository: SubtitleRepository,
    private val languageRepository: LanguageRepository
) : BaseViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    private var searchQuery: String? = null
    private val searchQueryChannel = Channel<String>()
    val subtitles: StateFlow<List<SearchSubtitleItem>?> = scope {
        searchQueryChannel.transformToFlow { query ->
            searchQuery = query
            if (query.isNotEmpty())
                emitAll(searchSubtitleFlow(query))
            else {
                emit(listOf())
                _loading.value = false
            }
        }
    }

    private val _selectedLanguages = MutableStateFlow(listOf<Language>())
    val selectedLanguages: StateFlow<List<Language>> get() = _selectedLanguages
    private val selectedLanguagesCode: String
        get() = _selectedLanguages.value.run {
            if (isNotEmpty()) joinToString(separator = ",") { it.code } else "all"
        }

    private val _languagesLoaded = MutableStateFlow(false)
    val languagesLoaded: StateFlow<Boolean> get() = _languagesLoaded
    val languages: StateFlow<List<LanguageItem>?> = scope {
        languageRepository.fetchLanguages({ _languagesLoaded.value = true }, {})
            .mapToLanguageList()
    }

    private val _clickedItem = MutableStateFlow<Subtitle?>(null)
    val clickedItem: StateFlow<Subtitle?> get() = _clickedItem

    fun search(query: String) {
        loading()
        viewModelScope.launch {
            searchQueryChannel.send(query)
        }
    }

    private fun searchSubtitleFlow(query: String) = repository.search(
        searchParams(query),
        onSuccess = { loading(false) },
        onError = { loading(false) }
    ).mapToSubtitleList()

    private fun Flow<ApiResponse<Subtitle>>.mapToSubtitleList(): Flow<List<SearchSubtitleItem>> =
        map {
            it.data.map { data ->
                SearchSubtitleItem(data.attributes, ::onSubtitleClicked)
            }
        }

    private fun Flow<LanguageResponse>.mapToLanguageList(): Flow<List<LanguageItem>> =
        map {
            it.languages.map { language -> LanguageItem(language, ::onLanguageChanged) }
        }

    private fun onSubtitleClicked(subtitle: Subtitle) {
        _clickedItem.value = subtitle
    }

    private fun onLanguageChanged(language: Language) {
        _selectedLanguages.value = _selectedLanguages.value.toMutableList().apply {
            if (language.isSelected) {
                if (!contains(language))
                    add(language)
            } else
                remove(language)
        }
        searchQuery?.let {
            loading(true)
            viewModelScope.launch { searchQueryChannel.send(it) }
        }
    }

    private fun searchParams(query: String): Map<String, String> = mapOf(
        "query" to query,
        "languages" to selectedLanguagesCode
    )

    private fun loading(isLoading: Boolean = true) {
        this._loading.value = isLoading
    }
}