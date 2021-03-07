package m4trixtm.sublite.features.common

import com.google.gson.annotations.SerializedName

data class PaginationApiResponse<T>(
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("data")
    val data: List<Data<T>>
)

data class Data<T>(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("attributes")
    val attributes: T
)

data class ApiResponse<T>(
    @SerializedName("data")
    val data: List<Data<T>>
)