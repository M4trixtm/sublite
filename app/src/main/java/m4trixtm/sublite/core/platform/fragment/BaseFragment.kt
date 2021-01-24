package m4trixtm.sublite.core.platform.fragment

import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding>(@LayoutRes private val layoutRes: Int) : Fragment(layoutRes) {

    fun <T : ViewBinding> BaseFragment<T>.viewBinding(viewBindingFactory: (View) -> T) =
        FragmentViewBindingDelegate(this, viewBindingFactory)
}