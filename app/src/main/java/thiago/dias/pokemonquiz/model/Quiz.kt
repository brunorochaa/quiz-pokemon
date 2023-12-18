package thiago.dias.pokemonquiz.model

import android.content.Context
import thiago.dias.pokemonquiz.R

class Quiz {

    companion object {

        var questionsArray = ArrayList<Question>()
        var context: Context? = null

        init {
            generateQuestions()
        }

        fun generateQuestions() {
            questionsArray.add(Question("${context?.getText(R.string.genre_question)}", "a - Anime", "b - Manga", "c - Video Game", "${context?.getText(R.string.option_novel)}", "c - Video Game"))
            questionsArray.add(Question("${context?.getText(R.string.released_games)}", "${context?.getText(R.string.silver_gold)}", "${context?.getText(R.string.green_red)}", "${context?.getText(R.string.sword_shield)}", "${context?.getText(R.string.black_white)}", "${context?.getText(R.string.green_red)}"))
            questionsArray.add(Question("${context?.getText(R.string.game_platform)}", "a - Super Nintendo", "b - Playstation", "c - Neo Geo 64", "d - Game Boy", "d - Game Boy"))
            questionsArray.add(Question("${context?.getText(R.string.pikachu_evolution)}", "${context?.getText(R.string.by_level)}", "${context?.getText(R.string.raising_happiness)}", "${context?.getText(R.string.thunder_stone)}", "${context?.getText(R.string.hyper_training)}", "${context?.getText(R.string.thunder_stone)}"))
            questionsArray.add(Question("${context?.getText(R.string.ash_home)}", "${context?.getText(R.string.pallet_town)}", "${context?.getText(R.string.cinnabar_island)}", "${context?.getText(R.string.lavender_town)}", "${context?.getText(R.string.cerulean_city)}", "${context?.getText(R.string.pallet_town)}"))
            questionsArray.add(Question("${context?.getText(R.string.charizard_weakness)}", "${context?.getText(R.string.fire_moves)}", "${context?.getText(R.string.rock_moves)}", "${context?.getText(R.string.water_moves)}", "${context?.getText(R.string.fairy_moves)}", "${context?.getText(R.string.rock_moves)}"))
            questionsArray.add(Question("${context?.getText(R.string.ash_rival)}", "a - Serena", "b - Lance", "c - Misty", "d - Gary Oak", "d - Gary Oak"))
            questionsArray.add(Question("${context?.getText(R.string.broken_pokemon)}", "a - Mew", "b - Celebi", "c - Mewtwo", "d - Psyduck", "c - Mewtwo"))
            questionsArray.add(Question("${context?.getText(R.string.god_pokemon)}", "a - Arceus", "b - Dialga", "c - Zamazenta", "d - Celebi", "a - Arceus"))
            questionsArray.add(Question("${context?.getText(R.string.brock_weakness)}", "${context?.getText(R.string.water_pokemon)}", "${context?.getText(R.string.beautiful_women)}", "${context?.getText(R.string.dragon_moves)}", "${context?.getText(R.string.flying_moves)}", "${context?.getText(R.string.beautiful_women)}"))
        }

        fun markAsRepeatedAnswer() {
            questionsArray.get(0).repeatedQuestion = true
        }

        fun questionsShuffle() {
            while (questionsArray.get(0).repeatedQuestion) {
                questionsArray.shuffle()
            }
            markAsRepeatedAnswer()
        }

        fun score(): Int {
            var score = 0
            for (question in questionsArray) {
                if (question.correctAnswer) {
                    score++
                }
            }
            return score
        }

        fun checkAverageOfCorrectAnswers(): Double {
            return (score() / 5.0) * 100
        }

        fun finalMessage(): String {
            if(checkAverageOfCorrectAnswers()>80) {
                return "${context?.getText(R.string.great_score)}"
            } else if(checkAverageOfCorrectAnswers()>50) {
                return "${context?.getText(R.string.not_bad)}"
            } else {
                return "${context?.getText(R.string.really_bad)}"
            }
        }

        fun verifyTheCorrectAnswer(answer: String): Boolean {
            if(answer.equals(questionsArray.get(0).correctOptionAnswer)) {
                questionsArray.get(0).correctAnswer = true
                return true
            } else {
                return false
            }
        }

        fun clearAll() {
            questionsArray.clear()
            generateQuestions()
        }
    }
}