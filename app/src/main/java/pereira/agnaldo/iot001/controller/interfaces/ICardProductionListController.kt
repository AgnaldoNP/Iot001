package pereira.agnaldo.iot001.controller.interfaces

import pereira.agnaldo.iot001.presenter.ICardProductionListPresenter

interface ICardProductionListController {

    fun setPresenter(presenter: ICardProductionListPresenter)

    fun loadCards()

}
