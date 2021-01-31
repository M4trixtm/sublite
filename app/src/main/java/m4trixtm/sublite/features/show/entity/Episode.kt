package m4trixtm.sublite.features.show.entity


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import m4trixtm.sublite.core.platform.entity.BaseEntity

@Keep
data class Episode(
    @SerializedName("episode_number")
    val episodeNumber: Int,
    /**
     * This is feature id
     */
    @SerializedName("feature_id")
    override val id: Long,
    @SerializedName("feature_imdb_id")
    val featureImdbId: Long,
    @SerializedName("title")
    val title: String
) : BaseEntity<Long>