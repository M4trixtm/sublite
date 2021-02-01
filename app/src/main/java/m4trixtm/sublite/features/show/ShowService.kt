package m4trixtm.sublite.features.show

import com.haroldadmin.cnradapter.NetworkResponse
import m4trixtm.sublite.core.platform.model.NetworkError
import m4trixtm.sublite.features.common.ApiResponse
import m4trixtm.sublite.features.show.entity.Show
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * For APIs getting [] types
 */
interface ShowService {

    @GET("discover/popular")
    suspend fun getPopularFeatures(
        @Query("languages") languages: String? = null,
        @Query("type") type: String? = null
    ): NetworkResponse<ApiResponse<Show>, NetworkError>
}