package m4trixtm.sublite.features.subtitle.entity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class SubtitleDownloadLink(
    @SerializedName("link")
    val link: String,
    @SerializedName("file_name")
    val fileName: String,
    @SerializedName("allowed")
    val allowed: Int,
    @SerializedName("requests")
    val requests: Int,
    @SerializedName("remaining")
    val remaining: Int,
    @SerializedName("message")
    val message: String,
)