package m4trixtm.sublite

import m4trixtm.sublite.features.language.Language
import m4trixtm.sublite.features.language.LanguageResponse

object LanguageMocks {

    val languageResponse: LanguageResponse
        get() = LanguageResponse(
            languages = listOf(
                Language(
                    name = "Persian",
                    code = "fa"
                ),
                Language(
                    name = "Albanian",
                    code = "sq"
                ),
                Language(
                    name = "Northern Sami",
                    code = "se"
                ),
            )
        )
}