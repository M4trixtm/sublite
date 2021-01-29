package m4trixtm.sublite.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.haroldadmin.cnradapter.NetworkResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import m4trixtm.sublite.MainCoroutinesRule
import m4trixtm.sublite.SubtitleMocks
import m4trixtm.sublite.core.platform.model.NetworkError
import m4trixtm.sublite.core.platform.repository.BaseRepository
import m4trixtm.sublite.features.subtitle.SubtitleService
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class BaseRepositoryTest {

    private val service: SubtitleService = mock()
    private lateinit var repository: BaseRepository

    private lateinit var onSuccess: () -> Unit
    private lateinit var onError: (message: String) -> Unit

    @get:Rule
    @ExperimentalCoroutinesApi
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutor = InstantTaskExecutorRule()

    @Before
    fun setup() {
        repository = object : BaseRepository() {}
        onSuccess = mock()
        onError = mock()
    }

    @Test
    fun `Network request function success`() = runBlocking {
        val fileId = 12345
        val excepted =
            NetworkResponse.Success(body = SubtitleMocks.downloadLinkResponse, code = 200)

        whenever(service.getDownloadLink(fileId = fileId)).thenReturn(excepted)

        repository.networkRequest(
            request = { service.getDownloadLink(fileId) },
            onSuccess = { onSuccess() },
            onError = { }
        ).collect {
            verify(onSuccess).invoke()
            verify(onError, never()).invoke("")
        }
    }

    @Test
    fun `Network request function Network error`() = runBlocking {
        val fileId = 12345
        val excepted =
            NetworkResponse.NetworkError(IOException(""))

        whenever(service.getDownloadLink(fileId = fileId)).thenReturn(excepted)

        repository.networkRequest(
            request = { service.getDownloadLink(fileId) },
            onSuccess = { onSuccess() },
            onError = { onError("") }
        ).firstOrNull()

        verify(onSuccess, never()).invoke()
        verify(onError).invoke("")
    }

    @Test
    fun `Network request function Server error`() = runBlocking {
        val fileId = 12345
        val excepted =
            NetworkResponse.ServerError<NetworkError>(body = null, code = 404)

        whenever(service.getDownloadLink(fileId = fileId)).thenReturn(excepted)

        repository.networkRequest(
            request = { service.getDownloadLink(fileId) },
            onSuccess = { onSuccess() },
            onError = { onError("") }
        ).firstOrNull()

        verify(onSuccess, never()).invoke()
        verify(onError).invoke("")
    }

    @Test
    fun `Network request function Unknown error`() = runBlocking {
        val fileId = 12345
        val excepted =
            NetworkResponse.UnknownError(Throwable("Unknown Error"))

        whenever(service.getDownloadLink(fileId = fileId)).thenReturn(excepted)

        repository.networkRequest(
            request = { service.getDownloadLink(fileId) },
            onSuccess = { onSuccess() },
            onError = { onError("") }
        ).firstOrNull()

        verify(onSuccess, never()).invoke()
        verify(onError).invoke("")
    }
}