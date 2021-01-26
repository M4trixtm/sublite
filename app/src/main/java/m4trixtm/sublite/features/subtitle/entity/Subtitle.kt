package m4trixtm.sublite.features.subtitle.entity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import m4trixtm.sublite.core.platform.entity.BaseEntity
import java.util.*

@Keep
data class Subtitle(
    @SerializedName("subtitle_id")
    override val id: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("download_count")
    val downloadCount: Long,
    @SerializedName("new_download_count")
    val newDownloadCount: Long,
    @SerializedName("hd")
    val isHD: Boolean,
    @SerializedName("format")
    val format: String?,
    @SerializedName("fps")
    val fps: Float,
    @SerializedName("votes")
    val votes: Int,
    @SerializedName("points")
    val points: Int,
    @SerializedName("ratings")
    val ratings: Float,
    @SerializedName("from_trusted")
    val fromTrusted: Boolean,
    @SerializedName("foreign_parts_only")
    val foreignPartsOnly: Boolean,
    @SerializedName("auto_translation")
    val autoTranslation: Boolean,
    @SerializedName("ai_translated")
    val aiTranslated: Boolean,
    @SerializedName("upload_date")
    val uploadDate: Date,
    @SerializedName("release")
    val release: String,
    @SerializedName("feature_details")
    val details: SubtitleDetails,
    @SerializedName("files")
    val files: List<SubtitleFile>
): BaseEntity<String>