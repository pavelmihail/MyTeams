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
import com.practice.myteams.data.PlayerTransmit
import com.practice.myteams.data.TeamTransmit
import com.practice.myteams.viewmodel.PlayerDialogViewModel

class PlayerDialog: DialogFragment() {

    private val viewModel: PlayerDialogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.dialog_add_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sendButton = view.findViewById<Button>(R.id.btn_add_player)
        val playerLastName = view.findViewById<MaterialAutoCompleteTextView>(R.id.last_name_player)
        val playerFistName = view.findViewById<MaterialAutoCompleteTextView>(R.id.first_name_player)
        val playerBirthDate = view.findViewById<MaterialAutoCompleteTextView>(R.id.birth_date)
        val teamId = view.findViewById<MaterialAutoCompleteTextView>(R.id.id_team)

        val addPlayer = PlayerTransmit("", "", "", 1)


        sendButton.setOnClickListener{
            addPlayer.NUME = playerLastName.text.toString()
            addPlayer.PRENUME = playerFistName.text.toString()
            addPlayer.DATA_NASTERE = playerBirthDate.text.toString()
            addPlayer.ID_ECHIPA = teamId.text.toString().toInt()

            viewModel.postPlayer(addPlayer)
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
}