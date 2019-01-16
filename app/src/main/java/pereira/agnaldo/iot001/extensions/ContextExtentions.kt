package pereira.agnaldo.iot001.extensions

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.graphics.Point
import android.net.ConnectivityManager
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import pereira.agnaldo.iot001.R


@SuppressLint("MissingPermission")
fun Context.isConnectedToInternet(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}

fun Context.showToast(message: String, length: Int) {
    Toast.makeText(this, message, length)
        .show()
}

fun Context.getWidthOfScreen(): Int {
    try {
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.x
    } catch (e: Exception) {
        return 0
    }
}

fun Context.getHeightOfScreen(): Int {
    try {
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.y
    } catch (e: Exception) {
        return 0
    }
}


fun Context.isWriteExternalPermitted(): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val permission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        return permission == PackageManager.PERMISSION_GRANTED
    }
    return true
}

fun Context.isCameraPermitted(): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val permission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA
        )
        return permission == PackageManager.PERMISSION_GRANTED
    }
    return true
}


fun Context.showMessage(resMessage: Int) {
    showMessage(getString(resMessage))
}

fun Context.showMessage(resMessage: Int, okListener: View.OnClickListener) {
    showMessage(getString(resMessage), okListener)
}

fun Context.showMessage(message: String) {
    showMessage(message, null)
}

fun Context.showMessage(message: String, okListener: View.OnClickListener?) {
    showMessage(null, message, getString(android.R.string.ok), okListener, null, null)
}

fun Context.showMessage(
    resTitle: Int, resMessage: Int, resOk: Int,
    okListener: View.OnClickListener, resCancel: Int,
    cancelListener: View.OnClickListener
) {
    showMessage(
        getString(resTitle), getString(resMessage), getString(resOk),
        okListener, getString(resCancel), cancelListener
    )
}

fun Context.showMessage(
    message: String, resOk: Int,
    okListener: View.OnClickListener, resCancel: Int,
    cancelListener: View.OnClickListener
) {
    showMessage(
        null, message, getString(resOk),
        okListener, getString(resCancel), cancelListener
    )
}

fun Context.showMessage(
    resMessage: Int, resOk: Int,
    okListener: View.OnClickListener, resCancel: Int,
    cancelListener: View.OnClickListener
) {
    showMessage(
        getString(resMessage), getString(resOk),
        okListener, getString(resCancel), cancelListener
    )
}

fun Context.showMessage(
    message: String, resOk: Int,
    okListener: View.OnClickListener, resCancel: Int
) {
    showMessage(
        message, getString(resOk),
        okListener, getString(resCancel), null
    )
}

fun Context.showMessage(
    resMessage: Int, resOk: Int,
    okListener: View.OnClickListener, resCancel: Int
) {
    showMessage(
        getString(resMessage), getString(resOk),
        okListener, getString(resCancel), null
    )
}

fun Context.showMessage(
    message: String, ok: String,
    okListener: View.OnClickListener, cancel: String
) {
    showMessage(null, message, ok, okListener, cancel, null)
}

fun Context.showMessage(
    message: String, ok: String,
    okListener: View.OnClickListener, cancel: String,
    cancelListener: View.OnClickListener?
) {
    showMessage(null, message, ok, okListener, cancel, cancelListener)
}

fun Context.showMessage(
    message: String, ok: String,
    okListener: View.OnClickListener, cancel: String,
    cancelListener: View.OnClickListener, onDismissListener: DialogInterface.OnDismissListener
) {
    showMessage(null, message, ok, okListener, cancel, cancelListener, onDismissListener)
}

fun Context.showMessage(
    title: String, message: String, ok: String,
    okListener: View.OnClickListener, cancel: String
) {
    showMessage(title, message, ok, okListener, cancel, null)
}

fun Context.showMessage(
    title: String?, message: String, ok: String,
    okListener: View.OnClickListener?, cancel: String?,
    cancelListener: View.OnClickListener?
) {
    showMessage(title, message, ok, okListener, cancel, cancelListener, onDismissListener = null)
}

@SuppressLint("InflateParams")
fun Context.showMessage(
    title: String?, message: String?, ok: String,
    okListener: View.OnClickListener?, cancel: String?,
    cancelListener: View.OnClickListener?,
    onDismissListener: DialogInterface.OnDismissListener?
) {
    try {
        if (message.isNullOrEmpty())
            return

        val dialog = Dialog(this, R.style.FullHeightDialog)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_alert, null, false)
        dialog.setContentView(view)

        val yesButton = dialog.findViewById<View>(R.id.yes_buttom) as TextView
        val noButton = dialog.findViewById<View>(R.id.no_buttom) as TextView
        val alertTitle = dialog.findViewById<View>(R.id.alert_title) as TextView
        val alertMessage = dialog.findViewById<View>(R.id.alert_message) as TextView
        val close = dialog.findViewById<View>(R.id.close)

        alertMessage.text = message

        if (!title.isNullOrEmpty()) {
            alertTitle.text = title
            alertTitle.setVisible(true)
        } else {
            alertTitle.setVisible(false)
        }

        noButton.setOnClickListener {
            cancelListener?.onClick(null)
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        }

        yesButton.setOnClickListener {
            okListener?.onClick(null)
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        }

        if (!cancel.isNullOrEmpty()) {
            noButton.setVisible(true)
            noButton.text = cancel
        } else {
            noButton.setVisible(false)
        }

        yesButton.setVisible(true)
        if (!ok.isEmpty()) {
            yesButton.text = ok
        } else {
            yesButton.setText(R.string.ok)
        }

        close.setOnClickListener {
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        }

        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)

        dialog.show()
        dialog.window!!.setLayout((getWidthOfScreen() * 0.85f).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        if (onDismissListener != null) {
            dialog.setOnDismissListener(onDismissListener)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
