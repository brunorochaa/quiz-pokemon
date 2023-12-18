package thiago.dias.pokemonquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import thiago.dias.pokemonquiz.extension.toast
import thiago.dias.pokemonquiz.model.Pokemon
import thiago.dias.pokemonquiz.model.Quiz
import thiago.dias.pokemonquiz.service.RetrofitInitializer
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonPokemonAPI.setOnClickListener {
            val call = RetrofitInitializer().pokemonRegisterService().pokemonRegister()

            call.enqueue(object : retrofit2.Callback<List<Pokemon>>{
                override fun onFailure(call: Call<List<Pokemon>>, t: Throwable) {
                    toast("Erro: ${t.toString()}")
                }

                override fun onResponse(call: Call<List<Pokemon>>, response: Response<List<Pokemon>>) {
                    if (response.isSuccessful && response.code() == 200) {
                        val pokemons: List<Pokemon>? = response.body()

                        Toast.makeText(baseContext, "${response.code()}", Toast.LENGTH_LONG).show()

                        pokemons?.let {
                            for (pokemon in pokemons) {
                                Toast.makeText(
                                    baseContext,
                                    "${pokemon.toString()}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                    } else {
                        Toast.makeText(baseContext, "erro!", Toast.LENGTH_LONG).show()
                    }
                }

            })
        }

        Quiz.context = this@MainActivity
        Quiz.clearAll()
        buttonStart.setOnClickListener { startActivity(Intent(this@MainActivity, Screen1::class.java)) }
    }
}