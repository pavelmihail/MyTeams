package com.practice.myteams.views.activities

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.practice.myteams.R
import com.practice.myteams.databinding.ActivityMainBinding
import com.practice.myteams.views.fragments.PlayersFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val PLAYERS_DESTINATION = "Players"
    private val TEAMS_DESTINATION = "Teams"
    private var isPlayersFragmentOn = false
    private var isTeamsFragmentOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_players, R.id.navigation_teams
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //check what fragment is displayed
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.label == PLAYERS_DESTINATION){
                isPlayersFragmentOn = true
                isTeamsFragmentOn = false
            }
            if (destination.label == TEAMS_DESTINATION){
                isPlayersFragmentOn = false
                isTeamsFragmentOn = true
            }

        }

        binding.fab.setOnClickListener{
            if (isPlayersFragmentOn){
                val intent = Intent(this, AddEditPlayerActivity::class.java)
                startActivity(intent)
            }
            if(isTeamsFragmentOn){
                val intent = Intent(this, AddEditTeamActivity::class.java)
                startActivity(intent)
            }
        }


    }
}