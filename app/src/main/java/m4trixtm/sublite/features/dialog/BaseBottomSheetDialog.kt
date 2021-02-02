package m4trixtm.sublite.features.dialog

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import m4trixtm.sublite.R

abstract class BaseBottomSheetDialog<V : ViewDataBinding>(
    context: Context,
    @LayoutRes layout: Int
) : BottomSheetDialog(context, R.style.TransparentBottomSheetDialog) {

    val binding: V by lazy {
        DataBindingUtil.inflate(
            LayoutInflater.from(context), layout, null, false
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        setContentView(binding.root)
        window?.decorView?.viewTreeObserver?.addOnGlobalLayoutListener { adjustDialog() }
    }

    private fun adjustDialog() {
        val lp: WindowManager.LayoutParams? = window?.attributes
        val dm: DisplayMetrics = context.resources.displayMetrics
        lp?.width = dm.widthPixels
        lp?.height = dm.heightPixels
        window?.attributes = lp
    }
}
