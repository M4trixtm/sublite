package m4trixtm.sublite.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.haroldadmin.cnradapter.NetworkResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import m4trixtm.sublite.LanguageMocks
import m4trixtm.sublite.MainCoroutinesRule
import m4trixtm.sublite.features.language.LanguageRepositoryImpl
import m4trixtm.sublite.features.language.LanguageService
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LanguageRepositoryTest {

    private val service: LanguageService = mock()
    private lateinit var repository: LanguageRepositoryImpl

    @get:Rule
    @ExperimentalCoroutinesApi
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutor = InstantTaskExecutorRule()

    @Before
    fun setup() {
        repository = LanguageRepositoryImpl(service)
    }

    @Test
    fun `Fetch languages`() = runBlocking {
        val apiResponse = NetworkResponse.Success(body = LanguageMocks.languageResponse, code = 200)

        whenever(service.fetchLanguages()).thenReturn(apiResponse)
        repository.fetchLanguages({}, {}).collect {
            verify(service).fetchLanguages()
            it.languages shouldBe apiResponse.body.languages
        }
    }
}