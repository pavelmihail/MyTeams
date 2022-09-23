package com.practice.myteams.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.retrofitcrashcourse.RetrofitInstance.api
import com.practice.myteams.data.TeamResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class TeamsViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    var liveData = MutableLiveData<TeamResponse>()

    fun getLiveDataObserver(): MutableLiveData<TeamResponse> {
        return liveData
    }

    //    calls and error handling
    fun getTeams() {
        viewModelScope.launch {
            val response = try {
                api.getTeam()
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                liveData.postValue(response.body())
            } else {
                Log.e(TAG, "Response not successful")
            }
        }
    }
}

