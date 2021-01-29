package m4trixtm.sublite.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.haroldadmin.cnradapter.NetworkResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import m4trixtm.sublite.MainCoroutinesRule
import m4trixtm.sublite.SubtitleMocks
import m4trixtm.sublite.features.subtitle.SubtitleRepositoryImpl
import m4trixtm.sublite.features.subtitle.SubtitleService
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SubliteRepositoryTest {

    private val service: SubtitleService = mock()
    private lateinit var repository: SubtitleRepositoryImpl

    @get:Rule
    @ExperimentalCoroutinesApi
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutor = InstantTaskExecutorRule()

    @Before
    fun setup() {
        repository = SubtitleRepositoryImpl(service)
    }

    @Test
    fun `Search subtitles`() = runBlocking {
        val searchQuery = "interstellar"
        val apiResponse = NetworkResponse.Success(body = SubtitleMocks.searchResponse, code = 200)

        whenever(service.search(searchQuery)).thenReturn(apiResponse)
        repository.search(searchQuery, {}, {}).first()
        verify(service).search(searchQuery)
        Unit
    }

    @Test
    fun `Get download link`() = runBlocking {
        val subtitleId = 12345
        val apiResponse = NetworkResponse.Success(body = SubtitleMocks.downloadLinkResponse, code = 200)

        whenever(service.getDownloadLink(subtitleId)).thenReturn(apiResponse)
        repository.getDownloadLink(subtitleId, {}, {}).first()
        verify(service).getDownloadLink(subtitleId)

        Unit
    }

    @Test
    fun `Get Most downloaded`() = runBlocking {
        val apiResponse = NetworkResponse.Success(body = SubtitleMocks.searchResponse, code = 200)

        whenever(service.getMostDownloaded()).thenReturn(apiResponse)
        repository.getMostDownloaded(onSuccess = {}, onError = {}).first()

        verify(service).getMostDownloaded()

        Unit
    }

    @Test
    fun `Get latest Subtitles`() = runBlocking {
        val apiResponse = NetworkResponse.Success(body = SubtitleMocks.searchResponse, code = 200)

        whenever(service.getLatestSubtitles()).thenReturn(apiResponse)
        repository.getLatestSubtitles(onSuccess = {}, onError = {}).first()

        verify(service).getLatestSubtitles()

        Unit
    }
}