package com.example.itss_wsc.myapplication.Model

data class Favorite(
    val id: Long, val eventID: String
    , val date: String,
    val homeTeam: String, val homeScore: String,
    val awayTeam: String, val awayScore: String
) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID"
        const val EVENTID: String = "EVENTID"
    }
}