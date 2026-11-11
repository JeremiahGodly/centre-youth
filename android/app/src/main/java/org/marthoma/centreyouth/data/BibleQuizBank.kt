package org.marthoma.centreyouth.data

import org.marthoma.centreyouth.data.model.BibleQuizQuestion
import org.marthoma.centreyouth.data.model.QuizLevelInfo

/**
 * Comprehensive 120-Question Progressive Bible Quiz Bank
 * 10 Escalating Difficulty Levels (12 questions per level)
 * Timers tighten progressively from 25 seconds (Level 1) down to 10 seconds (Level 10).
 */
object BibleQuizBank {

    val levels: List<QuizLevelInfo> = listOf(
        QuizLevelInfo(1, "Genesis Dawn", "Foundational Bible Stories", "Easy", 25, 0xFF10B981, 0xFF14B8A6),
        QuizLevelInfo(2, "Ark & Miracles", "Beloved Miracles & Heroes", "Easy+", 24, 0xFF06B6D4, 0xFF0EA5E9),
        QuizLevelInfo(3, "Faithful Witness", "Kings, Judges & Apostles", "Normal", 22, 0xFF3B82F6, 0xFF6366F1),
        QuizLevelInfo(4, "Apostolic Trail", "Gospels & Early Church", "Moderate", 20, 0xFF8B5CF6, 0xFFA855F7),
        QuizLevelInfo(5, "Prophetic Voices", "Major & Minor Prophets", "Intermediate", 18, 0xFFD946EF, 0xFFEC4899),
        QuizLevelInfo(6, "Kingdom Chronicles", "Divided Monarchy & Epistles", "Challenging", 16, 0xFFF43F5E, 0xFFF59E0B),
        QuizLevelInfo(7, "Covenant Shadows", "Typology, Leviticus & Prophecy", "Hard", 15, 0xFFF97316, 0xFFFBBF24),
        QuizLevelInfo(8, "Sanctuary Secrets", "Obscure Names & Geographies", "Very Hard", 14, 0xFFEF4444, 0xFFDB2777),
        QuizLevelInfo(9, "Deep Exegesis", "Ancient Texts & Rare Details", "Brutal", 12, 0xFFDC2626, 0xFF7C3AED),
        QuizLevelInfo(10, "Omega Grandmaster", "Scholarly Biblical Mastery", "ULTRA HARD", 10, 0xFFF59E0B, 0xFFE11D48)
    )

    fun getLevelInfo(level: Int): QuizLevelInfo {
        return levels.find { it.level == level } ?: levels.first()
    }

    fun getQuestionsForLevel(level: Int): List<BibleQuizQuestion> {
        return allQuestions.filter { it.level == level }
    }

    val allQuestions: List<BibleQuizQuestion> = listOf(
        // ==================== LEVEL 1: GENESIS DAWN (Easy - 25s) ====================
        BibleQuizQuestion(
            id = 1, level = 1,
            question = "What is the very first book of the Bible?",
            options = listOf("Exodus", "Genesis", "Matthew", "Psalms"),
            correctIndex = 1,
            verseRef = "Genesis 1:1"
        ),
        BibleQuizQuestion(
            id = 2, level = 1,
            question = "In which town was Jesus Christ born?",
            options = listOf("Bethlehem", "Nazareth", "Jerusalem", "Capernaum"),
            correctIndex = 0,
            verseRef = "Matthew 2:1"
        ),
        BibleQuizQuestion(
            id = 3, level = 1,
            question = "Who built a massive wooden ark to survive the great flood?",
            options = listOf("Moses", "Abraham", "Noah", "Jacob"),
            correctIndex = 2,
            verseRef = "Genesis 6:14"
        ),
        BibleQuizQuestion(
            id = 4, level = 1,
            question = "How many close Apostles did Jesus appoint as His inner Twelve?",
            options = listOf("7", "10", "40", "12"),
            correctIndex = 3,
            verseRef = "Matthew 10:1"
        ),
        BibleQuizQuestion(
            id = 5, level = 1,
            question = "Which young shepherd defeated the Philistine giant Goliath with a sling?",
            options = listOf("David", "Solomon", "Saul", "Jonathan"),
            correctIndex = 0,
            verseRef = "1 Samuel 17:49"
        ),
        BibleQuizQuestion(
            id = 6, level = 1,
            question = "Who stretched out his staff to part the waters of the Red Sea?",
            options = listOf("Joshua", "Elijah", "Moses", "Aaron"),
            correctIndex = 2,
            verseRef = "Exodus 14:21"
        ),
        BibleQuizQuestion(
            id = 7, level = 1,
            question = "What is the final book of the New Testament?",
            options = listOf("Jude", "Revelation", "Acts", "Hebrews"),
            correctIndex = 1,
            verseRef = "Revelation 1:1"
        ),
        BibleQuizQuestion(
            id = 8, level = 1,
            question = "Who was the earthly mother of Jesus?",
            options = listOf("Elizabeth", "Martha", "Sarah", "Mary"),
            correctIndex = 3,
            verseRef = "Luke 1:30-31"
        ),
        BibleQuizQuestion(
            id = 9, level = 1,
            question = "On which day of Creation did God rest and sanctify it?",
            options = listOf("Fifth day", "Sixth day", "Seventh day", "First day"),
            correctIndex = 2,
            verseRef = "Genesis 2:2-3"
        ),
        BibleQuizQuestion(
            id = 10, level = 1,
            question = "How many days and nights did Jesus fast in the wilderness?",
            options = listOf("40 days", "14 days", "30 days", "7 days"),
            correctIndex = 0,
            verseRef = "Matthew 4:2"
        ),
        BibleQuizQuestion(
            id = 11, level = 1,
            question = "Who betrayed Jesus to the chief priests for thirty pieces of silver?",
            options = listOf("Thomas", "Peter", "Philip", "Judas Iscariot"),
            correctIndex = 3,
            verseRef = "Matthew 26:14-15"
        ),
        BibleQuizQuestion(
            id = 12, level = 1,
            question = "What swallowed Jonah for three days and three nights when he fled Tarshish-bound?",
            options = listOf("A crocodile", "A great fish", "A sea dragon", "A whale shark only"),
            correctIndex = 1,
            verseRef = "Jonah 1:17"
        ),

        // ==================== LEVEL 2: ARK & MIRACLES (Easy+ - 24s) ====================
        BibleQuizQuestion(
            id = 13, level = 2,
            question = "What was Jesus' very first recorded miracle in the Gospel of John?",
            options = listOf("Turning water into wine", "Walking on water", "Healing a blind man", "Raising Lazarus"),
            correctIndex = 0,
            verseRef = "John 2:11"
        ),
        BibleQuizQuestion(
            id = 14, level = 2,
            question = "Who climbed a sycamore-fig tree in Jericho to see Jesus pass by?",
            options = listOf("Nicodemus", "Bartimaeus", "Zacchaeus", "Lazarus"),
            correctIndex = 2,
            verseRef = "Luke 19:4"
        ),
        BibleQuizQuestion(
            id = 15, level = 2,
            question = "Which prophet survived a night unharmed inside a den of lions?",
            options = listOf("Isaiah", "Daniel", "Jeremiah", "Ezekiel"),
            correctIndex = 1,
            verseRef = "Daniel 6:22"
        ),
        BibleQuizQuestion(
            id = 16, level = 2,
            question = "Which king of Israel asked God for a discerning heart of wisdom rather than riches?",
            options = listOf("Saul", "Hezekiah", "David", "Solomon"),
            correctIndex = 3,
            verseRef = "1 Kings 3:9-12"
        ),
        BibleQuizQuestion(
            id = 17, level = 2,
            question = "With how many loaves and fish did Jesus feed the crowd of 5,000 men?",
            options = listOf("7 loaves and 3 fish", "5 loaves and 2 fish", "3 loaves and 5 fish", "12 loaves and 2 fish"),
            correctIndex = 1,
            verseRef = "Matthew 14:17-21"
        ),
        BibleQuizQuestion(
            id = 18, level = 2,
            question = "What sign did God set in the clouds as His covenant never again to flood the whole earth?",
            options = listOf("A rainbow", "A fiery pillar", "A white dove", "Seven stars"),
            correctIndex = 0,
            verseRef = "Genesis 9:13"
        ),
        BibleQuizQuestion(
            id = 19, level = 2,
            question = "Which mighty Israelite judge kept his supernatural strength as long as his hair remained uncut?",
            options = listOf("Gideon", "Jephthah", "Ehud", "Samson"),
            correctIndex = 3,
            verseRef = "Judges 16:17"
        ),
        BibleQuizQuestion(
            id = 20, level = 2,
            question = "Who denied knowing Jesus three times before the rooster crowed twice?",
            options = listOf("Andrew", "John", "Simon Peter", "James"),
            correctIndex = 2,
            verseRef = "Mark 14:72"
        ),
        BibleQuizQuestion(
            id = 21, level = 2,
            question = "Whom did Jesus raise from the dead in Bethany after four days in the tomb?",
            options = listOf("Lazarus", "Stephen", "Jairus", "Eutychus"),
            correctIndex = 0,
            verseRef = "John 11:39-44"
        ),
        BibleQuizQuestion(
            id = 22, level = 2,
            question = "On which mountain did Moses receive the Ten Commandments inscribed on stone?",
            options = listOf("Mount Carmel", "Mount Sinai", "Mount Tabor", "Mount Nebo"),
            correctIndex = 1,
            verseRef = "Exodus 19:20"
        ),
        BibleQuizQuestion(
            id = 23, level = 2,
            question = "What gift did Jacob give his favored son Joseph that sparked his brothers' jealousy?",
            options = listOf("A silver goblet", "An ornate coat of many colors", "A golden harp", "A royal signet ring"),
            correctIndex = 1,
            verseRef = "Genesis 37:3"
        ),
        BibleQuizQuestion(
            id = 24, level = 2,
            question = "Into which younger brother's grain sack did Joseph have his silver cup placed?",
            options = listOf("Reuben", "Judah", "Levi", "Benjamin"),
            correctIndex = 3,
            verseRef = "Genesis 44:2, 12"
        ),

        // ==================== LEVEL 3: FAITHFUL WITNESS (Normal - 22s) ====================
        BibleQuizQuestion(
            id = 25, level = 3,
            question = "Around what city's fortified walls did Joshua's army march before they collapsed?",
            options = listOf("Jericho", "Ai", "Bethel", "Gibeon"),
            correctIndex = 0,
            verseRef = "Joshua 6:15-20"
        ),
        BibleQuizQuestion(
            id = 26, level = 3,
            question = "Which prophet ascended into heaven in a whirlwind with a chariot and horses of fire?",
            options = listOf("Elisha", "Samuel", "Elijah", "Nathan"),
            correctIndex = 2,
            verseRef = "2 Kings 2:11"
        ),
        BibleQuizQuestion(
            id = 27, level = 3,
            question = "Which Moabite widow told Naomi, 'Your people will be my people and your God my God'?",
            options = listOf("Orpah", "Esther", "Hannah", "Ruth"),
            correctIndex = 3,
            verseRef = "Ruth 1:16"
        ),
        BibleQuizQuestion(
            id = 28, level = 3,
            question = "What was the Apostle Paul's Jewish name before his missionary name Paul took prominence?",
            options = listOf("Simon", "Saul", "Barnabas", "Silas"),
            correctIndex = 1,
            verseRef = "Acts 13:9"
        ),
        BibleQuizQuestion(
            id = 29, level = 3,
            question = "Who was the first Christian martyr, stoned while praying 'Lord, do not hold this sin against them'?",
            options = listOf("Stephen", "James", "Philip", "Barnabas"),
            correctIndex = 0,
            verseRef = "Acts 7:59-60"
        ),
        BibleQuizQuestion(
            id = 30, level = 3,
            question = "In the Fruit of the Spirit (Galatians 5), which comes FIRST in the list of nine?",
            options = listOf("Joy", "Peace", "Love", "Kindness"),
            correctIndex = 2,
            verseRef = "Galatians 5:22"
        ),
        BibleQuizQuestion(
            id = 31, level = 3,
            question = "Which Jewish queen risked her life appearing uninvited before King Xerxes to save her nation?",
            options = listOf("Vashti", "Esther", "Bathsheba", "Deborah"),
            correctIndex = 1,
            verseRef = "Esther 4:16"
        ),
        BibleQuizQuestion(
            id = 32, level = 3,
            question = "According to 1 Corinthians 13:13, faith, hope, and love remain—which is the greatest?",
            options = listOf("Faith", "Hope", "Peace", "Love"),
            correctIndex = 3,
            verseRef = "1 Corinthians 13:13"
        ),
        BibleQuizQuestion(
            id = 33, level = 3,
            question = "What were the Babylonian names of Daniel's three friends thrown into the fiery furnace?",
            options = listOf("Shadrach, Meshach, Abednego", "Shem, Ham, Japheth", "Gad, Asher, Dan", "Benaiah, Zadok, Abiathar"),
            correctIndex = 0,
            verseRef = "Daniel 3:19-20"
        ),
        BibleQuizQuestion(
            id = 34, level = 3,
            question = "Which Apostle was a former tax collector seated at his booth in Capernaum when Jesus called him?",
            options = listOf("Mark", "Luke", "Matthew", "Thaddaeus"),
            correctIndex = 2,
            verseRef = "Matthew 9:9"
        ),
        BibleQuizQuestion(
            id = 35, level = 3,
            question = "Which disciple famously refused to believe Jesus rose until he touched His nail marks?",
            options = listOf("Bartholomew", "Thomas", "Simon the Zealot", "Philip"),
            correctIndex = 1,
            verseRef = "John 20:24-25"
        ),
        BibleQuizQuestion(
            id = 36, level = 3,
            question = "Who bathed in the Jordan River seven times to be healed of leprosy?",
            options = listOf("Gehazi", "Ben-Hadad", "Hazael", "Naaman"),
            correctIndex = 3,
            verseRef = "2 Kings 5:14"
        ),

        // ==================== LEVEL 4: APOSTOLIC TRAIL (Moderate - 20s) ====================
        BibleQuizQuestion(
            id = 37, level = 4,
            question = "What manual trade did the Apostle Paul practice alongside Aquila and Priscilla in Corinth?",
            options = listOf("Fishing", "Carpentry", "Tentmaking", "Stone masonry"),
            correctIndex = 2,
            verseRef = "Acts 18:3"
        ),
        BibleQuizQuestion(
            id = 38, level = 4,
            question = "In which Syrian city were the disciples first called 'Christians'?",
            options = listOf("Antioch", "Damascus", "Jerusalem", "Ephesus"),
            correctIndex = 0,
            verseRef = "Acts 11:26"
        ),
        BibleQuizQuestion(
            id = 39, level = 4,
            question = "Which Pharisee and member of the Sanhedrin visited Jesus by night to learn of rebirth?",
            options = listOf("Caiaphas", "Gamaliel", "Nicodemus", "Joseph of Arimathea"),
            correctIndex = 2,
            verseRef = "John 3:1-3"
        ),
        BibleQuizQuestion(
            id = 40, level = 4,
            question = "To which Aegean island was the Apostle John exiled when he received Revelation?",
            options = listOf("Cyprus", "Crete", "Malta", "Patmos"),
            correctIndex = 3,
            verseRef = "Revelation 1:9"
        ),
        BibleQuizQuestion(
            id = 41, level = 4,
            question = "How many books make up the Protestant Bible canon (39 OT + 27 NT)?",
            options = listOf("62", "66", "70", "73"),
            correctIndex = 1,
            verseRef = "Biblical Canon"
        ),
        BibleQuizQuestion(
            id = 42, level = 4,
            question = "In the Beatitudes (Matthew 5), whom does Jesus promise 'will inherit the earth'?",
            options = listOf("The merciful", "The pure in heart", "The meek", "The persecuted"),
            correctIndex = 2,
            verseRef = "Matthew 5:5"
        ),
        BibleQuizQuestion(
            id = 43, level = 4,
            question = "Which angel appeared to Zechariah by the incense altar to announce John the Baptist's birth?",
            options = listOf("Gabriel", "Michael", "Raphael", "Uriel"),
            correctIndex = 0,
            verseRef = "Luke 1:19"
        ),
        BibleQuizQuestion(
            id = 44, level = 4,
            question = "Who was the prophetess and only female Judge recorded in the Book of Judges?",
            options = listOf("Jael", "Deborah", "Miriam", "Huldah"),
            correctIndex = 1,
            verseRef = "Judges 4:4"
        ),
        BibleQuizQuestion(
            id = 45, level = 4,
            question = "Which Gospel alone records both the Good Samaritan and the Prodigal Son?",
            options = listOf("Matthew", "Mark", "John", "Luke"),
            correctIndex = 3,
            verseRef = "Luke 10:30; 15:11"
        ),
        BibleQuizQuestion(
            id = 46, level = 4,
            question = "On the road to Damascus, which believer was sent to restore Saul's sight?",
            options = listOf("Ananias", "Philip", "Barnabas", "Silas"),
            correctIndex = 0,
            verseRef = "Acts 9:17"
        ),
        BibleQuizQuestion(
            id = 47, level = 4,
            question = "In Ephesians 6, what is called the 'sword of the Spirit' in the Armor of God?",
            options = listOf("Righteousness", "The word of God", "Faith", "Truth"),
            correctIndex = 1,
            verseRef = "Ephesians 6:17"
        ),
        BibleQuizQuestion(
            id = 48, level = 4,
            question = "Who offered his own newly hewn rock tomb for the burial of Jesus?",
            options = listOf("Simon of Cyrene", "Nicodemus", "Joseph of Arimathea", "Cleopas"),
            correctIndex = 2,
            verseRef = "Matthew 27:57-60"
        ),

        // ==================== LEVEL 5: PROPHETIC VOICES (Intermediate - 18s) ====================
        BibleQuizQuestion(
            id = 49, level = 5,
            question = "How many stone water jars used for ceremonial washing did Jesus turn to wine at Cana?",
            options = listOf("Three", "Six", "Seven", "Twelve"),
            correctIndex = 1,
            verseRef = "John 2:6"
        ),
        BibleQuizQuestion(
            id = 50, level = 5,
            question = "What was the name of the dealer in purple cloth from Thyatira baptized by Paul at Philippi?",
            options = listOf("Lydia", "Priscilla", "Phoebe", "Chloe"),
            correctIndex = 0,
            verseRef = "Acts 16:14-15"
        ),
        BibleQuizQuestion(
            id = 51, level = 5,
            question = "To how many men did God reduce Gideon's army after testing how they lapped water?",
            options = listOf("1,000", "500", "300", "120"),
            correctIndex = 2,
            verseRef = "Judges 7:7"
        ),
        BibleQuizQuestion(
            id = 52, level = 5,
            question = "On which mountain peak did Elijah defeat the 450 prophets of Baal?",
            options = listOf("Mount Horeb", "Mount Hermon", "Mount Ebal", "Mount Carmel"),
            correctIndex = 3,
            verseRef = "1 Kings 18:19-20"
        ),
        BibleQuizQuestion(
            id = 53, level = 5,
            question = "Which runaway slave from Colossae did Paul send back as a 'dear brother' to Philemon?",
            options = listOf("Onesimus", "Tychicus", "Epaphras", "Demas"),
            correctIndex = 0,
            verseRef = "Philemon 1:15-16"
        ),
        BibleQuizQuestion(
            id = 54, level = 5,
            question = "What was the name of the blind son of Timaeus healed by Jesus outside Jericho?",
            options = listOf("Malchus", "Bartimaeus", "Aeneas", "Cleopas"),
            correctIndex = 1,
            verseRef = "Mark 10:46-47"
        ),
        BibleQuizQuestion(
            id = 55, level = 5,
            question = "Which pre-flood patriarch 'walked faithfully with God; then he was no more, because God took him away'?",
            options = listOf("Methuselah", "Lamech", "Enosh", "Enoch"),
            correctIndex = 3,
            verseRef = "Genesis 5:24"
        ),
        BibleQuizQuestion(
            id = 56, level = 5,
            question = "Which Roman centurion in Caesarea received the Holy Spirit as Peter preached to his Gentile household?",
            options = listOf("Julius", "Claudius Lysias", "Cornelius", "Publius"),
            correctIndex = 2,
            verseRef = "Acts 10:1-44"
        ),
        BibleQuizQuestion(
            id = 57, level = 5,
            question = "Who was chosen by lot in Acts chapter 1 to take Judas Iscariot's place among the Twelve?",
            options = listOf("Matthias", "Barsabbas", "Silas", "Stephen"),
            correctIndex = 0,
            verseRef = "Acts 1:23-26"
        ),
        BibleQuizQuestion(
            id = 58, level = 5,
            question = "Which Old Testament historical book never mentions the explicit name of God even once?",
            options = listOf("Ruth", "Esther", "Ezra", "Nehemiah"),
            correctIndex = 1,
            verseRef = "Book of Esther"
        ),
        BibleQuizQuestion(
            id = 59, level = 5,
            question = "Inside which wilderness cave region did David secretly cut off a corner of King Saul's robe?",
            options = listOf("Adullam", "En Gedi", "Ziph", "Maon"),
            correctIndex = 1,
            verseRef = "1 Samuel 24:1-4"
        ),
        BibleQuizQuestion(
            id = 60, level = 5,
            question = "Which epistle likens the human tongue to a ship's small rudder and a spark setting a forest on fire?",
            options = listOf("1 Peter", "Hebrews", "James", "Jude"),
            correctIndex = 2,
            verseRef = "James 3:4-5"
        ),

        // ==================== LEVEL 6: KINGDOM CHRONICLES (Challenging - 16s) ====================
        BibleQuizQuestion(
            id = 61, level = 6,
            question = "What was the name of the high priest's servant whose right ear Simon Peter cut off?",
            options = listOf("Rufus", "Alexander", "Malchus", "Jason"),
            correctIndex = 2,
            verseRef = "John 18:10"
        ),
        BibleQuizQuestion(
            id = 62, level = 6,
            question = "How many years did King Solomon take to complete building the First Temple in Jerusalem?",
            options = listOf("7 years", "3 years", "12 years", "20 years"),
            correctIndex = 0,
            verseRef = "1 Kings 6:38"
        ),
        BibleQuizQuestion(
            id = 63, level = 6,
            question = "Which left-handed judge of Israel assassinated the Moabite king Eglon in his private summer chamber?",
            options = listOf("Othniel", "Ehud", "Tola", "Abdon"),
            correctIndex = 1,
            verseRef = "Judges 3:15-21"
        ),
        BibleQuizQuestion(
            id = 64, level = 6,
            question = "At how many years old did righteous King Josiah ascend the throne of Judah?",
            options = listOf("7 years old", "12 years old", "16 years old", "8 years old"),
            correctIndex = 3,
            verseRef = "2 Kings 22:1"
        ),
        BibleQuizQuestion(
            id = 65, level = 6,
            question = "Who fell asleep from a third-story window in Troas while Paul preached late into the night?",
            options = listOf("Trophimus", "Eutychus", "Sosthenes", "Sopater"),
            correctIndex = 1,
            verseRef = "Acts 20:9-10"
        ),
        BibleQuizQuestion(
            id = 66, level = 6,
            question = "Which Persian ruler does Isaiah 45:1 call the LORD's 'anointed' who would rebuild Jerusalem?",
            options = listOf("Cyrus", "Darius", "Artaxerxes", "Xerxes"),
            correctIndex = 0,
            verseRef = "Isaiah 45:1"
        ),
        BibleQuizQuestion(
            id = 67, level = 6,
            question = "When Jacob crossed his arms to bless Joseph's two sons, which younger son received the right-hand blessing?",
            options = listOf("Manasseh", "Machir", "Ephraim", "Gilead"),
            correctIndex = 2,
            verseRef = "Genesis 48:14-19"
        ),
        BibleQuizQuestion(
            id = 68, level = 6,
            question = "What were the names of the two monumental bronze pillars standing at the porch of Solomon's Temple?",
            options = listOf("Urim and Thummim", "Gog and Magog", "Mizpah and Ebenezer", "Jakin and Boaz"),
            correctIndex = 3,
            verseRef = "1 Kings 7:21"
        ),
        BibleQuizQuestion(
            id = 69, level = 6,
            question = "Which Judean king was granted 15 extra years of life when the stairway shadow retreated ten steps?",
            options = listOf("Asa", "Hezekiah", "Jehoshaphat", "Jotham"),
            correctIndex = 1,
            verseRef = "2 Kings 20:5-11"
        ),
        BibleQuizQuestion(
            id = 70, level = 6,
            question = "On which Mediterranean island was Paul shipwrecked and bitten harmlessly by a venomous viper?",
            options = listOf("Malta", "Crete", "Rhodes", "Sicily"),
            correctIndex = 0,
            verseRef = "Acts 28:1-5"
        ),
        BibleQuizQuestion(
            id = 71, level = 6,
            question = "Which prophet lay on his left side 390 days and his right side 40 days to symbolize Israel and Judah's sin?",
            options = listOf("Jeremiah", "Hosea", "Ezekiel", "Zechariah"),
            correctIndex = 2,
            verseRef = "Ezekiel 4:4-6"
        ),
        BibleQuizQuestion(
            id = 72, level = 6,
            question = "Which couple struck dead in Acts 5 conspired to lie to the Holy Spirit about their land proceeds?",
            options = listOf("Aquila and Priscilla", "Ahab and Jezebel", "Nabal and Abigail", "Ananias and Sapphira"),
            correctIndex = 3,
            verseRef = "Acts 5:1-10"
        ),

        // ==================== LEVEL 7: COVENANT SHADOWS (Hard - 15s) ====================
        BibleQuizQuestion(
            id = 73, level = 7,
            question = "Which priest-king of Salem brought out bread and wine to bless Abraham and received a tithe?",
            options = listOf("Melchizedek", "Abimelech", "Jethro", "Bera"),
            correctIndex = 0,
            verseRef = "Genesis 14:18-20"
        ),
        BibleQuizQuestion(
            id = 74, level = 7,
            question = "After Korah's rebellion, whose almond staff sprouted, blossomed, and yielded ripe almonds overnight?",
            options = listOf("Moses' staff", "Caleb's staff", "Aaron's staff", "Joshua's staff"),
            correctIndex = 2,
            verseRef = "Numbers 17:8"
        ),
        BibleQuizQuestion(
            id = 75, level = 7,
            question = "Which prophet was commanded by God to marry Gomer as an enacted sign of Israel's covenant unfaithfulness?",
            options = listOf("Amos", "Hosea", "Micah", "Habakkuk"),
            correctIndex = 1,
            verseRef = "Hosea 1:2-3"
        ),
        BibleQuizQuestion(
            id = 76, level = 7,
            question = "What does Jesus' Aramaic cry 'Eloi, Eloi, lema sabachthani?' mean?",
            options = listOf("Father, forgive them", "It is finished", "Into Your hands I commit My spirit", "My God, My God, why have You forsaken Me?"),
            correctIndex = 3,
            verseRef = "Mark 15:34"
        ),
        BibleQuizQuestion(
            id = 77, level = 7,
            question = "Who was the chief Spirit-filled artisan from Judah chosen to construct the Ark of the Covenant?",
            options = listOf("Bezalel", "Oholiab", "Ithamar", "Bezai"),
            correctIndex = 0,
            verseRef = "Exodus 31:2-5"
        ),
        BibleQuizQuestion(
            id = 78, level = 7,
            question = "How many Cities of Refuge were set apart across Canaan for cases of unintentional manslaughter?",
            options = listOf("3", "6", "7", "12"),
            correctIndex = 1,
            verseRef = "Numbers 35:13-14"
        ),
        BibleQuizQuestion(
            id = 79, level = 7,
            question = "Which usurping queen murdered almost all the royal offspring of Judah to seize the throne for six years?",
            options = listOf("Jezebel", "Maacah", "Athaliah", "Tahpenes"),
            correctIndex = 2,
            verseRef = "2 Kings 11:1-3"
        ),
        BibleQuizQuestion(
            id = 80, level = 7,
            question = "In Acts 4:36, what was the original given name of Barnabas, the Cypriot Levite nicknamed 'Son of Encouragement'?",
            options = listOf("John Mark", "Judas", "Barsabbas", "Joseph"),
            correctIndex = 3,
            verseRef = "Acts 4:36"
        ),
        BibleQuizQuestion(
            id = 81, level = 7,
            question = "What does the early Christian Aramaic prayer 'Maranatha' at the end of 1 Corinthians signify?",
            options = listOf("Our Lord, come!", "Grace be to all", "Holy, holy, holy", "Peace to Israel"),
            correctIndex = 0,
            verseRef = "1 Corinthians 16:22"
        ),
        BibleQuizQuestion(
            id = 82, level = 7,
            question = "At what exact age did Moses die on Mount Nebo with undimmed eyes and undiminished vigor?",
            options = listOf("100 years", "120 years", "110 years", "147 years"),
            correctIndex = 1,
            verseRef = "Deuteronomy 34:7"
        ),
        BibleQuizQuestion(
            id = 83, level = 7,
            question = "Which prophet beheld six-winged seraphim crying 'Holy, holy, holy' before the LORD's lofty throne?",
            options = listOf("Ezekiel", "Daniel", "Isaiah", "Joel"),
            correctIndex = 2,
            verseRef = "Isaiah 6:1-3"
        ),
        BibleQuizQuestion(
            id = 84, level = 7,
            question = "Which Judean king was struck with leprosy on his forehead for usurping the priestly duty of burning incense?",
            options = listOf("Manasseh", "Ahaz", "Jehoash", "Uzziah (Azariah)"),
            correctIndex = 3,
            verseRef = "2 Chronicles 26:16-21"
        ),

        // ==================== LEVEL 8: SANCTUARY SECRETS (Very Hard - 14s) ====================
        BibleQuizQuestion(
            id = 85, level = 8,
            question = "Who acted as King David's secret confidant to infiltrate Absalom's court and overturn Ahithophel's advice?",
            options = listOf("Hushai the Arkite", "Ittai the Gittite", "Ziba the servant", "Chimham"),
            correctIndex = 0,
            verseRef = "2 Samuel 15:32-37"
        ),
        BibleQuizQuestion(
            id = 86, level = 8,
            question = "Who physically penned Paul's Epistle to the Romans as his scribe, sending his own greeting in Romans 16?",
            options = listOf("Timothy", "Erastus", "Tertius", "Quartus"),
            correctIndex = 2,
            verseRef = "Romans 16:22"
        ),
        BibleQuizQuestion(
            id = 87, level = 8,
            question = "Which Edomite chief herdsman informed King Saul about Ahimelech and personally slaughtered 85 priests at Nob?",
            options = listOf("Shimei", "Doeg", "Nabal", "Abner"),
            correctIndex = 1,
            verseRef = "1 Samuel 22:18"
        ),
        BibleQuizQuestion(
            id = 88, level = 8,
            question = "Who was Jonathan's lamed son whom King David invited to eat perpetually at the king's table?",
            options = listOf("Ish-Bosheth", "Adonijah", "Machir", "Mephibosheth"),
            correctIndex = 3,
            verseRef = "2 Samuel 9:6-13"
        ),
        BibleQuizQuestion(
            id = 89, level = 8,
            question = "Who were the three chief opponents mocking and conspiring against Nehemiah's rebuilding of Jerusalem's walls?",
            options = listOf("Sanballat, Tobiah, and Geshem", "Bishlam, Mithredath, and Tabeel", "Tattenai, Shethar-Bozenai, and Rehum", "Haman, Memucan, and Carshena"),
            correctIndex = 0,
            verseRef = "Nehemiah 2:19; 6:1"
        ),
        BibleQuizQuestion(
            id = 90, level = 8,
            question = "What name had Israel given Moses' bronze serpent before King Hezekiah smashed it to purge idolatry?",
            options = listOf("Dagon", "Nehushtan", "Milcom", "Chemosh"),
            correctIndex = 1,
            verseRef = "2 Kings 18:4"
        ),
        BibleQuizQuestion(
            id = 91, level = 8,
            question = "To which of the seven churches in Revelation did Christ say, 'You have a reputation of being alive, but you are dead'?",
            options = listOf("Laodicea", "Thyatira", "Sardis", "Pergamum"),
            correctIndex = 2,
            verseRef = "Revelation 3:1"
        ),
        BibleQuizQuestion(
            id = 92, level = 8,
            question = "How many total years did Jacob state he worked in Laban's household (for his daughters and flocks)?",
            options = listOf("14 years", "21 years", "24 years", "20 years"),
            correctIndex = 3,
            verseRef = "Genesis 31:41"
        ),
        BibleQuizQuestion(
            id = 93, level = 8,
            question = "Who was King Ahab's God-fearing palace steward who risked his life hiding 100 prophets in caves?",
            options = listOf("Obadiah", "Micaiah", "Jehu son of Hanani", "Naboth"),
            correctIndex = 0,
            verseRef = "1 Kings 18:3-4"
        ),
        BibleQuizQuestion(
            id = 94, level = 8,
            question = "Which Gospel alone preserves the Aramaic healing word 'Ephphatha!' spoken to a deaf-mute man in the Decapolis?",
            options = listOf("Matthew", "Mark", "Luke", "John"),
            correctIndex = 1,
            verseRef = "Mark 7:34"
        ),
        BibleQuizQuestion(
            id = 95, level = 8,
            question = "Which generous 80-year-old Gileadite sustained David at Mahanaim and declined moving to Jerusalem's palace?",
            options = listOf("Shobi", "Machir", "Barzillai", "Zadok"),
            correctIndex = 2,
            verseRef = "2 Samuel 19:32-35"
        ),
        BibleQuizQuestion(
            id = 96, level = 8,
            question = "On Crete near Lasea, at which harbor did Paul warn Julius NOT to set sail toward Phoenix before the terrible storm hit?",
            options = listOf("Salmone", "Cnidus", "Myra", "Fair Havens"),
            correctIndex = 3,
            verseRef = "Acts 27:8-12"
        ),

        // ==================== LEVEL 9: DEEP EXEGESIS (Brutal - 12s) ====================
        BibleQuizQuestion(
            id = 97, level = 9,
            question = "What name was given to the valley where Jehoshaphat's army praised God after Moab and Ammon slew one another?",
            options = listOf("Valley of Beracah", "Valley of Achor", "Valley of Siddim", "Valley of Elah"),
            correctIndex = 0,
            verseRef = "2 Chronicles 20:26"
        ),
        BibleQuizQuestion(
            id = 98, level = 9,
            question = "Which Israelite deliverer struck down 600 Philistines using an oxgoad?",
            options = listOf("Ibzan", "Shamgar", "Jair", "Elon"),
            correctIndex = 1,
            verseRef = "Judges 3:31"
        ),
        BibleQuizQuestion(
            id = 99, level = 9,
            question = "Who was the Ephesian silversmith who sparked a citywide theater riot over Paul's impact on Artemis shrines?",
            options = listOf("Alexander", "Sceva", "Demetrius", "Gaius"),
            correctIndex = 2,
            verseRef = "Acts 19:24"
        ),
        BibleQuizQuestion(
            id = 100, level = 9,
            question = "Which priest of Bethel ordered the prophet Amos to flee to Judah and never prophesy again at the king's shrine?",
            options = listOf("Pashhur", "Hananiah", "Azariah", "Amaziah"),
            correctIndex = 3,
            verseRef = "Amos 7:10-13"
        ),
        BibleQuizQuestion(
            id = 101, level = 9,
            question = "Which false prophet shattered Jeremiah's wooden yoke and died within the year just as Jeremiah foretold?",
            options = listOf("Hananiah of Gibeon", "Shemaiah the Nehelamite", "Pashhur son of Immer", "Ahab son of Kolaiah"),
            correctIndex = 0,
            verseRef = "Jeremiah 28:10-17"
        ),
        BibleQuizQuestion(
            id = 102, level = 9,
            question = "Which brief New Testament epistle rebukes Diotrephes for loving to be first and refusing traveling brothers?",
            options = listOf("2 John", "3 John", "Jude", "Philemon"),
            correctIndex = 1,
            verseRef = "3 John 1:9-10"
        ),
        BibleQuizQuestion(
            id = 103, level = 9,
            question = "Which Cushite palace official rescued Jeremiah from starving in a muddy cistern using old rags as padding?",
            options = listOf("Ahikam", "Baruch", "Ebed-Melek", "Gedaliah"),
            correctIndex = 2,
            verseRef = "Jeremiah 38:7-13"
        ),
        BibleQuizQuestion(
            id = 104, level = 9,
            question = "Which surviving son of King Saul did commander Abner make king over Israel at Mahanaim?",
            options = listOf("Malchi-Shua", "Abinadab", "Mephibosheth", "Ish-Bosheth"),
            correctIndex = 3,
            verseRef = "2 Samuel 2:8-10"
        ),
        BibleQuizQuestion(
            id = 105, level = 9,
            question = "Who were the three formidable warrior sons of David's sister Zeruiah?",
            options = listOf("Joab, Abishai, and Asahel", "Abner, Amasa, and Asahel", "Benaiah, Eleazar, and Shammah", "Ira, Heleb, and Abiezer"),
            correctIndex = 0,
            verseRef = "1 Chronicles 2:16"
        ),
        BibleQuizQuestion(
            id = 106, level = 9,
            question = "Which of the seven churches in Revelation is praised for remaining faithful 'where Satan has his throne'?",
            options = listOf("Smyrna", "Pergamum", "Philadelphia", "Ephesus"),
            correctIndex = 1,
            verseRef = "Revelation 2:13"
        ),
        BibleQuizQuestion(
            id = 107, level = 9,
            question = "Who secretly hid infant prince Joash for six years in a temple storeroom from Queen Athaliah's massacre?",
            options = listOf("Huldah", "Jehoiada's daughter-in-law Tamar", "Jehosheba (daughter of King Joram)", "Rizpah"),
            correctIndex = 2,
            verseRef = "2 Kings 11:2"
        ),
        BibleQuizQuestion(
            id = 108, level = 9,
            question = "In Romans 16:7, whom does Paul honor as his fellow prisoners, 'outstanding among the apostles', who were in Christ before him?",
            options = listOf("Aquila and Priscilla", "Tryphena and Tryphosa", "Asyncritus and Phlegon", "Andronicus and Junia"),
            correctIndex = 3,
            verseRef = "Romans 16:7"
        ),

        // ==================== LEVEL 10: OMEGA GRANDMASTER (ULTRA HARD - 10s) ====================
        BibleQuizQuestion(
            id = 109, level = 10,
            question = "In Numbers 27:1, what are the EXACT five names of Zelophehad's daughters who petitioned Moses for an inheritance?",
            options = listOf(
                "Mahlah, Noah, Hoglah, Milkah, and Tirzah",
                "Puah, Shiphrah, Hoglah, Milkah, and Abigail",
                "Mahlah, Basemath, Noah, Oholibamah, and Tirzah",
                "Adah, Zillah, Hoglah, Cozbi, and Milkah"
            ),
            correctIndex = 0,
            verseRef = "Numbers 27:1"
        ),
        BibleQuizQuestion(
            id = 110, level = 10,
            question = "In Revelation 21:20, which gemstone constitutes the TWELFTH (final) foundation of the wall of the New Jerusalem?",
            options = listOf("Chrysoprase", "Amethyst", "Jacinth", "Sardonyx"),
            correctIndex = 1,
            verseRef = "Revelation 21:19-20"
        ),
        BibleQuizQuestion(
            id = 111, level = 10,
            question = "In the 22 Hebrew-letter acrostic structure of Psalm 119 (176 verses), exactly how many verses belong to each stanza?",
            options = listOf("6 verses", "10 verses", "8 verses", "12 verses"),
            correctIndex = 2,
            verseRef = "Psalm 119 (22 × 8 = 176)"
        ),
        BibleQuizQuestion(
            id = 112, level = 10,
            question = "In Jeremiah 36:25, which THREE officials pleaded with King Jehoiakim NOT to burn Jeremiah's scroll in the brazier?",
            options = listOf(
                "Gedaliah, Ishmael, and Johanan",
                "Pashhur, Zephaniah, and Seraiah",
                "Ahikam, Shaphan, and Hilkiah",
                "Elnathan, Delaiah, and Gemariah"
            ),
            correctIndex = 3,
            verseRef = "Jeremiah 36:25"
        ),
        BibleQuizQuestion(
            id = 113, level = 10,
            question = "What were the exact Aramaic words inscribed by the disembodied hand on King Belshazzar's palace wall in Daniel 5:25?",
            options = listOf(
                "MENE, MENE, TEKEL, PARSIN",
                "TEKEL, PARSIN, TEKEL, SELAH",
                "PERES, MENE, SHALOM, TEKEL",
                "KADOSH, MENE, ADONAI, PERES"
            ),
            correctIndex = 0,
            verseRef = "Daniel 5:25"
        ),
        BibleQuizQuestion(
            id = 114, level = 10,
            question = "Which twin son of Judah and Tamar actually emerged FIRST at birth despite a scarlet thread already tied to Zerah's wrist?",
            options = listOf("Er", "Perez", "Onan", "Shelah"),
            correctIndex = 1,
            verseRef = "Genesis 38:27-30"
        ),
        BibleQuizQuestion(
            id = 115, level = 10,
            question = "In Acts 28:11, what twin figurehead adorned the Alexandrian ship that carried Paul away from Malta toward Rome?",
            options = listOf("Zeus and Hermes", "Artemis and Apollo", "Castor and Pollux (Dioscuri)", "Poseidon and Triton"),
            correctIndex = 2,
            verseRef = "Acts 28:11"
        ),
        BibleQuizQuestion(
            id = 116, level = 10,
            question = "What THREE sacred articles does Hebrews 9:4 declare were placed inside the Ark of the Covenant?",
            options = listOf(
                "Two cherubim crowns, gold censer, and Law tablets",
                "Shewbread vessel, anointing horn, and David's sling",
                "Bronze serpent, golden ephod, and Urim & Thummim",
                "Gold jar of manna, Aaron's budded staff, and stone tablets"
            ),
            correctIndex = 3,
            verseRef = "Hebrews 9:4"
        ),
        BibleQuizQuestion(
            id = 117, level = 10,
            question = "In Colossians 4:17, which specific individual did Paul urge: 'See to it that you complete the ministry you have received in the Lord'?",
            options = listOf("Archippus", "Nympha", "Epaphras", "Aristarchus"),
            correctIndex = 0,
            verseRef = "Colossians 4:17"
        ),
        BibleQuizQuestion(
            id = 118, level = 10,
            question = "In Galatians 4:24-25, what geographic mountain and region does Paul equate allegorically with Hagar?",
            options = listOf("Mount Gerizim in Samaria", "Mount Sinai in Arabia", "Mount Seir in Edom", "Mount Hor on Moab's border"),
            correctIndex = 1,
            verseRef = "Galatians 4:24-25"
        ),
        BibleQuizQuestion(
            id = 119, level = 10,
            question = "After which terrifying northeaster storm wind was Paul's grain ship named as helplessly driven in Acts 27:14?",
            options = listOf("Sirocco", "Boreas", "Euroaquilo (Eurakylon)", "Notus"),
            correctIndex = 2,
            verseRef = "Acts 27:14"
        ),
        BibleQuizQuestion(
            id = 120, level = 10,
            question = "Which longest-reigning king of Judah was hauled to Babylon with a hook in his nose before repenting and regaining his throne?",
            options = listOf("Amon", "Ahaz", "Jehoiachin", "Manasseh"),
            correctIndex = 3,
            verseRef = "2 Chronicles 33:11-13"
        )
    )
}
