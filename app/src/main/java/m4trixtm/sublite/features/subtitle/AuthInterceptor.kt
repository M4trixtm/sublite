package m4trixtm.sublite.features.subtitle

import m4trixtm.sublite.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()

        request = request.newBuilder()
            .addHeader(
                HEADER_API_KEY,
                BuildConfig.SUB_API_KEY
            )
            .build()

        return chain.proceed(request)
    }

    companion object {
        private const val HEADER_API_KEY = "Api-Key"
    }
}