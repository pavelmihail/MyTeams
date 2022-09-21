package com.practice.myteams.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.retrofitcrashcourse.RetrofitInstance
import com.practice.myteams.data.PlayerResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class PlayersViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    var liveData = MutableLiveData<PlayerResponse>()

    fun getLiveDataObserver(): MutableLiveData<PlayerResponse> {
        return liveData
    }

    init {
        getPlayer()
    }

    //    calls and error handling
    private fun getPlayer() {
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.api.getPlayers()
            } catch (e: IOException) {
                Log.e(ContentValues.TAG, "IOException, you might not have internet connection")
                return@launch
            } catch (e: HttpException) {
                Log.e(ContentValues.TAG, "HttpException, unexpected response")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                liveData.postValue(response.body())
            } else {
                Log.e(ContentValues.TAG, "Response not successful")
            }
        }
    }
}