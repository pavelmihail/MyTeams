package com.practice.myteams.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.myteams.databinding.FragmentTeamsBinding
import com.practice.myteams.viewmodel.TeamsViewModel
import com.practice.myteams.views.adapters.TeamListAdapter

class TeamsFragment : Fragment() {

    private lateinit var binding: FragmentTeamsBinding
    private val viewModel: TeamsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTeamsBinding.inflate(inflater, container, false)

        //setup recycleView
        binding.rvTeamsList.layoutManager = LinearLayoutManager(requireActivity())
        val teamListAdapter = TeamListAdapter(this)
        binding.rvTeamsList.adapter = teamListAdapter

        if (viewModel.teams != null) {
            println("s a ajuns si aici")
            teamListAdapter.teams = viewModel.teams?.DATA!!
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
}