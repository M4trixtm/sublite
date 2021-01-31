package m4trixtm.sublite.features.show.entity


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import m4trixtm.sublite.core.platform.entity.BaseEntity

@Keep
data class Show(
    @SerializedName("attributes")
    val attributes: ShowAttribute,
    @SerializedName("id")
    override val id: String,
    @SerializedName("type")
    val type: String
) : BaseEntity<String>