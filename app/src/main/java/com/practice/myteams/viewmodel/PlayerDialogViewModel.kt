package com.practice.myteams.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.retrofitcrashcourse.RetrofitInstance
import com.practice.myteams.data.PlayerTransmit
import com.practice.myteams.data.PostResponse
import com.practice.myteams.data.TeamTransmit
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class PlayerDialogViewModel: ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private var liveData = MutableLiveData<PostResponse>()
    private lateinit var teamPost: TeamTransmit

    fun getLiveDataObserver(): MutableLiveData<PostResponse> {
        return liveData
    }

    //    calls and error handling
    fun postPlayer(playerPost: PlayerTransmit) {
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.api.postPlayer(playerPost)
            } catch (e: IOException) {
                Log.e(ContentValues.TAG, "IOException, you might not have internet connection")
                return@launch
            } catch (e: HttpException) {
                Log.e(ContentValues.TAG, "HttpException, unexpected response")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                liveData.postValue(response.body())
                RetrofitInstance.api.getPlayers()
            } else {
                println(response.body())
                Log.e(ContentValues.TAG, "Response not successful")
            }
        }
    }
}