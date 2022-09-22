package com.practice.myteams.views.activities

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.plcoding.retrofitcrashcourse.RetrofitInstance.api
import com.practice.myteams.R
import com.practice.myteams.data.TeamTrasmit
import com.practice.myteams.databinding.ActivityAddEditTeamBinding
import retrofit2.HttpException
import java.io.IOException

class AddEditTeamActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditTeamBinding
    private lateinit var teamName: TeamTrasmit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //set up back button
        setContentView(R.layout.activity_main)
        // calling the action bar
        var actionBar = getSupportActionBar()
        // showing the back button in action bar
        if (actionBar != null) {
            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        binding = ActivityAddEditTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        teamName = TeamTrasmit("Android")
        binding.addTeamBtn.setOnClickListener {

            teamName.DENUMIRE = binding.nameTeam.text.toString()
            lifecycleScope.launchWhenCreated {

                val response = try {
                    api.postTeam(teamName)
                } catch (e: IOException) {
                    Log.e(TAG, "IOException, you might not have internet connection")
                    return@launchWhenCreated
                } catch (e: HttpException) {
                    Log.e(TAG, "HttpException, unexpected response")
                    return@launchWhenCreated
                }
                if (response.isSuccessful && response.body() != null) {
                    Toast.makeText(
                        this@AddEditTeamActivity,
                        "Echipa a fost adaugata",
                        Toast.LENGTH_SHORT
                    ).show()
                    api.getTeam()
                } else {
                    Toast.makeText(
                        this@AddEditTeamActivity,
                        "Nu s-a putut adauga echipa",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e(TAG, "Response not successful")
                }
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

}
