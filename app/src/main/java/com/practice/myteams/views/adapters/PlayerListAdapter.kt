package com.practice.myteams.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.practice.myteams.data.Player
import com.practice.myteams.data.Team
import com.practice.myteams.databinding.ItemPlayerCardBinding
import com.practice.myteams.databinding.ItemTeamCardBinding

class PlayerListAdapter(private val fragment: Fragment) : RecyclerView.Adapter<PlayerListAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem.ID_JUCATOR == newItem.ID_JUCATOR
        }

        override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)

    var players: List<Player>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    class ViewHolder(view: ItemPlayerCardBinding): RecyclerView.ViewHolder(view.root){
        val tvPlayerFirstName = view.tvPlayerFirstName
        val tvPlayerLastName = view.tvPlayerLastName
        val tvPlayerIdTeam = view.tvPlayerIdTeam
        val tvPlayerIsActivate = view.cbPlayerIsActivate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPlayerCardBinding = ItemPlayerCardBinding.inflate(
            LayoutInflater.from(fragment.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = players[position]
        holder.tvPlayerFirstName.text = player.NUME
        holder.tvPlayerLastName.text = player.PRENUME
        holder.tvPlayerIdTeam.text = player.ID_ECHIPA.toString()
        holder.tvPlayerIsActivate.isChecked = player.ACTIV
    }

    override fun getItemCount(): Int {
        return  players.size
    }
}