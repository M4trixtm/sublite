package m4trixtm.sublite.features.show.entity


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import m4trixtm.sublite.core.platform.entity.BaseEntity

@Keep
data class Show(
    @SerializedName("episode_number")
    val episodeNumber: Int? = null,
    /**
     * This is featureId
     */
    @SerializedName("feature_id")
    override val id: String,
    @SerializedName("feature_type")
    val featureType: String? = null,
    @SerializedName("imdb_id")
    val imdbId: Long,
    @SerializedName("img_url")
    val imgUrl: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("parent_imdb_id")
    val parentImdbId: Long? = null,
    @SerializedName("parent_title")
    val parentTitle: String? = null,
    @SerializedName("season_number")
    val seasonNumber: Int? = null,
    @SerializedName("seasons")
    val seasons: List<Season>? = null,
    @SerializedName("seasons_count")
    val seasonsCount: Int? = null,
    @SerializedName("subtitles_count")
    val subtitlesCount: Int? = null,
    @SerializedName("subtitles_counts")
    val subtitlesCounts: SubtitlesCounts,
    @SerializedName("title")
    val title: String,
    @SerializedName("title_aka")
    val titleAka: List<String>,
    @SerializedName("tmdb_id")
    val tmdbId: Long,
    @SerializedName("url")
    val url: String,
    @SerializedName("year")
    val year: String
) : BaseEntity<String>