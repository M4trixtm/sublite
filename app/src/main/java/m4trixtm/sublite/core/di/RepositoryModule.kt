package m4trixtm.sublite.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import m4trixtm.sublite.features.common.repository.SubtitleRepository
import m4trixtm.sublite.features.common.repository.SubtitleRepositoryImpl
import m4trixtm.sublite.features.language.LanguageRepository
import m4trixtm.sublite.features.language.LanguageRepositoryImpl
import m4trixtm.sublite.features.language.LanguageService
import m4trixtm.sublite.features.subtitle.SubtitleService

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideLanguageRepository(service: LanguageService): LanguageRepository =
        LanguageRepositoryImpl(service)

    @Provides
    @ViewModelScoped
    fun provideSubtitleRepository(service: SubtitleService): SubtitleRepository =
        SubtitleRepositoryImpl(service)
}