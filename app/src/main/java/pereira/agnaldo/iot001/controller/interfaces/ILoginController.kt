package pereira.agnaldo.iot001.controller.interfaces

import pereira.agnaldo.iot001.presenter.ILoginPresenter
import pereira.agnaldo.iot001.resultcode.LoginResultCode

interface ILoginController {

    fun setPresenter(presenter: ILoginPresenter)

    fun login(user: String, pass: String, rememberMe: Boolean)

    fun forgotPass(user: String)

}
