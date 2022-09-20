package com.practice.myteams.api

data class Team(
    val ACTIV: Boolean,
    val DATA_CREARE: String,
    val DATA_MODIFICARE: Any,
    val DENUMIRE: String,
    val ID_ECHIPA: Int,
    val STATUS: String,
    val UTILIZATOR_CREARE: Int,
    val UTILIZATOR_MODIFICARE: Any
)