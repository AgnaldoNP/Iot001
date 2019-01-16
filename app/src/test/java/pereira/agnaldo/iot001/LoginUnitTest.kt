package pereira.agnaldo.iot001

import pereira.agnaldo.iot001.controller.LoginControllerImpl
import pereira.agnaldo.iot001.resultcode.LoginResultCode
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LoginUnitTest {
    private lateinit var loginControllerImpl: LoginControllerImpl
    @Before
    fun before() {
        loginControllerImpl = LoginControllerImpl()
    }

    @Test
    fun validateLoginInput() {
        assertEquals(
            LoginResultCode.VALIDATE_LOGIN_OR_PASS_EMPTY,
            loginControllerImpl.validateLoginInput("", "")
        )

        assertEquals(
            LoginResultCode.VALIDATE_LOGIN_OR_PASS_EMPTY,
            loginControllerImpl.validateLoginInput("user", "")
        )

        assertEquals(
            LoginResultCode.VALIDATE_LOGIN_OR_PASS_EMPTY,
            loginControllerImpl.validateLoginInput("", "pass")
        )
    }

    @Test
    fun validateForgotPassUserEmpty() {
        assertEquals(
            LoginResultCode.VALIDATE_FORGOT_PASS_USER_EMPTY,
            loginControllerImpl.validateForgotPassInput("")
        )
    }
}
