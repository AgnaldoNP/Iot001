package pereira.agnaldo.iot001.resultcode

enum class LoginResultCode(val resultCode: Int) {
    VALIDATE_LOGIN_OR_PASS_EMPTY(0),
    VALIDATE_LOGIN_AND_PASS_OK(1),

    VALIDATE_FORGOT_PASS_USER_EMPTY(2),
    VALIDATE_FORGOT_PASS_USER_OK(3),
}
