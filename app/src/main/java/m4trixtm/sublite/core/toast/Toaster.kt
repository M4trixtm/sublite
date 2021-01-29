package m4trixtm.sublite.core.toast

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.longToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Fragment.shortToast(message: String) = requireContext().shortToast(message)

fun Fragment.longToast(message: String) = requireContext().longToast(message)