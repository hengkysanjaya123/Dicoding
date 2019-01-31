package com.example.itss_wsc.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.itss_wsc.myapplication.Model.Teams
import kotlinx.android.synthetic.main.activity_team_detail.*

class TeamDetailActivity : AppCompatActivity() {

    private lateinit var adapter: TabAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var team: Teams

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewPager = findViewById<ViewPager>(R.id.viewpagerTeam)
        tabLayout = findViewById<TabLayout>(R.id.tabs)

        adapter = TabAdapter(supportFragmentManager)


        team = intent.getParcelableExtra<Teams>("team")
        Glide.with(this)
            .load(team.strTeamBadge)
            .into(ivBadge)

        tvTeamName.text = team.strTeam
        tvFormedYear.text = team.intFormedYear
        tvStadium.text = team.strStadium


        val teamOverView = team_overview_fragment()
        val b1 = Bundle()
        b1.putString("description", team.strDescriptionEN)
        teamOverView.arguments = b1
        adapter.addFragment(teamOverView, "OVERVIEWS")

        adapter.addFragment(NextMatchFragment(), "PLAYERS")
        viewPager.setAdapter(adapter)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
//        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
//                if (isFavorite) removeFromFavorite() else addToFavorite()
//
//                isFavorite = !isFavorite
//                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
