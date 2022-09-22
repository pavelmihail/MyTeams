package com.practice.myteams.api

import com.practice.myteams.data.PlayerResponse
import com.practice.myteams.data.TeamPostResponse
import com.practice.myteams.data.TeamResponse
import com.practice.myteams.data.TeamTrasmit
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyTeamsAPI {

    //functii echipe
    @GET("api/android/echipemihail")
    suspend fun getTeam(): Response<TeamResponse>

    @POST("api/android/echipemihail")
    suspend fun postTeam(@Body teamTransmit: TeamTrasmit): Response<TeamPostResponse>

    //functii jucatori
    @GET("api/android/jucatorimihail")
    suspend fun getPlayers(): Response<PlayerResponse>
}