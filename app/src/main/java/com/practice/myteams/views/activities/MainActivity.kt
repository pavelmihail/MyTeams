package com.practice.myteams.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.practice.myteams.R
import com.practice.myteams.databinding.ActivityMainBinding
import com.practice.myteams.views.dialogs.PlayerDialog
import com.practice.myteams.views.dialogs.TeamDialog

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val PLAYERS_DESTINATION = "Players"
    private val TEAMS_DESTINATION = "Teams"
    private var isPlayersFragmentOn = false
    private var isTeamsFragmentOn = false

    private var dialogTeam =  TeamDialog(true)
    private var dialogPlayer =  PlayerDialog()


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
        navController.addOnDestinationChangedListener { _, destination, _ ->
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
                dialogPlayer.show(supportFragmentManager, dialogPlayer.tag)
//                val intent = Intent(this, AddEditPlayerActivity::class.java)
//                startActivity(intent)
            }
            if(isTeamsFragmentOn){
                dialogTeam.show(supportFragmentManager, dialogTeam.tag)
//                val intent = Intent(this, AddEditTeamActivity::class.java)
//                startActivity(intent)
            }
        }
    }
}