package com.practice.myteams.api

import com.practice.myteams.data.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyTeamsAPI {

    //functii echipe
    @GET("api/android/echipemihail")
    suspend fun getTeam(): Response<TeamResponse>

    @POST("api/android/echipemihail")
    suspend fun postTeam(@Body teamTransmit: TeamTransmit): Response<PostResponse>

    //functii jucatori
    @GET("api/android/jucatorimihail")
    suspend fun getPlayers(): Response<PlayerResponse>

    @POST("api/android/jucatorimihail")
    suspend fun postPlayer(@Body playerTransmit: PlayerTransmit): Response<PostResponse>
}