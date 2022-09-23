package com.practice.myteams.api

import com.practice.myteams.data.*
import retrofit2.Response
import retrofit2.http.*

interface MyTeamsAPI {

    //functii echipe
    @GET("api/android/echipemihail")
    suspend fun getTeam(): Response<TeamResponse>

    @POST("api/android/echipemihail")
    suspend fun postTeam(@Body teamTransmit: TeamTransmit): Response<ResponseAPI>

    @PUT("api/android/echipemihail")
    suspend fun putTeam(@Body teamPut: TeamPutBody): Response<ResponseAPI>

    @HTTP(method = "DELETE", path = "/api/android/echipemihail", hasBody = true)
    suspend fun deleteTeam(@Body teamDelete: TeamDeleteBody): Response<ResponseAPI>

    @POST("api/android/echipemihail/restore")
    suspend fun postRestoreTeam(@Body teamRestore: TeamDeleteBody): Response<ResponseAPI>

    //functii jucatori
    @GET("api/android/jucatorimihail")
    suspend fun getPlayers(): Response<PlayerResponse>

    @POST("api/android/jucatorimihail")
    suspend fun postPlayer(@Body playerTransmit: PlayerTransmit): Response<ResponseAPI>

    @PUT("api/android/jucatorimihail")
    suspend fun putPlayer(@Body playerPut: PlayerPutBody): Response<ResponseAPI>

    @HTTP(method = "DELETE", path = "/api/android/jucatorimihail", hasBody = true)
    suspend fun deletePlayer(@Body playerDelete: PlayerDeleteBody): Response<ResponseAPI>

    @POST("api/android/jucatorimihail/restore")
    suspend fun postResotrePlayer(@Body playerRestore: PlayerTransmit): Response<ResponseAPI>
}