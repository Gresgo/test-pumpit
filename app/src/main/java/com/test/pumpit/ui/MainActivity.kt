package com.test.pumpit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.test.pumpit.R
import com.test.pumpit.ui.issues.IssuesFragment
import com.test.pumpit.ui.youdrive.YoudriveFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * launches when the app starts
 * this needs a little changes
 */
class MainActivity : AppCompatActivity() {

    private val fm = supportFragmentManager
    private lateinit var issuesFragment: Fragment
    private lateinit var youdriveFragment: Fragment
    private lateinit var TAG_ISSUES: String
    private lateinit var TAG_YOUDRIVE: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TAG_ISSUES = applicationContext.getString(R.string.title_issues)
        TAG_YOUDRIVE = applicationContext.getString(R.string.title_youDrive)

        supportActionBar!!.title = TAG_ISSUES

        bottom_nav_bar.setOnNavigationItemSelectedListener(navListener)

        issuesFragment = IssuesFragment()
        youdriveFragment = YoudriveFragment()

        fm.beginTransaction().add(R.id.main_fragment_host, youdriveFragment, TAG_YOUDRIVE).hide(youdriveFragment).commit()
        fm.beginTransaction().add(R.id.main_fragment_host, issuesFragment, TAG_ISSUES).commit()
    }

    /**
     * bottom bar tap custom listener
     */
    private val navListener =
        BottomNavigationView.OnNavigationItemSelectedListener {item ->  

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
