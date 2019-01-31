package com.example.itss_wsc.myapplication

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.itss_wsc.myapplication.Model.Teams
import kotlinx.android.synthetic.main.activity_match_detail.*
import kotlinx.android.synthetic.main.teams_customlayout.view.*

class TeamAdapter(
    private val context: Context,
    private val teams: List<Teams>,
    private val listener: (team: Teams) -> Unit
) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.teams_customlayout, parent, false), context)
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }


    class ViewHolder(val view: View, val mContext: Context) : RecyclerView.ViewHolder(view) {

        fun bindItem(team: Teams, listener: (team: Teams) -> Unit) {
            view.tvTeamName.text = team.strTeam;

            Glide.with(mContext)
                .load(team.strTeamBadge)
                .into(view.ivTeamLogo)

            view.setOnClickListener { listener(team) }
        }
    }
}