package m4trixtm.sublite.features.subtitle

import m4trixtm.sublite.features.ApiResponse
import m4trixtm.sublite.features.subtitle.entity.Subtitle
import m4trixtm.sublite.features.subtitle.entity.SubtitleDownloadLink
import retrofit2.http.*

interface SubtitleService {

    @GET("subtitles")
    suspend fun search(@Query("query") query: String): ApiResponse<Subtitle>

    @FormUrlEncoded
    @POST("download")
    suspend fun getDownloadLink(@Field("file_id") fileId: Int): SubtitleDownloadLink
}