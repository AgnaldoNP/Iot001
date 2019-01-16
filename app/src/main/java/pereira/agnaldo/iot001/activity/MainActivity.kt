package pereira.agnaldo.iot001.activity

import android.annotation.SuppressLint
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import pereira.agnaldo.iot001.R
import pereira.agnaldo.iot001.database.entity.User
import pereira.agnaldo.iot001.extensions.showMessage
import pereira.agnaldo.iot001.fragment.AboutFragment_
import pereira.agnaldo.iot001.fragment.CardProductionListFragment_
import pereira.agnaldo.iot001.fragment.ConfigurationFragment_
import com.rom4ek.arcnavigationview.ArcNavigationView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import org.androidannotations.annotations.*

@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
open class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    @ViewById(R.id.main_activity_toolbar)
    protected open lateinit var mToolbar: Toolbar

    @ViewById(R.id.main_activity_drawer_layout)
    protected open lateinit var mDrawer: DrawerLayout

    @ViewById(R.id.main_activity_nav_view)
    protected open lateinit var mNavigationView: ArcNavigationView

    private lateinit var mCurrentUser: User

    @AfterViews
    protected open fun afterViews() {
        super.onCreate()

        mNavigationView.setNavigationItemSelectedListener(this)
        mToolbar.title = getString(R.string.app_name)
        mToolbar.subtitle = ""

        setSupportActionBar(mToolbar)
        val toggle = ActionBarDrawerToggle(
            this, mDrawer, mToolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        mDrawer.addDrawerListener(toggle)
        toggle.syncState()

        invalidateOptionsMenu()

        val cardProductionListFragment = CardProductionListFragment_.builder().build()
        openFragment(cardProductionListFragment)

        loadUser()
    }

    @Background
    protected open fun loadUser() {
        mCurrentUser = getDatabase().daoUser().getUser()
        prepareDrawerUserInformation()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> openFragment(CardProductionListFragment_.builder().build())

            R.id.nav_about -> openFragment(AboutFragment_.builder().build())

            R.id.nav_settings -> openFragment(ConfigurationFragment_.builder().build())
        }

        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START)
        }

        return true
    }

    @UiThread
    protected open fun prepareDrawerUserInformation() {
        val headerView = mNavigationView.getHeaderView(0)
        val userAvatar: CircleImageView = headerView.findViewById(R.id.user_avatar)
        Picasso.get().load(mCurrentUser.photoUrl).into(userAvatar)

        val userNameView: TextView = headerView.findViewById(R.id.user_name)
        userNameView.text = mCurrentUser.name

        val userEmailView: TextView = headerView.findViewById(R.id.user_email)
        userEmailView.text = mCurrentUser.email

        (headerView.findViewById(R.id.icon_exit) as View).setOnClickListener {
            showMessage(
                R.string.really_exit_question, R.string.yes, View.OnClickListener { logout() }, R.string.no
            )
        }
    }

    private fun logout() {
        deleteDatabase()
    }

    @Background
    protected open fun deleteDatabase() {
        getDatabase().daoUser().deleteAll()
    }


}
