package com.practice.myteams.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.myteams.databinding.FragmentPlayersBinding
import com.practice.myteams.viewmodel.PlayersViewModel
import com.practice.myteams.views.adapters.PlayerListAdapter
import com.practice.myteams.views.dialogs.PlayerDialog
import com.practice.myteams.views.dialogs.TeamDialog


class PlayersFragment : Fragment(), PlayerListAdapter.OnItemClickListener {

    private lateinit var binding: FragmentPlayersBinding
    private val viewModel: PlayersViewModel by viewModels()
    private lateinit var playerListAdapter: PlayerListAdapter
    private lateinit var dialogPlayer: PlayerDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayersBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvPlayersList.layoutManager = LinearLayoutManager(requireActivity())
        playerListAdapter = PlayerListAdapter(this, this)
        binding.rvPlayersList.adapter = playerListAdapter

        val pullToRefresh = binding.pullToRefresh

        viewModel.getPlayer()

        binding.progressBar.visibility = View.VISIBLE
        viewModel.getLiveDataObserver().observe(
            viewLifecycleOwner
        ) {
            if (it.SUCCESS)
                if (it.DATA.isNotEmpty())
                    playerListAdapter.players = it.DATA
                else
                    binding.noPlayersAddedYet.visibility = View.VISIBLE
            else {
                binding.noPlayersAddedYet.visibility = View.VISIBLE
                binding.noPlayersAddedYet.text = "Nu s-au putut aduce pachetele"
            }
            binding.progressBar.visibility = View.GONE
        }

        pullToRefresh.setOnRefreshListener {
            viewModel.getPlayer()
            playerListAdapter.notifyDataSetChanged()
            pullToRefresh.isRefreshing = false
        }
    }

    override fun onItemClick(position: Int) {
        dialogPlayer = PlayerDialog(false, playerListAdapter.players[position])
        dialogPlayer.show(parentFragmentManager, dialogPlayer.tag)
    }
}