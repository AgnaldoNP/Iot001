package pereira.agnaldo.iot001.adapter

import android.support.annotation.IntRange
import pereira.agnaldo.iot001.adapter.item.BaseAdapterItem
import pereira.agnaldo.iot001.extensions.isNotNullAndNotEmpty
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import pereira.agnaldo.iot001.extensions.isNotNullNeitherEmpty
import java.util.*

open class BaseListAdapter(items: List<AbstractFlexibleItem<*>>?, listeners: Any?) :
    FlexibleAdapter<AbstractFlexibleItem<*>>(items, listeners) {

    internal val mAllItems: MutableList<AbstractFlexibleItem<*>>

    val selectedCount: Int
        get() {
            var selectedCount = 0
            val itemCount = itemCount
            for (position in 0 until itemCount) {
                val abstractFlexibleItem = getItem(position)
                if (abstractFlexibleItem != null && abstractFlexibleItem is BaseAdapterItem) {
                    val item = abstractFlexibleItem as BaseAdapterItem?
                    if (item?.mIsSelected ?: false) {
                        selectedCount++
                    }
                }
            }
            return selectedCount
        }

    fun getAllItems(): List<AbstractFlexibleItem<*>> {
        return mAllItems
    }

    init {
        mAllItems = ArrayList(items!!)
    }

    fun getItemsCount():Int{
        return mAllItems.size
    }

    override fun addItem(item: AbstractFlexibleItem<*>): Boolean {
        if (!contains(item)) {
            val added = super.addItem(item)
            if (added && !mAllItems.contains(item)) {
                mAllItems.add(item)
            }
            return added
        }
        return false
    }

    override fun addItem(@IntRange(from = 0L) position: Int, item: AbstractFlexibleItem<*>): Boolean {
        if (!contains(item)) {
            val added = super.addItem(position, item)
            if (added && !mAllItems.contains(item)) {
                mAllItems.add(position, item)
            }
            return added
        }
        return false
    }

    fun addItems(items: List<AbstractFlexibleItem<*>>) {
        items.forEach { addItem(it) }
    }

    override fun onLoadMoreComplete(newItems: List<AbstractFlexibleItem<*>>?) {
        super.onLoadMoreComplete(newItems)
        if (newItems.isNotNullAndNotEmpty()) {
            mAllItems.addAll(newItems!!)
        }
    }

    fun remove(objectId: String): Boolean {
        if (objectId.isNotNullNeitherEmpty()) {
            val itemCount = itemCount
            for (position in 0 until itemCount) {
                val item = getItem(position)
                if (item?.toString().equals(objectId, ignoreCase = true)) {
                    removeItem(position)
                    return true
                }
            }
        }
        return false
    }

    fun getPosition(objectId: String): Int {
        if (objectId.isNotNullNeitherEmpty()) {
            val itemCount = itemCount
            for (position in 0 until itemCount) {
                val item = getItem(position)
                if (item?.toString().equals(objectId, ignoreCase = true)) {
                    return position
                }
            }
        }
        return -1
    }

    fun swapItems(position: Int) {
        swapItems(mAllItems, position, 0)
    }

    fun unSelectAll() {
        val itemCount = itemCount
        for (position in 0 until itemCount) {
            val abstractFlexibleItem = getItem(position)
            if (abstractFlexibleItem != null && abstractFlexibleItem is BaseAdapterItem) {
                val item = abstractFlexibleItem as BaseAdapterItem?
                if (item?.mIsSelected ?: false) {
                    item!!.mIsSelected = false
                    notifyItemChanged(position)
                }
            }
        }
    }

    fun releaseMemory() {
        val itemCount = itemCount
        for (position in 0 until itemCount) {
            val abstractFlexibleItem = getItem(position)
            if (abstractFlexibleItem != null && abstractFlexibleItem is BaseAdapterItem) {
                val item = abstractFlexibleItem as BaseAdapterItem?
                item!!.relaseMemory()
            }
        }
    }
}
