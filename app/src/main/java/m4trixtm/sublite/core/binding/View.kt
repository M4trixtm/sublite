package m4trixtm.sublite.core.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.skydoves.whatif.whatIf
import com.skydoves.whatif.whatIfNotNullAs
import com.xwray.groupie.GroupieAdapter
import m4trixtm.sublite.core.extension.loadFromUrl
import m4trixtm.sublite.core.state.UiState
import m4trixtm.sublite.core.view.isLottieAnimationView
import m4trixtm.sublite.core.view.isSwipeRefreshLayout
import m4trixtm.sublite.core.view.isTextView

@BindingAdapter("android:gone")
fun gone(view: View, gone: Boolean) {
    view.visibility = if (gone) View.GONE else View.VISIBLE
}

@BindingAdapter("android:invisible")
fun invisible(view: View, invisible: Boolean) {
    view.visibility = if (invisible) View.INVISIBLE else View.VISIBLE
}

@BindingAdapter("android:adapter")
fun bindAdapter(view: RecyclerView, adapter: GroupieAdapter) {
    view.adapter = adapter
}

@BindingAdapter("android:refreshing")
fun swipeRefreshLayoutRefreshing(view: SwipeRefreshLayout, isRefreshing: Boolean?) {
    view.isRefreshing = isRefreshing ?: false
}

@BindingAdapter("android:loadImageFromUrl")
fun loadImageFromUrl(view: ImageView, url: String?) {
    url?.let { view.loadFromUrl(it) }
}

@BindingAdapter("android:hideOnLoading")
fun View.hideOnLoading(state: UiState) {
    visibility = if (state is UiState.Loading) View.GONE else View.VISIBLE
}

@BindingAdapter("android:showOnLoading")
fun View.showOnLoading(state: UiState) {
    whatIf(
        state is UiState.Loading,
        {
            visibility = View.VISIBLE
            isLottieAnimationView { lottie ->
                if (!lottie.isAnimating) lottie.resumeAnimation()
            }

            isSwipeRefreshLayout { refreshLayout ->
                if (!refreshLayout.isRefreshing) refreshLayout.isRefreshing = true
            }
        },
        {
            visibility = View.GONE

            isLottieAnimationView { lottie ->
                if (lottie.isAnimating) lottie.pauseAnimation()
            }

            isSwipeRefreshLayout { refreshLayout ->
                if (!refreshLayout.isRefreshing) refreshLayout.isRefreshing = false
            }
        }
    )
}

@BindingAdapter("android:showOnError")
fun View.showOnError(state: UiState) {
    whatIf(
        state is UiState.Failure,
        {
            visibility = View.VISIBLE

            isLottieAnimationView { lottie ->
                if (!lottie.isAnimating) lottie.resumeAnimation()
            }

            isTextView { tv ->
                state.whatIfNotNullAs<UiState.Failure> { failure ->
                    tv.text = failure.message
                }
            }
        },
        {
            visibility = View.GONE

            isLottieAnimationView { lottie ->
                if (lottie.isAnimating) lottie.pauseAnimation()
            }
        }
    )
}

@BindingAdapter("android:hideOnError")
fun View.hideOnError(state: UiState) {
    whatIf(state is UiState.Failure) {
        visibility = View.GONE
        isLottieAnimationView { lottie ->
            if (lottie.isAnimating) lottie.pauseAnimation()
        }

        isSwipeRefreshLayout { refreshLayout ->
            if (refreshLayout.isRefreshing) refreshLayout.isRefreshing = false
        }
    }
}

@BindingAdapter("android:showOnSuccess")
fun View.showOnSuccess(state: UiState) {
    whatIf(state is UiState.Success) {
        visibility = View.VISIBLE
    }
}