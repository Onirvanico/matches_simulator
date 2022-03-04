package br.com.projeto.matchsimulator.domain

import com.google.gson.annotations.SerializedName

data class Match(

    @SerializedName("descricao")
    val description: String,

    @SerializedName("local")
    val place: Place,

    @SerializedName("mandante")
    val leftTeam: Team,

    @SerializedName("visitante")
    val rightTeam: Team
)
