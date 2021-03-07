package m4trixtm.sublite.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.haroldadmin.cnradapter.NetworkResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import m4trixtm.sublite.FeatureMocks
import m4trixtm.sublite.MainCoroutinesRule
import m4trixtm.sublite.features.common.repository.ShowRepositoryImpl
import m4trixtm.sublite.features.show.ShowService
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ShowRepositoryTest {
    private val service: ShowService = mock()
    private lateinit var repository: ShowRepositoryImpl

    @get:Rule
    @ExperimentalCoroutinesApi
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutor = InstantTaskExecutorRule()

    @Before
    fun initialization() {
        repository = ShowRepositoryImpl(service)
    }

    @Test
    fun `get popular shows`() = runBlocking {
        val apiResponse =
            NetworkResponse.Success(body = FeatureMocks.featureApiResponse, code = 200)

        whenever(service.getPopularFeatures()).thenReturn(apiResponse)
        repository.getPopularFeatures(onSuccess = {}, onError = {}).first()

        verify(service).getPopularFeatures()

        Unit
    }
}