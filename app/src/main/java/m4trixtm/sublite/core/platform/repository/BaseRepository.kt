package m4trixtm.sublite.core.platform.repository

import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import m4trixtm.sublite.core.platform.model.NetworkError

abstract class BaseRepository {

    fun <T : Any> networkRequest(
        request: suspend () -> NetworkResponse<T, NetworkError>,
        onSuccess: () -> Unit,
        onError: (message: String) -> Unit
    ): Flow<T> = flow {

        when (val response = request()) {
            is NetworkResponse.Success -> {
                onSuccess()
                emit(response.body)
            }
            is NetworkResponse.NetworkError -> onError("Network Error")
            is NetworkResponse.ServerError -> onError("Server Error")
            is NetworkResponse.UnknownError -> onError("Unknown Error")
        }
    }
}