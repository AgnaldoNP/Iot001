package pereira.agnaldo.iot001

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import pereira.agnaldo.iot001.controller.LoginControllerImpl_
import pereira.agnaldo.iot001.resultcode.LoginResultCode
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class LoginInstrumentedTest {
    private lateinit var mLoginController: LoginControllerImpl_
    private lateinit var mContext: Context

    @Before
    fun before() {
        mContext = InstrumentationRegistry.getTargetContext()
        mLoginController = LoginControllerImpl_.getInstance_(mContext)
        mLoginController.getSharedPref().clear()
        //Fazer o Mock do presenter
    }

    @Test
    fun validateMessageByLoginResultCode() {
        assertEquals(
            mContext.getString(R.string.login_message_user_or_pass_empty),
            mLoginController.getMessage(LoginResultCode.VALIDATE_LOGIN_OR_PASS_EMPTY)
        )

        assertEquals(
            "", mLoginController.getMessage(LoginResultCode.VALIDATE_LOGIN_AND_PASS_OK)
        )

        assertEquals(
            mContext.getString(R.string.login_message_forgot_pass_user_empty),
            mLoginController.getMessage(LoginResultCode.VALIDATE_FORGOT_PASS_USER_EMPTY)
        )

        assertEquals(
            "", mLoginController.getMessage(LoginResultCode.VALIDATE_LOGIN_AND_PASS_OK)
        )
    }

    @Test
    fun saveLoginRememberMe() {
        val userName = "joao123"

        assertEquals("", mLoginController.getSharedPref().loginRememberMe().get())

        mLoginController.saveLoginRememberMe(true, userName)
        assertEquals(userName, mLoginController.getSharedPref().loginRememberMe().get())

        mLoginController.saveLoginRememberMe(false, userName)
        assertEquals("", mLoginController.getSharedPref().loginRememberMe().get())
    }

}
