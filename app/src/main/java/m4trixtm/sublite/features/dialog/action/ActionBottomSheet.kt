package m4trixtm.sublite.features.dialog.action

import android.content.Context
import androidx.fragment.app.Fragment
import com.xwray.groupie.Group
import com.xwray.groupie.GroupieAdapter
import m4trixtm.sublite.R
import m4trixtm.sublite.databinding.BottomSheetActionBinding
import m4trixtm.sublite.features.dialog.BaseBottomSheetDialog

class ActionBottomSheet(context: Context) :
    BaseBottomSheetDialog<BottomSheetActionBinding>(context, R.layout.bottom_sheet_action) {

    var items: List<Group> = listOf()
        set(value) {
            field = value
            binding.adapter = GroupieAdapter().apply {
                updateAsync(value)
            }
        }
}

inline fun Context.actionBottomSheet(func: ActionBottomSheet.() -> Unit) =
    ActionBottomSheet(this).apply { func() }

inline fun Fragment.actionBottomSheet(func: ActionBottomSheet.() -> Unit) =
    ActionBottomSheet(requireContext()).apply { func() }