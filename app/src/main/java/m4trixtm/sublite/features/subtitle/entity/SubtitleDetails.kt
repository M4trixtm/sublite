package m4trixtm.sublite.features.subtitle.entity

import com.google.gson.annotations.SerializedName

data class SubtitleDetails(
    @SerializedName("feature_id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("movie_name")
    val movieName: String,
    @SerializedName("feature_type")
    val type: String,
    @SerializedName("year")
    val year: Int,
    @SerializedName("imdb_id")
    val imdbId: Int,
    @SerializedName("tmdb_id")
    val tmdbId: Int
)