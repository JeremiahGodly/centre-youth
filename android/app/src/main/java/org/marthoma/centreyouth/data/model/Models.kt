package org.marthoma.centreyouth.data.model

import com.google.gson.annotations.SerializedName

data class CentreInfo(
    val nameEn: String = "",
    val nameMl: String = "",
    val dioceseEn: String = "",
    val dioceseMl: String = "",
    val mottoEn: String = "",
    val mottoMl: String = "",
    val themeVerse: String = "",
    val themeQuoteEn: String = "",
    val themeQuoteMl: String = ""
)

data class StatItem(
    val id: String = "",
    val value: String = "",
    val suffix: String = "",
    val labelEn: String = "",
    val labelMl: String = "",
    val icon: String = ""
)

data class PillarItem(
    val id: String = "",
    val titleEn: String = "",
    val titleMl: String = "",
    val descEn: String = "",
    val descMl: String = "",
    val icon: String = ""
)

data class OfficeBearer(
    val id: String = "",
    val roleEn: String = "",
    val roleMl: String = "",
    val nameEn: String = "",
    val nameMl: String = "",
    val parishEn: String = "",
    val parishMl: String = "",
    val phone: String = "",
    val whatsapp: String = "",
    val email: String = "",
    val image: String = ""
)

data class Parish(
    val id: String = "",
    val nameEn: String = "",
    val nameMl: String = "",
    val placeEn: String = "",
    val placeMl: String = "",
    val vicarEn: String = "",
    val vicarMl: String = "",
    val phone: String = "",
    val geo: String = ""
)

data class Readings(
    val lesson1: String? = null,
    val lesson2: String? = null,
    val epistle: String? = null,
    val gospel: String? = null,
    val evening1: String? = null,
    val evening2: String? = null
)

data class LectionaryEntry(
    val id: String = "",
    val date: String = "",
    val displayDateEn: String = "",
    val displayDateMl: String = "",
    val displayDateMang: String = "",
    val month: String = "",
    val seasonEn: String = "",
    val seasonMl: String = "",
    val titleEn: String = "",
    val titleMl: String = "",
    val titleMang: String = "",
    val themeEn: String = "",
    val themeMl: String = "",
    val readings: Readings? = null,
    // Direct fallbacks if unnested
    val lesson1: String? = null,
    val lesson2: String? = null,
    val epistle: String? = null,
    val gospel: String? = null,
    val evening1: String? = null,
    val evening2: String? = null
) {
    val firstLesson: String get() = readings?.lesson1 ?: lesson1 ?: ""
    val secondLesson: String get() = readings?.lesson2 ?: lesson2 ?: ""
    val epistleReading: String get() = readings?.epistle ?: epistle ?: ""
    val gospelReading: String get() = readings?.gospel ?: gospel ?: ""
    val eveningReading1: String get() = readings?.evening1 ?: evening1 ?: ""
    val eveningReading2: String get() = readings?.evening2 ?: evening2 ?: ""
}

data class Song(
    val id: String = "",
    val trackNo: Int = 0,
    val titleEn: String = "",
    val titleMl: String = "",
    val titleMang: String = "",
    val category: String = "",
    val collection: String = "",
    val driveUrl: String = "",
    val streamUrl: String = "",
    val previewUrl: String = "",
    var isFavorite: Boolean = false
)

data class CampInfo(
    val themeEn: String = "",
    val themeMl: String = "",
    val dateEn: String = "",
    val venueEn: String = "",
    val descriptionEn: String = ""
)

data class KalamelaInfo(
    val edition: String = "",
    val venueEn: String = "",
    val dateEn: String = "",
    val itemsCount: Int = 18
)
