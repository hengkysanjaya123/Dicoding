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
import com.bumptech.glide.Glide
import com.example.itss_wsc.myapplication.Model.Players
import com.example.itss_wsc.myapplication.Utils.EndPoint
import kotlinx.android.synthetic.main.players_customlayout.view.*
import org.json.JSONObject


class team_players_fragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private var players: ArrayList<Players> = arrayListOf()
    private var idTeam: String = ""
    private lateinit var adapter: TeamPlayerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_team_players, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        try {
            if (arguments != null) {
                idTeam = arguments!!.getString("idTeam")
            }
            Log.d("test:TeamPlayer", "idTeam:$idTeam")
            players.clear()

            getData() {
                val obj = JSONObject(it)
                Log.d("test:TeamPlayer", it)
                val playerArray = obj.getJSONArray("player")

                for (i in 0 until playerArray.length()) {
                    val objPlayer = playerArray.getJSONObject(i)

                    val idPlayer = objPlayer.getString("idPlayer")
                    val idTeam = objPlayer.getString("idTeam")
                    val strNationality = objPlayer.getString("strNationality")
                    val strPlayer = objPlayer.getString("strPlayer")
                    val strTeam = objPlayer.getString("strTeam")
                    val strSport = objPlayer.getString("strSport")
                    val intSOccerXMLTeamID = objPlayer.getString("intSoccerXMLTeamID")
                    val dateBorn = objPlayer.getString("dateBorn")
                    val dateSigned = objPlayer.getString("dateSigned")
                    val strSigning = objPlayer.getString("strSigning")
                    val strBirthLocation = objPlayer.getString("strBirthLocation")
                    val strDescriptionEN = objPlayer.getString("strDescriptionEN")
                    val strGender = objPlayer.getString("strGender")
                    val strPosition = objPlayer.getString("strPosition")
                    val strHeight = objPlayer.getString("strHeight")
                    val strWeight = objPlayer.getString("strWeight")
                    val intLoved = objPlayer.getString("intLoved")
                    val strCutout = objPlayer.getString("strCutout")
                    val strFanart1 = objPlayer.getString("strFanart1")

                    players.add(
                        Players(
                            idPlayer, idTeam, strNationality, strPlayer, strTeam, strSport,
                            intSOccerXMLTeamID, dateBorn, dateSigned, strSigning,
                            strBirthLocation, strDescriptionEN, strGender, strPosition,
                            strHeight, strWeight, intLoved, strCutout, strFanart1
                        )
                    )
                }

                recyclerView = view.findViewById(R.id.recyclerResultPlayer)
                recyclerView.layoutManager = LinearLayoutManager(view.context)
                Log.d("test:TeamPlayer", players.size.toString())
                adapter = TeamPlayerAdapter(view.context, players) {
                    Log.d("test:TeamPlayer", "clicked")
                    val intt = Intent(context, PlayerDetailActivity::class.java)
                    intt.putExtra("player", it)
                    startActivity(intt)
                }
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()

            }.execute(EndPoint.getPlayers(idTeam))


        } catch (e: Exception) {
            Log.d("test:TeamPlayer", e.toString())
        }
    }

    class TeamPlayerAdapter(
        private val context: Context,
        private val players: List<Players>,
        private val listener: (player: Players) -> Unit
    ) : RecyclerView.Adapter<TeamPlayerAdapter.ViewHolder>() {

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(context).inflate(R.layout.players_customlayout, p0, false), context)
        }

        override fun getItemCount(): Int {
            return players.size
        }

        override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
            p0.BindItem(players[p1], listener)
        }

        class ViewHolder(val view: View, val mContext: Context) : RecyclerView.ViewHolder(view) {
            fun BindItem(player: Players, listener: (player: Players) -> Unit) {
                try {
                    Glide.with(mContext)
                        .load(player.strCutout)
                        .into(view.ivPlayerPhoto)
                } catch (e: Exception) {
                    Log.d("test:TeamPlayer", e.toString())
                }

                view.tvPlayerName.text = player.strPlayer
                view.tvPlayerPosition.text = player.strPosition

                view.setOnClickListener { listener(player) }
            }
        }
    }
}
