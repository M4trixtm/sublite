package m4trixtm.sublite.features.show.entity


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import m4trixtm.sublite.core.platform.entity.BaseEntity

@Keep
data class Show(
    @SerializedName("episode_number")
    val episodeNumber: Int?,
    /**
     * This is featureId
     */
    @SerializedName("feature_id")
    override val id: String,
    @SerializedName("feature_type")
    val featureType: String,
    @SerializedName("imdb_id")
    val imdbId: Int,
    @SerializedName("img_url")
    val imgUrl: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("parent_imdb_id")
    val parentImdbId: Int,
    @SerializedName("parent_title")
    val parentTitle: String,
    @SerializedName("season_number")
    val seasonNumber: Int,
    @SerializedName("seasons")
    val seasons: List<Season>,
    @SerializedName("seasons_count")
    val seasonsCount: Int,
    @SerializedName("subtitles_count")
    val subtitlesCount: Int,
    @SerializedName("subtitles_counts")
    val subtitlesCounts: SubtitlesCounts,
    @SerializedName("title")
    val title: String,
    @SerializedName("title_aka")
    val titleAka: List<String>,
    @SerializedName("tmdb_id")
    val tmdbId: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("year")
    val year: String
) : BaseEntity<String>