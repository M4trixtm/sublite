package m4trixtm.sublite.core.platform.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    inline fun <T> scope(crossinline block: () -> Flow<T>): StateFlow<T?> {
        return MutableStateFlow<T?>(null).apply {
                viewModelScope.launch {
                    emitAll(block())
                }
        }
    }
}