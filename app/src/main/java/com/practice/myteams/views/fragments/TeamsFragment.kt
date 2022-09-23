package com.practice.myteams.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.myteams.databinding.FragmentTeamsBinding
import com.practice.myteams.viewmodel.TeamsViewModel
import com.practice.myteams.views.adapters.TeamListAdapter
import com.practice.myteams.views.dialogs.TeamDialog

class TeamsFragment : Fragment(), TeamListAdapter.OnItemClickListener {

    private lateinit var binding: FragmentTeamsBinding
    private val viewModel: TeamsViewModel by viewModels()
    private lateinit var teamListAdapter: TeamListAdapter
    private lateinit var dialogTeam :  TeamDialog


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTeamsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setup recycleView
        binding.rvTeamsList.layoutManager = LinearLayoutManager(requireActivity())
        teamListAdapter = TeamListAdapter(this, this)
        binding.rvTeamsList.adapter = teamListAdapter
        val pullToRefresh = binding.pullToRefresh

        viewModel.getTeams()

        binding.progressBar.visibility = View.VISIBLE
        viewModel.getLiveDataObserver().observe(
            viewLifecycleOwner
        ) {
            if (it.SUCCESS)
                if (it.DATA.isNotEmpty())
                    teamListAdapter.teams = it.DATA
                else
                    binding.noTeamsAddedYet.visibility = View.VISIBLE
            else {
                binding.noTeamsAddedYet.visibility = View.VISIBLE
                binding.noTeamsAddedYet.text = "Nu s-au putut aduce pachetele"
            }
            binding.progressBar.visibility = View.GONE
        }


        pullToRefresh.setOnRefreshListener {
            viewModel.getTeams()
            teamListAdapter.notifyDataSetChanged()
            pullToRefresh.isRefreshing = false
        }
    }

    override fun onItemClick(position: Int) {
        dialogTeam = TeamDialog(false, teamListAdapter.teams[position])
        dialogTeam.show(parentFragmentManager, dialogTeam.tag)
    }
}