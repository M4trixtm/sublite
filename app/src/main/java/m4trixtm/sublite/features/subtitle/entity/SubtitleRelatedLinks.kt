package m4trixtm.sublite.features.subtitle.entity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SubtitleRelatedLinks(
    @SerializedName("label")
    val label: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("img_url")
    val imageUrl: String?
)