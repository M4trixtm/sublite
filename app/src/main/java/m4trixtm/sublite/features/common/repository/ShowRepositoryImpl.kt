package m4trixtm.sublite.features.common.repository

import kotlinx.coroutines.flow.Flow
import m4trixtm.sublite.core.platform.repository.BaseRepository
import m4trixtm.sublite.features.common.ApiResponse
import m4trixtm.sublite.features.show.ShowService
import m4trixtm.sublite.features.show.entity.Show

class ShowRepositoryImpl(private val service: ShowService) : BaseRepository(), ShowRepository {

    override fun getPopularFeatures(
        languages: String?,
        type: String?,
        onSuccess: () -> Unit,
        onError: (message: String) -> Unit
    ): Flow<ApiResponse<Show>> = networkRequest(
        request = { service.getPopularFeatures(languages, type) },
        onSuccess = { onSuccess() },
        onError = { onError(it) }
    )
}