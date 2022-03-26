package com.arash.altafi.sampleflow1.data.remote

import com.arash.altafi.sampleflow1.model.Pokemon
import com.arash.altafi.sampleflow1.model.PokemonResponse
import com.arash.altafi.sampleflow1.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET(Constants.ALL_POKEMON_URL)
    suspend fun getAllPokemon(): Response<PokemonResponse>

    @GET(Constants.POKEMON_URL)
    suspend fun getPokemonDetails(@Path("id") id: String): Response<Pokemon>

}