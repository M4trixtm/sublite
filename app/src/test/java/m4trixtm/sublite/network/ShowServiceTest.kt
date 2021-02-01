package m4trixtm.sublite.network

import com.haroldadmin.cnradapter.NetworkResponse
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import m4trixtm.sublite.FeatureMocks
import m4trixtm.sublite.MainCoroutinesRule
import m4trixtm.sublite.assertForeach
import m4trixtm.sublite.assertThat
import m4trixtm.sublite.features.show.ShowService
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class ShowServiceTest : ServiceTest<ShowService>() {

    private lateinit var service: ShowService

    @get:Rule
    @ExperimentalCoroutinesApi
    var coroutinesRule = MainCoroutinesRule()

    @Before
    fun initService() {
        service = createService(ShowService::class.java)
    }

    @Test
    @Throws(IOException::class)
    fun `Popular feature is parsing`() = runBlocking {
        enqueueResponse("popularFeatures/popularFeatures.json")

        val expected = FeatureMocks.featureApiResponse
        val actual = service.getPopularFeatures()

        mockWebServer.takeRequest()

        (actual as NetworkResponse.Success).body.assertThat {
            data.assertForeach { position ->
                val feature = expected.data[position]

                id shouldBe feature.id
                type shouldBe feature.type

                attributes.assertThat {
                    val show = feature.attributes

                    title shouldBe show.title
                    originalTitle shouldBe show.originalTitle
                    imdbId shouldBe show.imdbId
                    tmdbId shouldBe show.tmdbId
                    id shouldBe show.id
                    year shouldBe show.year
                    titleAka.assertForeach { position ->
                        val aka = show.titleAka[position]
                        this shouldBe aka
                    }
                    subtitlesCounts shouldBe show.subtitlesCounts
                    url shouldBe show.url
                    imgUrl shouldBe show.imgUrl
                }
            }
        }
    }
}