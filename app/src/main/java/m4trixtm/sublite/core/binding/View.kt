package m4trixtm.sublite.core.binding

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.DecimalFormat

@BindingAdapter("android:gone")
fun gone(view: View, gone: Boolean) {
    view.visibility = if (gone) View.GONE else View.VISIBLE
}

@BindingAdapter("android:invisible")
fun invisible(view: View, invisible: Boolean) {
    view.visibility = if (invisible) View.INVISIBLE else View.VISIBLE
}

@BindingAdapter("android:adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("android:refreshing")
fun swipeRefreshLayoutRefreshing(view: SwipeRefreshLayout, isRefreshing: Boolean?) {
    view.isRefreshing = isRefreshing ?: false
}