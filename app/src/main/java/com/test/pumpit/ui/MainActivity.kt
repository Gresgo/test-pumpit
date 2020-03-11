package com.test.pumpit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.test.pumpit.R
import com.test.pumpit.ui.issues.IssuesFragment
import com.test.pumpit.ui.youdrive.YouDriveFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val fm = supportFragmentManager
    private lateinit var issuesFragment: Fragment
    private lateinit var youDriveFragment: Fragment
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
        youDriveFragment = YouDriveFragment()

        fm.beginTransaction().add(R.id.main_fragment_host, youDriveFragment, TAG_YOUDRIVE).hide(youDriveFragment).commit()
        fm.beginTransaction().add(R.id.main_fragment_host, issuesFragment, TAG_ISSUES).commit()
    }

    private val navListener =
        BottomNavigationView.OnNavigationItemSelectedListener {item ->  

            when (item.itemId) {
                R.id.nav_issues -> {
                    fm.beginTransaction().hide(youDriveFragment).show(issuesFragment).commit()
                    supportActionBar!!.title = TAG_ISSUES
                }

                R.id.nav_youDrive -> {
                    fm.beginTransaction().hide(issuesFragment).show(youDriveFragment).commit()
                    supportActionBar!!.title = TAG_YOUDRIVE
                }
            }

            true
        }

}
