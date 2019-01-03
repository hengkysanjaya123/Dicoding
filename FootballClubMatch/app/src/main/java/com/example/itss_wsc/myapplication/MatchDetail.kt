package com.example.itss_wsc.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.example.itss_wsc.myapplication.Model.Events
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.json.JSONObject

class MatchDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        try {
            var event = intent.getParcelableExtra<Events>("events")
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
                GetMatchData(pb) {

                    var obj = JSONObject(it)
                    var teamsArray = obj.getJSONArray("teams")
                    var objTeam = teamsArray.getJSONObject(0)

                    var strTeamLogo = objTeam.getString("strTeamLogo")

                    Glide.with(this)
                        .load(strTeamLogo)
                        .into(ivHomeTeam)
                }.execute("https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=${event.idHomeTeam}")

                GetMatchData(pb) {

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
