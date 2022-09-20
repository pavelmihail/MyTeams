package com.practice.myteams.api

import retrofit2.Response
import retrofit2.http.GET
interface MyTeamsAPI {

    //functii echipe
    @GET("api/android/echipemihail")
    suspend fun getTeam(): Response<TeamRespose>

    //functii jucatori
    @GET("api/android/jucatorimihail")
    suspend fun getPlayers(): Response<TeamRespose>
}