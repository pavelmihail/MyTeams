package com.practice.myteams.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.retrofitcrashcourse.RetrofitInstance.api
import com.practice.myteams.data.ResponseAPI
import com.practice.myteams.data.TeamDeleteBody
import com.practice.myteams.data.TeamPutBody
import com.practice.myteams.data.TeamTransmit
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class TeamDialogViewModel: ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private var LiveData = MutableLiveData<ResponseAPI>()
    private lateinit var teamPost: TeamTransmit

    fun getLiveDataObserver(): MutableLiveData<ResponseAPI> {
        return LiveData
    }

    //    calls and error handling
    fun postTeam(teamPost: TeamTransmit) {
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
                LiveData.postValue(response.body())
                api.getTeam()
            } else {
                Log.e(ContentValues.TAG, "Response not successful")
            }
        }
    }

    fun putTeam(teamPut: TeamPutBody) {
        viewModelScope.launch {
            val response = try {
                api.putTeam(teamPut)
            } catch (e: IOException) {
                Log.e(ContentValues.TAG, "IOException, you might not have internet connection")
                return@launch
            } catch (e: HttpException) {
                Log.e(ContentValues.TAG, "HttpException, unexpected response")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                LiveData.postValue(response.body())
                api.getTeam()
            } else {
                Log.e(ContentValues.TAG, "Response not successful")
            }
        }
    }

    fun deleteTeam(teamDelete: TeamDeleteBody) {
        viewModelScope.launch {
            val response = try {
                api.deleteTeam(teamDelete)
            } catch (e: IOException) {
                Log.e(ContentValues.TAG, "IOException, you might not have internet connection")
                return@launch
            } catch (e: HttpException) {
                Log.e(ContentValues.TAG, "HttpException, unexpected response")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                LiveData.postValue(response.body())
                api.getTeam()
            } else {
                Log.e(ContentValues.TAG, "Response not successful")
            }
        }
    }
}