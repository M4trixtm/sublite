package m4trixtm.sublite.network

import com.haroldadmin.cnradapter.NetworkResponse
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
            totalPages equals expected.totalPages
            totalCount equals expected.totalCount
            page equals expected.page

            data.assertForeach { dataPosition ->
                val subtitle = expected.data[dataPosition]

                id equals subtitle.id
                type equals subtitle.type

                attributes.assertThat {
                    val attributes = subtitle.attributes

                    id equals attributes.id
                    language equals attributes.language
                    downloadCount equals attributes.downloadCount
                    newDownloadCount equals attributes.newDownloadCount
                    isHD equals attributes.isHD
                    format equals attributes.format
                    fps equals attributes.fps
                    votes equals attributes.votes
                    points equals attributes.points
                    ratings equals attributes.ratings
                    fromTrusted equals attributes.fromTrusted
                    foreignPartsOnly equals attributes.foreignPartsOnly
                    autoTranslation equals attributes.autoTranslation
                    aiTranslated equals attributes.aiTranslated
                    uploadDate equals attributes.uploadDate
                    release equals attributes.release

                    details.assertThat {
                        val details = attributes.details

                        id equals details.id
                        type equals details.type
                        year equals details.year
                        title equals details.title
                        movieName equals details.movieName
                        imdbId equals details.imdbId
                        tmdbId equals details.tmdbId
                    }

                    files.assertForeach { filesPosition ->
                        val file = attributes.files[filesPosition]

                        id equals file.id
                        cdNumber equals file.cdNumber
                        fileName equals file.fileName
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
            link equals mocked.link
            fileName equals mocked.fileName
            requests equals mocked.requests
            allowed equals mocked.allowed
            remaining equals mocked.remaining
            message equals mocked.message
        }
    }
}