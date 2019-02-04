package com.example.itss_wsc.myapplication.Model

data class TeamFavorite(
    val id: Long, val teamID: String, val teamName: String, val teamBadge: String
) {

    companion object {
        const val TABLE_TEAM_FAVORITE: String = "TABLE_TEAM_FAVORITE"
        const val ID: String = "ID"
        const val TEAMID: String = "TEAMID"
    }
}