package thiago.dias.pokemonquiz.service

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import thiago.dias.pokemonquiz.Constant

class RetrofitInitializer {

    var gson = GsonBuilder()
        .setDateFormat("yyy-MM-dd'T'HH:mm:ss")
        .create()

    private val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun pokemonRegisterService() = retrofit.create(PokemonRegisterService::class.java)
}