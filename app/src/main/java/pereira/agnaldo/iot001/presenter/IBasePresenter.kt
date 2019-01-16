package pereira.agnaldo.iot001.presenter

interface IBasePresenter {

    fun showLoading()

    fun hideLoading()

    fun showLoading(message: String)

    fun showLoading(resMessage: Int)

    fun isShowingLoading(): Boolean

    fun showAlertMessage(message: String)

}