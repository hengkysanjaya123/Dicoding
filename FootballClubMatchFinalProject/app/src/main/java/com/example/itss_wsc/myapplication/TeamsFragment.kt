package com.example.itss_wsc.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.example.itss_wsc.myapplication.Model.League
import com.example.itss_wsc.myapplication.Model.Teams
import com.example.itss_wsc.myapplication.Utils.EndPoint
import org.json.JSONObject

class TeamsFragment : Fragment(), FragmentSearchInterface {

    private lateinit var recycler: RecyclerView
    private lateinit var spinner: Spinner
    private var leagues: ArrayList<League> = arrayListOf()
    private var listTeam: ArrayList<Teams> = arrayListOf()
    private lateinit var adapter: TeamAdapter
    private lateinit var mContext: Context


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_teams, container, false)


        try {
            mContext = v.context
            spinner = v.findViewById(R.id.spinnerLeague)
            recycler = v.findViewById(R.id.recyclerResultTeams)

            setHasOptionsMenu(true)

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
            Log.d("test:TeamsFragment", e.toString())
        }

        return v
    }


    fun loadData(leagueId: String, context: Context, pb: ProgressBar) {
        pb.visibility = View.VISIBLE

        listTeam.clear()

        adapter = TeamAdapter(mContext, listTeam) {
            var intt = Intent(mContext, TeamDetailActivity::class.java)
            intt.putExtra("team", it)
            startActivity(intt)
        }
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter

        getData() {
            val obj = JSONObject(it)
            val teamsArray = obj.getJSONArray("teams")

            for (i in 0 until teamsArray.length()) {
                val objTeam = teamsArray.getJSONObject(i)
                val idTeam = objTeam.getString("idTeam")
                val strTeam = objTeam.getString("strTeam")
                val strTeamShort = objTeam.getString("strTeamShort")
                val strAlternate = objTeam.getString("strAlternate")
                val intFormedYear = objTeam.getString("intFormedYear")
                val strSport = objTeam.getString("strSport")
                val idLeague = objTeam.getString("idLeague")
                val strDivision = objTeam.getString("strDivision")
                val strManager = objTeam.getString("strManager")
                val strStadium = objTeam.getString("strStadium")
                val strKeywords = objTeam.getString("strKeywords")
                val strStadiumDescription = objTeam.getString("strStadiumDescription")
                val strStadiumLocation = objTeam.getString("strStadiumLocation")
                val intStadiumCapacity = objTeam.getString("intStadiumCapacity")
                val strWebsite = objTeam.getString("strWebsite")
                val strDescriptionEN = objTeam.getString("strDescriptionEN")
                val strCountry = objTeam.getString("strCountry")
                val strTeamBadge = objTeam.getString("strTeamBadge")

                listTeam.add(
                    Teams(
                        idTeam,
                        strTeam,
                        strTeamShort,
                        strAlternate,
                        intFormedYear,
                        strSport,
                        idLeague,
                        strDivision,
                        strManager,
                        strStadium,
                        strKeywords,
                        strStadiumDescription,
                        strStadiumLocation,
                        intStadiumCapacity,
                        strWebsite,
                        strDescriptionEN,
                        strTeamBadge,
                        strCountry
                    )
                )
            }

            adapter.notifyDataSetChanged()
            pb.visibility = View.GONE

        }.execute(EndPoint.listTeamsByLeague(leagueId))
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)

        val searchView = menu?.findItem(R.id.actionSearch)?.actionView as SearchView?

        searchView?.queryHint = "Search Teams"

        searchView?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                OnSearch(newText)
                return false
            }
        })

    }

    override fun OnSearch(query: String) {
        try {
            val lowerQuery = query.toLowerCase()
            var test = listTeam.filter {
                it.strTeam.toLowerCase().contains(lowerQuery) ||
                        lowerQuery == ""
            }

            adapter = TeamAdapter(mContext, test) {
                var intt = Intent(mContext, TeamDetailActivity::class.java)
                intt.putExtra("team", it)
                startActivity(intt)
            }
            recycler.layoutManager = LinearLayoutManager(mContext)
            recycler.adapter = adapter

        } catch (e: Exception) {
            Log.d("test:TeamsFragment", e.toString())
        }
    }

}