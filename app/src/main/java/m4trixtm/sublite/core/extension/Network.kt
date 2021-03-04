package m4trixtm.sublite.core.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.fragment.app.Fragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import m4trixtm.sublite.core.platform.fragment.BaseFragment

/**
 * Receive network stats as [Flow]
 */
@ExperimentalCoroutinesApi
fun Context.networkStateFlow(): Flow<Boolean> = callbackFlow {
    val callBack = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            offer(true)
        }

        override fun onLost(network: Network) {
            offer(false)
        }
    }

    val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    manager.registerNetworkCallback(
        NetworkRequest.Builder().run {
            addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            build()
        },
        callBack
    )

    awaitClose {
        manager.unregisterNetworkCallback(callBack)
    }
}

@ExperimentalCoroutinesApi
val Fragment.networkStateFlow: Flow<Boolean>
    get() = requireContext().networkStateFlow()

@ExperimentalCoroutinesApi
inline fun BaseFragment<*>.onNetworkStateChanged(crossinline onChanged: (connected: Boolean) -> Unit) =
    networkStateFlow.collectOnLifecycleScope(onChanged)