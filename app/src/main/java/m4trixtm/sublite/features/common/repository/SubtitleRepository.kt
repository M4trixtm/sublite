package m4trixtm.sublite.features.common.repository

import kotlinx.coroutines.flow.Flow
import m4trixtm.sublite.features.ApiResponse
import m4trixtm.sublite.features.subtitle.entity.Subtitle
import m4trixtm.sublite.features.subtitle.entity.SubtitleDownloadLink

interface SubtitleRepository {

    fun search(
        query: String,
        onSuccess: () -> Unit,
        onError: (message: String) -> Unit
    ): Flow<ApiResponse<Subtitle>>

    fun getDownloadLink(
        subtitleId: Int,
        onSuccess: () -> Unit,
        onError: (message: String) -> Unit
    ): Flow<SubtitleDownloadLink>

    /**
     * Get the most downloaded subtitles from past 30 days on OpenSubtitles
     * @see [SubtitleService]
     * @param languages Optional [String] language codes comma separated like: en,fa or all
     * @param type Optional [String] , could be one of these values: movie or tvshow
     * @return [Flow] including [ApiResponse] that consists of [Subtitle]
     */
    fun getMostDownloaded(
        languages: String? = null,
        type: String? = null,
        onSuccess: () -> Unit,
        onError: (message: String) -> Unit
    ): Flow<ApiResponse<Subtitle>>

    /**
     * Lists of 60 latest uploaded subtitles
     * @see <a href="https://opensubtitles.stoplight.io/docs/opensubtitles-api/open_api.json/paths/~1api~1v1~1discover~1latest/get">API document</a>
     * @param languages Optional [String] language codes comma separated like: en,fa or all
     * @param type Optional [String] , could be one of these values: movie or tvshow
     * @return [Flow] including [ApiResponse] that consists of [Subtitle]
     */
    fun getLatestSubtitles(
        languages: String? = null,
        type: String? = null,
        onSuccess: () -> Unit,
        onError: (message: String) -> Unit
    ): Flow<ApiResponse<Subtitle>>
}