package com.example.itss_wsc.myapplication

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.itss_wsc.myapplication.Model.Events
import com.example.itss_wsc.myapplication.Utils.EndPoint
import kotlinx.android.synthetic.main.match_customlayout.view.*

class RecyclerViewAdapter(
    private val context: Context,
    private val events: List<Events>,
    private val listener: (events: Events) -> Unit
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.match_customlayout, parent, false))
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(events: Events, listener: (events: Events) -> Unit) {
            try {
                view.tvHomeTeam.text = events.strHomeTeam
                view.tvHomeScore.text = if (events.intHomeScore == "null") "" else events.intHomeScore
                view.tvAwayTeam.text = events.strAwayTeam
                view.tvAwayScore.text = if (events.intAwayScore == "null") "" else events.intAwayScore

                view.tvDate.text = events.strDate
                view.tvTime.text = EndPoint.getTime(events.strDate + ' ' + events.strTime)

                view.setOnClickListener {
                    listener(events)
                }

            } catch (e: Exception) {
                Log.d("testing", e.toString())
            }
        }
    }
}