package m4trixtm.sublite.features.language

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.databinding.BindableItem
import m4trixtm.sublite.R
import m4trixtm.sublite.databinding.ItemLanguageBinding

class LanguageItem(
    private val item: Language,
    private val onCheckedChanged: (language: Language) -> Unit
) : BindableItem<ItemLanguageBinding>() {

    override fun bind(viewBinding: ItemLanguageBinding, position: Int) = with(viewBinding) {
        root.setOnClickListener {
            isSelected.toggle()
        }
        isSelected.setOnCheckedChangeListener { _, isChecked ->
            item.isSelected = isChecked
            onCheckedChanged(item)
        }
        language = item
    }

    override fun getLayout(): Int = R.layout.item_language
}

@BindingAdapter("android:bindLanguagesList")
fun bindLanguagesList(view: RecyclerView, list: List<LanguageItem>?) {
    list?.let {
        (view.adapter as GroupieAdapter).apply {
            updateAsync(list)
        }
    }
}