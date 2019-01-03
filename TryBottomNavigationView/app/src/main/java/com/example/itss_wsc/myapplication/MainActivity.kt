package com.example.itss_wsc.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.example.itss_wsc.myapplication.R.id.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                teams -> {
                    loadPrev(savedInstanceState)
                }
                favorites -> {
                    loadNext(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = teams

    }

    private fun loadPrev(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, PrevMatchFragment(), PrevMatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadNext(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, NextMatchFragment(), NextMatchFragment::class.java.simpleName)
                .commit()
        }
    }
}
