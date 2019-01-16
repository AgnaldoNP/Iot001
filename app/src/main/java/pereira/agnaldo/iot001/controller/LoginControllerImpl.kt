package pereira.agnaldo.iot001.controller

import android.support.annotation.VisibleForTesting
import pereira.agnaldo.iot001.R
import pereira.agnaldo.iot001.controller.interfaces.ILoginController
import pereira.agnaldo.iot001.presenter.ILoginPresenter
import pereira.agnaldo.iot001.resultcode.LoginResultCode
import org.androidannotations.annotations.Background
import org.androidannotations.annotations.EBean

@EBean
open class LoginControllerImpl : BaseControllerImpl(), ILoginController {

    private lateinit var mPresenter: ILoginPresenter

    //region ILoginController implementations
    override fun setPresenter(presenter: ILoginPresenter) {
        super.setBasePresenter(presenter)
        this.mPresenter = presenter
    }

    @Background
    override fun login(user: String, pass: String, rememberMe: Boolean) {
        mPresenter.showLoading()
        val result: LoginResultCode = validateLoginInput(user, pass)
        if (result == LoginResultCode.VALIDATE_LOGIN_AND_PASS_OK) {
            // TODO(Implement login function with web authentication)

            saveLoginRememberMe(rememberMe, user)

            mPresenter.onLoginSuccessful()
        } else {
            val message: String = getMessage(result)
            mPresenter.showAlertMessage(message)
            mPresenter.hideLoading()
        }
    }

    @Background
    override fun forgotPass(user: String) {
        validateForgotPassInput(user)
    }
    //endregion

    //region controller methods visible for tests
    fun getMessage(result: LoginResultCode): String {
        return when (result) {
            LoginResultCode.VALIDATE_LOGIN_OR_PASS_EMPTY ->
                getContext().getString(R.string.login_message_user_or_pass_empty)
            else -> ""
        }
    }

    fun validateLoginInput(user: String, pass: String): LoginResultCode {
        if (user.isNullOrBlank() || pass.isNullOrBlank()) {
            return LoginResultCode.VALIDATE_LOGIN_OR_PASS_EMPTY
        }
        return LoginResultCode.VALIDATE_LOGIN_AND_PASS_OK
    }

    fun validateForgotPassInput(user: String): LoginResultCode {
        if (user.isNullOrBlank()) {
            return LoginResultCode.VALIDATE_FORGOT_PASS_USER_EMPTY
        }
        return LoginResultCode.VALIDATE_FORGOT_PASS_USER_OK
    }

    fun saveLoginRememberMe(rememberMe: Boolean, user: String) {
        if (rememberMe)
            getSharedPref().loginRememberMe().put(user)
        else
            getSharedPref().loginRememberMe().remove()
    }
    //endregion

}
