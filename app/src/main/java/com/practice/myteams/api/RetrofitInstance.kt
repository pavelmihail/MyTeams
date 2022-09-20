package com.plcoding.retrofitcrashcourse

import com.practice.myteams.api.MyTeamsAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: MyTeamsAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://recrutare.compexin.ro")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyTeamsAPI::class.java)
    }
}