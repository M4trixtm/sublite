package m4trixtm.sublite.features.subtitle

import com.haroldadmin.cnradapter.NetworkResponse
import m4trixtm.sublite.core.platform.model.NetworkError
import m4trixtm.sublite.features.ApiResponse
import m4trixtm.sublite.features.subtitle.entity.Subtitle
import m4trixtm.sublite.features.subtitle.entity.SubtitleDownloadLink
import retrofit2.http.*

interface SubtitleService {

    @GET("subtitles")
    suspend fun search(@QueryMap parameters: Map<String, String>): NetworkResponse<ApiResponse<Subtitle>, NetworkError>

    @FormUrlEncoded
    @POST("download")
    suspend fun getDownloadLink(@Field("file_id") fileId: Int): NetworkResponse<SubtitleDownloadLink, NetworkError>
}