package m4trixtm.sublite.core.platform.recyclerview

import androidx.recyclerview.widget.DiffUtil
import m4trixtm.sublite.core.platform.entity.BaseEntity

class DiffCallBack<T> : DiffUtil.ItemCallback<T>() where T : BaseEntity<*> {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.equals(newItem)
    }
}