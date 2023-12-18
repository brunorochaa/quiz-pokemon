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
import kotlinx.android.synthetic.main.activity_screen3.*
import kotlinx.android.synthetic.main.activity_screen5.*
import thiago.dias.pokemonquiz.extension.toast
import thiago.dias.pokemonquiz.model.Question
import thiago.dias.pokemonquiz.model.Quiz

class Screen5 : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen5)

        activity5ButtonNext.visibility = View.INVISIBLE
        vibrate()

        Quiz.questionsShuffle()
        val question = Quiz.questionsArray.get(0)

        getScore()

        activity5textViewScore.text = Quiz.score().toString()

        activity5TextViewDescription.text = question.questionDescription
        activity5RadioButtonOption1.text = question.option1
        activity5RadioButtonOption2.text = question.option2
        activity5RadioButtonOption3.text = question.option3
        activity5RadioButtonOption4.text = question.option4

        activity5ButtonAnswer.setOnClickListener {
            activity5ButtonNext.visibility = View.VISIBLE
            activity5ButtonAnswer.visibility = View.INVISIBLE


            val id = activity5RadioGroup.checkedRadioButtonId
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

        activity5ButtonNext.setOnClickListener {
            startActivity(Intent(this@Screen5, FinalQuiz::class.java))
            finish()
        }
    }

    fun getScore() {
        activity5textViewScore.text = Quiz.score().toString()
    }

    private fun play(sound: Int) {
        mediaPlayer = MediaPlayer.create(this@Screen5, sound)
        mediaPlayer.start()
    }

    private fun stop() {
        if(mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
    }

    private fun vibrate() {

        val pattern =  longArrayOf(0, 200, 100, 300)

        val hardware = this@Screen5.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        hardware?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                hardware.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else{
                hardware.vibrate(pattern, -1)
            }
        }
    }
}