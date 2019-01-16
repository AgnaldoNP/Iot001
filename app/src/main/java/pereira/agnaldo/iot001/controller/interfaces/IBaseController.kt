package pereira.agnaldo.iot001.controller.interfaces

import android.content.Context
import pereira.agnaldo.iot001.database.IoT001Database
import pereira.agnaldo.iot001.presenter.IBasePresenter
import pereira.agnaldo.iot001.sharedpreferences.SPref_

interface IBaseController {

    fun setBasePresenter(presenter: IBasePresenter)
    fun getSharedPref(): SPref_
    fun getContext(): Context
    fun getDatabase(): IoT001Database


}