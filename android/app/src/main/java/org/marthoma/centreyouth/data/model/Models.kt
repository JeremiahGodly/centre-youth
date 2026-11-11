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
) {
    val directImageUrl: String
        get() {
            if (image.isBlank()) return ""
            if (image.contains("drive.google.com") || image.contains("drive.usercontent.google.com")) {
                val fileId = ProgramEvent.extractGoogleDriveFileId(image)
                if (fileId.isNotBlank()) {
                    return "https://lh3.googleusercontent.com/d/$fileId"
                }
            }
            if (image.startsWith("http://") || image.startsWith("https://")) {
                return image
            }
            return ""
        }
}

data class ParishActivity(
    val id: String = "",
    val title: String = "",
    val date: String = "",
    val time: String = "",
    val venue: String = "",
    val description: String = "",
    val coordinator: String = "",
    val category: String = "Activity"
)

data class Parish(
    val id: String = "",
    val nameEn: String = "",
    val nameMl: String = "",
    val placeEn: String = "",
    val placeMl: String = "",
    val location: String = "",
    val presidentEn: String = "",
    val presidentMl: String = "",
    val vicarEn: String = "",
    val vicarMl: String = "",
    val vicePresident: String = "",
    val secretary: String = "",
    val secretaryPhone: String = "",
    val treasurer: String = "",
    val phone: String = "",
    val geo: String = "",
    val tag: String = "",
    val badge: String = "",
    val mapUrl: String = "",
    val activities: List<ParishActivity> = emptyList()
) {
    val displayPlace: String
        get() = placeEn.ifBlank { location.ifBlank { placeMl } }

    val displayPresident: String
        get() = presidentEn.ifBlank { presidentMl.ifBlank { vicarEn.ifBlank { vicarMl } } }

    val displayVicar: String
        get() = displayPresident
}

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

data class ProgramEvent(
    val id: String = "",
    val title: String = "",
    val titleMl: String = "",
    val category: String = "Event",
    val date: String = "",
    val time: String = "",
    val venue: String = "",
    val mapUrl: String = "",
    val description: String = "",
    val speakers: String = "",
    val imageUrl: String = "",
    val registrationUrl: String = "",
    val contactName: String = "",
    val contactPhone: String = "",
    val isActive: Boolean = true
) {
    /**
     * Converts Google Drive share URLs or raw web links into direct viewable image URLs for Coil
     */
    val directImageUrl: String
        get() {
            if (imageUrl.isBlank()) return ""
            if (imageUrl.contains("drive.google.com") || imageUrl.contains("drive.usercontent.google.com")) {
                val fileId = extractGoogleDriveFileId(imageUrl)
                if (fileId.isNotBlank()) {
                    return "https://lh3.googleusercontent.com/d/$fileId"
                }
            }
            return imageUrl
        }

    companion object {
        fun extractGoogleDriveFileId(url: String): String {
            val patterns = listOf(
                "d/([a-zA-Z0-9_-]+)".toRegex(),
                "id=([a-zA-Z0-9_-]+)".toRegex()
            )
            for (pattern in patterns) {
                val match = pattern.find(url)
                if (match != null) {
                    return match.groupValues[1]
                }
            }
            return ""
        }
    }
}
