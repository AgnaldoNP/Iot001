@file:Suppress("DEPRECATION")

package pereira.agnaldo.iot001.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import pereira.agnaldo.iot001.R
import pereira.agnaldo.iot001.database.IoT001Database
import pereira.agnaldo.iot001.extensions.isConnectedToInternet
import pereira.agnaldo.iot001.extensions.showMessage
import pereira.agnaldo.iot001.fragment.BaseFragment
import pereira.agnaldo.iot001.presenter.IBasePresenter
import pereira.agnaldo.iot001.presenter.ILoginPresenter
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.UiThread

@SuppressLint("Registered")
@EActivity
open class BaseActivity : AppCompatActivity(), IBasePresenter {

    private var mFragmentManager: FragmentManager? = null
    private var mWifiLock: WifiManager.WifiLock? = null
    var mProgressDialog: ProgressDialog? = null

    private lateinit var mDatabase: IoT001Database

    fun getDatabase()= mDatabase

    @AfterViews
    protected open fun onCreate() {
        mFragmentManager = supportFragmentManager
        mDatabase = IoT001Database.getInstance(this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        mWifiLock = wifiManager.createWifiLock(WifiManager.WIFI_MODE_FULL, "LockTag")
    }

    override fun onResume() {
        super.onResume()
        if (mWifiLock != null && this.isConnectedToInternet()) {
            if (mWifiLock!!.isHeld()) {
                mWifiLock?.release()
            }
            mWifiLock?.acquire()
        }
    }

    override fun onPostResume() {
        super.onPostResume()
        val topFragment = getTopFragment()
        if (topFragment is BaseFragment) {
            topFragment.onPostResume()
        }
    }

    override fun onPause() {
        if (mWifiLock != null && mWifiLock!!.isHeld()) {
            mWifiLock?.release()
        }

        hideLoading()
        super.onPause()
    }

    @UiThread
    protected open fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    @UiThread
    override fun onBackPressed() {
        hideLoading()

        val topFragment = getTopFragment()
        if (topFragment != null && topFragment is BaseFragment) {
            val handle = topFragment.onBackPressed()
            if (handle) {
                return
            }
        }

        val secondFragmentFromTop = getSecondFragmentFromTop()

        if (mFragmentManager != null && mFragmentManager!!.getBackStackEntryCount() == 1) {
            finish()
        } else {
            if (topFragment != null && topFragment is BaseFragment) {
                topFragment.onRemoveFromBackStack()
            }
            super.onBackPressed()
        }

        if (secondFragmentFromTop != null) {
            secondFragmentFromTop.onResume()

            if (secondFragmentFromTop is BaseFragment) {
                secondFragmentFromTop.onBackFromTopStack()
            }

        }
    }

    @UiThread
    override fun showAlertMessage(message: String) {
        this.showMessage(message)
    }

    private fun buildProgressDialog(): ProgressDialog {
        val progress = ProgressDialog(this)
        progress.setCancelable(false)
        progress.setCanceledOnTouchOutside(false)
        return progress
    }

    @UiThread(propagation = UiThread.Propagation.REUSE)
    override fun showLoading() {
        hideLoading()
        mProgressDialog = buildProgressDialog()
        mProgressDialog?.setMessage(getString(R.string.please_wait))
        mProgressDialog?.show()
    }

    @UiThread(propagation = UiThread.Propagation.REUSE)
    override fun showLoading(message: String) {
        hideLoading()
        mProgressDialog = buildProgressDialog()
        mProgressDialog?.setMessage(message)
        mProgressDialog?.show()
    }

    override fun showLoading(resMessage: Int) {
        hideLoading()
        showLoading(getString(resMessage))
    }

    override fun isShowingLoading(): Boolean {
        return mProgressDialog != null && mProgressDialog!!.isShowing()
    }

    @UiThread(propagation = UiThread.Propagation.REUSE)
    override fun hideLoading() {
        mProgressDialog?.dismiss()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragment = getTopFragment()
        fragment?.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val fragment = getTopFragment()
        fragment?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        try {
            val ret = super.dispatchTouchEvent(event)
            val currentFocus = this.currentFocus
            if (currentFocus != null && currentFocus is EditText) {
                val scrcoords = IntArray(2)
                currentFocus.getLocationOnScreen(scrcoords)
                val x = event.rawX + currentFocus.left.toFloat() - scrcoords[0].toFloat()
                val y = event.rawY + currentFocus.top.toFloat() - scrcoords[1].toFloat()
                if (event.action == 1 && (x < currentFocus.left.toFloat() || x >= currentFocus.right.toFloat() ||
                            y < currentFocus.top.toFloat() || y > currentFocus.bottom.toFloat())
                ) {
                    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
                }
            }
            return ret
        } catch (e: Exception) {
            e.printStackTrace()
            return true
        }
    }

    @UiThread
    open fun openFragment(baseFragment: BaseFragment) {
        if (mFragmentManager != null) {
            val fragmentTransaction = mFragmentManager!!.beginTransaction()
            fragmentTransaction.setCustomAnimations(
                R.anim.anim_slide_in_left, R.anim.anim_slide_out_left,
                R.anim.anim_slide_in_right, R.anim.anim_slide_out_right
            )
            val fragmentOnStack = getFragmentOnStack(baseFragment)
            if (fragmentOnStack != null) {
                removeFragmentsAbove(baseFragment, fragmentTransaction)
                if (fragmentOnStack is BaseFragment) {
                    fragmentOnStack.mBundle = baseFragment.mBundle
                }
                fragmentTransaction.show(fragmentOnStack)
                fragmentOnStack.onResume()
            } else {
                fragmentTransaction.add(R.id.fragment_container, baseFragment, baseFragment.javaClass.simpleName)
                fragmentTransaction.addToBackStack(baseFragment.javaClass.simpleName)
            }

            fragmentTransaction.commitAllowingStateLoss()
        }
    }

    private fun getFragmentOnStack(baseFragment: BaseFragment): Fragment? {
        if (mFragmentManager != null) {
            for (i in 0 until mFragmentManager!!.getBackStackEntryCount()) {
                val fragmentName = mFragmentManager?.getBackStackEntryAt(i)?.name
                if (fragmentName.equals(baseFragment.javaClass.simpleName))
                    return mFragmentManager?.findFragmentByTag(fragmentName)
            }
        }
        return null
    }

    private fun removeFragmentsAbove(baseFragment: BaseFragment, fragmentTransaction: FragmentTransaction) {
        try {
            if (mFragmentManager == null)
                return

            for (i in mFragmentManager!!.getBackStackEntryCount() - 1 downTo 1) {
                val fragmentName = mFragmentManager!!.getBackStackEntryAt(i).name
                if (fragmentName.equals(baseFragment.javaClass.simpleName))
                    return

                val fragmentByTag = mFragmentManager!!.findFragmentByTag(fragmentName)
                if (fragmentByTag is BaseFragment) {
                    val baseFragmentByTag = fragmentByTag as BaseFragment?
                    baseFragmentByTag!!.onRemoveFromBackStack()
                }
                fragmentTransaction.remove(fragmentByTag!!)
                mFragmentManager!!.popBackStackImmediate()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getTopFragment(): Fragment? {
        if (mFragmentManager != null && mFragmentManager!!.getBackStackEntryCount() > 0) {
            val topFragmentIndex = mFragmentManager!!.getBackStackEntryCount() - 1
            val topFragmentName = mFragmentManager!!.getBackStackEntryAt(topFragmentIndex).name
            return mFragmentManager!!.findFragmentByTag(topFragmentName)
        }
        return null
    }

    fun getSecondFragmentFromTop(): Fragment? {
        if (mFragmentManager != null && mFragmentManager!!.getBackStackEntryCount() > 1) {
            val secondFragmentFromTopIndex = mFragmentManager!!.getBackStackEntryCount() - 2
            val secondFragmentFromTopName = mFragmentManager!!.getBackStackEntryAt(secondFragmentFromTopIndex).name
            return mFragmentManager!!.findFragmentByTag(secondFragmentFromTopName)
        }
        return null
    }

    fun getThirdFragmentFromTop(): Fragment? {
        if (mFragmentManager != null && mFragmentManager!!.getBackStackEntryCount() > 2) {
            val thirdFragmentFromTopIndex = mFragmentManager!!.getBackStackEntryCount() - 3
            val thirdFragmentFromTopName = mFragmentManager!!.getBackStackEntryAt(thirdFragmentFromTopIndex).name
            return mFragmentManager!!.findFragmentByTag(thirdFragmentFromTopName)
        }
        return null
    }

}
