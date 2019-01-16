package pereira.agnaldo.iot001.extensions

import android.text.TextUtils
import java.text.SimpleDateFormat
import java.util.*

val FORMAT_DATETIME_DEFAULT_EN = "yyyy-MM-dd HH:mm:ss.SSS"
val FORMAT_DATETIME_DEFAULT_EN_T = "yyyy-MM-dd'T'HH:mm:ss.SSS"
private val FORMAT_DATETIME_SLASH_EN = "yyyy/MM/dd HH:mm:ss.SSS"
private val FORMAT_DATETIME_SLASH_EN_T = "yyyy/MM/dd'T'HH:mm:ss.SSS"
val FORMAT_TIME = "HH:mm"
private val FORMAT_DATE_WITH_MONTH = "dd MMM yyyy"

fun String.toDBDate(): Date? {
    try {
        return if (!TextUtils.isEmpty(this)) {
            when {
                this.contains("-") -> this.toDate(
                    if (this.contains("T"))
                        FORMAT_DATETIME_DEFAULT_EN_T
                    else
                        FORMAT_DATETIME_DEFAULT_EN
                )
                this.contains("/") -> this.toDate(
                    if (this.contains("T"))
                        FORMAT_DATETIME_SLASH_EN_T
                    else
                        FORMAT_DATETIME_SLASH_EN
                )
                else -> Date(java.lang.Long.parseLong(this))
            }
        } else {
            null
        }
    } catch (error: Exception) {
        error.printStackTrace()
        return null
    }

}

fun Date.toDBDateString(): String {
    val df = SimpleDateFormat(FORMAT_DATETIME_DEFAULT_EN, Locale.US)
    return df.format(this.time)
}

fun Date.toString(format: String): String {
    val df = SimpleDateFormat(format, Locale.US)
    return df.format(this.time)
}

fun String.toDate(format: String): Date? {
    return try {
        val formatter = SimpleDateFormat(format, Locale.US)
        formatter.parse(this)
    } catch (error: Exception) {
        error.printStackTrace()
        null
    }
}
