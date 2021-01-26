package m4trixtm.sublite.features.subtitle.entity

import com.google.gson.annotations.SerializedName

data class SubtitleFile(
    @SerializedName("file_id")
    val id: Int,
    @SerializedName("file_name")
    val fileName: String,
    @SerializedName("cd_number")
    val cdNumber: Int,
)