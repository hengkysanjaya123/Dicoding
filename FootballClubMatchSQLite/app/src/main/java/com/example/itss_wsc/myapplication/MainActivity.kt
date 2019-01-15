package com.example.itss_wsc.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.prevmatch -> {
                    loadLast(savedInstanceState)
                }
                R.id.nextmatch -> {
                    loadNext(savedInstanceState)
                }
                R.id.favorites -> {
                    loadFavorite(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.prevmatch
    }




    private fun loadNext(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, NextMatchFragment(), NextMatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadLast(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, LastMatchFragment(), LastMatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavorite(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                .commit()
        }
    }
}
