package m4trixtm.sublite.features.language

import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Keep
data class LanguageResponse(
    @SerializedName("data")
    val languages: List<Language>
)

@Keep
data class Language(
    @SerializedName("language_name")
    val name: String,
    @SerializedName("language_code")
    val code: String,
    @Expose
    var isSelected: Boolean = false
)