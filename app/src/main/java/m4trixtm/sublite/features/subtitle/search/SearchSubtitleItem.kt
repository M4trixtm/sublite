package m4trixtm.sublite.features.subtitle.search

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.databinding.BindableItem
import m4trixtm.sublite.R
import m4trixtm.sublite.databinding.ItemSubtitleSearchBinding
import m4trixtm.sublite.features.subtitle.entity.Subtitle

class SearchSubtitleItem(
    private val subtitle: Subtitle,
    private val onItemClicked: (item: Subtitle) -> Unit
) :
    BindableItem<ItemSubtitleSearchBinding>() {

    override fun bind(viewBinding: ItemSubtitleSearchBinding, position: Int) {
        viewBinding.subtitle = subtitle
        viewBinding.root.setOnClickListener { onItemClicked(subtitle) }
    }

    override fun getLayout(): Int = R.layout.item_subtitle_search
}

@BindingAdapter("android:bindSearchSubtitleList")
fun bindSearchSubtitleList(view: RecyclerView, list: List<SearchSubtitleItem>?) {
    list?.let {
        (view.adapter as GroupieAdapter).apply {
            this.clear()
            addAll(it)
        }
    }
}