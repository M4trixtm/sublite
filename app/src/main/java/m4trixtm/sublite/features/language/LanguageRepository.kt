package m4trixtm.sublite.features.language

import kotlinx.coroutines.flow.Flow

interface LanguageRepository {

    fun fetchLanguages(onSuccess: () -> Unit, onError: (String) -> Unit): Flow<LanguageResponse>
}