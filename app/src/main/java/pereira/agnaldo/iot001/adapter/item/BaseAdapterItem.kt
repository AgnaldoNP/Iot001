@file:Suppress("UNCHECKED_CAST", "PARAMETER_NAME_CHANGED_ON_OVERRIDE")

package pereira.agnaldo.iot001.adapter.item

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pereira.agnaldo.iot001.adapter.item.viewholder.BaseViewHolder
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFilterable
import java.io.Serializable

class BaseAdapterItem(private val mContext: Context) : AbstractFlexibleItem<BaseViewHolder>(), Serializable, IFilterable {
    var mIsSelected: Boolean = false
        set(selected) {
            if (!mIsBlocked) {
                field = selected
            }
        }
    var mIsBlocked: Boolean = false

    init {
        this.isSelectable = true
    }

    override fun getLayoutRes(): Int {
        return 0
    }

    override fun createViewHolder(view: View?, adapter: FlexibleAdapter<*>): BaseViewHolder {
        return BaseViewHolder(LayoutInflater.from(mContext).inflate(layoutRes,
                if (view != null && view is ViewGroup) view else null, false), adapter)
    }

    override fun bindViewHolder(adapter: FlexibleAdapter<*>, holder: BaseViewHolder, position: Int, payloads: List<*>) {

    }

    override fun equals(obj: Any?): Boolean {
        return false
    }

    override fun filter(constraint: String): Boolean {
        return false
    }

    fun relaseMemory() {

    }

    override fun hashCode(): Int {
        var result = mContext.hashCode()
        result = 31 * result + mIsSelected.hashCode()
        result = 31 * result + mIsBlocked.hashCode()
        return result
    }
}
