package pereira.agnaldo.iot001.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import pereira.agnaldo.iot001.activity.BaseActivity
import pereira.agnaldo.iot001.database.IoT001Database
import pereira.agnaldo.iot001.presenter.IBasePresenter
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.IgnoreWhen
import org.androidannotations.annotations.UiThread

@EFragment
open class BaseFragment : Fragment(), IBasePresenter {

    internal var mBundle: Bundle? = null

    protected open fun onCreate() {

    }

    open fun onPostResume() {

    }

    open fun onRemoveFromBackStack() {
        hideLoading()
    }

    fun onBackPressed(): Boolean {
        hideLoading()
        return false
    }

    protected fun getDatabase(): IoT001Database {
        if (activity != null && activity is BaseActivity) {
            return (activity as BaseActivity).getDatabase()
        } else {
            return IoT001Database.getInstance(activity!!)
        }
    }

    @IgnoreWhen(IgnoreWhen.State.DETACHED)
    open fun onBackFromTopStack() {

    }

    @UiThread(propagation = UiThread.Propagation.REUSE)
    @IgnoreWhen(IgnoreWhen.State.DETACHED)
    override fun showLoading(message: String) {
        val activity = activity
        if (activity != null && activity is BaseActivity) {
            activity.showLoading(message)
        }
    }

    @UiThread(propagation = UiThread.Propagation.REUSE)
    @IgnoreWhen(IgnoreWhen.State.DETACHED)
    override fun showLoading(resMessage: Int) {
        val activity = activity
        if (activity != null && activity is BaseActivity) {
            activity.showLoading(resMessage)
        }
    }

    @UiThread(propagation = UiThread.Propagation.REUSE)
    @IgnoreWhen(IgnoreWhen.State.DETACHED)
    override fun showAlertMessage(message: String) {
        val activity = activity
        if (activity != null && activity is BaseActivity) {
            activity.showAlertMessage(message)
        }
    }

    @UiThread(propagation = UiThread.Propagation.REUSE)
    @IgnoreWhen(IgnoreWhen.State.DETACHED)
    override fun showLoading() {
        val activity = activity
        if (activity != null && activity is BaseActivity) {
            activity.showLoading()
        }
    }

    override fun isShowingLoading(): Boolean {
        val activity = activity
        return activity is BaseActivity && activity.isShowingLoading()
    }

    @UiThread
    @IgnoreWhen(IgnoreWhen.State.DETACHED)
    override fun hideLoading() {
        val activity = activity
        if (activity != null && activity is BaseActivity) {
            activity.hideLoading()
        }
    }

    @UiThread
    @IgnoreWhen(IgnoreWhen.State.DETACHED)
    protected open fun openFragment(baseFragment: BaseFragment?) {
        if (baseFragment != null) {
            val activity = activity
            if (activity != null && activity is BaseActivity) {
                activity.openFragment(baseFragment)
            }
        }
    }

    fun getTopFragment(): BaseFragment? {
        val activity = activity
        if (activity != null && activity is BaseActivity) {
            val topFragment = (getActivity() as BaseActivity).getTopFragment()
            return if (topFragment != null && topFragment is BaseFragment)
                topFragment
            else
                null
        }
        return null
    }

    fun getSecondFragmentFromTop(): Fragment? {
        val activity = activity
        return if (activity != null && activity is BaseActivity) {
            (getActivity() as BaseActivity).getSecondFragmentFromTop()
        } else null
    }

    fun getThirdFragmentFromTop(): Fragment? {
        val activity = activity
        return if (activity != null && activity is BaseActivity) {
            (getActivity() as BaseActivity).getThirdFragmentFromTop()
        } else null
    }

}