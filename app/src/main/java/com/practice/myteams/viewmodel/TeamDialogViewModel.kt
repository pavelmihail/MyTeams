package com.practice.myteams.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.retrofitcrashcourse.RetrofitInstance
import com.plcoding.retrofitcrashcourse.RetrofitInstance.api
import com.practice.myteams.data.PlayerResponse
import com.practice.myteams.data.TeamPostResponse
import com.practice.myteams.data.TeamTrasmit
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class TeamDialogViewModel: ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private var liveData = MutableLiveData<TeamPostResponse>()
    private lateinit var teamPost: TeamTrasmit

    fun getLiveDataObserver(): MutableLiveData<TeamPostResponse> {
        return liveData
    }

//    init {
//        getPlayer()
//    }

    //    calls and error handling
    fun postTeam(teamPost: TeamTrasmit) {
        viewModelScope.launch {
            val response = try {
                api.postTeam(teamPost)
            } catch (e: IOException) {
                Log.e(ContentValues.TAG, "IOException, you might not have internet connection")
                return@launch
            } catch (e: HttpException) {
                Log.e(ContentValues.TAG, "HttpException, unexpected response")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                liveData.postValue(response.body())
                api.getTeam()
            } else {
                Log.e(ContentValues.TAG, "Response not successful")
            }
        }
    }
}