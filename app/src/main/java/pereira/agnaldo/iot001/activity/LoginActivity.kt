package pereira.agnaldo.iot001.activity

import android.annotation.SuppressLint
import android.support.design.widget.TextInputEditText
import android.widget.CheckBox
import pereira.agnaldo.iot001.R
import pereira.agnaldo.iot001.controller.LoginControllerImpl
import pereira.agnaldo.iot001.controller.interfaces.ILoginController
import pereira.agnaldo.iot001.presenter.ILoginPresenter
import pereira.agnaldo.iot001.sharedpreferences.SPref_
import com.xwray.passwordview.PasswordView
import org.androidannotations.annotations.*
import org.androidannotations.annotations.sharedpreferences.Pref

@SuppressLint("Registered")
@EActivity(R.layout.activity_login)
open class LoginActivity : BaseActivity(), ILoginPresenter {

    //region Controller dependency injection
    protected lateinit var mController: ILoginController

    @Bean(LoginControllerImpl::class)
    fun injectController(controller: ILoginController) {
        this.mController = controller
        this.mController.setPresenter(this)
    }

    @Pref
    protected open lateinit var sharedPref: SPref_
    //endregion

    //region User interface view mapping
    @ViewById(R.id.login_user_input)
    protected open lateinit var userInputView: TextInputEditText

    @ViewById(R.id.login_pass_input)
    protected open lateinit var passInputView: PasswordView

    @ViewById(R.id.login_remember_me)
    protected open lateinit var rememberMeCheckBox: CheckBox
    //endregion

    @AfterViews
    protected open fun afterViews() {
        val loginRememberMe = sharedPref.loginRememberMe()
        rememberMeCheckBox.isChecked = loginRememberMe.exists()
        if (rememberMeCheckBox.isChecked) {
            userInputView.setText(loginRememberMe.get())
        }
    }

    //region User view interaction
    @Click(R.id.login_enter_button)
    protected open fun onEnterClick() {
        mController.login(
            userInputView.text.toString(),
            passInputView.text.toString(),
            rememberMeCheckBox.isChecked
        )
    }

    @Click(R.id.login_forgot_your_pass)
    protected open fun onForgotYourPassClick() {
        mController.forgotPass(userInputView.text.toString())
    }
    //endregion

    //region ILoginPresenter implementations
    override fun onLoginSuccessful() {
        MainActivity_.intent(this).start()
        finish()
    }
    //endregion

}
