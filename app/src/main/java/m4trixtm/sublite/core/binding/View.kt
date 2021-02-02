package m4trixtm.sublite.core.binding

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.xwray.groupie.GroupieAdapter
import m4trixtm.sublite.core.extension.loadFromUrl
import m4trixtm.sublite.features.language.Language
import m4trixtm.sublite.features.subtitle.entity.SubtitleRelatedLinks

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

@BindingAdapter("android:loadSubtitleRelatedLinksImage")
fun loadSubtitleRelatedLinksImage(view: ImageView, links: List<SubtitleRelatedLinks>?) {
    var hasRelatedLinksAnyImage = false
    links?.let {
        it.forEach { relatedLink ->
            relatedLink.imageUrl?.let { imageUrl ->
                view.loadFromUrl(imageUrl)
                hasRelatedLinksAnyImage = true
            }
        }
        if (!hasRelatedLinksAnyImage || it.isEmpty())
            view.loadFromUrl("null")
    }
}

@BindingAdapter("android:bindSelectedLanguage")
fun bindSelectedLanguages(view: EditText, list: List<Language>?) {
    list?.let {
        view.setText(
            if (it.isNotEmpty())
                it.map { language ->
                    language.name
                }.toString()
            else ""
        )
    }
}