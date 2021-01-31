package m4trixtm.sublite.features.language

import com.haroldadmin.cnradapter.NetworkResponse
import m4trixtm.sublite.core.platform.model.NetworkError
import retrofit2.http.GET

interface LanguageService {

    @GET("infos/languages")
    suspend fun fetchLanguages(): NetworkResponse<LanguageResponse, NetworkError>
}