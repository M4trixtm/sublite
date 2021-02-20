package m4trixtm.sublite.features.language

import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

@BindingAdapter("android:bindLanguagesChipsList")
fun bindLanguagesChipsList(view: ChipGroup, list: List<Language>?) = with(view) {
    removeAllViews()
    list.orEmpty().forEach { addView(chip(it)) }
}

fun ChipGroup.chip(language: Language): Chip = Chip(context).apply {
    text = language.name
}