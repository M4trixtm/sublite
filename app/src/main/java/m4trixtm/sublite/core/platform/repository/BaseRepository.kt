package m4trixtm.sublite.core.platform.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import m4trixtm.sublite.core.log.appLog
import java.io.IOException

abstract class BaseRepository {

    fun <T> networkRequest(
        request: suspend () -> T,
        onSuccess: () -> Unit,
        onError: (message: String) -> Unit
    ): Flow<T> = flow {
        try {
            val response = request()
            onSuccess()
            emit(response)

        } catch (e: IOException) {
            e.message?.let {
                onError(it)
                appLog(message = { it })
            }
        }
    }
}