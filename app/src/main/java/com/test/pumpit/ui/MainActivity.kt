package com.test.pumpit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.test.pumpit.R
import com.test.pumpit.ui.issues.IssuesFragment
import com.test.pumpit.ui.youdrive.YoudriveFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * launches when the app starts
 * this needs a little changes
 */
class MainActivity : AppCompatActivity(), HasAndroidInjector {

    private val fm = supportFragmentManager
    private lateinit var issuesFragment: Fragment
    private lateinit var youdriveFragment: Fragment
    private lateinit var TAG_ISSUES: String
    private lateinit var TAG_YOUDRIVE: String

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        TAG_ISSUES = applicationContext.getString(R.string.title_issues)
        TAG_YOUDRIVE = applicationContext.getString(R.string.title_youDrive)

        supportActionBar!!.title = TAG_ISSUES

        bottom_nav_bar.setOnNavigationItemSelectedListener(navListener)

        //TODO: find a better way
        if (savedInstanceState == null) {
            issuesFragment = IssuesFragment()
            youdriveFragment = YoudriveFragment()

            fm.beginTransaction().add(R.id.main_fragment_host, youdriveFragment, TAG_YOUDRIVE).hide(youdriveFragment).commit()
            fm.beginTransaction().add(R.id.main_fragment_host, issuesFragment, TAG_ISSUES).commit()
        } else {
            issuesFragment = fm.findFragmentByTag(TAG_ISSUES)!!
            youdriveFragment = fm.findFragmentByTag(TAG_YOUDRIVE)!!
        }

    }

    /**
     * bottom bar tap custom listener
     * this need to be changed
     */
    private val navListener =
        BottomNavigationView.OnNavigationItemSelectedListener {item ->  
            //TODO: change?
            when (item.itemId) {
                R.id.nav_issues -> {
                    fm.beginTransaction().hide(youdriveFragment).show(issuesFragment).commit()
                    supportActionBar!!.title = TAG_ISSUES
                }

                R.id.nav_youDrive -> {
                    fm.beginTransaction().hide(issuesFragment).show(youdriveFragment).commit()
                    supportActionBar!!.title = TAG_YOUDRIVE
                }
            }

            true
        }

}
