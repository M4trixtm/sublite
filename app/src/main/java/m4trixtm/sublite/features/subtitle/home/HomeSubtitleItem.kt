package m4trixtm.sublite.features.subtitle.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.databinding.BindableItem
import m4trixtm.sublite.R
import m4trixtm.sublite.databinding.ItemHomeSubtitleBinding
import m4trixtm.sublite.features.subtitle.entity.Subtitle

class HomeSubtitleItem(val subtitle: Subtitle, val onItemClicked: (item: Subtitle) -> Unit) :
    BindableItem<ItemHomeSubtitleBinding>() {

    override fun bind(viewBinding: ItemHomeSubtitleBinding, position: Int) {
        viewBinding.subtitle = subtitle
        viewBinding.root.setOnClickListener { onItemClicked(subtitle) }
    }

    override fun getLayout(): Int = R.layout.item_home_subtitle
}

@BindingAdapter("android:bindHomeSubtitleList")
fun bindHomeSubtitleList(view: RecyclerView, list: List<HomeSubtitleItem>?) {
    list?.let {
        (view.adapter as GroupieAdapter).apply {
            this.clear()
            addAll(it)
        }
    }
}