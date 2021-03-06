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
import android.widget.Toast
import com.example.itss_wsc.myapplication.Model.Events
import com.example.itss_wsc.myapplication.Model.Favorite
import com.example.itss_wsc.myapplication.Utils.EndPoint
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.json.JSONObject

class Favorite_matchesFragment : Fragment() {

    private var favorites: ArrayList<Favorite> = arrayListOf()
    private lateinit var adapter: FavoriteAdapter
    private lateinit var recycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorite_matches, container, false)

        try {
            showFavorite()
            recycler = view.findViewById(R.id.recyclerfavorite)

            Toast.makeText(context, favorites.size.toString(), Toast.LENGTH_SHORT).show()
            adapter = FavoriteAdapter(view.context, favorites) {
                getData() {
                    var obj = JSONObject(it)
                    var eventsArray = obj.getJSONArray("events")

                    try {
                        var objEvent = eventsArray.getJSONObject(0)
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


                        val event = Events(
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

                        var intt = Intent(view.context, MatchDetail::class.java)
                        intt.putExtra("events", event)
                        startActivity(intt)

                    } catch (e: Exception) {
                        Log.d("testing", e.toString())
                    }


                }.execute(EndPoint.matchDetailById(it.eventID))
            }
            recycler.layoutManager = LinearLayoutManager(view.context)
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()


        } catch (e: Exception) {
            Log.d("testing", e.toString())
        }

        return view
    }

    private fun showFavorite() {
        try {
            favorites.clear()
            context?.database?.use {
                val result = select(Favorite.TABLE_FAVORITE)
                Log.d("testing", result.toString())
                val rowParser = classParser<Favorite>()
                val favorite = result.parseList(rowParser)

                favorites.addAll(favorite)
            }
        } catch (e: Exception) {
            Log.d("testing", e.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
        adapter.notifyDataSetChanged()
    }
}