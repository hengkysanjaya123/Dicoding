package com.example.itss_wsc.myapplication

import junit.framework.TestCase.assertEquals
import org.json.JSONObject
import org.junit.Test

class GetMatchDataTest{
    @Test
    fun GetMatchDetail() {
        GetMatchData {

            var obj = JSONObject(it)
            var teamsArray = obj.getJSONArray("teams")
            var objTeam = teamsArray.getJSONObject(0)

            var strLeague = objTeam.getString("strLeague")

            assertEquals("English Premier League", strLeague)

        }.execute("https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=133604")
    }
}