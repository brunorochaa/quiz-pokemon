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
import thiago.dias.pokemonquiz.extension.toast
import thiago.dias.pokemonquiz.model.Question
import thiago.dias.pokemonquiz.model.Quiz

class Screen3 : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen3)

        activity3ButtonNext.visibility = View.INVISIBLE
        vibrate()

        Quiz.questionsShuffle()
        val question = Quiz.questionsArray.get(0)

        getScore()

        activity3textViewScore.text = Quiz.score().toString()

        activity3TextViewDescription.text = question.questionDescription
        activity3RadioButtonOption1.text = question.option1
        activity3RadioButtonOption2.text = question.option2
        activity3RadioButtonOption3.text = question.option3
        activity3RadioButtonOption4.text = question.option4

        activity3ButtonAnswer.setOnClickListener {
            activity3ButtonNext.visibility = View.VISIBLE
            activity3ButtonAnswer.visibility = View.INVISIBLE


            val id = activity3RadioGroup.checkedRadioButtonId
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

        activity3ButtonNext.setOnClickListener {
            startActivity(Intent(this@Screen3, Screen4::class.java))
            finish()
        }
    }

    fun getScore() {
        activity3textViewScore.text = Quiz.score().toString()
    }

    private fun play(sound: Int) {
        mediaPlayer = MediaPlayer.create(this@Screen3, sound)
        mediaPlayer.start()
    }

    private fun stop() {
        if(mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
    }

    private fun vibrate() {

        val pattern =  longArrayOf(0, 200, 100, 300)

        val hardware = this@Screen3.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        hardware?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                hardware.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else{
                hardware.vibrate(pattern, -1)
            }
        }
    }
}