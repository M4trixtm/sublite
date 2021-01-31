package m4trixtm.sublite.features.language

import kotlinx.coroutines.flow.Flow
import m4trixtm.sublite.core.platform.repository.BaseRepository

class LanguageRepositoryImpl(private val service: LanguageService) : BaseRepository(),
    LanguageRepository {

    override fun fetchLanguages(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ): Flow<LanguageResponse> = networkRequest(
        request = service::fetchLanguages,
        onSuccess = onSuccess,
        onError = onError
    )
}