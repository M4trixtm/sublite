package m4trixtm.sublite.features.subtitle

import com.haroldadmin.cnradapter.NetworkResponse
import m4trixtm.sublite.core.platform.model.NetworkError
import m4trixtm.sublite.features.common.PaginationApiResponse
import m4trixtm.sublite.features.subtitle.entity.Subtitle
import m4trixtm.sublite.features.subtitle.entity.SubtitleDownloadLink
import retrofit2.http.*

interface SubtitleService {

    @GET("subtitles")
    suspend fun search(@Query("query") query: String): NetworkResponse<PaginationApiResponse<Subtitle>, NetworkError>

    @FormUrlEncoded
    @POST("download")
    suspend fun getDownloadLink(@Field("file_id") fileId: Int): NetworkResponse<SubtitleDownloadLink, NetworkError>

    /**
     * Get the most downloaded subtitles from past 30 days on OpenSubtitles
     * @see <a href="https://opensubtitles.stoplight.io/docs/opensubtitles-api/open_api.json/paths/~1api~1v1~1discover~1most_downloaded/get">API document</a>
     * @param languages Optional [String] language codes comma separated like: en,fa or all
     * @param type Optional [String] , could be one of these values: movie or tvshow
     * @return [NetworkResponse] including [PaginationApiResponse] that consists of [Subtitle]
     */
    @GET("discover/most_downloaded")
    suspend fun getMostDownloaded(@Query("languages") languages: String? = null, @Query("type") type: String? = null): NetworkResponse<PaginationApiResponse<Subtitle>, NetworkError>

    /**
     * Lists of 60 latest uploaded subtitles
     * @see <a href="https://opensubtitles.stoplight.io/docs/opensubtitles-api/open_api.json/paths/~1api~1v1~1discover~1latest/get">API document</a>
     * @param languages Optional [String] language codes comma separated like: en,fa or all
     * @param type Optional [String] , could be one of these values: movie or tvshow
     * @return [NetworkResponse] including [PaginationApiResponse] that consists of [Subtitle]
     */
    @GET("discover/latest")
    suspend fun getLatestSubtitles(@Query("languages") languages: String? = null, @Query("type") type: String? = null): NetworkResponse<PaginationApiResponse<Subtitle>, NetworkError>
}