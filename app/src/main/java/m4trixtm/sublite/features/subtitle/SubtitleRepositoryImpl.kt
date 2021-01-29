package m4trixtm.sublite.features.subtitle

import kotlinx.coroutines.flow.Flow
import m4trixtm.sublite.core.platform.repository.BaseRepository
import m4trixtm.sublite.features.ApiResponse
import m4trixtm.sublite.features.subtitle.entity.Subtitle
import m4trixtm.sublite.features.subtitle.entity.SubtitleDownloadLink

class SubtitleRepositoryImpl(private val service: SubtitleService) :
    BaseRepository(),
    SubtitleRepository {

    override fun search(
        query: String,
        onSuccess: () -> Unit,
        onError: (message: String) -> Unit
    ): Flow<ApiResponse<Subtitle>> = networkRequest(
        request = { service.search(query) },
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

    override fun getMostDownloaded(
        languages: String?,
        type: String?,
        onSuccess: () -> Unit,
        onError: (message: String) -> Unit
    ): Flow<ApiResponse<Subtitle>> = networkRequest(
        request = { service.getMostDownloaded(languages, type) },
        onSuccess = { onSuccess() },
        onError = { onError(it) }
    )

    override fun getLatestSubtitles(
        languages: String?,
        type: String?,
        onSuccess: () -> Unit,
        onError: (message: String) -> Unit
    ): Flow<ApiResponse<Subtitle>> = networkRequest(
        request = { service.getLatestSubtitles(languages, type) },
        onSuccess = { onSuccess() },
        onError = { onError(it) }
    )
}