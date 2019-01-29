package com.example.itss_wsc.myapplication

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ApiClientTest {
    @Test
    fun testDoRequest() {
        val apiRepository = mock(ApiClient::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League"
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }
}