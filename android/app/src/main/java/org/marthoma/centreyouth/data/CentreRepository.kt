package org.marthoma.centreyouth.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import org.marthoma.centreyouth.data.model.*
import java.io.InputStreamReader
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

            if (root.has("officeBearers")) {
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

        } catch (e: Exception) {
            e.printStackTrace()
        }
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
}
