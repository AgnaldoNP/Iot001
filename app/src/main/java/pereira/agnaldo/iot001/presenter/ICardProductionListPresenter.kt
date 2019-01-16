package pereira.agnaldo.iot001.presenter

import eu.davidea.flexibleadapter.items.AbstractFlexibleItem

interface ICardProductionListPresenter : IBasePresenter {

    fun addCardToList(item: AbstractFlexibleItem<*>)

}