package com.example.itss_wsc.myapplication

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.example.itss_wsc.myapplication.Model.Events
import com.example.itss_wsc.myapplication.Model.Favorite
import com.example.itss_wsc.myapplication.R.drawable.ic_add_to_favorites
import com.example.itss_wsc.myapplication.R.drawable.ic_added_to_favorites
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.json.JSONObject

class MatchDetail : AppCompatActivity() {

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var event: Events

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
                    Favorite.TABLE_FAVORITE,
                    Favorite.EVENTID to event.idEvent
                    ,
                    "DATE" to event.strDate,
                    "HOMETEAM" to event.strHomeTeam,
                    "HOMESCORE" to event.intHomeScore,
                    "AWAYTEAM" to event.strAwayTeam,
                    "AWAYSCORE" to event.intAwayScore
                )
            }
            val sb = Snackbar.make(scrollview, "Added to favorite", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            val sb = Snackbar.make(scrollview, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENTID = ${event.idEvent})")
            }
            Snackbar.make(scrollview, "Removed to favorite", Snackbar.LENGTH_SHORT).show()

        } catch (e: SQLiteConstraintException) {
            Snackbar.make(scrollview, e.localizedMessage, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        try {
            if (isFavorite)
                menuItem?.findItem(R.id.add_to_favorite)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
            else
                menuItem?.findItem(R.id.add_to_favorite)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
        } catch (e: Exception) {
            Log.d("testing", e.toString())
        }
    }

    private fun favoriteState() {
        try {
            database.use {
                val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(EVENTID = ${event.idEvent})")

                val favorite = result.parseList(classParser<Favorite>())
                if (!favorite.isEmpty()) isFavorite = true
            }
        } catch (e: Exception) {
            Log.d("testing", e.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        try {
            event = intent.getParcelableExtra<Events>("events")

            favoriteState()

            tvDate.text = event.dateEvent
            tvHomeScore.text = if (event.intHomeScore == "null") "" else event.intHomeScore
            tvAwayScore.text = if (event.intAwayScore == "null") "" else event.intAwayScore
            tvHomeTeam.text = event.strHomeTeam
            tvAwayTeam.text = event.strAwayTeam

            tvHomeGoal.text = if (event.strHomeGoalDetails == "null") "" else event.strHomeGoalDetails
            tvAwayGoal.text = if (event.strAwayGoalDetails == "null") "" else event.strAwayGoalDetails

            tvHomeShots.text = if (event.intHomeShots == "null") "" else event.intHomeShots
            tvAwayShots.text = if (event.intAwayShots == "null") "" else event.intAwayShots

            tvHomeGoalKeeper.text = if (event.strHomeLineupGoalKeeper == "null") "" else event.strHomeLineupGoalKeeper
            tvAwayGoalKeeper.text = if (event.strAwayLineupGoalKeeper == "null") "" else event.strAwayLineupGoalKeeper
            tvHomeDefense.text = if (event.strHomeLineupDefense == "null") "" else event.strHomeLineupDefense
            tvAwayDefense.text = if (event.strAwayLineupDefense == "null") "" else event.strAwayLineupDefense
            tvHomeMidfield.text = if (event.strHomeLinupMidfield == "null") "" else event.strHomeLinupMidfield
            tvAwayMidfield.text = if (event.strAwayLinupMidfield == "null") "" else event.strAwayLinupMidfield
            tvHomeForward.text = if (event.strHomeLineupForward == "null") "" else event.strHomeLineupForward
            tvAwayForward.text = if (event.strAwayLineupForward == "null") "" else event.strAwayLineupForward
            tvHomeSubstitutes.text =
                    if (event.strHomeLineupSubstitutes == "null") "" else event.strHomeLineupSubstitutes
            tvAwaySubstitutes.text =
                    if (event.strAwayLineupSubstitutes == "null") "" else event.strAwayLineupSubstitutes


            var pb = findViewById<ProgressBar>(R.id.pbAll)
            try {
                getMatchData() {

                    var obj = JSONObject(it)
                    var teamsArray = obj.getJSONArray("teams")
                    var objTeam = teamsArray.getJSONObject(0)

                    var strTeamLogo = objTeam.getString("strTeamLogo")

                    Glide.with(this)
                        .load(strTeamLogo)
                        .into(ivHomeTeam)
                }.execute("https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=${event.idHomeTeam}")

                getMatchData() {

                    var obj = JSONObject(it)
                    var teamsArray = obj.getJSONArray("teams")
                    var objTeam = teamsArray.getJSONObject(0)

                    var strTeamLogo = objTeam.getString("strTeamLogo")

                    Glide.with(this)
                        .load(strTeamLogo)
                        .into(ivAwayTeam)
                }.execute("https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=${event.idAwayTeam}")

            } catch (e: Exception) {
                Log.d("testing", e.toString())
            }

        } catch (e: Exception) {
            Log.d("testing", e.toString())
        }
    }
}
