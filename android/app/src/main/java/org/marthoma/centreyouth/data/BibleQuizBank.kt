package org.marthoma.centreyouth.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.marthoma.centreyouth.data.model.BibleQuizQuestion
import org.marthoma.centreyouth.data.model.QuizLevelInfo
import java.io.InputStreamReader

/**
 * Bilingual (English + Sathyavedapusthakam Malayalam) 100-Level Bible Quiz Bank
 * containing 500 questions (5 per level across 10 escalating difficulty tiers).
 */
object BibleQuizBank {

    data class QuizTierSpec(
        val tier: Int,
        val baseTitle: String,
        val baseTitleMl: String,
        val subtitle: String,
        val subtitleMl: String,
        val difficultyLabel: String,
        val difficultyLabelMl: String,
        val primaryColorHex: Long,
        val secondaryColorHex: Long
    )

    val tiers: List<QuizTierSpec> = listOf(
        QuizTierSpec(
            tier = 1,
            baseTitle = "Genesis Dawn",
            baseTitleMl = "ഉല്പത്തി പ്രഭാതം",
            subtitle = "Creation, Patriarchs & Nativity",
            subtitleMl = "സൃഷ്ടി, പിതാക്കന്മാർ & തിരുപ്പിറവി",
            difficultyLabel = "Easy",
            difficultyLabelMl = "എളുപ്പം",
            primaryColorHex = 0xFF00E5A3,
            secondaryColorHex = 0xFF00B4D8
        ),
        QuizTierSpec(
            tier = 2,
            baseTitle = "Galilee Walk",
            baseTitleMl = "ഗലീല യാത്ര",
            subtitle = "Parables, Disciples & Early Ministry",
            subtitleMl = "ഉപമകൾ, ശിഷ്യന്മാർ & ശുശ്രൂഷ",
            difficultyLabel = "Easy+",
            difficultyLabelMl = "ലളിതം+",
            primaryColorHex = 0xFF38BDF8,
            secondaryColorHex = 0xFF6366F1
        ),
        QuizTierSpec(
            tier = 3,
            baseTitle = "Exodus Trail",
            baseTitleMl = "പുറപ്പാടിന്റെ പാത",
            subtitle = "Moses, Sinai & Wilderness Victories",
            subtitleMl = "മോശെ, സീനായി & മരുഭൂമി യാത്ര",
            difficultyLabel = "Moderate",
            difficultyLabelMl = "മിതമായത്",
            primaryColorHex = 0xFFA855F7,
            secondaryColorHex = 0xFFEC4899
        ),
        QuizTierSpec(
            tier = 4,
            baseTitle = "Kingdom Songs",
            baseTitleMl = "രാജ്യഗീതങ്ങൾ",
            subtitle = "Psalms, David & Early Church Triumphs",
            subtitleMl = "സങ്കീർത്തനങ്ങൾ, ദാവീദ് & ആദിമസഭ",
            difficultyLabel = "Moderate+",
            difficultyLabelMl = "മിതമായത്+",
            primaryColorHex = 0xFFF59E0B,
            secondaryColorHex = 0xFFEF4444
        ),
        QuizTierSpec(
            tier = 5,
            baseTitle = "Prophets' Fire",
            baseTitleMl = "പ്രവാചകാഗ്നി",
            subtitle = "Elijah, Elisha & Royal Reformers",
            subtitleMl = "ഏലീയാവ്, എലീശാ & നവീകരണം",
            difficultyLabel = "Challenging",
            difficultyLabelMl = "വെല്ലുവിളി",
            primaryColorHex = 0xFFEC4899,
            secondaryColorHex = 0xFF8B5CF6
        ),
        QuizTierSpec(
            tier = 6,
            baseTitle = "Monarchy & Wisdom",
            baseTitleMl = "രാജത്വവും ജ്ഞാനവും",
            subtitle = "Divided Kingdom, Proverbs & Paul's Letters",
            subtitleMl = "വിഭജിച്ച രാജ്യം, സദൃശവാക്യങ്ങൾ & ലേഖനങ്ങൾ",
            difficultyLabel = "Hard",
            difficultyLabelMl = "കഠിനം",
            primaryColorHex = 0xFF14B8A6,
            secondaryColorHex = 0xFF3B82F6
        ),
        QuizTierSpec(
            tier = 7,
            baseTitle = "Sacred Sanctuary",
            baseTitleMl = "വിശുദ്ധ മന്ദിരം",
            subtitle = "Tabernacle Law, Judges & Epistles",
            subtitleMl = "സമാഗമനക്കുടാരം, ന്യായാധിപന്മാർ & ലേഖനങ്ങൾ",
            difficultyLabel = "Hard+",
            difficultyLabelMl = "കഠിനം+",
            primaryColorHex = 0xFFF97316,
            secondaryColorHex = 0xFFDC2626
        ),
        QuizTierSpec(
            tier = 8,
            baseTitle = "Scrolls & Storms",
            baseTitleMl = "ചുരുളുകളും കൊടുങ്കാറ്റും",
            subtitle = "Minor Prophets, Acts & Gospel Details",
            subtitleMl = "ചെറിയ പ്രവാചകന്മാർ & അപ്പൊസ്തല പ്രവൃത്തികൾ",
            difficultyLabel = "Very Hard",
            difficultyLabelMl = "അതീവ കഠിനം",
            primaryColorHex = 0xFF6366F1,
            secondaryColorHex = 0xFFD946EF
        ),
        QuizTierSpec(
            tier = 9,
            baseTitle = "Scholar's Trial",
            baseTitleMl = "പണ്ഡിത പരീക്ഷ",
            subtitle = "Deep Chronicles, Names & Chronology",
            subtitleMl = "ദിനവൃത്താന്തം, പേരുകൾ & കാലഗണന",
            difficultyLabel = "Expert",
            difficultyLabelMl = "വിദഗ്ധത",
            primaryColorHex = 0xFFE11D48,
            secondaryColorHex = 0xFFFB923C
        ),
        QuizTierSpec(
            tier = 10,
            baseTitle = "Revelation Glory",
            baseTitleMl = "വെളിപ്പാടിന്റെ മഹത്വം",
            subtitle = "Ultimate Biblical Mastery",
            subtitleMl = "പരമോന്നത വേദജ്ഞാനം",
            difficultyLabel = "Master",
            difficultyLabelMl = "മഹാവിദഗ്ധം",
            primaryColorHex = 0xFFFACC15,
            secondaryColorHex = 0xFFEF4444
        )
    )

    private fun timerForLevel(level: Int): Int = when (level) {
        in 1..10 -> 25
        in 11..20 -> 24
        in 21..30 -> 22
        in 31..40 -> 20
        in 41..50 -> 18
        in 51..60 -> 17
        in 61..70 -> 15
        in 71..80 -> 14
        in 81..90 -> 12
        in 91..95 -> 11
        else -> 10
    }

    val levels: List<QuizLevelInfo> = (1..100).map { level ->
        val tierIdx = (level - 1) / 10
        val stageInTier = ((level - 1) % 10) + 1
        val spec = tiers[tierIdx]
        QuizLevelInfo(
            level = level,
            title = "${spec.baseTitle} $stageInTier",
            titleMl = "${spec.baseTitleMl} $stageInTier",
            subtitle = "Tier ${spec.tier} • ${spec.subtitle}",
            subtitleMl = "ഘട്ടം ${spec.tier} • ${spec.subtitleMl}",
            difficultyLabel = spec.difficultyLabel,
            difficultyLabelMl = spec.difficultyLabelMl,
            timePerQuestionSec = timerForLevel(level),
            primaryColorHex = spec.primaryColorHex,
            secondaryColorHex = spec.secondaryColorHex
        )
    }

    @Volatile
    private var cachedQuestionsByLevel: Map<Int, List<BibleQuizQuestion>>? = null

    fun ensureLoaded(context: Context) {
        if (cachedQuestionsByLevel != null) return
        synchronized(this) {
            if (cachedQuestionsByLevel != null) return
            val listType = object : TypeToken<List<BibleQuizQuestion>>() {}.type
            val questions: List<BibleQuizQuestion> = context.assets.open("bible_quiz_100_levels.json").use { input ->
                InputStreamReader(input, Charsets.UTF_8).use { reader ->
                    Gson().fromJson(reader, listType) ?: emptyList()
                }
            }
            cachedQuestionsByLevel = questions.groupBy { it.level }
        }
    }

    fun getLevelInfo(level: Int): QuizLevelInfo {
        return levels.firstOrNull { it.level == level } ?: levels.first()
    }

    fun getQuestionsForLevel(context: Context, level: Int): List<BibleQuizQuestion> {
        ensureLoaded(context)
        return cachedQuestionsByLevel?.get(level).orEmpty()
    }
}
