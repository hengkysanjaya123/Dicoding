package com.example.itss_wsc.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.itss_wsc.myapplication.Model.Events
import org.json.JSONObject

class NextMatchFragment : Fragment() {

    private lateinit var recycler: RecyclerView
    private var events: ArrayList<Events> = arrayListOf()
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var v = inflater.inflate(R.layout.fragment_nextmatch, container, false)

        try {
            recycler = v.findViewById(R.id.recyclerResult2)
            adapter = RecyclerViewAdapter(v.context, events) {
                var intt = Intent(v.context, MatchDetail::class.java)
                intt.putExtra("events", it)
                startActivity(intt)
            }
            recycler.layoutManager = LinearLayoutManager(v.context)
            recycler.adapter = adapter

            var pb = v.findViewById<ProgressBar>(R.id.pbAll)

            GetMatchData(pb) {

                //                Toast.makeText(v.context, it, Toast.LENGTH_SHORT).show()

                var obj = JSONObject(it)
                var eventsArray = obj.getJSONArray("events")
                for (i in 0 until eventsArray.length()) {
                    try {
                        var objEvent = eventsArray.getJSONObject(i)
                        var idEvent = objEvent.getString("idEvent")
                        var strEvent = objEvent.getString("strEvent")
                        var strFilename = objEvent.getString("strFilename")
                        var strSport = objEvent.getString("strSport")
                        var idLeague = objEvent.getString("idLeague")
                        var strLeague = objEvent.getString("strLeague")
                        var strHomeTeam = objEvent.getString("strHomeTeam")
                        var intHomeScore = objEvent.getString("intHomeScore")
                        var intAwayScore = objEvent.getString("intHomeScore")
                        var strAwayTeam = objEvent.getString("strAwayTeam")
                        var intRound = objEvent.getString("intRound")
                        var dateEvent = objEvent.getString("dateEvent")
                        var strDate = objEvent.getString("strDate")
                        var strTime = objEvent.getString("strTime")
                        var strThumb = objEvent.getString("strThumb")

                        var strHomeGoalDetails = objEvent.getString("strHomeGoalDetails")
                        var strAwayGoalDetails = objEvent.getString("strAwayGoalDetails")

                        var intHomeShots = objEvent.getString("intHomeShots")
                        var intAwayShots = objEvent.getString("intAwayShots")

                        var strHomeLineupGoalKeeper = objEvent.getString("strHomeLineupGoalkeeper")
                        var strHomeLineupDefense = objEvent.getString("strHomeLineupDefense")
                        var strHomeLinupMidfield = objEvent.getString("strHomeLineupMidfield")
                        var strHomeLineupForward = objEvent.getString("strHomeLineupForward")
                        var strHomeLineupSubstitutes = objEvent.getString("strHomeLineupSubstitutes")

                        var strAwayLineupGoalKeeper = objEvent.getString("strAwayLineupGoalkeeper")
                        var strAwayLineupDefense = objEvent.getString("strAwayLineupDefense")
                        var strAwayLinupMidfield = objEvent.getString("strAwayLineupMidfield")
                        var strAwayLineupForward = objEvent.getString("strAwayLineupForward")
                        var strAwayLineupSubstitutes = objEvent.getString("strAwayLineupSubstitutes")
                        var idHomeTeam = objEvent.getString("idHomeTeam")
                        var idAwayTeam = objEvent.getString("idAwayTeam")

                        events.add(
                            Events(
                                idEvent,
                                strEvent,
                                strFilename,
                                strSport,
                                idLeague,
                                strLeague,
                                strHomeTeam,
                                intHomeScore,
                                intAwayScore,
                                strAwayTeam,
                                intRound,
                                dateEvent,
                                strDate,
                                strTime,
                                strThumb,
                                strHomeGoalDetails,
                                strAwayGoalDetails,
                                intHomeShots,
                                intAwayShots,
                                strHomeLineupGoalKeeper,
                                strHomeLineupDefense,
                                strHomeLinupMidfield,
                                strHomeLineupForward,
                                strHomeLineupSubstitutes,
                                strAwayLineupGoalKeeper,
                                strAwayLineupDefense,
                                strAwayLinupMidfield,
                                strAwayLineupForward,
                                strAwayLineupSubstitutes,
                                idHomeTeam,
                                idAwayTeam
                            )
                        )
                    } catch (e: Exception) {
                        Log.d("testing", e.toString())
                    }
                }

                adapter.notifyDataSetChanged()
            }.execute("https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328")
        } catch (e: Exception) {
            Log.d("testing", e.toString())
        }

        return v;
    }
}