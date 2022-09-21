package com.practice.myteams.api

import com.practice.myteams.data.PlayerResponse
import com.practice.myteams.data.TeamResponse
import retrofit2.Response
import retrofit2.http.GET
interface MyTeamsAPI {

    //functii echipe
    @GET("api/android/echipemihail")
    suspend fun getTeam(): Response<TeamResponse>

    //functii jucatori
    @GET("api/android/jucatorimihail")
    suspend fun getPlayers(): Response<PlayerResponse>
}