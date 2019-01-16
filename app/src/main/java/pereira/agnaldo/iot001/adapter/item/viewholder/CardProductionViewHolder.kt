package pereira.agnaldo.iot001.adapter.item.viewholder

import android.view.View
import android.widget.TextView
import pereira.agnaldo.iot001.R
import com.eralp.circleprogressview.CircleProgressView
import com.jonas.jgraph.graph.JcoolGraph
import eu.davidea.flexibleadapter.FlexibleAdapter

class CardProductionViewHolder(view: View, adapter: FlexibleAdapter<*>) : BaseViewHolder(view, adapter) {
    var title: TextView = view.findViewById<View>(R.id.card_production_title) as TextView
    var date: TextView = view.findViewById<View>(R.id.card_production_date) as TextView
    var dateFrom: TextView = view.findViewById<View>(R.id.card_production_date_from) as TextView
    var dateTo: TextView = view.findViewById<View>(R.id.card_production_date_to) as TextView
    var chart: JcoolGraph = view.findViewById<View>(R.id.card_production_chart) as JcoolGraph
    var chart2: JcoolGraph = view.findViewById<View>(R.id.card_production_chart_2) as JcoolGraph
    var circleProgressView: CircleProgressView =
        view.findViewById<View>(R.id.circle_progress_view) as CircleProgressView
}
