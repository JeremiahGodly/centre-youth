package org.marthoma.centreyouth.data.model

data class BibleQuizQuestion(
    val id: Int,
    val level: Int,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val verseRef: String
)

data class QuizLevelInfo(
    val level: Int,
    val title: String,
    val subtitle: String,
    val difficultyLabel: String,
    val timePerQuestionSec: Int,
    val primaryColorHex: Long,
    val secondaryColorHex: Long
)
