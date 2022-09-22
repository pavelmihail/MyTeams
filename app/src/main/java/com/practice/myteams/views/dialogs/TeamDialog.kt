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
import com.practice.myteams.data.TeamTrasmit
import com.practice.myteams.viewmodel.TeamDialogViewModel


class TeamDialog : DialogFragment() {

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

        val sendButton = view.findViewById<Button>(R.id.add_team_btn)
        val teamName = view.findViewById<MaterialAutoCompleteTextView>(R.id.name_team)
        val addTeam = TeamTrasmit("")


        sendButton.setOnClickListener{
            addTeam.DENUMIRE = teamName.text.toString()
            viewModel.postTeam(addTeam)
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
            }
            else {
                Toast.makeText(
                    requireContext(),
                    "A aparut o eroare",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }


}