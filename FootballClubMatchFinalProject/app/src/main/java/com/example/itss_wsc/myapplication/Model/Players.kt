package com.example.itss_wsc.myapplication.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Players(
    val idPlayer: String,
    val idTeam: String,
    val strNationality: String,
    val strPlayer: String,
    val strTeam: String,
    val strSport: String,
    val intSOccerXMLTeamID: String,
    val dateBorn: String,
    val dateSIgned: String,
    val strSigning: String,
    val strBirthLocation: String,
    val strDescriptionEN: String,
    val strGender: String,
    val strPosition: String,
    val strHeight: String,
    val strWeight: String,
    val intLoved: String,
    val strCutout: String,
    val strFanart1: String
): Parcelable