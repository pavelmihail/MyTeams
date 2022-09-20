package com.practice.myteams.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.retrofitcrashcourse.RetrofitInstance.api
import com.practice.myteams.api.TeamRespose
import kotlinx.coroutines.launch

enum class TeamApiStatus { LOADING, ERROR, DONE }

class TeamsViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    var status = MutableLiveData<TeamApiStatus>()

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsPhoto
    // with new values
    var teams : TeamRespose? = null

    init {
        getMarsPhotos()
    }
    //calls and error handling
    private fun getMarsPhotos() {
        viewModelScope.launch{
            val listResult = api.getTeam()
            teams = listResult.body()
            println(teams)
            status.value = TeamApiStatus.LOADING
            try {
                teams = api.getTeam().body()
                status.value = TeamApiStatus.DONE
            } catch (e: Exception) {
                status.value = TeamApiStatus.ERROR
                teams = null
            }
        }
    }
}

