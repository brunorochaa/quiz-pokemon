package thiago.dias.pokemonquiz.service


import retrofit2.http.GET
import thiago.dias.pokemonquiz.model.Pokemon
import retrofit2.Call

interface PokemonRegisterService {

    @GET("pokemon")
    fun pokemonRegister() : Call<List<Pokemon>>
}