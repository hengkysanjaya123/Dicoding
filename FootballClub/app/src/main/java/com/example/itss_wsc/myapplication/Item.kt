package com.example.itss_wsc.myapplication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(val name: String?, val image: Int?, val description: String) : Parcelable