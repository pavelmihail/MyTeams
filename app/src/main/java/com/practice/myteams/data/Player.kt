package com.practice.myteams.data

data class Player(
    val ACTIV: Boolean,
    val DATA_CREARE: String,
    val DATA_MODIFICARE: Any,
    val DATA_NASTERE: String,
    val ECHIPA: Team,
    val ID_ECHIPA: Int,
    val ID_JUCATOR: Int,
    val NUME: String,
    val PRENUME: String,
    val STATUS: String,
    val UTILIZATOR_CREARE: Int,
    val UTILIZATOR_MODIFICARE: Any
)