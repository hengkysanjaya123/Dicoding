package com.example.itss_wsc.myapplication.Utils

import junit.framework.Assert.assertEquals
import org.junit.Test


class EndPointTest {
    companion object {
        const val LEAGUE_ID = "4328"
    }

    @Test
    fun testNextMatchesByLeagueId() {
        val expected = "https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=$LEAGUE_ID"
        val actual = EndPoint.nextMatchesByLeagueId(LEAGUE_ID)
        assertEquals(expected, actual)
    }

    @Test
    fun testLastMatchesByLeagueId() {
        val expected = "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=$LEAGUE_ID"
        val actual = EndPoint.lastMatchesByLeagueId(LEAGUE_ID)
        assertEquals(expected, actual)
    }

    @Test
    fun textMatchDetailById() {
        val expected = "https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id=$LEAGUE_ID"
        val actual = EndPoint.matchDetailById(LEAGUE_ID)
        assertEquals(expected, actual)
    }

    @Test
    fun testListTeamsByLeague() {
        val expected = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League"
        val actual = EndPoint.listTeamsByLeague("English Premier League")
        assertEquals(expected, actual)
    }

    @Test
    fun testGetTeam() {
        val teamId = "133604"
        val expected = "https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=$teamId"
        val actual = EndPoint.getTeam(teamId)
        assertEquals(expected, actual)
    }
}