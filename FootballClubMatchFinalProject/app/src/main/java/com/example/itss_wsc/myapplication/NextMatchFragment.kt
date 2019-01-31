package com.example.itss_wsc.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.example.itss_wsc.myapplication.Model.Events
import com.example.itss_wsc.myapplication.Model.League
import com.example.itss_wsc.myapplication.Utils.EndPoint
import org.json.JSONObject

class NextMatchFragment : Fragment(), FragmentSearchInterface {


    private lateinit var recycler: RecyclerView
    private lateinit var spinner: Spinner
    private var events: ArrayList<Events> = arrayListOf()
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var mContext: Context
    private var leagues: ArrayList<League> = arrayListOf()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var v = inflater.inflate(R.layout.fragment_nextmatch, container, false)

        try {
            mContext = v.context
            spinner = v.findViewById(R.id.spinnerLeague)
            recycler = v.findViewById(R.id.recyclerResult2)

            val pb = v.findViewById<ProgressBar>(R.id.pbAll)

            // populate spinner leagues data
            getData() {

                pb.visibility = View.VISIBLE

                var obj = JSONObject(it)
                var leaguesArray = obj.getJSONArray("leagues")

                for (i in 0 until leaguesArray.length()) {
                    var objLeague = leaguesArray.getJSONObject(i)
                    var idLeague = objLeague.getString("idLeague")
                    var strLeague = objLeague.getString("strLeague")
                    var strSport = objLeague.getString("strSport")
                    var strLeagueAlternate = objLeague.getString("strLeagueAlternate")
                    leagues.add(League(idLeague, strLeague, strSport, strLeagueAlternate))
                }

                spinner.adapter = ArrayAdapter<League>(v.context, android.R.layout.simple_spinner_item, leagues)

                pb.visibility = View.GONE
            }.execute(EndPoint.allLeagues())

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    loadData((spinner.selectedItem as League).idLeague, v.context, pb)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }


            loadData((spinner.selectedItem as League).idLeague, v.context, pb)


        } catch (e: Exception) {
            Log.d("testing", e.toString())
        }

        return v;
    }


    fun loadData(idLeague: String, context: Context, pb: ProgressBar) {

        pb.visibility = View.VISIBLE

        events.clear()

        adapter = RecyclerViewAdapter(context, events) {
            var intt = Intent(context, MatchDetail::class.java)
            intt.putExtra("events", it)
            startActivity(intt)
        }
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter

        getData() {

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
                    var intAwayScore = objEvent.getString("intAwayScore")
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
            pb.visibility = View.GONE

        }.execute(EndPoint.nextMatchesByLeagueId(idLeague))
    }

    override fun OnSearch(query: String) {
        try {
            val lowerQuery = query.toLowerCase()
            var original = events
            var test = events.filter {
                it.strHomeTeam.toLowerCase().contains(lowerQuery) ||
                        it.strAwayTeam.toLowerCase().contains(lowerQuery) ||
                        lowerQuery == ""
            }

            adapter = RecyclerViewAdapter(mContext, test) {
                var intt = Intent(mContext, MatchDetail::class.java)
                intt.putExtra("events", it)
                startActivity(intt)
            }
            recycler.layoutManager = LinearLayoutManager(mContext)
            recycler.adapter = adapter

        } catch (e: Exception) {
            Log.d("test", e.toString())
        }
    }
}