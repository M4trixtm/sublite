package m4trixtm.sublite.core.di

import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import m4trixtm.sublite.features.common.AuthInterceptor
import m4trixtm.sublite.features.show.ShowService
import m4trixtm.sublite.features.subtitle.SubtitleService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    const val BASE_URL = "https://www.opensubtitles.com/api/v1/"

    @Provides
    @Singleton
    fun provideSubtitleService(retrofit: Retrofit): SubtitleService =
        retrofit.create(SubtitleService::class.java)

    @Provides
    @Singleton
    fun provideShowService(retrofit: Retrofit): ShowService =
        retrofit.create(ShowService::class.java)

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(AuthInterceptor())
            .build()
    }
}