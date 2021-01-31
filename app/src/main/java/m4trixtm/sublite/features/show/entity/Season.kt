package m4trixtm.sublite.features.show.entity


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import m4trixtm.sublite.core.platform.entity.BaseEntity

@Keep
data class Season(
    @SerializedName("episodes")
    val episodes: List<Episode>,
    /**
     * This is season number
     */
    @SerializedName("season_number")
    override val id: Int
) : BaseEntity<Int>