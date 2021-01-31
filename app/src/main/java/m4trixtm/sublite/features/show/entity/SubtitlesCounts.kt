package m4trixtm.sublite.features.show.entity


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class SubtitlesCounts(
    @SerializedName("ar")
    val ar: Int,
    @SerializedName("bg")
    val bg: Int,
    @SerializedName("bs")
    val bs: Int,
    @SerializedName("cs")
    val cs: Int,
    @SerializedName("el")
    val el: Int,
    @SerializedName("en")
    val en: Int,
    @SerializedName("es")
    val es: Int,
    @SerializedName("et")
    val et: Int,
    @SerializedName("fa")
    val fa: Int,
    @SerializedName("fi")
    val fi: Int,
    @SerializedName("fr")
    val fr: Int,
    @SerializedName("he")
    val he: Int,
    @SerializedName("hr")
    val hr: Int,
    @SerializedName("hu")
    val hu: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("ja")
    val ja: Int,
    @SerializedName("nl")
    val nl: Int,
    @SerializedName("pl")
    val pl: Int,
    @SerializedName("pt-BR")
    val ptBR: Int,
    @SerializedName("pt-PT")
    val ptPT: Int,
    @SerializedName("ro")
    val ro: Int,
    @SerializedName("ru")
    val ru: Int,
    @SerializedName("sk")
    val sk: Int,
    @SerializedName("sl")
    val sl: Int,
    @SerializedName("sr")
    val sr: Int,
    @SerializedName("th")
    val th: Int,
    @SerializedName("tr")
    val tr: Int,
    @SerializedName("vi")
    val vi: Int
)