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
import com.practice.myteams.data.Player
import com.practice.myteams.data.PlayerDeleteBody
import com.practice.myteams.data.PlayerPutBody
import com.practice.myteams.data.PlayerTransmit
import com.practice.myteams.viewmodel.PlayerDialogViewModel

class PlayerDialog(
    private var isNew: Boolean = true,
    private var toInsertPlayer: Player = Player(
        false,
        "",
        "",
        "",
        null,
        1,
        1,
        "",
        "",
        "",
        1,
        ""
    )
) : DialogFragment() {

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
        val deleteButton = view.findViewById<Button>(R.id.btn_delete_player)
        val playerLastName = view.findViewById<MaterialAutoCompleteTextView>(R.id.last_name_player)
        val playerFirstName = view.findViewById<MaterialAutoCompleteTextView>(R.id.first_name_player)
        val playerBirthDate = view.findViewById<MaterialAutoCompleteTextView>(R.id.birth_date)
        val teamId = view.findViewById<MaterialAutoCompleteTextView>(R.id.id_team)

        val addPlayer = PlayerTransmit("", "", "", 1)
        val putPlayer = PlayerPutBody(0, "", "", "", 0)
        val deletePlayer = PlayerDeleteBody(0)

        if (!isNew) {
            playerLastName.setText(toInsertPlayer.NUME)
            playerFirstName.setText(toInsertPlayer.PRENUME)
            playerBirthDate.setText(toInsertPlayer.DATA_NASTERE)
            teamId.setText(toInsertPlayer.ID_ECHIPA.toString())
            sendButton.setText(R.string.player_modify_btn)
            deleteButton.visibility = View.VISIBLE
        } else {
            sendButton.setText(R.string.team_add_btn)
            deleteButton.visibility = View.GONE
        }

        sendButton.setOnClickListener {
            if (playerLastName.text.isNotEmpty() &&
                playerFirstName.text.isNotEmpty() &&
                playerBirthDate.text.isNotEmpty() &&
                teamId.text.isNotEmpty())
            {
                if (isNew) {
                    addPlayer.NUME = playerLastName.text.toString()
                    addPlayer.PRENUME = playerFirstName.text.toString()
                    addPlayer.DATA_NASTERE = playerBirthDate.text.toString()
                    addPlayer.ID_ECHIPA = teamId.text.toString().toInt()

                    viewModel.postPlayer(addPlayer)

                } else {
                    putPlayer.ID_JUCATOR = toInsertPlayer.ID_JUCATOR
                    putPlayer.NUME = playerLastName.text.toString()
                    putPlayer.PRENUME = playerFirstName.text.toString()
                    putPlayer.DATA_NASTERE = playerBirthDate.text.toString()
                    putPlayer.ID_ECHIPA = teamId.text.toString().toInt()

                    viewModel.putPlayer(putPlayer)
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Nu ai completat toate spatiile",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }

        deleteButton.setOnClickListener{
            deletePlayer.ID_JUCATOR = toInsertPlayer.ID_ECHIPA
            viewModel.deletePlayer(deletePlayer)
        }

        viewModel.getLiveDataObserver().observe(
            viewLifecycleOwner
        ) {
            if (it.SUCCESS) {
                Toast.makeText(
                    requireContext(),
                    "Jucatorul a fost adaugat cu succes!",
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