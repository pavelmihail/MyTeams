package com.practice.myteams.views.fragments

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setup recycleView
        binding.rvTeamsList.layoutManager = LinearLayoutManager(requireActivity())
        val teamListAdapter = TeamListAdapter(this)
        binding.rvTeamsList.adapter = teamListAdapter

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
    }
}