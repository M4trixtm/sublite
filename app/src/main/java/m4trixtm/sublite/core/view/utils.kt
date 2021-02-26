package m4trixtm.sublite.core.view

import android.view.View
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airbnb.lottie.LottieAnimationView

inline fun View.isLottieAnimationView(view: (LottieAnimationView) -> Unit) {
    if (this is LottieAnimationView) {
        view(this)
    }
}

inline fun View.isTextView(view: (TextView) -> Unit) {
    if (this is TextView) {
        view(this)
    }
}

inline fun View.isSwipeRefreshLayout(view: (SwipeRefreshLayout) -> Unit) {
    if (this is SwipeRefreshLayout) {
        view(this)
    }
}