package com.example.itss_wsc.myapplication.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Teams(val idTeam: String,
                 val strTeam: String,
                 val strTeamShort: String,
                 val strAlternate: String,
                 val intFormedYear: String,
                 val strSport: String,
                 val idLeague: String,
                 val strDivision: String,
                 val strManager: String,
                 val strStadium: String,
                 val strKeywords: String,
                 val strStadiumDescription: String,
                 val strStadiumLocation: String,
                 val intStadiumCapacity: String,
                 val strWebsite: String,
                 val strDescriptionEN: String,
                 val strTeamBadge: String,
                 val strCountry: String)
    : Parcelable{
}