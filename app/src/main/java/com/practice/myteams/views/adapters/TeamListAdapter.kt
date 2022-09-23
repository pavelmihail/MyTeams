package com.practice.myteams.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.practice.myteams.data.Team
import com.practice.myteams.databinding.ItemTeamCardBinding

class TeamListAdapter(private val fragment: Fragment, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<TeamListAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem.ID_ECHIPA == newItem.ID_ECHIPA
        }

        override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)

    var teams: List<Team>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    inner class ViewHolder(view: ItemTeamCardBinding) : RecyclerView.ViewHolder(view.root),
        View.OnClickListener {
        val tvTeamName = view.tvTeamName
        val tvTeamId = view.tvTeamId
        val tvTeamIsActive = view.tvTeamIsActivate

        init {
            view.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            if(adapterPosition != RecyclerView.NO_POSITION)
            listener.onItemClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemTeamCardBinding = ItemTeamCardBinding.inflate(
            LayoutInflater.from(fragment.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = teams[position]
        holder.tvTeamId.text = team.ID_ECHIPA.toString()
        holder.tvTeamName.text = team.DENUMIRE
        holder.tvTeamIsActive.isChecked = team.ACTIV

    }

    override fun getItemCount(): Int {
        return teams.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}