package com.example.itss_wsc.myapplication.Utils

import android.net.Uri
import android.os.Build
import com.example.itss_wsc.myapplication.BuildConfig
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

//import java.text.SimpleDateFormat
//import java.util.*


object EndPoint {

    private fun baseApiUrl(): Uri.Builder {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
    }

    internal fun allLeagues(): String {
        return baseApiUrl()
            .appendPath("all_leagues.php")
            .build()
            .toString()
    }

    internal fun nextMatchesByLeagueId(leagueId: String): String {
        return baseApiUrl()
            .appendPath("eventsnextleague.php")
            .appendQueryParameter("id", leagueId)
            .build()
            .toString()
    }

    internal fun lastMatchesByLeagueId(leagueId: String): String {
        return baseApiUrl()
            .appendPath("eventspastleague.php")
            .appendQueryParameter("id", leagueId)
            .build()
            .toString()
    }

    internal fun matchDetailById(matchId: String): String {
        return baseApiUrl()
            .appendPath("lookupevent.php")
            .appendQueryParameter("id", matchId)
            .build()
            .toString()
    }

    internal fun listTeamsByLeague(leagueId: String): String {
        return baseApiUrl()
            .appendPath("lookup_all_teams.php")
            .appendQueryParameter("id", leagueId)
            .build()
            .toString()
    }

    internal fun getTeam(teamId: String): String {
        return baseApiUrl()
            .appendPath("lookupteam.php")
            .appendQueryParameter("id", teamId)
            .build()
            .toString()
    }

    internal fun getPlayers(teamId: String): String {
        return baseApiUrl()
            .appendPath("lookup_all_players.php")
            .appendQueryParameter("id", teamId)
            .build()
            .toString()
    }

    fun getTime(dateTime: String): String {
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"))
        val myDate = simpleDateFormat.parse(dateTime)

        val format = SimpleDateFormat("HH:mm")
        val short = format.format(myDate)

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            return LocalDateTime.parse(myDate.toString(), DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)).toString()
//        }
        return short
    }
}