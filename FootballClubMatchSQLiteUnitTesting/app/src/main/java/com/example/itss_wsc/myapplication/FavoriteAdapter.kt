package com.example.itss_wsc.myapplication

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.itss_wsc.myapplication.Model.Favorite
import kotlinx.android.synthetic.main.favorite_custom_layout.view.*

class FavoriteAdapter(
    private val context: Context,
    private val favorite: List<Favorite>,
    private val listener: (Favorite) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(context).inflate(R.layout.favorite_custom_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return favorite.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        try {
            holder.bindItem(favorite[position], listener)
        } catch (e: Exception) {
            Log.d("testing", e.toString())
        }
    }

    class FavoriteViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit) {
            try {
                view.tvDate.text = favorite.date
                view.tvHomeTeam.text = favorite.homeTeam
                view.tvHomeScore.text = if (favorite.homeScore == "null") "" else favorite.homeScore
                view.tvAwayTeam.text = favorite.awayTeam
                view.tvAwayScore.text = if (favorite.awayScore == "null") "" else favorite.awayScore

                view.setOnClickListener {
                    listener(favorite)
                }

            } catch (e: Exception) {
                Log.d("testing", e.toString())
            }
        }
    }
}