package com.example.itss_wsc.myapplication.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Events(
    var idEvent: String,
    var strEvent: String,
    var strFilename: String,
    var strSport: String,
    var idLeague: String,
    var strLeague: String,
    var strHomeTeam: String,
    var intHomeScore: String,
    var intAwayScore: String,
    var strAwayTeam: String,
    var intRound: String,
    var dateEvent: String,
    var strDate: String,
    var strTime: String,
    var strThumb: String,

    var strHomeGoalDetails: String,
    var strAwayGoalDetails: String,

    var intHomeShots: String,
    var intAwayShots: String,

    var strHomeLineupGoalKeeper: String,
    var strHomeLineupDefense: String,
    var strHomeLinupMidfield: String,
    var strHomeLineupForward: String,
    var strHomeLineupSubstitutes: String,

    var strAwayLineupGoalKeeper: String,
    var strAwayLineupDefense: String,
    var strAwayLinupMidfield: String,
    var strAwayLineupForward: String,
    var strAwayLineupSubstitutes: String,
    var idHomeTeam: String,
    var idAwayTeam: String
) : Parcelable