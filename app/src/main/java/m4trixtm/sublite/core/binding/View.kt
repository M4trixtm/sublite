package m4trixtm.sublite.core.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.xwray.groupie.GroupieAdapter

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