package com.example.itss_wsc.myapplication

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.itss_wsc.myapplication.Model.TeamFavorite
import com.example.itss_wsc.myapplication.Model.Teams
import kotlinx.android.synthetic.main.activity_match_detail.*
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

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

        favoriteState()

        val teamOverView = team_overview_fragment()
        val b1 = Bundle()
        b1.putString("description", team.strDescriptionEN)
        teamOverView.arguments = b1
        adapter.addFragment(teamOverView, "OVERVIEWS")

        val teamPlayer = team_players_fragment()
        val b2 = Bundle()
        b2.putString("idTeam", team.idTeam)
        teamPlayer.arguments = b2

        adapter.addFragment(teamPlayer, "PLAYERS")

        viewPager.setAdapter(adapter)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    TeamFavorite.TABLE_TEAM_FAVORITE,
                    TeamFavorite.TEAMID to team.idTeam,
                    "TEAMNAME" to team.strTeam,
                    "TEAMBADGE" to team.strTeamBadge
                )
            }
            Snackbar.make(teamDetailParent, "Added to favorite", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Snackbar.make(teamDetailParent, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(TeamFavorite.TABLE_TEAM_FAVORITE, "(TEAMID = ${team.idTeam})")
            }
            Snackbar.make(teamDetailParent, "Removed to favorite", Snackbar.LENGTH_SHORT).show()

        } catch (e: SQLiteConstraintException) {
            Snackbar.make(teamDetailParent, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        try {
            if (isFavorite)
                menuItem?.findItem(R.id.add_to_favorite)?.icon = ContextCompat.getDrawable(this,
                    R.drawable.ic_added_to_favorites
                )
            else
                menuItem?.findItem(R.id.add_to_favorite)?.icon = ContextCompat.getDrawable(this,
                    R.drawable.ic_add_to_favorites
                )
        } catch (e: Exception) {
            Log.d("testing", e.toString())
        }
    }

    private fun favoriteState() {
        try {
            database.use {
                val result = select(TeamFavorite.TABLE_TEAM_FAVORITE)
                    .whereArgs("(TEAMID = ${team.idTeam})")

                val favorite = result.parseList(classParser<TeamFavorite>())
                if (!favorite.isEmpty()) isFavorite = true
            }
        } catch (e: Exception) {
            Log.d("testing", e.toString())
        }
    }
}
