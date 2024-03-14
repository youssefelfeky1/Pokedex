package com.example.pokedex.repository

import com.example.pokedex.data.remote.PokeApi
import com.example.pokedex.data.remote.responses.Pokemon
import com.example.pokedex.data.remote.responses.PokemonList
import com.example.pokedex.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

//usually we need to make interface to repo and implement it in our real repo which is good for testability of our application

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api:PokeApi
) {

    suspend fun getPokemonList ( limit:Int, offset:Int ):Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        }catch (_:Exception){
            return Resource.Error("An unknown error occurred")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonInfo ( pokemonName:String ):Resource<Pokemon> {
        val response = try {
            api.getPokemonInfo(pokemonName)
        }catch (_:Exception){
            return Resource.Error("An unknown error occurred")
        }
        return Resource.Success(response)
    }
}