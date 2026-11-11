package org.marthoma.centreyouth.data

import org.marthoma.centreyouth.data.model.BibleQuizQuestion
import org.marthoma.centreyouth.data.model.QuizLevelInfo

/**
 * Comprehensive 120-Question Bilingual Progressive Bible Quiz Bank (English + Malayalam)
 * 10 Escalating Difficulty Levels (12 questions per level)
 * Timers tighten progressively from 25 seconds (Level 1) down to 10 seconds (Level 10).
 */
object BibleQuizBank {

    val levels: List<QuizLevelInfo> = listOf(
        QuizLevelInfo(
            level = 1,
            title = "Genesis Dawn", titleMl = "ഉല്പത്തി പ്രഭാതം",
            subtitle = "Foundational Bible Stories", subtitleMl = "അടിസ്ഥാന ബൈബിൾ കഥകൾ",
            difficultyLabel = "Easy", difficultyLabelMl = "ലളിതം",
            timePerQuestionSec = 25,
            primaryColorHex = 0xFF10B981, secondaryColorHex = 0xFF14B8A6
        ),
        QuizLevelInfo(
            level = 2,
            title = "Ark & Miracles", titleMl = "പെട്ടകവും അത്ഭുതങ്ങളും",
            subtitle = "Beloved Miracles & Heroes", subtitleMl = "അത്ഭുതങ്ങളും ബൈബിൾ നായകരും",
            difficultyLabel = "Easy+", difficultyLabelMl = "എളുപ്പം",
            timePerQuestionSec = 24,
            primaryColorHex = 0xFF06B6D4, secondaryColorHex = 0xFF0EA5E9
        ),
        QuizLevelInfo(
            level = 3,
            title = "Faithful Witness", titleMl = "വിശ്വസ്ത സാക്ഷികൾ",
            subtitle = "Kings, Judges & Apostles", subtitleMl = "രാജാക്കന്മാരും അപ്പൊസ്തലരും",
            difficultyLabel = "Normal", difficultyLabelMl = "സാധാരണം",
            timePerQuestionSec = 22,
            primaryColorHex = 0xFF3B82F6, secondaryColorHex = 0xFF6366F1
        ),
        QuizLevelInfo(
            level = 4,
            title = "Apostolic Trail", titleMl = "അപ്പൊസ്തല പഥം",
            subtitle = "Gospels & Early Church", subtitleMl = "സുവിശേഷങ്ങളും ആദിമ സഭയും",
            difficultyLabel = "Moderate", difficultyLabelMl = "മിതമായത്",
            timePerQuestionSec = 20,
            primaryColorHex = 0xFF8B5CF6, secondaryColorHex = 0xFFA855F7
        ),
        QuizLevelInfo(
            level = 5,
            title = "Prophetic Voices", titleMl = "പ്രവാചക ശബ്ദങ്ങൾ",
            subtitle = "Major & Minor Prophets", subtitleMl = "പ്രവാചകന്മാരും പ്രവചനങ്ങളും",
            difficultyLabel = "Intermediate", difficultyLabelMl = "മദ്ധ്യമം",
            timePerQuestionSec = 18,
            primaryColorHex = 0xFFD946EF, secondaryColorHex = 0xFFEC4899
        ),
        QuizLevelInfo(
            level = 6,
            title = "Kingdom Chronicles", titleMl = "രാജ്യ ചരിത്രം",
            subtitle = "Divided Monarchy & Epistles", subtitleMl = "രാജഭരണവും ലേഖനങ്ങളും",
            difficultyLabel = "Challenging", difficultyLabelMl = "വെല്ലുവിളി",
            timePerQuestionSec = 16,
            primaryColorHex = 0xFFF43F5E, secondaryColorHex = 0xFFF59E0B
        ),
        QuizLevelInfo(
            level = 7,
            title = "Covenant Shadows", titleMl = "ഉടമ്പടി നിഴലുകൾ",
            subtitle = "Typology, Leviticus & Prophecy", subtitleMl = "ഉടമ്പടിയും ആഴമേറിയ ദർശനങ്ങളും",
            difficultyLabel = "Hard", difficultyLabelMl = "കഠിനം",
            timePerQuestionSec = 15,
            primaryColorHex = 0xFFF97316, secondaryColorHex = 0xFFFBBF24
        ),
        QuizLevelInfo(
            level = 8,
            title = "Sanctuary Secrets", titleMl = "വിശുദ്ധമന്ദിര രഹസ്യങ്ങൾ",
            subtitle = "Obscure Names & Geographies", subtitleMl = "സൂക്ഷ്മ നാമങ്ങളും ഭൂമിശാസ്ത്രവും",
            difficultyLabel = "Very Hard", difficultyLabelMl = "അതികഠിനം",
            timePerQuestionSec = 14,
            primaryColorHex = 0xFFEF4444, secondaryColorHex = 0xFFDB2777
        ),
        QuizLevelInfo(
            level = 9,
            title = "Deep Exegesis", titleMl = "വേദശാസ്ത്ര ആഴങ്ങൾ",
            subtitle = "Ancient Texts & Rare Details", subtitleMl = "അപൂർവ്വ വേദഭാഗങ്ങളും വിവരണങ്ങളും",
            difficultyLabel = "Brutal", difficultyLabelMl = "അതീവ കഠിനം",
            timePerQuestionSec = 12,
            primaryColorHex = 0xFFDC2626, secondaryColorHex = 0xFF7C3AED
        ),
        QuizLevelInfo(
            level = 10,
            title = "Omega Grandmaster", titleMl = "ഒമേഗ മഹാഗുരു",
            subtitle = "Scholarly Biblical Mastery", subtitleMl = "പണ്ഡിതോചിത വേദപരിജ്ഞാനം",
            difficultyLabel = "ULTRA HARD", difficultyLabelMl = "മഹാ കഠിനം",
            timePerQuestionSec = 10,
            primaryColorHex = 0xFFF59E0B, secondaryColorHex = 0xFFE11D48
        )
    )

    val allQuestions: List<BibleQuizQuestion> by lazy {
        quizQuestionsPart1 + quizQuestionsPart2
    }

    fun getLevelInfo(level: Int): QuizLevelInfo {
        return levels.find { it.level == level } ?: levels.first()
    }

    fun getQuestionsForLevel(level: Int): List<BibleQuizQuestion> {
        return allQuestions.filter { it.level == level }
    }
}
