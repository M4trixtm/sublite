package m4trixtm.sublite.features.show.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.databinding.BindableItem
import m4trixtm.sublite.R
import m4trixtm.sublite.databinding.ItemHomeShowBinding
import m4trixtm.sublite.features.show.entity.Show

class HomeShowItem(val show: Show, val onItemClicked: (item: Show) -> Unit) :
    BindableItem<ItemHomeShowBinding>() {

    override fun bind(viewBinding: ItemHomeShowBinding, position: Int) {
        viewBinding.show = show
        viewBinding.root.setOnClickListener { onItemClicked(show) }
    }

    override fun getLayout(): Int = R.layout.item_home_show
}

@BindingAdapter("android:bindHomeShowList")
fun bindHomeShowList(view: RecyclerView, list: List<HomeShowItem>?) {
    list?.let {
        (view.adapter as GroupieAdapter).apply {
            updateAsync(it)
        }
    }
}