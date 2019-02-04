package com.example.itss_wsc.myapplication

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.itss_wsc.myapplication.Model.TeamFavorite
import kotlinx.android.synthetic.main.favorite_teams_custom_layout.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteTeamsFragment : Fragment() {

    private var teamFavorites: ArrayList<TeamFavorite> = arrayListOf()
    private lateinit var adapter: FavoriteTeamAdapter
    private lateinit var recycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorite_teams, container, false)

        try {
            loadFavorites()
            recycler = view.findViewById(R.id.recyclerfavorite)

            Toast.makeText(context, teamFavorites.size.toString(), Toast.LENGTH_SHORT).show()

            adapter = FavoriteTeamAdapter(view.context, teamFavorites){

            }
            recycler.layoutManager = LinearLayoutManager(view.context)
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()


        } catch (e: Exception) {
            Log.d("testing", e.toString())
        }

        return view
    }

    private fun loadFavorites() {
        try {
            teamFavorites.clear()
            context?.database?.use {
                val result = select(TeamFavorite.TABLE_TEAM_FAVORITE)
                Log.d("testing", result.toString())
                val rowParser = classParser<TeamFavorite>()
                val favorite = result.parseList(rowParser)

                teamFavorites.addAll(favorite)
            }
        } catch (e: Exception) {
            Log.d("testing", e.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        loadFavorites()
        adapter.notifyDataSetChanged()
    }
}


class FavoriteTeamAdapter(
    private val context: Context,
    private val teamFavorite: List<TeamFavorite>,
    private val listener: (TeamFavorite) -> Unit
) : RecyclerView.Adapter<FavoriteTeamAdapter.FavoriteTeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): FavoriteTeamViewHolder {
        return FavoriteTeamViewHolder(LayoutInflater.from(context).inflate(R.layout.favorite_teams_custom_layout, parent, false), context)
    }

    override fun getItemCount(): Int {
        return teamFavorite.size
    }

    override fun onBindViewHolder(holder: FavoriteTeamViewHolder, position: Int) {
        try {
            holder.bindItem(teamFavorite[position], listener)
        } catch (e: Exception) {
            Log.d("testing", e.toString())
        }
    }

    class FavoriteTeamViewHolder(var view: View, val mContext: Context) : RecyclerView.ViewHolder(view) {

        fun bindItem(teamFavorite: TeamFavorite, listener: (TeamFavorite) -> Unit) {
            try {

                view.tvTeamName.text = teamFavorite.teamName
                Glide.with(mContext)
                    .load(teamFavorite.teamBadge)
                    .into(view.ivTeamPhoto)

                view.setOnClickListener {
                    listener(teamFavorite)
                }

            } catch (e: Exception) {
                Log.d("testing", e.toString())
            }
        }
    }
}