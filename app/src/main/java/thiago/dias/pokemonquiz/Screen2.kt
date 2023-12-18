package thiago.dias.pokemonquiz

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_screen1.*
import kotlinx.android.synthetic.main.activity_screen2.*
import thiago.dias.pokemonquiz.extension.toast
import thiago.dias.pokemonquiz.model.Question
import thiago.dias.pokemonquiz.model.Quiz

class Screen2 : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen2)

        activity2ButtonNext.visibility = View.INVISIBLE
        vibrate()

        Quiz.questionsShuffle()
        val question = Quiz.questionsArray.get(0)

        getScore()

        activity2textViewScore.text = Quiz.score().toString()

        activity2TextViewDescription.text = question.questionDescription
        activity2RadioButtonOption1.text = question.option1
        activity2RadioButtonOption2.text = question.option2
        activity2RadioButtonOption3.text = question.option3
        activity2RadioButtonOption4.text = question.option4

        activity2ButtonAnswer.setOnClickListener {
            activity2ButtonNext.visibility = View.VISIBLE
            activity2ButtonAnswer.visibility = View.INVISIBLE


            val id = activity2RadioGroup.checkedRadioButtonId
            val radio: RadioButton = findViewById(id)

            if (Quiz.verifyTheCorrectAnswer(radio.text.toString())) {
                toast("${Quiz.context?.getText(R.string.thats_correct)}")
                play(R.raw.pikachu_sound)
                getScore()
            } else {
                toast("${Quiz.context?.getText(R.string.thats_incorrect)}")
                play(R.raw.pikachu_attack)
            }
        }

        activity2ButtonNext.setOnClickListener {
            startActivity(Intent(this@Screen2, Screen3::class.java))
            finish()
        }
    }

    fun getScore() {
        activity2textViewScore.text = Quiz.score().toString()
    }

    private fun play(sound: Int) {
        mediaPlayer = MediaPlayer.create(this@Screen2, sound)
        mediaPlayer.start()
    }

    private fun stop() {
        if(mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
    }

    private fun vibrate() {

        val pattern =  longArrayOf(0, 200, 100, 300)

        val hardware = this@Screen2.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        hardware?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                hardware.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else{
                hardware.vibrate(pattern, -1)
            }
        }
    }
}