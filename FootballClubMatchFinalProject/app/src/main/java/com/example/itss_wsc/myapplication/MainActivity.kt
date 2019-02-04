package com.example.itss_wsc.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.matchesmenu -> {
                    loadMatches(savedInstanceState)
                }
                R.id.teamsMenu -> {
                    loadTeams(savedInstanceState)
                }
                R.id.favorites -> {
                    loadFavorite(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.matchesmenu
    }




    private fun loadTeams(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, TeamsFragment(), TeamsFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadMatches(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, MatchesFragment(), MatchesFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavorite(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, Favorites_Fragment(), Favorites_Fragment::class.java.simpleName)
                .commit()
        }
    }
}
