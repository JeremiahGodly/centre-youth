package org.marthoma.centreyouth.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.marthoma.centreyouth.data.model.*
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

            if (root.has("parishes")) {
                val type = object : TypeToken<List<Parish>>() {}.type
                cachedParishes = gson.fromJson(root.getJSONArray("parishes").toString(), type)
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
    fun getParishes(): List<Parish> = cachedParishes
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
                    if (list.isNotEmpty()) {
                        cachedPrograms = list
                        prefs.edit().putString("cached_programs_json", pArray).apply()
                    }
                }
                if (root.has("leaders")) {
                    val lArray = root.getJSONArray("leaders").toString()
                    val leadType = object : TypeToken<List<OfficeBearer>>() {}.type
                    val lList: List<OfficeBearer> = gson.fromJson(lArray, leadType)
                    if (lList.isNotEmpty()) {
                        cachedOfficeBearers = lList
                        prefs.edit().putString("cached_leaders_json", lArray).apply()
                    }
                }
                Result.success(cachedPrograms)
            } else if (jsonText.startsWith("[")) {
                val progType = object : TypeToken<List<ProgramEvent>>() {}.type
                val list: List<ProgramEvent> = gson.fromJson(jsonText, progType)
                if (list.isNotEmpty()) {
                    cachedPrograms = list
                    prefs.edit().putString("cached_programs_json", jsonText).apply()
                }
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
                    prefs.edit().putString("cached_leaders_json", jsonText).apply()
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
                        prefs.edit().putString("cached_leaders_json", lArray).apply()
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
}
