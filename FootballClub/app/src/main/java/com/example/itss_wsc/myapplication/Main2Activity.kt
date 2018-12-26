package com.example.itss_wsc.myapplication

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class Main2Activity : AppCompatActivity() {

    private lateinit var currentItem: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        currentItem = intent.getParcelableExtra("item") as Item

        Main2ActivityUI(currentItem).setContentView(this)
    }

    class Main2ActivityUI(private val currentItem: Item) : AnkoComponent<Main2Activity> {
        override fun createView(ui: AnkoContext<Main2Activity>) = with(ui) {
            verticalLayout {
                padding = dip(16)

                currentItem.image?.let {
                    Picasso.get().load(it).fit().into(imageView().lparams(width = dip(100), height = dip(100)) {
                        gravity = Gravity.CENTER_HORIZONTAL
                    })
                }

                textView {
                    text = currentItem.name
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }
                }.lparams(width = matchParent) {
                    topMargin = dip(8)
                }

                textView {
                    text = currentItem.description
                }.lparams(width = matchParent) {
                    topMargin = dip(8)
                }
            }
        }

    }
}
