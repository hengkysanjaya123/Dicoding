package com.example.itss_wsc.myapplication

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    private var items: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            initData()

            MainActivityUI(this, items).setContentView(this)
        } catch (ex: Exception) {
            Log.d("abc", ex.toString())
        }
    }

    private fun initData() {
        try {
            val name = resources.getStringArray(R.array.club_name)
            val image = resources.obtainTypedArray(R.array.club_image)
            items.clear()
            for (i in name.indices) {
                items.add(
                    Item(
                        name[i],
                        image.getResourceId(i, 0),
                        "This is the description for ${name[i]} club.Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                    )
                )
            }
            //Recycle the typed array
            image.recycle()
        } catch (ex: Exception) {
            Log.d("abc", ex.toString())
        }
    }

    class MainActivityUI(private val context: Context, private val items: MutableList<Item>) :
        AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            verticalLayout {

                recyclerView {
                    layoutManager = LinearLayoutManager(context)
                    adapter = RecyclerViewAdapter(context, items) {
                        startActivity<Main2Activity>("item" to it)
                    }
                }
            }
        }
    }
}