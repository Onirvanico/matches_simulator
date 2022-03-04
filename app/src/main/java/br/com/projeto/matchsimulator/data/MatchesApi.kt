package br.com.projeto.matchsimulator.data

import br.com.projeto.matchsimulator.domain.Match
import retrofit2.Call
import retrofit2.http.GET

interface MatchesApi {

    @GET("matches.json")
    fun getMatches(): Call<List<Match>>
}