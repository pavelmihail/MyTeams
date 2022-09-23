package com.practice.myteams.views.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.practice.myteams.R
import com.practice.myteams.data.Team
import com.practice.myteams.data.TeamDeleteBody
import com.practice.myteams.data.TeamPutBody
import com.practice.myteams.data.TeamTransmit
import com.practice.myteams.viewmodel.TeamDialogViewModel

class TeamDialog(
    private var isNew: Boolean = true,
    private var toInsertTeam: Team = Team(
        false,
        "",
        "",
        "",
        1,
        "",
        1,
        ""
    )
) : DialogFragment() {

    private val viewModel: TeamDialogViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.dialog_add_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sendButton = view.findViewById<Button>(R.id.btn_add_team)
        val deleteButton = view.findViewById<Button>(R.id.btn_delete)
        val teamName = view.findViewById<MaterialAutoCompleteTextView>(R.id.team_name)
        val addTeam = TeamTransmit("")
        val putTeam = TeamPutBody("", 0)
        val deleteTeam = TeamDeleteBody(0)

        if (!isNew) {
            teamName.setText(toInsertTeam.DENUMIRE)
            sendButton.setText(R.string.team_modify_btn)
            deleteButton.visibility = View.VISIBLE
        } else {
            sendButton.setText(R.string.team_add_btn)
            deleteButton.visibility = View.GONE
        }

        sendButton.setOnClickListener {
            if(teamName.text.isNotEmpty()){
                if (isNew) {
                    addTeam.DENUMIRE = teamName.text.toString()
                    viewModel.postTeam(addTeam)
                } else {
                    putTeam.DENUMIRE = teamName.text.toString()
                    putTeam.ID_ECHIPA = toInsertTeam.ID_ECHIPA
                    viewModel.putTeam(putTeam)
                }
            }else{
                Toast.makeText(
                    requireContext(),
                    "Nu ai completat toate spatiile",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        deleteButton.setOnClickListener{
            deleteTeam.ID_ECHIPA = toInsertTeam.ID_ECHIPA
            viewModel.deleteTeam(deleteTeam)
        }

        viewModel.getLiveDataObserver().observe(
            viewLifecycleOwner
        ) {
            if (it.SUCCESS) {
                Toast.makeText(
                    requireContext(),
                    "Echipa a fost postata cu succes!",
                    Toast.LENGTH_SHORT
                ).show()
                dialog?.dismiss()
            } else {
                Toast.makeText(
                    requireContext(),
                    "A aparut o eroare",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}