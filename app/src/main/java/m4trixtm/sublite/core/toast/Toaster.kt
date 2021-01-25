package m4trixtm.sublite.core.toast

import android.content.Context
import android.widget.Toast

infix fun Context.shortToast(message: () -> String) {
    Toast.makeText(this, message(), Toast.LENGTH_SHORT).show()
}

infix fun Context.longToast(message: () -> String) {
    Toast.makeText(this, message(), Toast.LENGTH_LONG).show()
}