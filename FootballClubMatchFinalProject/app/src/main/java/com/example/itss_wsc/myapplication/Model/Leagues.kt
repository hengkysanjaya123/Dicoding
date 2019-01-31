package com.example.itss_wsc.myapplication.Model

data class League(var idLeague: String, var strLeague: String, var strSport: String, var strLeagueAlternate: String){

    override fun toString(): String {
        return strLeague;
        return super.toString()
    }
}