package pereira.agnaldo.iot001.controller

import android.support.annotation.VisibleForTesting
import android.widget.BaseAdapter
import pereira.agnaldo.iot001.R
import pereira.agnaldo.iot001.adapter.item.CardProductionAdapterItem
import pereira.agnaldo.iot001.controller.interfaces.ICardProductionListController
import pereira.agnaldo.iot001.controller.interfaces.ILoginController
import pereira.agnaldo.iot001.presenter.ICardProductionListPresenter
import pereira.agnaldo.iot001.presenter.ILoginPresenter
import pereira.agnaldo.iot001.resultcode.LoginResultCode
import org.androidannotations.annotations.Background
import org.androidannotations.annotations.EBean

@EBean
open class CardProductionListControllerImpl : BaseControllerImpl(), ICardProductionListController {

    private lateinit var mPresenter: ICardProductionListPresenter

    //region ILoginController implementations
    override fun setPresenter(presenter: ICardProductionListPresenter) {
        super.setBasePresenter(presenter)
        this.mPresenter = presenter
    }

    @Background
    override fun loadCards() {
        mPresenter.showLoading()

        val ops = getDatabase().daoOP().getAll()
        ops.forEach { op ->
            val opCards = getDatabase().daoOPCard()
                .getByOpdAndRange(getContext(), op, "2018-12-13 09:00:00.000", "2018-12-13 09:07:00.000")
            opCards.forEach { opCard ->
                if (opCard.opcardItems.size >= 2) {
                    mPresenter.addCardToList(CardProductionAdapterItem(getContext(), opCard))
                }
            }
        }

        mPresenter.hideLoading()
    }
    //endregion


}
