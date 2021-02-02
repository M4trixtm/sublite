package m4trixtm.sublite.core.extension

import com.google.android.material.bottomsheet.BottomSheetBehavior

fun BottomSheetBehavior<*>.toggleState() {
    state = when (state) {
        BottomSheetBehavior.STATE_COLLAPSED -> BottomSheetBehavior.STATE_EXPANDED
        BottomSheetBehavior.STATE_EXPANDED -> BottomSheetBehavior.STATE_COLLAPSED
        else -> state
    }
}