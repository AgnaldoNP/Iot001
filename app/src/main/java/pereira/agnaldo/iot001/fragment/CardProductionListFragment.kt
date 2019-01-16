package pereira.agnaldo.iot001.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import pereira.agnaldo.iot001.R
import pereira.agnaldo.iot001.adapter.BaseListAdapter
import pereira.agnaldo.iot001.controller.CardProductionListControllerImpl
import pereira.agnaldo.iot001.controller.LoginControllerImpl
import pereira.agnaldo.iot001.controller.interfaces.ICardProductionListController
import pereira.agnaldo.iot001.extensions.setVisible
import pereira.agnaldo.iot001.presenter.ICardProductionListPresenter
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import org.androidannotations.annotations.*
import java.util.*

@EFragment(R.layout.fragment_card_list_production)
open class CardProductionListFragment : BaseFragment(), ICardProductionListPresenter {

    @ViewById(R.id.simple_recycle_view_id)
    protected lateinit var mRecyclerView: RecyclerView

    @ViewById(R.id.simple_recycle_view_progressbar)
    protected lateinit var mRecyclerViewLoading: View

    //region Controller dependency injection
    protected lateinit var mController: ICardProductionListController

    @Bean(CardProductionListControllerImpl::class)
    fun injectController(controller: ICardProductionListController) {
        this.mController = controller
        this.mController.setPresenter(this)
    }
    //endregion

    @AfterViews
    protected open fun afterViews() {
        super.onCreate()
        configureRecyclerView()

        mRecyclerViewLoading.post(fun() {
            mController.loadCards()
        })
    }

    @UiThread(id = "addCardToList", propagation = UiThread.Propagation.ENQUEUE)
    @IgnoreWhen(IgnoreWhen.State.DETACHED)
    override fun addCardToList(item: AbstractFlexibleItem<*>) {
        getAdatper()?.addItem(item)
        getAdatper()?.notifyItemInserted(getAdatper()?.getItemsCount()!!)
    }

    @UiThread(id = "updateAdapter", propagation = UiThread.Propagation.ENQUEUE)
    @IgnoreWhen(IgnoreWhen.State.DETACHED)
    protected open fun updateAdater() {
        getAdatper()?.notifyDataSetChanged()
    }

    //region RecyclerView methods
    @UiThread
    @IgnoreWhen(IgnoreWhen.State.DETACHED)
    protected open fun configureRecyclerView() {
        mRecyclerView.layoutManager = SmoothScrollLinearLayoutManager(
            activity, LinearLayoutManager.VERTICAL, false
        )
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.scrollToPosition(0)
        mRecyclerViewLoading.setVisible(true)

        val items = ArrayList<AbstractFlexibleItem<*>>()
        setAdapter(BaseListAdapter(items, this))
    }

    @UiThread
    @IgnoreWhen(IgnoreWhen.State.DETACHED)
    protected open fun setAdapter(adapter: BaseListAdapter) {
        mRecyclerView.adapter = adapter
        mRecyclerViewLoading.setVisible(false)
    }

    @UiThread
    @IgnoreWhen(IgnoreWhen.State.DETACHED)
    protected open fun emptyData() {
        mRecyclerView.removeAllViews()
        mRecyclerViewLoading.setVisible(false)
    }

    fun getAdatper(): BaseListAdapter? {
        val adapter = mRecyclerView.adapter
        return adapter as? BaseListAdapter
    }
    //endregion

}
