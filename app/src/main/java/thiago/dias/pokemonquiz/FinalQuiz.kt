package thiago.dias.pokemonquiz

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_final_quiz.*
import kotlinx.android.synthetic.main.activity_screen1.*
import thiago.dias.pokemonquiz.model.Quiz

class FinalQuiz : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_quiz)

        play(R.raw.pokemon_theme)
        getScore()

        finalQuizTextViewCorrectAnswers.text =
            "${Quiz.context?.getText(R.string.correct_answers)} ${Quiz.checkAverageOfCorrectAnswers().toString()}%"

        finalQuizTextViewFinalMessage.text = Quiz.finalMessage()

        finalQuizButtonPlayAgain.setOnClickListener {
            Quiz.clearAll()
            startActivity(Intent(this@FinalQuiz, Screen1::class.java))
            finish()
        }
    }

    //Ao fechar a activity
    override fun onStop() {
        stop()
        super.onStop()
    }

    fun getScore() {
        finalQuizTextViewScore.text = "${Quiz.context?.getText(R.string.final_score)} ${Quiz.score().toString()}"
    }

    private fun play(sound: Int) {
        mediaPlayer = MediaPlayer.create(this@FinalQuiz, sound)
        mediaPlayer.start()
    }

    private fun stop() {
        if(mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
    }
}