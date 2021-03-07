package m4trixtm.sublite.core.platform.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.skydoves.whatif.whatIfNotNullAs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import m4trixtm.sublite.core.extension.onNetworkStateChanged
import m4trixtm.sublite.core.platform.activity.BaseActivity

abstract class BaseFragment<B : ViewDataBinding>(@LayoutRes private val layoutRes: Int) :
    Fragment(layoutRes) {

    lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, layoutRes, container, false)
        return binding.root
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNetworkState()
    }

    /**
     * NOTE: Move this to [BaseFragment] if you want to observe in all fragments!
     */
    @ExperimentalCoroutinesApi
    private fun observeNetworkState() = onNetworkStateChanged { connected ->
        if (connected) {
            hideOfflineStatus()
            onNetworkAvailable()
        } else showOfflineStatus()
    }

    open fun onNetworkAvailable() {}

    @InternalCoroutinesApi
    inline fun <T> StateFlow<T>.collectOnLifecycleScope(crossinline collector: (T) -> Unit) {
        lifecycleScope.launchWhenCreated {
            collect { collector(it) }
        }
    }

    inline fun <T> Flow<T>.collectOnLifecycleScope(crossinline collector: (T) -> Unit) {
        lifecycleScope.launchWhenCreated {
            collect {
                collector(it)
            }
        }
    }

    fun showOfflineStatus() {
        requireActivity().whatIfNotNullAs<BaseActivity<*>> {
            it.showOfflineStatus()
        }
    }

    fun hideOfflineStatus() {
        requireActivity().whatIfNotNullAs<BaseActivity<*>> {
            it.hideOfflineStatus()
        }
    }
}