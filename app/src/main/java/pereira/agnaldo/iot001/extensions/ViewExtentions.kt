package pereira.agnaldo.iot001.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

fun View.setVisible(visible: Boolean) {
    val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)
    visibility = if (visible) View.VISIBLE else View.GONE
    animate().setDuration(shortAnimTime.toLong()).alpha(
        (if (visible) 1 else 0).toFloat()
    ).setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            visibility = if (visible) View.VISIBLE else View.GONE
        }
    })
}

fun View?.isVisible() = this?.visibility == View.VISIBLE

fun View?.isGone() = this?.visibility == View.GONE

fun View?.isInvisible() = this?.visibility == View.INVISIBLE

fun View?.isGoneOrInvisible() = this?.visibility == View.GONE || this?.visibility == View.INVISIBLE