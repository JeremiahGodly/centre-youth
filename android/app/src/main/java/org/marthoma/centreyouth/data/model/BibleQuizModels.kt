package org.marthoma.centreyouth.data.model

enum class QuizLanguage(val label: String) {
    EN("EN"),
    ML("ML"),
    BOTH("Both")
}

data class BibleQuizQuestion(
    val id: Int,
    val level: Int,
    val question: String,
    val questionMl: String,
    val options: List<String>,
    val optionsMl: List<String>,
    val correctIndex: Int,
    val verseRef: String,
    val verseRefMl: String
)

data class QuizLevelInfo(
    val level: Int,
    val title: String,
    val titleMl: String,
    val subtitle: String,
    val subtitleMl: String,
    val difficultyLabel: String,
    val difficultyLabelMl: String,
    val timePerQuestionSec: Int,
    val primaryColorHex: Long,
    val secondaryColorHex: Long
)
