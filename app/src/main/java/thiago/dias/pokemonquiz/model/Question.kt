package thiago.dias.pokemonquiz.model

class Question(
    var questionDescription: String,
    var option1: String,
    var option2: String,
    var option3: String,
    var option4: String,
    var correctOptionAnswer: String) {

    var correctAnswer: Boolean = false
    var repeatedQuestion: Boolean = false

    override fun toString(): String {
        return "Question(questionDescription='$questionDescription', option1='$option1', option2='$option2', option3='$option3', option4='$option4', correctOptionAnswer='$correctOptionAnswer', correctAnswer=$correctAnswer, repeatedQuestion=$repeatedQuestion)"
    }
}