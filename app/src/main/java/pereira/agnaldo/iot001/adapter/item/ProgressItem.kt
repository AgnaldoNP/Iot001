@file:Suppress("EqualsOrHashCode")

package pereira.agnaldo.iot001.adapter.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import pereira.agnaldo.iot001.R

import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.viewholders.FlexibleViewHolder

class ProgressItem : AbstractFlexibleItem<ProgressItem.ProgressViewHolder>() {

    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun getLayoutRes(): Int {
        return R.layout.progress_item
    }

    override fun createViewHolder(view: View?, adapter: FlexibleAdapter<*>): ProgressViewHolder {
        return ProgressViewHolder(
            LayoutInflater.from(view!!.context).inflate(
                layoutRes,
                if (view is ViewGroup) view else null, false
            ), adapter
        )
    }

    override fun bindViewHolder(
        adapter: FlexibleAdapter<*>, holder: ProgressViewHolder,
        position: Int, payloads: List<*>
    ) {
    }

    inner class ProgressViewHolder(view: View, adapter: FlexibleAdapter<*>) : FlexibleViewHolder(view, adapter) {
        var progressBar: ProgressBar

        init {
            progressBar = view.findViewById(R.id.progress_bar)
        }
    }

}