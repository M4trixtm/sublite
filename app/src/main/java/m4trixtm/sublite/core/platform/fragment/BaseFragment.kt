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
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
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