package org.marthoma.centreyouth.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.marthoma.centreyouth.data.model.*
import org.marthoma.centreyouth.notifications.NotificationHelper
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class CentreRepository(private val context: Context) {

    private val gson = Gson()
    private val prefs: SharedPreferences =
        context.getSharedPreferences("centre_youth_prefs", Context.MODE_PRIVATE)

    private var cachedCentreInfo: CentreInfo? = null
    private var cachedStats: List<StatItem> = emptyList()
    private var cachedPillars: List<PillarItem> = emptyList()
    private var cachedOfficeBearers: List<OfficeBearer> = emptyList()
    private var cachedParishes: List<Parish> = emptyList()
    private var cachedLectionary: List<LectionaryEntry> = emptyList()
    private var cachedSongs: List<Song> = emptyList()
    private var cachedPrograms: List<ProgramEvent> = emptyList()
    private var cachedPrayerWall: List<PrayerRequest> = emptyList()
    private var cachedGalleryPhotos: List<GalleryPhoto> = emptyList()
    private var cachedLeaderboard: List<LeaderboardEntry> = emptyList()

    // Configurable Google Sheet Web App / JSON URL
    private val PREF_SHEET_URL = "google_sheet_programs_url"
    private val DEFAULT_SHEET_URL = "https://script.google.com/macros/s/AKfycbyfdOFNZ4U3Hmwvi8STt56LHB_VDhiST6T89d1vq8YyZ6gw4IswpEUHsLIH6uBMZdc/exec"

    init {
        loadData()
    }

    private fun loadData() {
        try {
            // Load Sakhyam data
            val sakhyamStream = context.assets.open("sakhyam_data.json")
            val jsonText = sakhyamStream.bufferedReader().use { it.readText() }
            val root = JSONObject(jsonText)

            if (root.has("centreInfo")) {
                cachedCentreInfo = gson.fromJson(root.getJSONObject("centreInfo").toString(), CentreInfo::class.java)
            }

            if (root.has("stats")) {
                val type = object : TypeToken<List<StatItem>>() {}.type
                cachedStats = gson.fromJson(root.getJSONArray("stats").toString(), type)
            }

            if (root.has("pillars")) {
                val type = object : TypeToken<List<PillarItem>>() {}.type
                cachedPillars = gson.fromJson(root.getJSONArray("pillars").toString(), type)
            }

            // Load Office Bearers: check local cached updates from Google Sheet first, else bundled
            val cachedLeadersJson = prefs.getString("cached_leaders_json", null)
            if (!cachedLeadersJson.isNullOrBlank()) {
                val type = object : TypeToken<List<OfficeBearer>>() {}.type
                cachedOfficeBearers = gson.fromJson(cachedLeadersJson, type)
            } else if (root.has("officeBearers")) {
                val type = object : TypeToken<List<OfficeBearer>>() {}.type
                cachedOfficeBearers = gson.fromJson(root.getJSONArray("officeBearers").toString(), type)
            }

            // Load Parishes: check local cached updates from Google Sheet first, else bundled
            val cachedParishesJson = prefs.getString("cached_parishes_json", null)
            if (!cachedParishesJson.isNullOrBlank()) {
                val type = object : TypeToken<List<Parish>>() {}.type
                val pList: List<Parish> = gson.fromJson(cachedParishesJson, type)
                cachedParishes = pList.sortedBy { it.nameEn.lowercase().trim() }
            } else if (root.has("parishes")) {
                val type = object : TypeToken<List<Parish>>() {}.type
                val pList: List<Parish> = gson.fromJson(root.getJSONArray("parishes").toString(), type)
                cachedParishes = pList.sortedBy { it.nameEn.lowercase().trim() }
            }

            if (root.has("lectionary")) {
                val type = object : TypeToken<List<LectionaryEntry>>() {}.type
                cachedLectionary = gson.fromJson(root.getJSONArray("lectionary").toString(), type)
            }

            // Load Songs
            val songsStream = context.assets.open("songs_data.json")
            val songsType = object : TypeToken<List<Song>>() {}.type
            val songs: List<Song> = gson.fromJson(InputStreamReader(songsStream), songsType)
            val favs = getFavoriteSongIds()
            songs.forEach { it.isFavorite = favs.contains(it.id) }
            cachedSongs = songs

            // Load Programs: first from local cache, else from bundled defaults
            val cachedProgramsJson = prefs.getString("cached_programs_json", null)
            if (!cachedProgramsJson.isNullOrBlank()) {
                val progType = object : TypeToken<List<ProgramEvent>>() {}.type
                cachedPrograms = gson.fromJson(cachedProgramsJson, progType)
            }
            if (cachedPrograms.isEmpty()) {
                cachedPrograms = buildInitialDefaultPrograms(root)
            }

            // Load Prayer Wall
            val cachedPrayerJson = prefs.getString("cached_prayer_wall_json", null)
            if (!cachedPrayerJson.isNullOrBlank()) {
                val pType = object : TypeToken<List<PrayerRequest>>() {}.type
                cachedPrayerWall = gson.fromJson(cachedPrayerJson, pType)
            }
            if (cachedPrayerWall.isEmpty()) {
                cachedPrayerWall = buildDefaultPrayerRequests()
            }

            // Load Gallery Photos (Sheet/Drive cache first, else bundled sakhyam_data.json gallery)
            val cachedGalleryJson = prefs.getString("cached_gallery_json", null)
            if (!cachedGalleryJson.isNullOrBlank()) {
                val gType = object : TypeToken<List<GalleryPhoto>>() {}.type
                cachedGalleryPhotos = gson.fromJson(cachedGalleryJson, gType)
            }
            if (cachedGalleryPhotos.isEmpty() && root.has("gallery")) {
                val gArray = root.getJSONArray("gallery")
                val gList = mutableListOf<GalleryPhoto>()
                for (i in 0 until gArray.length()) {
                    val obj = gArray.getJSONObject(i)
                    gList.add(
                        GalleryPhoto(
                            id = obj.optString("id", "gal-$i"),
                            title = obj.optString("titleEn", ""),
                            titleMl = obj.optString("titleMl", ""),
                            category = obj.optString("category", "Memories").replaceFirstChar { it.uppercase() },
                            date = "2026",
                            imageUrl = obj.optString("src", ""),
                            caption = obj.optString("titleEn", ""),
                            isActive = true
                        )
                    )
                }
                cachedGalleryPhotos = gList
            }

            // Load Leaderboard
            val cachedLeaderboardJson = prefs.getString("cached_leaderboard_json", null)
            if (!cachedLeaderboardJson.isNullOrBlank()) {
                val lbType = object : TypeToken<List<LeaderboardEntry>>() {}.type
                val lList: List<LeaderboardEntry> = gson.fromJson(cachedLeaderboardJson, lbType)
                cachedLeaderboard = lList.sortedWith(
                    compareByDescending<LeaderboardEntry> { it.score }
                        .thenByDescending { it.stars }
                        .thenByDescending { it.highestLevel }
                )
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun buildInitialDefaultPrograms(root: JSONObject): List<ProgramEvent> {
        val list = mutableListOf<ProgramEvent>()
        if (root.has("cShoreCamp")) {
            val camp = root.getJSONObject("cShoreCamp")
            list.add(
                ProgramEvent(
                    id = "cshore-camp-2026",
                    title = camp.optString("titleEn", "C-SHORE Youth Camp 2026"),
                    titleMl = camp.optString("titleMl", "സി-ഷോർ യൂത്ത് ക്യാമ്പ് 2026"),
                    category = "Camp",
                    date = camp.optString("dateEn", "October 19, 20, 21, 2026"),
                    time = "Full Day Residential",
                    venue = camp.optString("venueEn", "MCMAT, Perumbavoor"),
                    description = "${camp.optString("themeEn", "")}\nInaugurator: ${camp.optString("inauguratorEn", "")}\nMain Talk: ${camp.optString("mainTalkEn", "")}\nMusic: ${camp.optString("musicEn", "")}",
                    speakers = "${camp.optString("inauguratorEn", "")}, ${camp.optString("chiefGuestEn", "")}",
                    imageUrl = "",
                    registrationUrl = "https://forms.gle/cmtz",
                    contactName = "Ashin K Santhosh (Secretary)",
                    contactPhone = "9497882179",
                    isActive = true
                )
            )
        }
        if (root.has("kalamela")) {
            val k = root.getJSONObject("kalamela")
            list.add(
                ProgramEvent(
                    id = "kalamela-2026",
                    title = "LUMORA 2026 (Centre Kalamela)",
                    titleMl = "ലുമോറ 2026 - സെന്റർ യുവജന കലാമേള",
                    category = "Kalamela",
                    date = k.optString("dateEn", "November 14, 2026 (Saturday)"),
                    time = "9:00 AM Onwards",
                    venue = k.optString("venueEn", "St. Paul's Mar Thoma Church, Perumbavoor"),
                    description = "Keezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam Talent Fest. 18 competition items across 4 stages adhering to MTYS Prathibha Vedi Manual.\n\nThemes: Mime (Creation), Choreography (Hope), Tableau (Miracle of Jesus), Bible Quiz (1 Chronicles, Philippians, Colossians, 1 & 2 Thessalonians).",
                    speakers = "Executive Committee MTYS & Special Judges Panel",
                    imageUrl = "https://drive.google.com/file/d/1zWQfoC1HsWhhv6EALpBFNwDzFhNDTEhz/view?usp=sharing",
                    registrationUrl = "https://forms.gle/lumora2026",
                    contactName = "Gibin Saji (Treasurer)",
                    contactPhone = "9061391963",
                    isActive = true
                )
            )
        }
        return list
    }

    fun getCentreInfo(): CentreInfo = cachedCentreInfo ?: CentreInfo(
        nameEn = "Keezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam",
        nameMl = "കീഴില്ലം - മൂന്നാർ സെന്റർ മാർത്തോമ്മാ യുവജനസഖ്യം",
        mottoEn = "Lighted to Lighten",
        mottoMl = "പ്രകാശിതരായി പ്രകാശിപ്പിക്കുക",
        dioceseEn = "Kottayam - Kochi Diocese",
        dioceseMl = "കോട്ടയം - കൊച്ചി ഭദ്രാസനം",
        themeVerse = "Romans 12:2",
        themeQuoteEn = "Do not be conformed to this world, but be transformed by the renewal of your mind."
    )

    fun getStats(): List<StatItem> = cachedStats
    fun getPillars(): List<PillarItem> = cachedPillars
    fun getOfficeBearers(): List<OfficeBearer> = cachedOfficeBearers
    fun getParishes(): List<Parish> = cachedParishes.sortedBy { it.nameEn.lowercase().trim() }
    fun getLectionary(): List<LectionaryEntry> = cachedLectionary
    fun getSongs(): List<Song> = cachedSongs

    fun getFavoriteSongIds(): Set<String> {
        return prefs.getStringSet("favorite_song_ids", emptySet()) ?: emptySet()
    }

    fun toggleFavoriteSong(songId: String): Boolean {
        val currentFavs = getFavoriteSongIds().toMutableSet()
        val isNowFav = if (currentFavs.contains(songId)) {
            currentFavs.remove(songId)
            false
        } else {
            currentFavs.add(songId)
            true
        }
        prefs.edit().putStringSet("favorite_song_ids", currentFavs).apply()
        cachedSongs.find { it.id == songId }?.isFavorite = isNowFav
        return isNowFav
    }

    /**
     * Returns the upcoming Sunday lectionary entry.
     */
    fun getUpcomingSunday(): LectionaryEntry? {
        if (cachedLectionary.isEmpty()) return null
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val todayStr = sdf.format(Date())

        val upcomingList = cachedLectionary
            .filter { it.date >= todayStr }
            .sortedBy { it.date }

        return upcomingList.firstOrNull() ?: cachedLectionary.lastOrNull()
    }

    /**
     * Backward-compatible alias for the active/upcoming Sunday.
     */
    fun getTodayLectionary(): LectionaryEntry? = getUpcomingSunday()

    /**
     * Filters out past / finished Sundays so users only see upcoming and active lectionary readings.
     */
    fun getUpcomingLectionaries(): List<LectionaryEntry> {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val todayStr = sdf.format(Date())

        val upcoming = cachedLectionary
            .filter { it.date >= todayStr }
            .sortedBy { it.date }

        return if (upcoming.isNotEmpty()) upcoming else cachedLectionary
    }

    fun getSongCategories(): List<String> {
        val cats = cachedSongs.map { it.category }.filter { it.isNotBlank() }.distinct()
        return listOf("All") + cats
    }

    /**
     * Active upcoming programs & event specifications (offline cached or live synced)
     */
    fun getPrograms(): List<ProgramEvent> = cachedPrograms.filter { it.isActive }

    fun getGoogleSheetUrl(): String {
        val saved = prefs.getString(PREF_SHEET_URL, null)
        return if (!saved.isNullOrBlank()) saved else DEFAULT_SHEET_URL
    }

    fun setGoogleSheetUrl(url: String) {
        prefs.edit().putString(PREF_SHEET_URL, url.trim()).apply()
    }

    fun getLastSyncedFormatted(): String {
        val timestamp = prefs.getLong("last_synced_timestamp", 0L)
        if (timestamp == 0L) return "Pending initial sync"
        val now = System.currentTimeMillis()
        val diffMs = now - timestamp
        if (diffMs < 60_000) return "Just now"
        if (diffMs < 3_600_000) {
            val mins = diffMs / 60_000
            return "$mins min${if (mins > 1) "s" else ""} ago"
        }
        val cal = Calendar.getInstance().apply { timeInMillis = timestamp }
        val nowCal = Calendar.getInstance().apply { timeInMillis = now }
        val timeFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
        return if (cal.get(Calendar.DAY_OF_YEAR) == nowCal.get(Calendar.DAY_OF_YEAR) &&
            cal.get(Calendar.YEAR) == nowCal.get(Calendar.YEAR)
        ) {
            "Today at ${timeFormat.format(Date(timestamp))}"
        } else {
            val dateFormat = SimpleDateFormat("MMM d, h:mm a", Locale.getDefault())
            dateFormat.format(Date(timestamp))
        }
    }

    private fun processIncomingPrograms(list: List<ProgramEvent>, rawJson: String) {
        if (list.isEmpty()) return
        cachedPrograms = list

        val currentKnown = prefs.getStringSet("known_event_ids", null)
        val incomingIds = list.map { it.id.ifBlank { it.title } }.toSet()

        if (currentKnown != null) {
            val newlyAdded = list.filter { it.isActive && !currentKnown.contains(it.id.ifBlank { it.title }) }
            for (newEvent in newlyAdded) {
                NotificationHelper.showNewEventNotification(
                    context = context,
                    eventTitle = newEvent.title,
                    eventDate = newEvent.date,
                    eventVenue = newEvent.venue
                )
            }
        }

        prefs.edit()
            .putString("cached_programs_json", rawJson)
            .putStringSet("known_event_ids", incomingIds)
            .putLong("last_synced_timestamp", System.currentTimeMillis())
            .apply()
    }

    /**
     * Background sync: pulls the latest specifications, posters & details from the Google Sheet.
     * Supports both direct array of programs and combined { programs: [...], leaders: [...] }
     */
    suspend fun syncProgramsFromGoogleSheet(targetUrl: String? = null): Result<List<ProgramEvent>> = withContext(Dispatchers.IO) {
        val endpoint = targetUrl?.trim().takeUnless { it.isNullOrBlank() }
            ?: getGoogleSheetUrl().takeUnless { it.isBlank() }
            ?: return@withContext Result.failure(Exception("No Google Sheet URL configured"))

        try {
            val jsonText = fetchUrlWithRedirects(endpoint).trim()
            if (jsonText.startsWith("{")) {
                val root = JSONObject(jsonText)
                if (root.has("programs")) {
                    val pArray = root.getJSONArray("programs").toString()
                    val progType = object : TypeToken<List<ProgramEvent>>() {}.type
                    val list: List<ProgramEvent> = gson.fromJson(pArray, progType)
                    processIncomingPrograms(list, pArray)
                }
                if (root.has("leaders")) {
                    val lArray = root.getJSONArray("leaders").toString()
                    val leadType = object : TypeToken<List<OfficeBearer>>() {}.type
                    val lList: List<OfficeBearer> = gson.fromJson(lArray, leadType)
                    if (lList.isNotEmpty()) {
                        cachedOfficeBearers = lList
                        prefs.edit()
                            .putString("cached_leaders_json", lArray)
                            .putLong("last_synced_timestamp", System.currentTimeMillis())
                            .apply()
                    }
                }
                if (root.has("parishes")) {
                    val parArray = root.getJSONArray("parishes").toString()
                    val parType = object : TypeToken<List<Parish>>() {}.type
                    val pList: List<Parish> = gson.fromJson(parArray, parType)
                    if (pList.isNotEmpty()) {
                        cachedParishes = pList.sortedBy { it.nameEn.lowercase().trim() }
                        prefs.edit()
                            .putString("cached_parishes_json", parArray)
                            .putLong("last_synced_timestamp", System.currentTimeMillis())
                            .apply()
                    }
                }
                if (root.has("prayerWall")) {
                    val pwArray = root.getJSONArray("prayerWall").toString()
                    val pwType = object : TypeToken<List<PrayerRequest>>() {}.type
                    val pwList: List<PrayerRequest> = gson.fromJson(pwArray, pwType)
                    if (pwList.isNotEmpty()) {
                        cachedPrayerWall = pwList
                        prefs.edit().putString("cached_prayer_wall_json", pwArray).apply()
                    }
                }
                if (root.has("gallery")) {
                    val galArray = root.getJSONArray("gallery").toString()
                    val galType = object : TypeToken<List<GalleryPhoto>>() {}.type
                    val gList: List<GalleryPhoto> = gson.fromJson(galArray, galType)
                    if (gList.isNotEmpty()) {
                        cachedGalleryPhotos = gList
                        prefs.edit().putString("cached_gallery_json", galArray).apply()
                    }
                }
                if (root.has("leaderboard")) {
                    val lbArray = root.getJSONArray("leaderboard").toString()
                    val lbType = object : TypeToken<List<LeaderboardEntry>>() {}.type
                    val lbList: List<LeaderboardEntry> = gson.fromJson(lbArray, lbType)
                    if (lbList.isNotEmpty()) {
                        cachedLeaderboard = lbList.sortedWith(
                            compareByDescending<LeaderboardEntry> { it.score }
                                .thenByDescending { it.stars }
                                .thenByDescending { it.highestLevel }
                        )
                        prefs.edit().putString("cached_leaderboard_json", lbArray).apply()
                    }
                }
                Result.success(cachedPrograms)
            } else if (jsonText.startsWith("[")) {
                val progType = object : TypeToken<List<ProgramEvent>>() {}.type
                val list: List<ProgramEvent> = gson.fromJson(jsonText, progType)
                processIncomingPrograms(list, jsonText)
                Result.success(list)
            } else {
                Result.failure(Exception("Invalid JSON response"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    /**
     * Pulls latest leaders & office bearer profiles from Google Sheet (?tab=leaders)
     */
    suspend fun syncLeadersFromGoogleSheet(): Result<List<OfficeBearer>> = withContext(Dispatchers.IO) {
        val baseUrl = getGoogleSheetUrl()
        if (baseUrl.isBlank()) return@withContext Result.failure(Exception("No Google Sheet URL configured"))
        val endpoint = if (baseUrl.contains("?")) "$baseUrl&tab=leaders" else "$baseUrl?tab=leaders"

        try {
            val jsonText = fetchUrlWithRedirects(endpoint).trim()
            if (jsonText.startsWith("[")) {
                val leadType = object : TypeToken<List<OfficeBearer>>() {}.type
                val list: List<OfficeBearer> = gson.fromJson(jsonText, leadType)
                if (list.isNotEmpty()) {
                    cachedOfficeBearers = list
                    prefs.edit()
                        .putString("cached_leaders_json", jsonText)
                        .putLong("last_synced_timestamp", System.currentTimeMillis())
                        .apply()
                }
                Result.success(list)
            } else if (jsonText.startsWith("{")) {
                val root = JSONObject(jsonText)
                if (root.has("leaders")) {
                    val lArray = root.getJSONArray("leaders").toString()
                    val leadType = object : TypeToken<List<OfficeBearer>>() {}.type
                    val list: List<OfficeBearer> = gson.fromJson(lArray, leadType)
                    if (list.isNotEmpty()) {
                        cachedOfficeBearers = list
                        prefs.edit()
                            .putString("cached_leaders_json", lArray)
                            .putLong("last_synced_timestamp", System.currentTimeMillis())
                            .apply()
                    }
                    Result.success(list)
                } else {
                    Result.failure(Exception("No leaders array in JSON"))
                }
            } else {
                Result.failure(Exception("Invalid leaders JSON"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    /**
     * Pulls latest parishes and their specific events/activities from Google Sheet (?tab=parishes)
     */
    suspend fun syncParishesFromGoogleSheet(): Result<List<Parish>> = withContext(Dispatchers.IO) {
        val baseUrl = getGoogleSheetUrl()
        if (baseUrl.isBlank()) return@withContext Result.failure(Exception("No Google Sheet URL configured"))
        val endpoint = if (baseUrl.contains("?")) "$baseUrl&tab=parishes" else "$baseUrl?tab=parishes"

        try {
            val jsonText = fetchUrlWithRedirects(endpoint).trim()
            if (jsonText.startsWith("[")) {
                val parishType = object : TypeToken<List<Parish>>() {}.type
                val list: List<Parish> = gson.fromJson(jsonText, parishType)
                if (list.isNotEmpty()) {
                    val sorted = list.sortedBy { it.nameEn.lowercase().trim() }
                    cachedParishes = sorted
                    prefs.edit()
                        .putString("cached_parishes_json", jsonText)
                        .putLong("last_synced_timestamp", System.currentTimeMillis())
                        .apply()
                    Result.success(sorted)
                } else {
                    Result.failure(Exception("Empty parishes list"))
                }
            } else if (jsonText.startsWith("{")) {
                val root = JSONObject(jsonText)
                if (root.has("parishes")) {
                    val pArray = root.getJSONArray("parishes").toString()
                    val parishType = object : TypeToken<List<Parish>>() {}.type
                    val list: List<Parish> = gson.fromJson(pArray, parishType)
                    if (list.isNotEmpty()) {
                        val sorted = list.sortedBy { it.nameEn.lowercase().trim() }
                        cachedParishes = sorted
                        prefs.edit()
                            .putString("cached_parishes_json", pArray)
                            .putLong("last_synced_timestamp", System.currentTimeMillis())
                            .apply()
                        Result.success(sorted)
                    } else {
                        Result.failure(Exception("Empty parishes array"))
                    }
                } else {
                    Result.failure(Exception("No parishes array in JSON"))
                }
            } else {
                Result.failure(Exception("Invalid parishes JSON"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    private fun fetchUrlWithRedirects(urlString: String): String {
        var currentUrl = urlString
        var redirects = 0
        while (redirects < 5) {
            val url = URL(currentUrl)
            val conn = url.openConnection() as HttpURLConnection
            conn.connectTimeout = 10000
            conn.readTimeout = 10000
            conn.instanceFollowRedirects = true
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Android; Mobile)")

            val status = conn.responseCode
            if (status == HttpURLConnection.HTTP_MOVED_TEMP ||
                status == HttpURLConnection.HTTP_MOVED_PERM ||
                status == HttpURLConnection.HTTP_SEE_OTHER ||
                status == 307 || status == 308
            ) {
                val newUrl = conn.getHeaderField("Location")
                if (newUrl != null) {
                    currentUrl = newUrl
                    redirects++
                    continue
                }
            }
            if (status in 200..299) {
                return conn.inputStream.bufferedReader().use { it.readText() }
            } else {
                throw Exception("HTTP Error: $status")
            }
        }
        throw Exception("Too many redirects")
    }

    // ==================== BIBLE QUIZ PERSISTENCE ====================

    fun saveQuizLevelResult(level: Int, score: Int, correctCount: Int, totalCount: Int): Int {
        val accuracy = if (totalCount > 0) (correctCount.toFloat() / totalCount.toFloat()) else 0f
        val earnedStars = when {
            accuracy >= 0.85f -> 3
            accuracy >= 0.60f -> 2
            accuracy >= 0.30f -> 1
            else -> 0
        }
        val prevBestScore = getQuizBestScore(level)
        val prevStars = getQuizStars(level)
        val prevCorrect = getQuizBestCorrect(level)

        prefs.edit().apply {
            if (score > prevBestScore) putInt("quiz_best_score_$level", score)
            if (earnedStars > prevStars) putInt("quiz_best_stars_$level", earnedStars)
            if (correctCount > prevCorrect) putInt("quiz_best_correct_$level", correctCount)
            apply()
        }
        return earnedStars
    }

    fun getQuizBestScore(level: Int): Int = prefs.getInt("quiz_best_score_$level", 0)

    fun getQuizStars(level: Int): Int = prefs.getInt("quiz_best_stars_$level", 0)

    fun getQuizBestCorrect(level: Int): Int = prefs.getInt("quiz_best_correct_$level", 0)

    fun getQuizTotalScore(): Int = (1..100).sumOf { getQuizBestScore(it) }

    fun getQuizTotalStars(): Int = (1..100).sumOf { getQuizStars(it) }

    fun getQuizLanguage(): QuizLanguage {
        val raw = prefs.getString("quiz_language", QuizLanguage.BOTH.name)
        return try {
            QuizLanguage.valueOf(raw ?: QuizLanguage.BOTH.name)
        } catch (_: Exception) {
            QuizLanguage.BOTH
        }
    }

    fun saveQuizLanguage(language: QuizLanguage) {
        prefs.edit().putString("quiz_language", language.name).apply()
    }

    fun getHighestQuizLevel(): Int {
        return (100 downTo 1).firstOrNull { getQuizBestScore(it) > 0 } ?: 1
    }

    // ==================== DAILY STREAK TRACKER ====================

    private fun dateKeyForCalendar(cal: Calendar): String {
        return "${cal.get(Calendar.YEAR)}-${cal.get(Calendar.DAY_OF_YEAR)}"
    }

    fun recordDailyStreakCheckIn(): Int {
        val todayCal = Calendar.getInstance()
        val yesterdayCal = (todayCal.clone() as Calendar).apply {
            add(Calendar.DAY_OF_YEAR, -1)
        }
        val todayKey = dateKeyForCalendar(todayCal)
        val yesterdayKey = dateKeyForCalendar(yesterdayCal)

        val lastDay = prefs.getString("daily_streak_last_day", null)
        val current = prefs.getInt("daily_streak_current", 0)
        val longest = prefs.getInt("daily_streak_longest", 0)

        val updated = when (lastDay) {
            todayKey -> current.coerceAtLeast(1)
            yesterdayKey -> current + 1
            else -> 1
        }
        val newLongest = maxOf(longest, updated)

        if (lastDay != todayKey || updated != current || newLongest != longest) {
            prefs.edit()
                .putInt("daily_streak_current", updated)
                .putInt("daily_streak_longest", newLongest)
                .putString("daily_streak_last_day", todayKey)
                .apply()
        }
        return updated
    }

    fun getDailyStreak(): Int {
        val todayCal = Calendar.getInstance()
        val yesterdayCal = (todayCal.clone() as Calendar).apply {
            add(Calendar.DAY_OF_YEAR, -1)
        }
        val todayKey = dateKeyForCalendar(todayCal)
        val yesterdayKey = dateKeyForCalendar(yesterdayCal)
        val lastDay = prefs.getString("daily_streak_last_day", null)
        return when (lastDay) {
            todayKey, yesterdayKey -> prefs.getInt("daily_streak_current", 0).coerceAtLeast(1)
            else -> 0
        }
    }

    fun getLongestDailyStreak(): Int {
        return maxOf(prefs.getInt("daily_streak_longest", 0), getDailyStreak())
    }

    fun hasPlayedQuizToday(): Boolean {
        val todayKey = dateKeyForCalendar(Calendar.getInstance())
        return prefs.getString("daily_quiz_played_day", null) == todayKey
    }

    fun markQuizPlayedToday() {
        recordDailyStreakCheckIn()
        val todayKey = dateKeyForCalendar(Calendar.getInstance())
        prefs.edit().putString("daily_quiz_played_day", todayKey).apply()
    }

    fun getPlayerName(): String = prefs.getString("player_profile_name", "") ?: ""

    fun getPlayerParish(): String = prefs.getString("player_profile_parish", "") ?: ""

    fun savePlayerProfile(name: String, parish: String) {
        prefs.edit()
            .putString("player_profile_name", name.trim())
            .putString("player_profile_parish", parish.trim())
            .apply()
    }

    // ==================== APP SETTINGS (THEME & NOTIFICATIONS) ====================

    fun getThemeMode(): AppThemeMode {
        val raw = prefs.getString("app_theme_mode", AppThemeMode.SYSTEM.name)
        return try {
            AppThemeMode.valueOf(raw ?: AppThemeMode.SYSTEM.name)
        } catch (_: Exception) {
            AppThemeMode.SYSTEM
        }
    }

    fun saveThemeMode(mode: AppThemeMode) {
        prefs.edit().putString("app_theme_mode", mode.name).apply()
    }

    fun isDailyNotificationsEnabled(): Boolean = prefs.getBoolean("daily_notifications_enabled", true)

    fun setDailyNotificationsEnabled(enabled: Boolean) {
        prefs.edit().putBoolean("daily_notifications_enabled", enabled).apply()
    }

    fun getDefaultSheetUrl(): String = DEFAULT_SHEET_URL

    fun resetGoogleSheetUrl() {
        prefs.edit().remove(PREF_SHEET_URL).apply()
    }

    // ==================== PRAYER WALL ====================

    private fun buildDefaultPrayerRequests(): List<PrayerRequest> {
        return listOf(
            PrayerRequest(
                id = "pw-1",
                name = "Centre Prayer Cell",
                parish = "Keezhillam Nazareth MTC",
                category = "Church & Youth",
                request = "Pray for the spiritual growth of all youth across the 15 parishes in Keezhillam - Munnar Centre and upcoming Kalamela & Camp preparations.",
                date = "Today",
                prayedCount = 18
            ),
            PrayerRequest(
                id = "pw-2",
                name = "Anonymous",
                parish = "Perumbavoor St. Paul's MTC",
                category = "Exams / Career",
                request = "Please uphold our youth preparing for university, board, and competitive examinations for wisdom, peace, and strength.",
                date = "Recent",
                prayedCount = 12
            ),
            PrayerRequest(
                id = "pw-3",
                name = "Fellowship Member",
                parish = "Valakom MTC",
                category = "Healing",
                request = "Praying for complete healing and comfort for all sick elders and hospitalized members in our parishes.",
                date = "Recent",
                prayedCount = 24
            )
        )
    }

    fun getPrayerRequests(): List<PrayerRequest> = cachedPrayerWall.filter { it.isActive }

    fun hasPrayedFor(prayerId: String): Boolean {
        val prayedSet = prefs.getStringSet("prayed_prayer_ids", emptySet()) ?: emptySet()
        return prayedSet.contains(prayerId)
    }

    suspend fun markPrayerAsPrayed(prayerId: String) = withContext(Dispatchers.IO) {
        val prayedSet = (prefs.getStringSet("prayed_prayer_ids", emptySet()) ?: emptySet()).toMutableSet()
        if (prayedSet.contains(prayerId)) return@withContext

        prayedSet.add(prayerId)
        cachedPrayerWall = cachedPrayerWall.map {
            if (it.id == prayerId) it.copy(prayedCount = it.prayedCount + 1) else it
        }
        prefs.edit()
            .putStringSet("prayed_prayer_ids", prayedSet)
            .putString("cached_prayer_wall_json", gson.toJson(cachedPrayerWall))
            .apply()

        try {
            val baseUrl = getGoogleSheetUrl()
            if (baseUrl.isNotBlank()) {
                val payload = JSONObject().apply {
                    put("action", "prayIncrement")
                    put("id", prayerId)
                }
                postJsonToSheet(baseUrl, payload.toString())
            }
        } catch (_: Exception) {
            // Local count already updated gracefully
        }
    }

    suspend fun syncPrayerWallFromGoogleSheet(): Result<List<PrayerRequest>> = withContext(Dispatchers.IO) {
        val baseUrl = getGoogleSheetUrl()
        if (baseUrl.isBlank()) return@withContext Result.failure(Exception("No Google Sheet URL configured"))
        val endpoint = if (baseUrl.contains("?")) "$baseUrl&tab=prayerwall" else "$baseUrl?tab=prayerwall"

        try {
            val jsonText = fetchUrlWithRedirects(endpoint).trim()
            if (jsonText.startsWith("[")) {
                val pwType = object : TypeToken<List<PrayerRequest>>() {}.type
                val list: List<PrayerRequest> = gson.fromJson(jsonText, pwType)
                if (list.isNotEmpty()) {
                    cachedPrayerWall = list
                    prefs.edit().putString("cached_prayer_wall_json", jsonText).apply()
                }
                Result.success(cachedPrayerWall)
            } else if (jsonText.startsWith("{")) {
                val root = JSONObject(jsonText)
                if (root.has("prayerWall")) {
                    val arr = root.getJSONArray("prayerWall").toString()
                    val pwType = object : TypeToken<List<PrayerRequest>>() {}.type
                    val list: List<PrayerRequest> = gson.fromJson(arr, pwType)
                    if (list.isNotEmpty()) {
                        cachedPrayerWall = list
                        prefs.edit().putString("cached_prayer_wall_json", arr).apply()
                    }
                    Result.success(cachedPrayerWall)
                } else {
                    Result.success(cachedPrayerWall)
                }
            } else {
                Result.failure(Exception("Invalid Prayer Wall response"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    suspend fun submitPrayerRequest(
        name: String,
        parish: String,
        category: String,
        request: String
    ): Result<PrayerRequest> = withContext(Dispatchers.IO) {
        val cleanName = name.trim().ifBlank { "Anonymous" }
        val cleanParish = parish.trim().ifBlank { "Centre General" }
        val cleanCategory = category.trim().ifBlank { "General" }
        val cleanRequest = request.trim()
        val dateStr = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val newEntry = PrayerRequest(
            id = "pw-${System.currentTimeMillis()}",
            name = cleanName,
            parish = cleanParish,
            category = cleanCategory,
            request = cleanRequest,
            date = dateStr,
            prayedCount = 1,
            isActive = true
        )

        // Update local wall immediately for instant visual feedback
        cachedPrayerWall = listOf(newEntry) + cachedPrayerWall
        prefs.edit().putString("cached_prayer_wall_json", gson.toJson(cachedPrayerWall)).apply()

        val baseUrl = getGoogleSheetUrl()
        if (baseUrl.isBlank()) {
            return@withContext Result.success(newEntry)
        }

        try {
            val payload = JSONObject().apply {
                put("action", "submitPrayer")
                put("id", newEntry.id)
                put("name", cleanName)
                put("parish", cleanParish)
                put("category", cleanCategory)
                put("request", cleanRequest)
                put("date", dateStr)
            }
            postJsonToSheet(baseUrl, payload.toString())
            Result.success(newEntry)
        } catch (e: Exception) {
            e.printStackTrace()
            // Prayer saved locally; report failure only so UI can note "Saved locally"
            Result.failure(e)
        }
    }

    // ==================== CENTRE PHOTO GALLERY (DRIVE + SHEET) ====================

    fun getGalleryPhotos(): List<GalleryPhoto> = cachedGalleryPhotos.filter { it.isActive && it.imageUrl.isNotBlank() }

    suspend fun syncGalleryFromGoogleSheet(): Result<List<GalleryPhoto>> = withContext(Dispatchers.IO) {
        val baseUrl = getGoogleSheetUrl()
        if (baseUrl.isBlank()) return@withContext Result.failure(Exception("No Google Sheet URL configured"))
        val endpoint = if (baseUrl.contains("?")) "$baseUrl&tab=gallery" else "$baseUrl?tab=gallery"

        try {
            val jsonText = fetchUrlWithRedirects(endpoint).trim()
            if (jsonText.startsWith("[")) {
                val gType = object : TypeToken<List<GalleryPhoto>>() {}.type
                val list: List<GalleryPhoto> = gson.fromJson(jsonText, gType)
                if (list.isNotEmpty()) {
                    cachedGalleryPhotos = list
                    prefs.edit().putString("cached_gallery_json", jsonText).apply()
                }
                Result.success(cachedGalleryPhotos)
            } else if (jsonText.startsWith("{")) {
                val root = JSONObject(jsonText)
                if (root.has("gallery")) {
                    val arr = root.getJSONArray("gallery").toString()
                    val gType = object : TypeToken<List<GalleryPhoto>>() {}.type
                    val list: List<GalleryPhoto> = gson.fromJson(arr, gType)
                    if (list.isNotEmpty()) {
                        cachedGalleryPhotos = list
                        prefs.edit().putString("cached_gallery_json", arr).apply()
                    }
                    Result.success(cachedGalleryPhotos)
                } else {
                    Result.success(cachedGalleryPhotos)
                }
            } else {
                Result.failure(Exception("Invalid gallery JSON"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    // ==================== QUIZ LEADERBOARD (GOOGLE SHEET) ====================

    fun getLeaderboard(): List<LeaderboardEntry> {
        return cachedLeaderboard.sortedWith(
            compareByDescending<LeaderboardEntry> { it.score }
                .thenByDescending { it.stars }
                .thenByDescending { it.highestLevel }
        )
    }

    suspend fun syncLeaderboardFromGoogleSheet(): Result<List<LeaderboardEntry>> = withContext(Dispatchers.IO) {
        val baseUrl = getGoogleSheetUrl()
        if (baseUrl.isBlank()) return@withContext Result.failure(Exception("No Google Sheet URL configured"))
        val endpoint = if (baseUrl.contains("?")) "$baseUrl&tab=leaderboard" else "$baseUrl?tab=leaderboard"

        try {
            val jsonText = fetchUrlWithRedirects(endpoint).trim()
            if (jsonText.startsWith("[")) {
                val lbType = object : TypeToken<List<LeaderboardEntry>>() {}.type
                val list: List<LeaderboardEntry> = gson.fromJson(jsonText, lbType)
                if (list.isNotEmpty()) {
                    cachedLeaderboard = list.sortedWith(
                        compareByDescending<LeaderboardEntry> { it.score }
                            .thenByDescending { it.stars }
                            .thenByDescending { it.highestLevel }
                    )
                    prefs.edit().putString("cached_leaderboard_json", gson.toJson(cachedLeaderboard)).apply()
                }
                Result.success(getLeaderboard())
            } else if (jsonText.startsWith("{")) {
                val root = JSONObject(jsonText)
                if (root.has("leaderboard")) {
                    val arr = root.getJSONArray("leaderboard").toString()
                    val lbType = object : TypeToken<List<LeaderboardEntry>>() {}.type
                    val list: List<LeaderboardEntry> = gson.fromJson(arr, lbType)
                    if (list.isNotEmpty()) {
                        cachedLeaderboard = list.sortedWith(
                            compareByDescending<LeaderboardEntry> { it.score }
                                .thenByDescending { it.stars }
                                .thenByDescending { it.highestLevel }
                        )
                        prefs.edit().putString("cached_leaderboard_json", gson.toJson(cachedLeaderboard)).apply()
                    }
                    Result.success(getLeaderboard())
                } else {
                    Result.success(getLeaderboard())
                }
            } else {
                Result.failure(Exception("Invalid leaderboard JSON"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    suspend fun submitQuizLeaderboardScore(
        name: String,
        parish: String
    ): Result<LeaderboardEntry> = withContext(Dispatchers.IO) {
        val cleanName = name.trim()
        val cleanParish = parish.trim().ifBlank { "Centre Youth" }
        savePlayerProfile(cleanName, cleanParish)

        val totalScore = getQuizTotalScore()
        val totalStars = getQuizTotalStars()
        val maxLvl = getHighestQuizLevel()
        val dateStr = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        val entry = LeaderboardEntry(
            id = "${cleanName.lowercase()}-${cleanParish.lowercase()}",
            name = cleanName,
            parish = cleanParish,
            score = totalScore,
            stars = totalStars,
            highestLevel = maxLvl,
            updatedAt = dateStr
        )

        // Upsert locally
        val mutable = cachedLeaderboard.toMutableList()
        val existingIdx = mutable.indexOfFirst {
            it.name.equals(cleanName, ignoreCase = true) && it.parish.equals(cleanParish, ignoreCase = true)
        }
        if (existingIdx != -1) {
            mutable[existingIdx] = entry
        } else {
            mutable.add(entry)
        }
        cachedLeaderboard = mutable.sortedWith(
            compareByDescending<LeaderboardEntry> { it.score }
                .thenByDescending { it.stars }
                .thenByDescending { it.highestLevel }
        )
        prefs.edit().putString("cached_leaderboard_json", gson.toJson(cachedLeaderboard)).apply()

        val baseUrl = getGoogleSheetUrl()
        if (baseUrl.isBlank()) {
            return@withContext Result.success(entry)
        }

        try {
            val payload = JSONObject().apply {
                put("action", "submitScore")
                put("name", cleanName)
                put("parish", cleanParish)
                put("score", totalScore)
                put("stars", totalStars)
                put("highestLevel", maxLvl)
                put("updatedAt", dateStr)
            }
            postJsonToSheet(baseUrl, payload.toString())
            Result.success(entry)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    private fun postJsonToSheet(urlString: String, jsonBody: String): String {
        val url = URL(urlString)
        val conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "POST"
        conn.doOutput = true
        conn.instanceFollowRedirects = false
        conn.connectTimeout = 10000
        conn.readTimeout = 10000
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Android; Mobile)")

        conn.outputStream.use { os ->
            os.write(jsonBody.toByteArray(Charsets.UTF_8))
            os.flush()
        }

        val status = conn.responseCode
        if (status == HttpURLConnection.HTTP_MOVED_TEMP ||
            status == HttpURLConnection.HTTP_MOVED_PERM ||
            status == HttpURLConnection.HTTP_SEE_OTHER ||
            status == 307 || status == 308
        ) {
            val redirectUrl = conn.getHeaderField("Location")
            if (!redirectUrl.isNullOrBlank()) {
                return fetchUrlWithRedirects(redirectUrl)
            }
        }
        if (status in 200..299) {
            return conn.inputStream.bufferedReader().use { it.readText() }
        }
        throw Exception("HTTP POST Error: $status")
    }
}
