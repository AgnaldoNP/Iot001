package pereira.agnaldo.iot001.controller

import android.content.Context
import pereira.agnaldo.iot001.controller.interfaces.IBaseController
import pereira.agnaldo.iot001.database.IoT001Database
import pereira.agnaldo.iot001.presenter.IBasePresenter
import pereira.agnaldo.iot001.sharedpreferences.SPref_
import org.androidannotations.annotations.EBean
import org.androidannotations.annotations.RootContext

@EBean
open class BaseControllerImpl : IBaseController {

    private lateinit var mSharedPrefs: SPref_
    private lateinit var mPresenter: IBasePresenter
    private lateinit var mContext: Context
    private lateinit var mDatabase: IoT001Database

    @RootContext
    fun setRootContext(context: Context) {
        this.mContext = context
        this.mDatabase = IoT001Database.getInstance(context)
        this.mSharedPrefs = SPref_(context)
    }

    override fun setBasePresenter(presenter: IBasePresenter) {
        this.mPresenter = presenter
    }

    override fun getSharedPref() = mSharedPrefs

    override fun getContext() = mContext

    override fun getDatabase(): IoT001Database = mDatabase

}