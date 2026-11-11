package org.marthoma.centreyouth.data

import org.marthoma.centreyouth.data.model.BibleQuizQuestion

/**
 * Levels 6 to 10 (Questions 61..120) — Bilingual (English + Sathyavedapusthakam Malayalam)
 */
internal val quizQuestionsPart2: List<BibleQuizQuestion> = listOf(
    // ==================== LEVEL 6: KINGDOM CHRONICLES (Challenging - 16s) ====================
    BibleQuizQuestion(
        id = 61, level = 6,
        question = "What was the name of the high priest's servant whose right ear Simon Peter cut off?",
        questionMl = "ശിമോൻ പത്രൊസ് വലതുചെവി വെട്ടിയ മഹാപുരോഹിതന്റെ ദാസന്റെ പേര് എന്തായിരുന്നു?",
        options = listOf("Rufus", "Alexander", "Malchus", "Jason"),
        optionsMl = listOf("റൂഫൊസ്", "അലെക്സാന്ദർ", "മൽക്കൊസ്", "യാസോൻ"),
        correctIndex = 2,
        verseRef = "John 18:10",
        verseRefMl = "യോഹന്നാൻ 18:10"
    ),
    BibleQuizQuestion(
        id = 62, level = 6,
        question = "How many years did King Solomon take to complete building the First Temple in Jerusalem?",
        questionMl = "യെരൂശലേമിലെ ഒന്നാമത്തെ ദൈവാലയം പണിതു തീർക്കാൻ ശലോമോൻ രാജാവിന് എത്ര വർഷം വേണ്ടിവന്നു?",
        options = listOf("7 years", "3 years", "12 years", "20 years"),
        optionsMl = listOf("7 വർഷം", "3 വർഷം", "12 വർഷം", "20 വർഷം"),
        correctIndex = 0,
        verseRef = "1 Kings 6:38",
        verseRefMl = "1 രാജാക്കന്മാർ 6:38"
    ),
    BibleQuizQuestion(
        id = 63, level = 6,
        question = "Which left-handed judge of Israel assassinated the Moabite king Eglon in his private summer chamber?",
        questionMl = "മോവാബ് രാജാവായ എഗ്ലോനെ തന്റെ കുളിർമാളികയിൽ വെച്ച് വധിച്ച ഇടംകൈയൻ ന്യായാധിപൻ ആര്?",
        options = listOf("Othniel", "Ehud", "Tola", "Abdon"),
        optionsMl = listOf("ഒത്നിയേൽ", "ഏഹൂദ്", "തോലാ", "അബ്ദോൻ"),
        correctIndex = 1,
        verseRef = "Judges 3:15-21",
        verseRefMl = "ന്യായാധിപന്മാർ 3:15-21"
    ),
    BibleQuizQuestion(
        id = 64, level = 6,
        question = "At how many years old did righteous King Josiah ascend the throne of Judah?",
        questionMl = "നീതിമാനായ യോശീയാവ് യെഹൂദാ സിംഹാസനത്തിൽ കയറുമ്പോൾ അവന് എത്ര വയസ്സായിരുന്നു?",
        options = listOf("7 years old", "12 years old", "16 years old", "8 years old"),
        optionsMl = listOf("7 വയസ്സ്", "12 വയസ്സ്", "16 വയസ്സ്", "8 വയസ്സ്"),
        correctIndex = 3,
        verseRef = "2 Kings 22:1",
        verseRefMl = "2 രാജാക്കന്മാർ 22:1"
    ),
    BibleQuizQuestion(
        id = 65, level = 6,
        question = "Who fell asleep from a third-story window in Troas while Paul was preaching late into the night?",
        questionMl = "ത്രോവാസിൽ പൗലോസ് രാത്രി ഏറെ വൈകി പ്രസംഗിച്ചുകൊണ്ടിരിക്കെ മൂന്നാം നിലയിലെ ജനാലയിൽ നിന്ന് ഉറങ്ങി വീണത് ആര്?",
        options = listOf("Trophimus", "Eutychus", "Sosthenes", "Sopater"),
        optionsMl = listOf("ത്രൊഫിമൊസ്", "യൂത്തിക്കൊസ്", "സൊസ്തെനേസ്", "സോപത്രൊസ്"),
        correctIndex = 1,
        verseRef = "Acts 20:9-10",
        verseRefMl = "പ്രവൃത്തികൾ 20:9-10"
    ),
    BibleQuizQuestion(
        id = 66, level = 6,
        question = "Which Persian ruler does Isaiah 45:1 call the LORD's 'anointed' who would rebuild Jerusalem?",
        questionMl = "യെരൂശലേം പണിയാൻ യഹോവയുടെ 'അഭിഷിക്തൻ' എന്ന് യെശയ്യാവ് 45:1 വിശേഷിപ്പിക്കുന്ന പേർഷ്യൻ ചക്രവർത്തി ആര്?",
        options = listOf("Cyrus", "Darius", "Artaxerxes", "Xerxes"),
        optionsMl = listOf("കോരെശ്", "ദാര്യാവേശ്", "അർത്ഥഹ്ശഷ്ടാ", "അഹശ്വേരോശ്"),
        correctIndex = 0,
        verseRef = "Isaiah 45:1",
        verseRefMl = "യെശയ്യാവ് 45:1"
    ),
    BibleQuizQuestion(
        id = 67, level = 6,
        question = "When Jacob crossed his arms to bless Joseph's two sons, which younger son received the right-hand blessing?",
        questionMl = "യോസേഫിന്റെ രണ്ട് പുത്രന്മാരെ അനുഗ്രഹിക്കാൻ യാക്കോബ് കൈകൾ പിണച്ചുവെച്ചപ്പോൾ വലങ്കൈ അനുഗ്രഹം ലഭിച്ച ഇളയ മകൻ ആര്?",
        options = listOf("Manasseh", "Machir", "Ephraim", "Gilead"),
        optionsMl = listOf("മനശ്ശെ", "മാഖീർ", "എഫ്രയീം", "ഗിലെയാദ്"),
        correctIndex = 2,
        verseRef = "Genesis 48:14-19",
        verseRefMl = "ഉല്പത്തി 48:14-19"
    ),
    BibleQuizQuestion(
        id = 68, level = 6,
        question = "What were the names of the two monumental bronze pillars standing at the porch of Solomon's Temple?",
        questionMl = "ശലോമോന്റെ ആലയത്തിന്റെ മണ്ഡപത്തിൽ സ്ഥാപിച്ച രണ്ട് വലിയ താമ്രസ്തംഭങ്ങളുടെ പേരുകൾ എന്തൊക്കെയായിരുന്നു?",
        options = listOf("Urim and Thummim", "Gog and Magog", "Mizpah and Ebenezer", "Jakin and Boaz"),
        optionsMl = listOf("ഊരീം, തുമ്മീം", "ഗോഗ്, മാഗോഗ്", "മിസ്പാ, എബെനേസർ", "യാഖീൻ, ബോവസ്"),
        correctIndex = 3,
        verseRef = "1 Kings 7:21",
        verseRefMl = "1 രാജാക്കന്മാർ 7:21"
    ),
    BibleQuizQuestion(
        id = 69, level = 6,
        question = "Which Judean king was granted 15 extra years of life when the stairway shadow retreated ten steps?",
        questionMl = "നിഴൽ പത്ത് പടികൾ പിന്നോട്ട് പോയ അടയാളത്തിലൂടെ 15 വർഷം അധിക ആയുസ്സ് ലഭിച്ച യെഹൂദാ രാജാവ് ആര്?",
        options = listOf("Asa", "Hezekiah", "Jehoshaphat", "Jotham"),
        optionsMl = listOf("ആസാ", "ഹിസ്കീയാവ്", "യെഹോശാഫാത്ത്", "യോഥാം"),
        correctIndex = 1,
        verseRef = "2 Kings 20:5-11",
        verseRefMl = "2 രാജാക്കന്മാർ 20:5-11"
    ),
    BibleQuizQuestion(
        id = 70, level = 6,
        question = "On which Mediterranean island was Paul shipwrecked and bitten harmlessly by a venomous viper?",
        questionMl = "കപ്പൽഛേദം സംഭവിച്ച ശേഷം ഒരു ഉഗ്രസർപ്പം കടിച്ചിട്ടും പൗലോസിന് പരിക്കേൽക്കാതിരുന്ന മദ്ധ്യധരണ്യാഴി ദ്വീപ് ഏതാണ്?",
        options = listOf("Malta", "Crete", "Rhodes", "Sicily"),
        optionsMl = listOf("മെലിത്ത (മാൾട്ട)", "ക്രേത്ത", "റോദൊസ്", "സിസിലി"),
        correctIndex = 0,
        verseRef = "Acts 28:1-5",
        verseRefMl = "പ്രവൃത്തികൾ 28:1-5"
    ),
    BibleQuizQuestion(
        id = 71, level = 6,
        question = "Which prophet lay on his left side 390 days and his right side 40 days to symbolize Israel and Judah's sin?",
        questionMl = "യിസ്രായേലിന്റെയും യെഹൂദയുടെയും പാപം പ്രതീകപ്പെടുത്താൻ 390 ദിവസം ഇടതുവശത്തും 40 ദിവസം വലതുവശത്തും കിടന്ന പ്രവാചകൻ ആര്?",
        options = listOf("Jeremiah", "Hosea", "Ezekiel", "Zechariah"),
        optionsMl = listOf("യിരെമ്യാവ്", "ഹോശേയ", "യെഹെസ്കേൽ", "സെഖര്യാവ്"),
        correctIndex = 2,
        verseRef = "Ezekiel 4:4-6",
        verseRefMl = "യെഹെസ്കേൽ 4:4-6"
    ),
    BibleQuizQuestion(
        id = 72, level = 6,
        question = "Which couple struck dead in Acts 5 conspired to lie to the Holy Spirit about their land proceeds?",
        questionMl = "നിലം വിറ്റ തുകയെക്കുറിച്ച് പരിശുദ്ധാത്മാവിനോട് വ്യാജം പറഞ്ഞ് പ്രവൃത്തികൾ 5-ൽ മരിച്ചുവീണ ദമ്പതികൾ ആര്?",
        options = listOf("Aquila and Priscilla", "Ahab and Jezebel", "Nabal and Abigail", "Ananias and Sapphira"),
        optionsMl = listOf("അക്വിലാസും പ്രിസ്കില്ലയും", "ആഹാബും ഈസേബെലും", "നാബാലും അബീഗയിലും", "അനന്യാസും സഫീറയും"),
        correctIndex = 3,
        verseRef = "Acts 5:1-10",
        verseRefMl = "പ്രവൃത്തികൾ 5:1-10"
    ),

    // ==================== LEVEL 7: COVENANT SHADOWS (Hard - 15s) ====================
    BibleQuizQuestion(
        id = 73, level = 7,
        question = "Which priest-king of Salem brought out bread and wine to bless Abraham and received a tithe?",
        questionMl = "അപ്പവും വീഞ്ഞും കൊണ്ടുവന്ന് അബ്രാഹാമിനെ അനുഗ്രഹിക്കുകയും ദശാംശം സ്വീകരിക്കുകയും ചെയ്ത ശാലേം രാജാവും പുരോഹിതനുമായത് ആര്?",
        options = listOf("Melchizedek", "Abimelech", "Jethro", "Bera"),
        optionsMl = listOf("മെൽക്കീസേദെക്ക്", "അബീമേലെക്ക്", "യിത്രോ", "ബേരാ"),
        correctIndex = 0,
        verseRef = "Genesis 14:18-20",
        verseRefMl = "ഉല്പത്തി 14:18-20"
    ),
    BibleQuizQuestion(
        id = 74, level = 7,
        question = "After Korah's rebellion, whose almond staff sprouted, blossomed, and yielded ripe almonds overnight?",
        questionMl = "കോരഹിന്റെ മത്സരത്തിന് ശേഷം ഒറ്റരാത്രികൊണ്ട് തളിർക്കുകയും പൂക്കുകയും ബദാം ഫലം കായ്ക്കുകയും ചെയ്ത വടി ആരുടേതായിരുന്നു?",
        options = listOf("Moses' staff", "Caleb's staff", "Aaron's staff", "Joshua's staff"),
        optionsMl = listOf("മോശെയുടെ വടി", "കാലേബിന്റെ വടി", "അഹരോന്റെ വടി", "യോശുവയുടെ വടി"),
        correctIndex = 2,
        verseRef = "Numbers 17:8",
        verseRefMl = "സംഖ്യാപുസ്തകം 17:8"
    ),
    BibleQuizQuestion(
        id = 75, level = 7,
        question = "Which prophet was commanded by God to marry Gomer as an enacted sign of Israel's covenant unfaithfulness?",
        questionMl = "യിസ്രായേലിന്റെ അവിശ്വസ്തതയുടെ പ്രതീകമായി ഗോമെറിനെ വിവാഹം കഴിക്കാൻ ദൈവം കല്പിച്ച പ്രവാചകൻ ആര്?",
        options = listOf("Amos", "Hosea", "Micah", "Habakkuk"),
        optionsMl = listOf("ആമോസ്", "ഹോശേയ", "മീഖാ", "ഹബക്കൂക്ക്"),
        correctIndex = 1,
        verseRef = "Hosea 1:2-3",
        verseRefMl = "ഹോശേയ 1:2-3"
    ),
    BibleQuizQuestion(
        id = 76, level = 7,
        question = "What does Jesus' Aramaic cry 'Eloi, Eloi, lema sabachthani?' mean?",
        questionMl = "'എലോഹീ, എലോഹീ, ലമ്മാ ശബക്താനി?' എന്ന യേശുവിന്റെ അരമായ നിലവിളിയുടെ അർത്ഥമെന്ത്?",
        options = listOf("Father, forgive them", "It is finished", "Into Your hands I commit My spirit", "My God, My God, why have You forsaken Me?"),
        optionsMl = listOf("പിതാവേ, ഇവരോട് ക്ഷമിക്കേണമേ", "നിവൃത്തിയായി", "എന്റെ ആത്മാവിനെ തൃക്കൈകളിൽ ഏല്പിക്കുന്നു", "എന്റെ ദൈവമേ, എന്റെ ദൈവമേ, നീ എന്നെ കൈവിട്ടത് എന്ത്?"),
        correctIndex = 3,
        verseRef = "Mark 15:34",
        verseRefMl = "മർക്കൊസ് 15:34"
    ),
    BibleQuizQuestion(
        id = 77, level = 7,
        question = "Who was the chief Spirit-filled artisan from Judah chosen to construct the Ark of the Covenant?",
        questionMl = "നിയമപെട്ടകം നിർമ്മിക്കാൻ യെഹൂദാ ഗോത്രത്തിൽ നിന്ന് ദൈവാത്മാവിനാൽ തിരഞ്ഞെടുക്കപ്പെട്ട പ്രധാന ശില്പി ആരായിരുന്നു?",
        options = listOf("Bezalel", "Oholiab", "Ithamar", "Bezai"),
        optionsMl = listOf("ബെസലേൽ", "അഹല്യാബ്", "ഈഥാമാർ", "ബേസായി"),
        correctIndex = 0,
        verseRef = "Exodus 31:2-5",
        verseRefMl = "പുറപ്പാട് 31:2-5"
    ),
    BibleQuizQuestion(
        id = 78, level = 7,
        question = "How many Cities of Refuge were set apart across Canaan for cases of unintentional manslaughter?",
        questionMl = "അബദ്ധവശാലുള്ള കൊലപാതകക്കേസുകളിൽ അഭയം തേടാൻ കനാനിൽ എത്ര സങ്കേതനഗരങ്ങൾ വേർതിരിച്ചിരുന്നു?",
        options = listOf("3", "6", "7", "12"),
        optionsMl = listOf("3", "6", "7", "12"),
        correctIndex = 1,
        verseRef = "Numbers 35:13-14",
        verseRefMl = "സംഖ്യാപുസ്തകം 35:13-14"
    ),
    BibleQuizQuestion(
        id = 79, level = 7,
        question = "Which usurping queen murdered almost all the royal offspring of Judah to seize the throne for six years?",
        questionMl = "ആറ് വർഷം യെഹൂദാ ഭരിക്കാനായി രാജവംശത്തെ മുഴുവൻ കൊന്നൊടുക്കിയ ക്രൂരയായ രാജ്ഞി ആര്?",
        options = listOf("Jezebel", "Maacah", "Athaliah", "Tahpenes"),
        optionsMl = listOf("ഈസേബെൽ", "മയഖാ", "അഥല്യ", "തഹ്പെനേസ്"),
        correctIndex = 2,
        verseRef = "2 Kings 11:1-3",
        verseRefMl = "2 രാജാക്കന്മാർ 11:1-3"
    ),
    BibleQuizQuestion(
        id = 80, level = 7,
        question = "In Acts 4:36, what was the original given name of Barnabas, the Cypriot Levite nicknamed 'Son of Encouragement'?",
        questionMl = "പ്രവൃത്തികൾ 4:36-ൽ 'പ്രബോധനപുത്രൻ' എന്ന് അർത്ഥമുള്ള ബർന്നബാസ് എന്ന് വിളിക്കപ്പെട്ട കുപ്രൊസ് ലേവ്യന്റെ യഥാർത്ഥ പേരെന്ത്?",
        options = listOf("John Mark", "Judas", "Barsabbas", "Joseph"),
        optionsMl = listOf("യോഹന്നാൻ മർക്കൊസ്", "യൂദാ", "ബർശബ്ബാസ്", "യോസേഫ്"),
        correctIndex = 3,
        verseRef = "Acts 4:36",
        verseRefMl = "പ്രവൃത്തികൾ 4:36"
    ),
    BibleQuizQuestion(
        id = 81, level = 7,
        question = "What does the early Christian Aramaic prayer 'Maranatha' at the end of 1 Corinthians signify?",
        questionMl = "1 കൊരിന്ത്യർ ലേഖനത്തിന്റെ അവസാനത്തിൽ കാണുന്ന 'മരനാഥാ' എന്ന അരമായ പദത്തിന്റെ അർത്ഥമെന്ത്?",
        options = listOf("Our Lord, come!", "Grace be to all", "Holy, holy, holy", "Peace to Israel"),
        optionsMl = listOf("ഞങ്ങളുടെ കർത്താവേ, വരേണമേ!", "എല്ലാവർക്കും കൃപയുണ്ടാകട്ടെ", "പരിശുദ്ധൻ, പരിശുദ്ധൻ", "യിസ്രായേലിന് സമാധാനം"),
        correctIndex = 0,
        verseRef = "1 Corinthians 16:22",
        verseRefMl = "1 കൊരിന്ത്യർ 16:22"
    ),
    BibleQuizQuestion(
        id = 82, level = 7,
        question = "At what exact age did Moses die on Mount Nebo with undimmed eyes and undiminished vigor?",
        questionMl = "കണ്ണു മങ്ങാതെയും ശക്തി ക്ഷയിക്കാതെയും നെബോ പർവ്വതത്തിൽ വെച്ച് മോശെ മരിക്കുമ്പോൾ എത്ര വയസ്സായിരുന്നു?",
        options = listOf("100 years", "120 years", "110 years", "147 years"),
        optionsMl = listOf("100 വയസ്സ്", "120 വയസ്സ്", "110 വയസ്സ്", "147 വയസ്സ്"),
        correctIndex = 1,
        verseRef = "Deuteronomy 34:7",
        verseRefMl = "ആവർത്തനപുസ്തകം 34:7"
    ),
    BibleQuizQuestion(
        id = 83, level = 7,
        question = "Which prophet beheld six-winged seraphim crying 'Holy, holy, holy' before the LORD's lofty throne?",
        questionMl = "ഉയർന്ന സിംഹാസനത്തിന് മുന്നിൽ 'പരിശുദ്ധൻ, പരിശുദ്ധൻ, പരിശുദ്ധൻ' എന്ന് ആർക്കുന്ന ആറു ചിറകുള്ള സാറാഫുകളെ കണ്ട പ്രവാചകൻ ആര്?",
        options = listOf("Ezekiel", "Daniel", "Isaiah", "Joel"),
        optionsMl = listOf("യെഹെസ്കേൽ", "ദാനീയേൽ", "യെശയ്യാവ്", "യോവേൽ"),
        correctIndex = 2,
        verseRef = "Isaiah 6:1-3",
        verseRefMl = "യെശയ്യാവ് 6:1-3"
    ),
    BibleQuizQuestion(
        id = 84, level = 7,
        question = "Which Judean king was struck with leprosy on his forehead for usurping the priestly duty of burning incense?",
        questionMl = "പുരോഹിതന്മാരുടെ മാത്രം കടമയായ ധൂപം അർപ്പിക്കാൻ തുനിഞ്ഞതുകൊണ്ട് നെറ്റിയിൽ കുഷ്ഠരോഗം ബാധിച്ച യെഹൂദാ രാജാവ് ആര്?",
        options = listOf("Manasseh", "Ahaz", "Jehoash", "Uzziah (Azariah)"),
        optionsMl = listOf("മനശ്ശെ", "ആഹാസ്", "യെഹോവാശ്", "ഉസ്സീയാവ് (അസര്യാവ്)"),
        correctIndex = 3,
        verseRef = "2 Chronicles 26:16-21",
        verseRefMl = "2 ദിനവൃത്താന്തം 26:16-21"
    ),

    // ==================== LEVEL 8: SANCTUARY SECRETS (Very Hard - 14s) ====================
    BibleQuizQuestion(
        id = 85, level = 8,
        question = "Who acted as King David's secret confidant to infiltrate Absalom's court and overturn Ahithophel's advice?",
        questionMl = "അഹീഥോഫെലിന്റെ ആലോചന നിഷ്ഫലമാക്കാൻ അബ്ശാലോമിന്റെ സഭയിൽ നുഴഞ്ഞുകയറിയ ദാവീദിന്റെ വിശ്വസ്ത സുഹൃത്ത് ആരായിരുന്നു?",
        options = listOf("Hushai the Arkite", "Ittai the Gittite", "Ziba the servant", "Chimham"),
        optionsMl = listOf("അർക്ക്യനായ ഹൂശായി", "ഗിത്യനായ ഇത്തായി", "ദാസനായ സീബാ", "കിംഹാം"),
        correctIndex = 0,
        verseRef = "2 Samuel 15:32-37",
        verseRefMl = "2 ശമൂവേൽ 15:32-37"
    ),
    BibleQuizQuestion(
        id = 86, level = 8,
        question = "Who physically penned Paul's Epistle to the Romans as his scribe, sending his own greeting in Romans 16?",
        questionMl = "റോമർ 16:22-ൽ സ്വന്തം വന്ദനം ചേർത്ത് പൗലോസിന്റെ റോമർ ലേഖനം നേരിട്ട് എഴുതിയ ലേഖകൻ (കൈയ്യെഴുത്തുകാരൻ) ആര്?",
        options = listOf("Timothy", "Erastus", "Tertius", "Quartus"),
        optionsMl = listOf("തിമൊഥെയൊസ്", "എരസ്തൊസ്", "തെർത്യൊസ്", "ക്വർത്തൊസ്"),
        correctIndex = 2,
        verseRef = "Romans 16:22",
        verseRefMl = "റോമർ 16:22"
    ),
    BibleQuizQuestion(
        id = 87, level = 8,
        question = "Which Edomite chief herdsman informed King Saul about Ahimelech and personally slaughtered 85 priests at Nob?",
        questionMl = "അഹീമേലെക്കിനെക്കുറിച്ച് ശൗലിനോട് പറയുകയും നോബിൽ വെച്ച് 85 പുരോഹിതന്മാരെ കൊല്ലുകയും ചെയ്ത എദോമ്യനായ ഇടയപ്രമാണി ആര്?",
        options = listOf("Shimei", "Doeg", "Nabal", "Abner"),
        optionsMl = listOf("ശിമെയി", "ദോവേഗ്", "നാബാൽ", "അബ്നേർ"),
        correctIndex = 1,
        verseRef = "1 Samuel 22:18",
        verseRefMl = "1 ശമൂവേൽ 22:18"
    ),
    BibleQuizQuestion(
        id = 88, level = 8,
        question = "Who was Jonathan's lamed son whom King David invited to eat perpetually at the king's table?",
        questionMl = "രാജാവിന്റെ മേശയിങ്കൽ നിത്യമായി ഭക്ഷണം കഴിക്കാൻ ദാവീദ് ക്ഷണിച്ച യോനാഥാന്റെ മുടന്തനായ മകന്റെ പേരെന്ത്?",
        options = listOf("Ish-Bosheth", "Adonijah", "Machir", "Mephibosheth"),
        optionsMl = listOf("ഈശ്-ബോശെത്ത്", "അദോനീയാവ്", "മാഖീർ", "മെഫീബോശെത്ത്"),
        correctIndex = 3,
        verseRef = "2 Samuel 9:6-13",
        verseRefMl = "2 ശമൂവേൽ 9:6-13"
    ),
    BibleQuizQuestion(
        id = 89, level = 8,
        question = "Who were the three chief opponents mocking and conspiring against Nehemiah's rebuilding of Jerusalem's walls?",
        questionMl = "യെരൂശലേം മതിൽ പണിയിൽ നെഹെമ്യാവിനെ പരിഹസിക്കുകയും ഗൂഢാലോചന നടത്തുകയും ചെയ്ത മൂന്ന് പ്രധാന ശത്രുക്കൾ ആരൊക്കെയായിരുന്നു?",
        options = listOf("Sanballat, Tobiah, and Geshem", "Bishlam, Mithredath, and Tabeel", "Tattenai, Shethar-Bozenai, and Rehum", "Haman, Memucan, and Carshena"),
        optionsMl = listOf("സൻബല്ലത്ത്, തോബീയാവ്, ഗേശെം", "ബിശ്ലാം, മിത്രെദാത്ത്, താബെയേൽ", "തത്നായി, ശേഥർ-ബോസ്നായി, രെഹൂം", "ഹാമാൻ, മെമൂഖാൻ, കർശെനാ"),
        correctIndex = 0,
        verseRef = "Nehemiah 2:19; 6:1",
        verseRefMl = "നെഹെമ്യാവ് 2:19; 6:1"
    ),
    BibleQuizQuestion(
        id = 90, level = 8,
        question = "What name had Israel given Moses' bronze serpent before King Hezekiah smashed it to purge idolatry?",
        questionMl = "ഹിസ്കീയാവ് രാജാവ് തകർത്തുകളയുന്നതിന് മുമ്പ് മോശെ ഉണ്ടാക്കിയ താമ്രസർപ്പത്തിന് യിസ്രായേല്യർ വിളിച്ചിരുന്ന പേരെന്ത്?",
        options = listOf("Dagon", "Nehushtan", "Milcom", "Chemosh"),
        optionsMl = listOf("ദാഗോൻ", "നെഹുഷ്താൻ", "മിൽക്കോം", "കെമോശ്"),
        correctIndex = 1,
        verseRef = "2 Kings 18:4",
        verseRefMl = "2 രാജാക്കന്മാർ 18:4"
    ),
    BibleQuizQuestion(
        id = 91, level = 8,
        question = "To which of the seven churches in Revelation did Christ say, 'You have a reputation of being alive, but you are dead'?",
        questionMl = "'ജീവനുള്ളവൻ എന്ന് പേരുണ്ടെങ്കിലും നീ മരിച്ചവൻ തന്നേ' എന്ന് വെളിപ്പാടിൽ ക്രിസ്തു പറഞ്ഞത് ഏത് സഭയോടാണ്?",
        options = listOf("Laodicea", "Thyatira", "Sardis", "Pergamum"),
        optionsMl = listOf("ലവൊദിക്യ", "തുയഥൈര", "സർദ്ദിസ്", "പെർഗ്ഗമൊസ്"),
        correctIndex = 2,
        verseRef = "Revelation 3:1",
        verseRefMl = "വെളിപ്പാട് 3:1"
    ),
    BibleQuizQuestion(
        id = 92, level = 8,
        question = "How many total years did Jacob state he worked in Laban's household (for his daughters and flocks)?",
        questionMl = "ലാബാന്റെ വീട്ടിൽ മക്കൾക്കും ആട്ടിൻകൂട്ടത്തിനുമായി ആകെ എത്ര വർഷം സേവനം ചെയ്തതായി യാക്കോബ് പറഞ്ഞു?",
        options = listOf("14 years", "21 years", "24 years", "20 years"),
        optionsMl = listOf("14 വർഷം", "21 വർഷം", "24 വർഷം", "20 വർഷം"),
        correctIndex = 3,
        verseRef = "Genesis 31:41",
        verseRefMl = "ഉല്പത്തി 31:41"
    ),
    BibleQuizQuestion(
        id = 93, level = 8,
        question = "Who was King Ahab's God-fearing palace steward who risked his life hiding 100 prophets in caves?",
        questionMl = "ദൈവഭക്തനായിരുന്ന ആഹാബ് രാജാവിന്റെ കൊട്ടാരം വിചാരിപ്പുകാരനായി 100 പ്രവാചകന്മാരെ ഗുഹകളിൽ ഒളിപ്പിച്ചത് ആര്?",
        options = listOf("Obadiah", "Micaiah", "Jehu son of Hanani", "Naboth"),
        optionsMl = listOf("ഓബദ്യാവ്", "മീഖായാവ്", "ഹനാനിയുടെ മകൻ യേഹൂ", "നാബോത്ത്"),
        correctIndex = 0,
        verseRef = "1 Kings 18:3-4",
        verseRefMl = "1 രാജാക്കന്മാർ 18:3-4"
    ),
    BibleQuizQuestion(
        id = 94, level = 8,
        question = "Which Gospel alone preserves the Aramaic healing word 'Ephphatha!' spoken to a deaf-mute man in the Decapolis?",
        questionMl = "ദെക്കപ്പൊലിസിലെ ചെകിടനും വിക്കനുമായ മനുഷ്യനോട് യേശു പറഞ്ഞ 'എഫഥാ!' എന്ന അരമായ പദം രേഖപ്പെടുത്തിയ ഏക സുവിശേഷം ഏത്?",
        options = listOf("Matthew", "Mark", "Luke", "John"),
        optionsMl = listOf("മത്തായി", "മർക്കൊസ്", "ലൂക്കൊസ്", "യോഹന്നാൻ"),
        correctIndex = 1,
        verseRef = "Mark 7:34",
        verseRefMl = "മർക്കൊസ് 7:34"
    ),
    BibleQuizQuestion(
        id = 95, level = 8,
        question = "Which generous 80-year-old Gileadite sustained David at Mahanaim and declined moving to Jerusalem's palace?",
        questionMl = "മഹനയീമിൽ ദാവീദിനെ സഹായിക്കുകയും വാർദ്ധക്യം കാരണം യെരൂശലേം കൊട്ടാരത്തിലേക്കുള്ള ക്ഷണം സ്നേഹപൂർവ്വം നിരസിക്കുകയും ചെയ്ത 80-കാരൻ ആര്?",
        options = listOf("Shobi", "Machir", "Barzillai", "Zadok"),
        optionsMl = listOf("ശോബി", "മാഖീർ", "ബർസില്ലായി", "സാദോക്ക്"),
        correctIndex = 2,
        verseRef = "2 Samuel 19:32-35",
        verseRefMl = "2 ശമൂവേൽ 19:32-35"
    ),
    BibleQuizQuestion(
        id = 96, level = 8,
        question = "On Crete near Lasea, at which harbor did Paul warn Julius NOT to set sail toward Phoenix before the terrible storm hit?",
        questionMl = "കൊടുങ്കാറ്റ് അടിക്കുന്നതിന് മുമ്പ് ഫൊയ്നീക്സ് തുറമുഖത്തേക്ക് കപ്പൽ നീക്കരുതെന്ന് ക്രേത്തയിലെ ഏത് തുറമുഖത്ത് വെച്ചാണ് പൗലോസ് മുന്നറിയിപ്പ് നൽകിയത്?",
        options = listOf("Salmone", "Cnidus", "Myra", "Fair Havens"),
        optionsMl = listOf("സൽമോനെ", "ക്നീദൊസ്", "മുറാ", "ശുഭതുറമുഖം (ലസയാ)"),
        correctIndex = 3,
        verseRef = "Acts 27:8-12",
        verseRefMl = "പ്രവൃത്തികൾ 27:8-12"
    ),

    // ==================== LEVEL 9: DEEP EXEGESIS (Brutal - 12s) ====================
    BibleQuizQuestion(
        id = 97, level = 9,
        question = "What name was given to the valley where Jehoshaphat's army praised God after Moab and Ammon slew one another?",
        questionMl = "മോവാബും അമ്മോനും പരസ്പരം നശിപ്പിച്ച ശേഷം യെഹോശാഫാത്തിന്റെ സൈന്യം ദൈവത്തെ വാഴ്ത്തിയ താഴ്വരയ്ക്ക് നൽകിയ പേരെന്ത്?",
        options = listOf("Valley of Beracah", "Valley of Achor", "Valley of Siddim", "Valley of Elah"),
        optionsMl = listOf("ബെരാഖാ താഴ്വര", "ആഖോർ താഴ്വര", "സിദ്ദീം താഴ്വര", "ഏലാ താഴ്വര"),
        correctIndex = 0,
        verseRef = "2 Chronicles 20:26",
        verseRefMl = "2 ദിനവൃത്താന്തം 20:26"
    ),
    BibleQuizQuestion(
        id = 98, level = 9,
        question = "Which Israelite deliverer struck down 600 Philistines using an oxgoad?",
        questionMl = "ഉഴവുകോൽ (താരുക്കോൽ) കൊണ്ട് 600 ഫെലിസ്ത്യരെ സംഹരിച്ച യിസ്രായേൽ രക്ഷകൻ ആരായിരുന്നു?",
        options = listOf("Ibzan", "Shamgar", "Jair", "Elon"),
        optionsMl = listOf("ഇബ്സാൻ", "ശംഗർ", "യായീർ", "ഏലോൻ"),
        correctIndex = 1,
        verseRef = "Judges 3:31",
        verseRefMl = "ന്യായാധിപന്മാർ 3:31"
    ),
    BibleQuizQuestion(
        id = 99, level = 9,
        question = "Who was the Ephesian silversmith who sparked a citywide theater riot over Paul's impact on Artemis shrines?",
        questionMl = "അർത്തെമിസ് ക്ഷേത്രക്കച്ചവടത്തിന് നഷ്ടമുണ്ടാകുന്നു എന്ന് ആരോപിച്ച് എഫെസൊസിൽ കലാപം ഇളക്കിവിട്ട തട്ടാൻ ആര്?",
        options = listOf("Alexander", "Sceva", "Demetrius", "Gaius"),
        optionsMl = listOf("അലെക്സാന്ദർ", "സ്കേവാ", "ദേമേത്രിയൊസ്", "ഗായൊസ്"),
        correctIndex = 2,
        verseRef = "Acts 19:24",
        verseRefMl = "പ്രവൃത്തികൾ 19:24"
    ),
    BibleQuizQuestion(
        id = 100, level = 9,
        question = "Which priest of Bethel ordered the prophet Amos to flee to Judah and never prophesy again at the king's shrine?",
        questionMl = "രാജാവിന്റെ വിശുദ്ധമന്ദിരത്തിൽ ഇനി പ്രവചിക്കരുതെന്നും യെഹൂദയിലേക്ക് ഓടിപ്പോകണമെന്നും ആമോസിനോട് കല്പിച്ച ബെഥേൽ പുരോഹിതൻ ആര്?",
        options = listOf("Pashhur", "Hananiah", "Azariah", "Amaziah"),
        optionsMl = listOf("പശ്ഹൂർ", "ഹനന്യാവ്", "അസര്യാവ്", "അമസ്യാവ്"),
        correctIndex = 3,
        verseRef = "Amos 7:10-13",
        verseRefMl = "ആമോസ് 7:10-13"
    ),
    BibleQuizQuestion(
        id = 101, level = 9,
        question = "Which false prophet shattered Jeremiah's wooden yoke and died within the year just as Jeremiah foretold?",
        questionMl = "യിരെമ്യാവിന്റെ കഴുത്തിലെ മരനുകം ഒടിച്ചുകളയുകയും പ്രവചിക്കപ്പെട്ടതുപോലെ ആ വർഷം തന്നെ മരിക്കുകയും ചെയ്ത കള്ളപ്രവാചകൻ ആര്?",
        options = listOf("Hananiah of Gibeon", "Shemaiah the Nehelamite", "Pashhur son of Immer", "Ahab son of Kolaiah"),
        optionsMl = listOf("ഗിബെയോന്യനായ ഹനന്യാവ്", "നെഹെലാമ്യനായ ശെമയ്യാവ്", "ഇമ്മേരിന്റെ മകൻ പശ്ഹൂർ", "കോലായാവിന്റെ മകൻ ആഹാബ്"),
        correctIndex = 0,
        verseRef = "Jeremiah 28:10-17",
        verseRefMl = "യിരെമ്യാവ് 28:10-17"
    ),
    BibleQuizQuestion(
        id = 102, level = 9,
        question = "Which brief New Testament epistle rebukes Diotrephes for loving to be first and refusing traveling brothers?",
        questionMl = "മുമ്പനാകുവാൻ ആഗ്രഹിക്കുകയും സഹോദരന്മാരെ കൈക്കൊള്ളാതിരിക്കുകയും ചെയ്യുന്ന ദിയൊത്രെഫെസിനെ ശാസിക്കുന്ന പുതിയ നിയമ ലേഖനം ഏത്?",
        options = listOf("2 John", "3 John", "Jude", "Philemon"),
        optionsMl = listOf("2 യോഹന്നാൻ", "3 യോഹന്നാൻ", "യൂദാ", "ഫിലേമോൻ"),
        correctIndex = 1,
        verseRef = "3 John 1:9-10",
        verseRefMl = "3 യോഹന്നാൻ 1:9-10"
    ),
    BibleQuizQuestion(
        id = 103, level = 9,
        question = "Which Cushite palace official rescued Jeremiah from starving in a muddy cistern using old rags as padding?",
        questionMl = "പഴന്തുണികൾ ഉപയോഗിച്ച് ചെളിക്കുണ്ടിൽ നിന്ന് യിരെമ്യാവിനെ വലിച്ചുകയറ്റി രക്ഷിച്ച കൂശ്യനായ രാജഭൃത്യൻ ആര്?",
        options = listOf("Ahikam", "Baruch", "Ebed-Melek", "Gedaliah"),
        optionsMl = listOf("അഹീക്കാം", "ബാരൂക്ക്", "ഏബെദ്-മേലെക്ക്", "ഗെദല്യാവ്"),
        correctIndex = 2,
        verseRef = "Jeremiah 38:7-13",
        verseRefMl = "യിരെമ്യാവ് 38:7-13"
    ),
    BibleQuizQuestion(
        id = 104, level = 9,
        question = "Which surviving son of King Saul did commander Abner make king over Israel at Mahanaim?",
        questionMl = "ശൗലിന്റെ മരണശേഷം സൈന്യാധിപനായ അബ്നേർ മഹനയീമിൽ വെച്ച് യിസ്രായേലിന് രാജാവാക്കിയ ശൗലിന്റെ മകൻ ആര്?",
        options = listOf("Malchi-Shua", "Abinadab", "Mephibosheth", "Ish-Bosheth"),
        optionsMl = listOf("മൽക്കീശൂവ", "അബീനാദാബ്", "മെഫീബോശെത്ത്", "ഈശ്-ബോശെത്ത്"),
        correctIndex = 3,
        verseRef = "2 Samuel 2:8-10",
        verseRefMl = "2 ശമൂവേൽ 2:8-10"
    ),
    BibleQuizQuestion(
        id = 105, level = 9,
        question = "Who were the three formidable warrior sons of David's sister Zeruiah?",
        questionMl = "ദാവീദിന്റെ സഹോദരിയായ സെരൂയയുടെ ധീരയോദ്ധാക്കളായ മൂന്ന് പുത്രന്മാർ ആരൊക്കെയായിരുന്നു?",
        options = listOf("Joab, Abishai, and Asahel", "Abner, Amasa, and Asahel", "Benaiah, Eleazar, and Shammah", "Ira, Heleb, and Abiezer"),
        optionsMl = listOf("യോവാബ്, അബീശായി, അസാഹേൽ", "അബ്നേർ, അമാസാ, അസാഹേൽ", "ബെനായാവ്, എലെയാസാർ, ശമ്മാ", "ഈരാ, ഹേലെബ്, അബീയേസെർ"),
        correctIndex = 0,
        verseRef = "1 Chronicles 2:16",
        verseRefMl = "1 ദിനവൃത്താന്തം 2:16"
    ),
    BibleQuizQuestion(
        id = 106, level = 9,
        question = "Which of the seven churches in Revelation is praised for remaining faithful 'where Satan has his throne'?",
        questionMl = "'സാത്താന്റെ സിംഹാസനം ഉള്ളിടത്ത്' വിശ്വസ്തതയോടെ നിലനിന്നതായി വെളിപ്പാടിൽ പ്രശംസിക്കപ്പെടുന്ന സഭ ഏത്?",
        options = listOf("Smyrna", "Pergamum", "Philadelphia", "Ephesus"),
        optionsMl = listOf("സ്മുർന്നാ", "പെർഗ്ഗമൊസ്", "ഫിലദെൽഫ്യ", "എഫെസൊസ്"),
        correctIndex = 1,
        verseRef = "Revelation 2:13",
        verseRefMl = "വെളിപ്പാട് 2:13"
    ),
    BibleQuizQuestion(
        id = 107, level = 9,
        question = "Who secretly hid infant prince Joash for six years in a temple storeroom from Queen Athaliah's massacre?",
        questionMl = "അഥല്യയുടെ കൂട്ടക്കൊലയിൽ നിന്ന് രക്ഷിക്കാൻ ശിശുവായ യോവാശ് രാജകുമാരനെ ദൈവാലയത്തിൽ ആറ് വർഷം ഒളിപ്പിച്ചത് ആര്?",
        options = listOf("Huldah", "Tamar daughter-in-law of Jehoiada", "Jehosheba (daughter of King Joram)", "Rizpah"),
        optionsMl = listOf("ഹുൽദാ", "താമാർ", "യെഹോശെബ (യോരാം രാജാവിന്റെ മകൾ)", "രിസ്പാ"),
        correctIndex = 2,
        verseRef = "2 Kings 11:2",
        verseRefMl = "2 രാജാക്കന്മാർ 11:2"
    ),
    BibleQuizQuestion(
        id = 108, level = 9,
        question = "In Romans 16:7, whom does Paul honor as his fellow prisoners, 'outstanding among the apostles', who were in Christ before him?",
        questionMl = "റോമർ 16:7-ൽ തനിക്ക് മുമ്പേ ക്രിസ്തുവിലായിരുന്നവരും 'അപ്പൊസ്തലന്മാരുടെ ഇടയിൽ പ്രശസ്തരും' സഹതടവുകാരുമായി പൗലോസ് വന്ദനം ചൊല്ലുന്നത് ആരോട്?",
        options = listOf("Aquila and Priscilla", "Tryphena and Tryphosa", "Asyncritus and Phlegon", "Andronicus and Junia"),
        optionsMl = listOf("അക്വിലാസ്, പ്രിസ്കില്ല", "ത്രുഫേനാ, ത്രുഫോസാ", "അസുൻക്രിതൊസ്, ഫ്ലെഗോൻ", "അന്ത്രൊനീക്കൊസ്, യൂനിയ"),
        correctIndex = 3,
        verseRef = "Romans 16:7",
        verseRefMl = "റോമർ 16:7"
    ),

    // ==================== LEVEL 10: OMEGA GRANDMASTER (ULTRA HARD - 10s) ====================
    BibleQuizQuestion(
        id = 109, level = 10,
        question = "In Numbers 27:1, what are the EXACT five names of Zelophehad's daughters who petitioned Moses for an inheritance?",
        questionMl = "സംഖ്യാപുസ്തകം 27:1 പ്രകാരം അവകാശത്തിനായി മോശെയോട് അപേക്ഷിച്ച സെലോഫെഹാദിന്റെ അഞ്ച് പുത്രിമാരുടെ കൃത്യമായ പേരുകൾ ഏവ?",
        options = listOf(
            "Mahlah, Noah, Hoglah, Milkah, and Tirzah",
            "Puah, Shiphrah, Hoglah, Milkah, and Abigail",
            "Mahlah, Basemath, Noah, Oholibamah, and Tirzah",
            "Adah, Zillah, Hoglah, Cozbi, and Milkah"
        ),
        optionsMl = listOf(
            "മഹ്ലാ, നോവാ, ഹൊഗ്ലാ, മിൽക്കാ, തിർസാ",
            "പൂവാ, ശിഫ്രാ, ഹൊഗ്ലാ, മിൽക്കാ, അബീഗയിൽ",
            "മഹ്ലാ, ബാസമത്ത്, നോവാ, ഒഹൊലീബാമാ, തിർസാ",
            "ആദാ, സില്ലാ, ഹൊഗ്ലാ, കൊസ്ബി, മിൽക്കാ"
        ),
        correctIndex = 0,
        verseRef = "Numbers 27:1",
        verseRefMl = "സംഖ്യാപുസ്തകം 27:1"
    ),
    BibleQuizQuestion(
        id = 110, level = 10,
        question = "In Revelation 21:20, which gemstone constitutes the TWELFTH (final) foundation of the wall of the New Jerusalem?",
        questionMl = "വെളിപ്പാട് 21:20 പ്രകാരം പുതിയ യെരൂശലേം മതിലിന്റെ പന്ത്രണ്ടാമത്തെ (അവസാനത്തെ) അടിസ്ഥാനരത്നം ഏതാണ്?",
        options = listOf("Chrysoprase", "Amethyst", "Jacinth", "Sardonyx"),
        optionsMl = listOf("ക്രുസൊപ്രാസൊസ്", "സുഗന്ധിമണി (അമെഥിസ്റ്റ്)", "പദ്മരാഗം (ഹുകിന്തൊസ്)", "ഗോമേദകം"),
        correctIndex = 1,
        verseRef = "Revelation 21:19-20",
        verseRefMl = "വെളിപ്പാട് 21:19-20"
    ),
    BibleQuizQuestion(
        id = 111, level = 10,
        question = "In the 22 Hebrew-letter acrostic structure of Psalm 119 (176 verses), exactly how many verses belong to each stanza?",
        questionMl = "22 എബ്രായ അക്ഷരമാല ക്രമത്തിലുള്ള 119-ാം സങ്കീർത്തനത്തിലെ (176 വാക്യങ്ങൾ) ഓരോ ഖണ്ഡത്തിലും കൃത്യമായി എത്ര വാക്യങ്ങളുണ്ട്?",
        options = listOf("6 verses", "10 verses", "8 verses", "12 verses"),
        optionsMl = listOf("6 വാക്യങ്ങൾ", "10 വാക്യങ്ങൾ", "8 വാക്യങ്ങൾ", "12 വാക്യങ്ങൾ"),
        correctIndex = 2,
        verseRef = "Psalm 119 (22 × 8 = 176)",
        verseRefMl = "സങ്കീർത്തനം 119 (22 × 8 = 176)"
    ),
    BibleQuizQuestion(
        id = 112, level = 10,
        question = "In Jeremiah 36:25, which THREE officials pleaded with King Jehoiakim NOT to burn Jeremiah's scroll in the brazier?",
        questionMl = "യിരെമ്യാവ് 36:25-ൽ യെഹോയാക്കീം രാജാവിനോട് ചുരുൾ ചുട്ടുകളയരുതേ എന്ന് അപേക്ഷിച്ച മൂന്ന് പ്രഭുക്കന്മാർ ആരൊക്കെയായിരുന്നു?",
        options = listOf(
            "Gedaliah, Ishmael, and Johanan",
            "Pashhur, Zephaniah, and Seraiah",
            "Ahikam, Shaphan, and Hilkiah",
            "Elnathan, Delaiah, and Gemariah"
        ),
        optionsMl = listOf(
            "ഗെദല്യാവ്, യിശ്മായേൽ, യോഹാനാൻ",
            "പശ്ഹൂർ, സെഫന്യാവ്, സെരായാവ്",
            "അഹീക്കാം, ശാഫാൻ, ഹിൽക്കീയാവ്",
            "എൽനാഥാൻ, ദെലായാവ്, ഗെമര്യാവ്"
        ),
        correctIndex = 3,
        verseRef = "Jeremiah 36:25",
        verseRefMl = "യിരെമ്യാവ് 36:25"
    ),
    BibleQuizQuestion(
        id = 113, level = 10,
        question = "What were the exact Aramaic words inscribed by the disembodied hand on King Belshazzar's palace wall in Daniel 5:25?",
        questionMl = "ദാനീയേൽ 5:25 പ്രകാരം ബേൽശസ്സർ രാജാവിന്റെ അരമന ഭിത്തിയിൽ കൈവിരലുകൾ എഴുതിയ കൃത്യമായ അരമായ വാചകം ഏത്?",
        options = listOf(
            "MENE, MENE, TEKEL, PARSIN",
            "TEKEL, PARSIN, TEKEL, SELAH",
            "PERES, MENE, SHALOM, TEKEL",
            "KADOSH, MENE, ADONAI, PERES"
        ),
        optionsMl = listOf(
            "മേനേ, മേനേ, തേക്കേൽ, ഉഫർസീൻ (പർസീൻ)",
            "തേക്കേൽ, പർസീൻ, തേക്കേൽ, സേലാ",
            "പേരെസ്, മേനേ, ശാലോം, തേക്കേൽ",
            "കാദോശ്, മേനേ, അദോനായ്, പേരെസ്"
        ),
        correctIndex = 0,
        verseRef = "Daniel 5:25",
        verseRefMl = "ദാനീയേൽ 5:25"
    ),
    BibleQuizQuestion(
        id = 114, level = 10,
        question = "Which twin son of Judah and Tamar actually emerged FIRST at birth despite a scarlet thread already tied to Zerah's wrist?",
        questionMl = "സേരഹിന്റെ കൈയിൽ ചുവപ്പുനൂൽ കെട്ടിയിരുന്നിട്ടും ഉല്പത്തി 38-ൽ ആദ്യം പുറത്തു വന്ന യെഹൂദായുടെ ഇരട്ടക്കുട്ടി ആര്?",
        options = listOf("Er", "Perez", "Onan", "Shelah"),
        optionsMl = listOf("ഏർ", "പേരെസ്", "ഓനാൻ", "ശേലാ"),
        correctIndex = 1,
        verseRef = "Genesis 38:27-30",
        verseRefMl = "ഉല്പത്തി 38:27-30"
    ),
    BibleQuizQuestion(
        id = 115, level = 10,
        question = "In Acts 28:11, what twin figurehead adorned the Alexandrian ship that carried Paul away from Malta toward Rome?",
        questionMl = "പ്രവൃത്തികൾ 28:11 പ്രകാരം മെലിത്തയിൽ നിന്ന് പൗലോസിനെ റോമിലേക്ക് കൊണ്ടുപോയ അലെക്സന്ത്രിയ കപ്പലിന്റെ അമരത്തുണ്ടായിരുന്ന ചിഹ്നം എന്ത്?",
        options = listOf("Zeus and Hermes", "Artemis and Apollo", "Castor and Pollux (Dioscuri)", "Poseidon and Triton"),
        optionsMl = listOf("സെയൂസ്, ഹെർമ്മേസ്", "അർത്തെമിസ്, അപ്പൊല്ലൊ", "മിഥുനദേവന്മാർ (കസ്തൊർ, പൊല്ലൂക്സ്)", "പൊസൈഡൺ, ട്രൈറ്റൺ"),
        correctIndex = 2,
        verseRef = "Acts 28:11",
        verseRefMl = "പ്രവൃത്തികൾ 28:11"
    ),
    BibleQuizQuestion(
        id = 116, level = 10,
        question = "What THREE sacred articles does Hebrews 9:4 declare were placed inside the Ark of the Covenant?",
        questionMl = "എബ്രായർ 9:4 പ്രകാരം നിയമപെട്ടകത്തിനുള്ളിൽ സൂക്ഷിച്ചിരുന്ന മൂന്ന് വിശുദ്ധ വസ്തുക്കൾ ഏതെല്ലാമാണ്?",
        options = listOf(
            "Two cherubim crowns, gold censer, and Law tablets",
            "Shewbread vessel, anointing horn, and David's sling",
            "Bronze serpent, golden ephod, and Urim & Thummim",
            "Gold jar of manna, Aaron's budded staff, and stone tablets"
        ),
        optionsMl = listOf(
            "രണ്ട് കെരൂബുകൾ, സ്വർണ്ണധൂപകലശം, കല്പലകകൾ",
            "കാഴ്ചയപ്പ പാത്രം, അഭിഷേകക്കൊമ്പ്, ദാവീദിന്റെ കവണ",
            "താമ്രസർപ്പം, സ്വർണ്ണ ഏഫോദ്, ഊരീം തുമ്മീം",
            "മന്നാ നിറച്ച സ്വർണ്ണക്കലശം, തളിർത്ത അഹരോന്റെ വടി, നിയമപലകകൾ"
        ),
        correctIndex = 3,
        verseRef = "Hebrews 9:4",
        verseRefMl = "എബ്രായർ 9:4"
    ),
    BibleQuizQuestion(
        id = 117, level = 10,
        question = "In Colossians 4:17, which specific individual did Paul urge: 'See to it that you complete the ministry you have received in the Lord'?",
        questionMl = "കൊലൊസ്സ്യർ 4:17-ൽ 'കർത്താവിൽ നിനക്കു ലഭിച്ച ശുശ്രൂഷ നിറവേറ്റുവാൻ നോക്കൂ' എന്ന് പൗലോസ് പ്രത്യേകം നിർദ്ദേശിച്ചത് ആരോടാണ്?",
        options = listOf("Archippus", "Nympha", "Epaphras", "Aristarchus"),
        optionsMl = listOf("അർഖിപ്പൊസ്", "നുംഫാ", "എപ്പഫ്രാസ്", "അരിസ്തർക്കൊസ്"),
        correctIndex = 0,
        verseRef = "Colossians 4:17",
        verseRefMl = "കൊലൊസ്സ്യർ 4:17"
    ),
    BibleQuizQuestion(
        id = 118, level = 10,
        question = "In Galatians 4:24-25, what geographic mountain and region does Paul equate allegorically with Hagar?",
        questionMl = "ഗലാത്യർ 4:24-25-ൽ പൗലോസ് ഹാഗാറിനെ പ്രതീകാത്മകമായി ഏത് പർവ്വതത്തോടും ദേശത്തോടുമാണ് താരതമ്യം ചെയ്യുന്നത്?",
        options = listOf("Mount Gerizim in Samaria", "Mount Sinai in Arabia", "Mount Seir in Edom", "Mount Hor on Moab's border"),
        optionsMl = listOf("ശമര്യയിലെ ഗെരിസീം പർവ്വതം", "അറബ്യയിലെ സീനായി പർവ്വതം", "എദോമിലെ സേയീർ പർവ്വതം", "മോവാബിലെ ഹോർ പർവ്വതം"),
        correctIndex = 1,
        verseRef = "Galatians 4:24-25",
        verseRefMl = "ഗലാത്യർ 4:24-25"
    ),
    BibleQuizQuestion(
        id = 119, level = 10,
        question = "After which terrifying northeaster storm wind was Paul's grain ship named as helplessly driven in Acts 27:14?",
        questionMl = "പ്രവൃത്തികൾ 27:14-ൽ പൗലോസ് സഞ്ചരിച്ചിരുന്ന കപ്പലിനെ അടിച്ചുനീക്കിയ ഭയങ്കര വടക്കുകിഴക്കൻ കൊടുങ്കാറ്റിന്റെ പേരെന്തായിരുന്നു?",
        options = listOf("Sirocco", "Boreas", "Euroaquilo (Eurakylon)", "Notus"),
        optionsMl = listOf("സിറോക്കോ", "ബൊറിയാസ്", "ഈരക്കുലോൻ (യൂറോക്ലിഡോൻ)", "നോത്തൊസ്"),
        correctIndex = 2,
        verseRef = "Acts 27:14",
        verseRefMl = "പ്രവൃത്തികൾ 27:14"
    ),
    BibleQuizQuestion(
        id = 120, level = 10,
        question = "Which longest-reigning king of Judah was hauled to Babylon with a hook in his nose before repenting and regaining his throne?",
        questionMl = "മൂക്കിൽ ചൂണ്ടയിട്ട് ബാബേലിലേക്ക് കൊണ്ടുപോകപ്പെട്ട ശേഷം അനുതപിച്ചപ്പോൾ സിംഹാസനം തിരികെ ലഭിച്ച ഏറ്റവും കൂടുതൽ കാലം ഭരിച്ച യെഹൂദാ രാജാവ് ആര്?",
        options = listOf("Amon", "Ahaz", "Jehoiachin", "Manasseh"),
        optionsMl = listOf("ആമോൻ", "ആഹാസ്", "യെഹോയാഖീൻ", "മനശ്ശെ"),
        correctIndex = 3,
        verseRef = "2 Chronicles 33:11-13",
        verseRefMl = "2 ദിനവൃത്താന്തം 33:11-13"
    )
)
