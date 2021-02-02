package m4trixtm.sublite.features.common.repository

import kotlinx.coroutines.flow.Flow
import m4trixtm.sublite.core.platform.repository.BaseRepository
import m4trixtm.sublite.features.ApiResponse
import m4trixtm.sublite.features.subtitle.SubtitleService
import m4trixtm.sublite.features.subtitle.entity.Subtitle
import m4trixtm.sublite.features.subtitle.entity.SubtitleDownloadLink

class SubtitleRepositoryImpl(private val service: SubtitleService) :
    BaseRepository(),
    SubtitleRepository {

    override fun search(
        parameters: Map<String, String>,
        onSuccess: () -> Unit,
        onError: (message: String) -> Unit
    ): Flow<ApiResponse<Subtitle>> = networkRequest(
        request = { service.search(parameters) },
        onSuccess = { onSuccess() },
        onError = { onError(it) }
    )

    override fun getDownloadLink(
        subtitleId: Int,
        onSuccess: () -> Unit,
        onError: (message: String) -> Unit
    ): Flow<SubtitleDownloadLink> = networkRequest(
        request = { service.getDownloadLink(subtitleId) },
        onSuccess = { onSuccess() },
        onError = { onError(it) }
    )
}