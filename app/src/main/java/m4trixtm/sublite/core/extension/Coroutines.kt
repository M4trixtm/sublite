package m4trixtm.sublite.core.extension

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

inline fun <T, R> Channel<T>.transformToFlow(
    crossinline transform: suspend FlowCollector<R>.(value: T) -> Unit
): Flow<R> = flow {
    this@transformToFlow.consumeAsFlow().collect { value ->
        return@collect transform(value)
    }
}