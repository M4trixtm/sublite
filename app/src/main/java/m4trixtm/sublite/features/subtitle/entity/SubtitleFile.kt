package m4trixtm.sublite.features.subtitle.entity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import m4trixtm.sublite.core.platform.entity.BaseEntity

@Keep
data class SubtitleFile(
    @SerializedName("file_id")
    override val id: Int,
    @SerializedName("file_name")
    val fileName: String,
    @SerializedName("cd_number")
    val cdNumber: Int,
) : BaseEntity<Int>