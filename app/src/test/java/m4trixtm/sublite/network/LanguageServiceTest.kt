package m4trixtm.sublite.network

import com.haroldadmin.cnradapter.NetworkResponse
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import m4trixtm.sublite.LanguageMocks
import m4trixtm.sublite.MainCoroutinesRule
import m4trixtm.sublite.assertForeach
import m4trixtm.sublite.assertThat
import m4trixtm.sublite.features.language.LanguageService
import okio.IOException
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LanguageServiceTest : ServiceTest<LanguageService>() {

    private lateinit var service: LanguageService

    @get:Rule
    @ExperimentalCoroutinesApi
    var coroutinesRule = MainCoroutinesRule()

    @Before
    fun initService() {
        service = createService(LanguageService::class.java)
    }

    @Test
    @Throws(IOException::class)
    fun `Languages parsing`() = runBlocking {
        enqueueResponse("language/response.json")
        val mocked = LanguageMocks.languageResponse
        val actual = service.fetchLanguages()

        (actual as NetworkResponse.Success).body.assertThat {
            languages.assertForeach { position ->
                val language = mocked.languages[position]
                name shouldBe language.name
                code shouldBe language.code
            }
        }
    }
}