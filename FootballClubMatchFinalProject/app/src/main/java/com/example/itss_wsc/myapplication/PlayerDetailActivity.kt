package com.example.itss_wsc.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.itss_wsc.myapplication.Model.Players
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        try {

            val player = intent.getParcelableExtra<Players>("player")
            Glide.with(this)
                .load(player.strFanart1)
                .into(ivPlayerPicture)

            tvPlayerName.text = player.strPlayer
            tvPlayerWeight.text = player.strWeight
            tvPlayerHeight.text = player.strHeight
            tvPlayerDescription.text = player.strDescriptionEN

            supportActionBar?.title = player.strPlayer
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        } catch (e: Exception) {
            Log.d("test:PlayerDetail", e.toString())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
