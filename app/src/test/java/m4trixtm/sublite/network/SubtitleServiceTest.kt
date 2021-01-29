package m4trixtm.sublite.network

import com.haroldadmin.cnradapter.NetworkResponse
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import m4trixtm.sublite.*
import m4trixtm.sublite.features.subtitle.SubtitleService
import okio.IOException
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SubtitleServiceTest : ServiceTest<SubtitleService>() {

    private lateinit var service: SubtitleService

    @get:Rule
    @ExperimentalCoroutinesApi
    var coroutinesRule = MainCoroutinesRule()

    @Before
    fun initService() {
        service = createService(SubtitleService::class.java)
    }

    @Test
    @Throws(IOException::class)
    fun `Subtitle search response parsing`() = runBlocking {
        enqueueResponse("search/response.json")
        val expected = SubtitleMocks.searchResponse
        val actual = service.search("interstellar")
        mockWebServer.takeRequest()

        (actual as NetworkResponse.Success).body.assertThat {
            totalPages shouldBe expected.totalPages
            totalCount shouldBe expected.totalCount
            page shouldBe expected.page

            data.assertForeach { dataPosition ->
                val subtitle = expected.data[dataPosition]

                id shouldBe subtitle.id
                type shouldBe subtitle.type

                attributes.assertThat {
                    val attributes = subtitle.attributes

                    id shouldBe attributes.id
                    language shouldBe attributes.language
                    downloadCount shouldBe attributes.downloadCount
                    newDownloadCount shouldBe attributes.newDownloadCount
                    isHD shouldBe attributes.isHD
                    format shouldBe attributes.format
                    fps shouldBe attributes.fps
                    votes shouldBe attributes.votes
                    points shouldBe attributes.points
                    ratings shouldBe attributes.ratings
                    fromTrusted shouldBe attributes.fromTrusted
                    foreignPartsOnly shouldBe attributes.foreignPartsOnly
                    autoTranslation shouldBe attributes.autoTranslation
                    aiTranslated shouldBe attributes.aiTranslated
                    uploadDate shouldBe attributes.uploadDate
                    release shouldBe attributes.release

                    details.assertThat {
                        val details = attributes.details

                        id shouldBe details.id
                        type shouldBe details.type
                        year shouldBe details.year
                        title shouldBe details.title
                        movieName shouldBe details.movieName
                        imdbId shouldBe details.imdbId
                        tmdbId shouldBe details.tmdbId
                    }

                    files.assertForeach { filesPosition ->
                        val file = attributes.files[filesPosition]

                        id shouldBe file.id
                        cdNumber shouldBe file.cdNumber
                        fileName shouldBe file.fileName
                    }
                }
            }
        }
    }

    @Test
    @Throws(IOException::class)
    fun `Download subtitle link parsing`() = runBlocking {
        enqueueResponse("download/response.json")
        val mocked = SubtitleMocks.downloadLinkResponse
        val actual = service.getDownloadLink(fileId = 12345)

        (actual as NetworkResponse.Success).body.assertThat {
            link shouldBe mocked.link
            fileName shouldBe mocked.fileName
            requests shouldBe mocked.requests
            allowed shouldBe mocked.allowed
            remaining shouldBe mocked.remaining
            message shouldBe mocked.message
        }
    }
}