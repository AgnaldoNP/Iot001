package pereira.agnaldo.iot001.adapter.item

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.FrameLayout
import pereira.agnaldo.iot001.R
import pereira.agnaldo.iot001.adapter.item.viewholder.CardProductionViewHolder
import pereira.agnaldo.iot001.database.entity.OPCard
import pereira.agnaldo.iot001.extensions.toDBDateString
import pereira.agnaldo.iot001.extensions.toString
import com.jonas.jgraph.graph.JcoolGraph
import com.jonas.jgraph.inter.BaseGraph
import com.jonas.jgraph.models.Jchart
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFilterable
import java.io.Serializable
import java.security.SecureRandom
import java.util.ArrayList

class CardProductionAdapterItem(private val mContext: Context, private val opCard: OPCard) :
    AbstractFlexibleItem<CardProductionViewHolder>(), Serializable, IFilterable {

    private var holder: CardProductionViewHolder? = null

    override fun toString(): String {
        return opCard.toString()
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_card_production
    }

    override fun createViewHolder(view: View?, adapter: FlexibleAdapter<*>): CardProductionViewHolder {
        return CardProductionViewHolder(
            LayoutInflater.from(mContext).inflate(
                layoutRes,
                if (view != null && view is ViewGroup) view else null, false
            ), adapter
        )
    }

    override fun equals(other: Any?): Boolean {
        return other is CardProductionAdapterItem && toString().equals(other.toString(), ignoreCase = true)
    }

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun bindViewHolder(
        adapter: FlexibleAdapter<*>,
        holder: CardProductionViewHolder,
        position: Int,
        payloads: List<*>
    ) {
        this.holder = holder

        this.holder?.circleProgressView?.setProgressWithAnimation(opCard.oee, 2)
        this.holder?.title?.text = opCard.op?.name + opCard.op?.description?.let { " | $it" }
        this.holder?.date?.text = opCard.dateRangeType
        this.holder?.dateFrom?.text = mContext.getString(R.string.from) +": " +  opCard.getFromDate()?.toString("dd/MM HH:mm")
        this.holder?.dateTo?.text = mContext.getString(R.string.to) +": " +  opCard.getToDate()?.toString("dd/MM HH:mm")

        this.holder?.let { holder ->
            val mLineChart = holder.chart
            val mLineChart2 = holder.chart2

            val lines = ArrayList<Jchart>()
            opCard.opcardItems.forEach { lines.add(Jchart(0f, it.quantity.toFloat(), it.description)) }

            mLineChart.setLinePointRadio(mLineChart.lineWidth.toInt())
            mLineChart.normalColor = Color.parseColor("#6a6567")

            mLineChart.feedData(lines)
            mLineChart2.feedData(lines)
            (mLineChart.parent as FrameLayout).setOnClickListener {
                mLineChart.lineMode = JcoolGraph.LINE_BROKEN
                mLineChart.setLineShowStyle(JcoolGraph.LINESHOW_ASWAVE)
                mLineChart.invalidate()
            }
        }
    }

    override fun filter(constraint: String?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        var result = mContext.hashCode()
        result = 31 * result + opCard.hashCode()
        result = 31 * result + (holder?.hashCode() ?: 0)
        return result
    }

}

