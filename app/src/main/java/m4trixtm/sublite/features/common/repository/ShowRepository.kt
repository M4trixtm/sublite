package m4trixtm.sublite.features.common.repository

import kotlinx.coroutines.flow.Flow
import m4trixtm.sublite.features.common.ApiResponse
import m4trixtm.sublite.features.show.entity.Show

interface ShowRepository {

    fun getPopularFeatures(
        languages: String? = null,
        type: String? = null,
        onSuccess: () -> Unit,
        onError: (message: String) -> Unit
    ): Flow<ApiResponse<Show>>
}