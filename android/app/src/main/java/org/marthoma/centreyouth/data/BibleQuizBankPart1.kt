package org.marthoma.centreyouth.data

import org.marthoma.centreyouth.data.model.BibleQuizQuestion

/**
 * Levels 1 to 5 (Questions 1..60) — Bilingual (English + Sathyavedapusthakam Malayalam)
 */
internal val quizQuestionsPart1: List<BibleQuizQuestion> = listOf(
    // ==================== LEVEL 1: GENESIS DAWN (Easy - 25s) ====================
    BibleQuizQuestion(
        id = 1, level = 1,
        question = "What is the very first book of the Bible?",
        questionMl = "ബൈബിളിലെ ഏറ്റവും ആദ്യത്തെ പുസ്തകം ഏതാണ്?",
        options = listOf("Exodus", "Genesis", "Matthew", "Psalms"),
        optionsMl = listOf("പുറപ്പാട്", "ഉല്പത്തി", "മത്തായി", "സങ്കീർത്തനങ്ങൾ"),
        correctIndex = 1,
        verseRef = "Genesis 1:1",
        verseRefMl = "ഉല്പത്തി 1:1"
    ),
    BibleQuizQuestion(
        id = 2, level = 1,
        question = "In which town was Jesus Christ born?",
        questionMl = "യേശുക്രിസ്തു ഏത് പട്ടണത്തിലാണ് ജനിച്ചത്?",
        options = listOf("Bethlehem", "Nazareth", "Jerusalem", "Capernaum"),
        optionsMl = listOf("ബേത്‌ലഹേം", "നസറെത്ത്", "യെരൂശലേം", "കഫർന്നഹൂം"),
        correctIndex = 0,
        verseRef = "Matthew 2:1",
        verseRefMl = "മത്തായി 2:1"
    ),
    BibleQuizQuestion(
        id = 3, level = 1,
        question = "Who built a massive wooden ark to survive the great flood?",
        questionMl = "മഹാപ്രളയത്തിൽ നിന്ന് രക്ഷപ്പെടാൻ വലിയൊരു പെട്ടകം പണിതത് ആരാണ്?",
        options = listOf("Moses", "Abraham", "Noah", "Jacob"),
        optionsMl = listOf("മോശെ", "അബ്രാഹാം", "നോഹ", "യാക്കോബ്"),
        correctIndex = 2,
        verseRef = "Genesis 6:14",
        verseRefMl = "ഉല്പത്തി 6:14"
    ),
    BibleQuizQuestion(
        id = 4, level = 1,
        question = "How many close Apostles did Jesus appoint as His inner Twelve?",
        questionMl = "യേശു തന്റെ അടുത്ത പന്ത്രണ്ട് അപ്പൊസ്തലന്മാരായി എത്ര ശിഷ്യന്മാരെ തിരഞ്ഞെടുത്തു?",
        options = listOf("7", "10", "40", "12"),
        optionsMl = listOf("7", "10", "40", "12"),
        correctIndex = 3,
        verseRef = "Matthew 10:1",
        verseRefMl = "മത്തായി 10:1"
    ),
    BibleQuizQuestion(
        id = 5, level = 1,
        question = "Which young shepherd defeated the Philistine giant Goliath with a sling?",
        questionMl = "കവണ ഉപയോഗിച്ച് ഫെലിസ്ത്യ മല്ലനായ ഗൊല്യാത്തിനെ തോൽപ്പിച്ച ബാലനായ ഇടയൻ ആര്?",
        options = listOf("David", "Solomon", "Saul", "Jonathan"),
        optionsMl = listOf("ദാവീദ്", "ശലോമോൻ", "ശൗൽ", "യോനാഥാൻ"),
        correctIndex = 0,
        verseRef = "1 Samuel 17:49",
        verseRefMl = "1 ശമൂവേൽ 17:49"
    ),
    BibleQuizQuestion(
        id = 6, level = 1,
        question = "Who stretched out his staff to part the waters of the Red Sea?",
        questionMl = "വടി നീട്ടി ചെങ്കടലിലെ വെള്ളത്തെ വിഭജിച്ചത് ആരാണ്?",
        options = listOf("Joshua", "Elijah", "Moses", "Aaron"),
        optionsMl = listOf("യോശുവ", "ഏലിയാവ്", "മോശെ", "അഹരോൻ"),
        correctIndex = 2,
        verseRef = "Exodus 14:21",
        verseRefMl = "പുറപ്പാട് 14:21"
    ),
    BibleQuizQuestion(
        id = 7, level = 1,
        question = "What is the final book of the New Testament?",
        questionMl = "പുതിയ നിയമത്തിലെ ഏറ്റവും അവസാനത്തെ പുസ്തകം ഏതാണ്?",
        options = listOf("Jude", "Revelation", "Acts", "Hebrews"),
        optionsMl = listOf("യൂദാ", "വെളിപ്പാട്", "പ്രവൃത്തികൾ", "എബ്രായർ"),
        correctIndex = 1,
        verseRef = "Revelation 1:1",
        verseRefMl = "വെളിപ്പാട് 1:1"
    ),
    BibleQuizQuestion(
        id = 8, level = 1,
        question = "Who was the earthly mother of Jesus?",
        questionMl = "യേശുവിന്റെ ഭൂമിയിലെ മാതാവ് ആരായിരുന്നു?",
        options = listOf("Elizabeth", "Martha", "Sarah", "Mary"),
        optionsMl = listOf("എലീശബെത്ത്", "മാർത്ത", "സാറാ", "മറിയ"),
        correctIndex = 3,
        verseRef = "Luke 1:30-31",
        verseRefMl = "ലൂക്കൊസ് 1:30-31"
    ),
    BibleQuizQuestion(
        id = 9, level = 1,
        question = "On which day of Creation did God rest and sanctify it?",
        questionMl = "സൃഷ്ടിയുടെ ഏത് ദിവസമാണ് ദൈവം വിശ്രമിക്കുകയും അതിനെ ശുദ്ധീകരിക്കുകയും ചെയ്തത്?",
        options = listOf("Fifth day", "Sixth day", "Seventh day", "First day"),
        optionsMl = listOf("അഞ്ചാം ദിവസം", "ആറാം ദിവസം", "ഏഴാം ദിവസം", "ഒന്നാം ദിവസം"),
        correctIndex = 2,
        verseRef = "Genesis 2:2-3",
        verseRefMl = "ഉല്പത്തി 2:2-3"
    ),
    BibleQuizQuestion(
        id = 10, level = 1,
        question = "How many days and nights did Jesus fast in the wilderness?",
        questionMl = "യേശു മരുഭൂമിയിൽ എത്ര രാവും പകലും ഉപവസിച്ചു?",
        options = listOf("40 days", "14 days", "30 days", "7 days"),
        optionsMl = listOf("40 ദിവസം", "14 ദിവസം", "30 ദിവസം", "7 ദിവസം"),
        correctIndex = 0,
        verseRef = "Matthew 4:2",
        verseRefMl = "മത്തായി 4:2"
    ),
    BibleQuizQuestion(
        id = 11, level = 1,
        question = "Who betrayed Jesus to the chief priests for thirty pieces of silver?",
        questionMl = "മുപ്പത് വെള്ളിക്കാശിന് യേശുവിനെ മഹാപുരോഹിതന്മാർക്ക് ഒറ്റിക്കൊടുത്തത് ആര്?",
        options = listOf("Thomas", "Peter", "Philip", "Judas Iscariot"),
        optionsMl = listOf("തോമാസ്", "പത്രൊസ്", "ഫിലിപ്പൊസ്", "യൂദാ ഇസ്കരിയോത്താ"),
        correctIndex = 3,
        verseRef = "Matthew 26:14-15",
        verseRefMl = "മത്തായി 26:14-15"
    ),
    BibleQuizQuestion(
        id = 12, level = 1,
        question = "What swallowed Jonah for three days and three nights when he fled Tarshish-bound?",
        questionMl = "തർശീശിലേക്ക് ഓടിപ്പോയ യോനായെ മൂന്ന് രാവും പകലും വിഴുങ്ങിയത് എന്ത്?",
        options = listOf("A crocodile", "A great fish", "A sea dragon", "An eagle"),
        optionsMl = listOf("ഒരു മുതല", "ഒരു മഹാമത്സ്യം", "ഒരു കടൽസർപ്പം", "ഒരു കഴുകൻ"),
        correctIndex = 1,
        verseRef = "Jonah 1:17",
        verseRefMl = "യോനാ 1:17"
    ),

    // ==================== LEVEL 2: ARK & MIRACLES (Easy+ - 24s) ====================
    BibleQuizQuestion(
        id = 13, level = 2,
        question = "What was Jesus' very first recorded miracle in the Gospel of John?",
        questionMl = "യോഹന്നാന്റെ സുവിശേഷത്തിൽ രേഖപ്പെടുത്തിയ യേശുവിന്റെ ആദ്യത്തെ അത്ഭുതം ഏതാണ്?",
        options = listOf("Turning water into wine", "Walking on water", "Healing a blind man", "Raising Lazarus"),
        optionsMl = listOf("വെള്ളത്തെ വീഞ്ഞാക്കി മാറ്റി", "വെള്ളത്തിന്മീതെ നടന്നു", "അന്ധനെ സൗഖ്യമാക്കി", "ലാസറിനെ ഉയിർപ്പിച്ചു"),
        correctIndex = 0,
        verseRef = "John 2:11",
        verseRefMl = "യോഹന്നാൻ 2:11"
    ),
    BibleQuizQuestion(
        id = 14, level = 2,
        question = "Who climbed a sycamore-fig tree in Jericho to see Jesus pass by?",
        questionMl = "യേശു കടന്നുപോകുന്നത് കാണാൻ യെരീഹോവിൽ കാട്ടത്തിമരത്തിൽ കയറിയത് ആര്?",
        options = listOf("Nicodemus", "Bartimaeus", "Zacchaeus", "Lazarus"),
        optionsMl = listOf("നിക്കൊദേമൊസ്", "ബർതിമായി", "സക്കായി", "ലാസർ"),
        correctIndex = 2,
        verseRef = "Luke 19:4",
        verseRefMl = "ലൂക്കൊസ് 19:4"
    ),
    BibleQuizQuestion(
        id = 15, level = 2,
        question = "Which prophet survived a night unharmed inside a den of lions?",
        questionMl = "സിംഹക്കൂട്ടിൽ ഒരു രാത്രി പരിക്കില്ലാതെ രക്ഷപ്പെട്ട പ്രവാചകൻ ആരാണ്?",
        options = listOf("Isaiah", "Daniel", "Jeremiah", "Ezekiel"),
        optionsMl = listOf("യെശയ്യാവ്", "ദാനീയേൽ", "യിരെമ്യാവ്", "യെഹെസ്കേൽ"),
        correctIndex = 1,
        verseRef = "Daniel 6:22",
        verseRefMl = "ദാനീയേൽ 6:22"
    ),
    BibleQuizQuestion(
        id = 16, level = 2,
        question = "Which king of Israel asked God for a discerning heart of wisdom rather than riches?",
        questionMl = "സമ്പത്തിന് പകരം വിവേകമുള്ള ഹൃദയത്തിനായി ദൈവത്തോട് അപേക്ഷിച്ച യിസ്രായേൽ രാജാവ് ആര്?",
        options = listOf("Saul", "Hezekiah", "David", "Solomon"),
        optionsMl = listOf("ശൗൽ", "ഹിസ്കീയാവ്", "ദാവീദ്", "ശലോമോൻ"),
        correctIndex = 3,
        verseRef = "1 Kings 3:9-12",
        verseRefMl = "1 രാജാക്കന്മാർ 3:9-12"
    ),
    BibleQuizQuestion(
        id = 17, level = 2,
        question = "With how many loaves and fish did Jesus feed the crowd of 5,000 men?",
        questionMl = "അയ്യായിരം പുരുഷന്മാരുടെ പുരുഷാരത്തെ യേശു എത്ര അപ്പവും മീനും കൊണ്ട് പോഷിപ്പിച്ചു?",
        options = listOf("7 loaves and 3 fish", "5 loaves and 2 fish", "3 loaves and 5 fish", "12 loaves and 2 fish"),
        optionsMl = listOf("7 അപ്പവും 3 മീനും", "5 അപ്പവും 2 മീനും", "3 അപ്പവും 5 മീനും", "12 അപ്പവും 2 മീനും"),
        correctIndex = 1,
        verseRef = "Matthew 14:17-21",
        verseRefMl = "മത്തായി 14:17-21"
    ),
    BibleQuizQuestion(
        id = 18, level = 2,
        question = "What sign did God set in the clouds as His covenant never again to flood the whole earth?",
        questionMl = "ഇനി ഒരിക്കലും ഭൂമിയെ പ്രളയം കൊണ്ട് നശിപ്പിക്കില്ലെന്ന ഉടമ്പടിയുടെ അടയാളമായി ദൈവം മേഘങ്ങളിൽ വെച്ചത് എന്ത്?",
        options = listOf("A rainbow", "A fiery pillar", "A white dove", "Seven stars"),
        optionsMl = listOf("മഴവില്ല്", "അഗ്നിസ്തംഭം", "വെള്ളപ്രാവ്", "ഏഴ് നക്ഷത്രങ്ങൾ"),
        correctIndex = 0,
        verseRef = "Genesis 9:13",
        verseRefMl = "ഉല്പത്തി 9:13"
    ),
    BibleQuizQuestion(
        id = 19, level = 2,
        question = "Which mighty Israelite judge kept his supernatural strength as long as his hair remained uncut?",
        questionMl = "തലമുടി മുറിക്കാതെയിരുന്ന കാലത്തോളം അമാനുഷിക ബലം സൂക്ഷിച്ചിരുന്ന ശക്തനായ യിസ്രായേൽ ന്യായാധിപൻ ആര്?",
        options = listOf("Gideon", "Jephthah", "Ehud", "Samson"),
        optionsMl = listOf("ഗിദെയോൻ", "യിഫ്താഹ്", "ഏഹൂദ്", "ശിംശോൻ"),
        correctIndex = 3,
        verseRef = "Judges 16:17",
        verseRefMl = "ന്യായാധിപന്മാർ 16:17"
    ),
    BibleQuizQuestion(
        id = 20, level = 2,
        question = "Who denied knowing Jesus three times before the rooster crowed twice?",
        questionMl = "കോഴി രണ്ടു പ്രാവശ്യം കൂവുന്നതിന് മുമ്പ് യേശുവിനെ അറിയില്ലെന്ന് മൂന്നു പ്രാവശ്യം തള്ളിപ്പറഞ്ഞത് ആര്?",
        options = listOf("Andrew", "John", "Simon Peter", "James"),
        optionsMl = listOf("അന്ത്രെയാസ്", "യോഹന്നാൻ", "ശിമോൻ പത്രൊസ്", "യാക്കോബ്"),
        correctIndex = 2,
        verseRef = "Mark 14:72",
        verseRefMl = "മർക്കൊസ് 14:72"
    ),
    BibleQuizQuestion(
        id = 21, level = 2,
        question = "Whom did Jesus raise from the dead in Bethany after four days in the tomb?",
        questionMl = "കല്ലറയിൽ വെച്ച് നാലു ദിവസമായ ശേഷം ബെഥാന്യയിൽ യേശു മരിച്ചവരിൽ നിന്ന് ഉയിർപ്പിച്ചത് ആരെ?",
        options = listOf("Lazarus", "Stephen", "Jairus", "Eutychus"),
        optionsMl = listOf("ലാസർ", "സ്തെഫാനൊസ്", "യായീറൊസ്", "യൂത്തിക്കൊസ്"),
        correctIndex = 0,
        verseRef = "John 11:39-44",
        verseRefMl = "യോഹന്നാൻ 11:39-44"
    ),
    BibleQuizQuestion(
        id = 22, level = 2,
        question = "On which mountain did Moses receive the Ten Commandments inscribed on stone?",
        questionMl = "കല്പലകകളിൽ എഴുതിയ പത്തു കല്പനകൾ മോശെയ്ക്ക് ലഭിച്ചത് ഏത് പർവ്വതത്തിൽ വെച്ചാണ്?",
        options = listOf("Mount Carmel", "Mount Sinai", "Mount Tabor", "Mount Nebo"),
        optionsMl = listOf("കർമ്മേൽ പർവ്വതം", "സീനായി പർവ്വതം", "താബോർ പർവ്വതം", "നെബോ പർവ്വതം"),
        correctIndex = 1,
        verseRef = "Exodus 19:20",
        verseRefMl = "പുറപ്പാട് 19:20"
    ),
    BibleQuizQuestion(
        id = 23, level = 2,
        question = "What gift did Jacob give his favored son Joseph that sparked his brothers' jealousy?",
        questionMl = "സഹോദരന്മാരുടെ അസൂയയ്ക്ക് കാരണമായി യാക്കോബ് തന്റെ പ്രിയപുത്രനായ യോസേഫിന് നൽകിയ സമ്മാനം എന്ത്?",
        options = listOf("A silver goblet", "An ornate coat of many colors", "A golden harp", "A royal signet ring"),
        optionsMl = listOf("വെള്ളിക്കോപ്പ", "നാനാവർണ്ണമായൊരു മേലങ്കി", "സ്വർണ്ണക്കിന്നരം", "രാജമുദ്രയുള്ള മോതിരം"),
        correctIndex = 1,
        verseRef = "Genesis 37:3",
        verseRefMl = "ഉല്പത്തി 37:3"
    ),
    BibleQuizQuestion(
        id = 24, level = 2,
        question = "Into which younger brother's grain sack did Joseph have his silver cup placed?",
        questionMl = "ഏത് ഇളയ സഹോദരന്റെ ധാന്യച്ചാക്കിലാണ് യോസേഫ് തന്റെ വെള്ളിപ്പാനപാത്രം ഒളിപ്പിച്ചു വെച്ചത്?",
        options = listOf("Reuben", "Judah", "Levi", "Benjamin"),
        optionsMl = listOf("രൂബേൻ", "യെഹൂദാ", "ലേവി", "ബെന്യാമീൻ"),
        correctIndex = 3,
        verseRef = "Genesis 44:2, 12",
        verseRefMl = "ഉല്പത്തി 44:2, 12"
    ),

    // ==================== LEVEL 3: FAITHFUL WITNESS (Normal - 22s) ====================
    BibleQuizQuestion(
        id = 25, level = 3,
        question = "Around what city's fortified walls did Joshua's army march before they collapsed?",
        questionMl = "ഏത് നഗരത്തിന്റെ ഉറപ്പുള്ള മതിലുകൾക്ക് ചുറ്റുമാണ് യോശുവയുടെ സൈന്യം അവ തകരുന്നതിന് മുമ്പ് പ്രദക്ഷിണം വെച്ചത്?",
        options = listOf("Jericho", "Ai", "Bethel", "Gibeon"),
        optionsMl = listOf("യെരീഹോ", "ഹായി", "ബെഥേൽ", "ഗിബെയോൻ"),
        correctIndex = 0,
        verseRef = "Joshua 6:15-20",
        verseRefMl = "യോശുവ 6:15-20"
    ),
    BibleQuizQuestion(
        id = 26, level = 3,
        question = "Which prophet ascended into heaven in a whirlwind with a chariot and horses of fire?",
        questionMl = "അഗ്നിരഥത്തിലും അഗ്നിഅശ്വങ്ങളിലും ചുഴലിക്കാറ്റിൽ സ്വർഗ്ഗത്തിലേക്ക് എടുക്കപ്പെട്ട പ്രവാചകൻ ആര്?",
        options = listOf("Elisha", "Samuel", "Elijah", "Nathan"),
        optionsMl = listOf("എലീശാ", "ശമൂവേൽ", "ഏലിയാവ്", "നാഥാൻ"),
        correctIndex = 2,
        verseRef = "2 Kings 2:11",
        verseRefMl = "2 രാജാക്കന്മാർ 2:11"
    ),
    BibleQuizQuestion(
        id = 27, level = 3,
        question = "Which Moabite widow told Naomi, 'Your people will be my people and your God my God'?",
        questionMl = "'നിന്റെ ജനം എന്റെ ജനം, നിന്റെ ദൈവം എന്റെ ദൈവം' എന്ന് നൊവൊമിയോട് പറഞ്ഞ മോവാബ്യ വിധവ ആര്?",
        options = listOf("Orpah", "Esther", "Hannah", "Ruth"),
        optionsMl = listOf("ഓർപ്പാ", "എസ്ഥേർ", "ഹന്നാ", "രൂത്ത്"),
        correctIndex = 3,
        verseRef = "Ruth 1:16",
        verseRefMl = "രൂത്ത് 1:16"
    ),
    BibleQuizQuestion(
        id = 28, level = 3,
        question = "What was the Apostle Paul's Jewish name before his missionary name Paul took prominence?",
        questionMl = "മിഷനറി നാമമായ പൗലോസ് പ്രചാരത്തിലാകുന്നതിന് മുമ്പ് അപ്പൊസ്തലനായ പൗലോസിന്റെ യഹൂദ നാമം എന്തായിരുന്നു?",
        options = listOf("Simon", "Saul", "Barnabas", "Silas"),
        optionsMl = listOf("ശിമോൻ", "ശൗൽ", "ബർന്നബാസ്", "ശീലാസ്"),
        correctIndex = 1,
        verseRef = "Acts 13:9",
        verseRefMl = "പ്രവൃത്തികൾ 13:9"
    ),
    BibleQuizQuestion(
        id = 29, level = 3,
        question = "Who was the first Christian martyr, stoned while praying 'Lord, do not hold this sin against them'?",
        questionMl = "'കർത്താവേ, ഈ പാപം അവരുടെ മേൽ ചുമത്തരുതേ' എന്ന് പ്രാർത്ഥിച്ചു കൊണ്ട് കല്ലേറ് കൊണ്ട് മരിച്ച ആദ്യത്തെ ക്രിസ്തീയ രക്തസാക്ഷി ആര്?",
        options = listOf("Stephen", "James", "Philip", "Barnabas"),
        optionsMl = listOf("സ്തെഫാനൊസ്", "യാക്കോബ്", "ഫിലിപ്പൊസ്", "ബർന്നബാസ്"),
        correctIndex = 0,
        verseRef = "Acts 7:59-60",
        verseRefMl = "പ്രവൃത്തികൾ 7:59-60"
    ),
    BibleQuizQuestion(
        id = 30, level = 3,
        question = "In the Fruit of the Spirit (Galatians 5), which comes FIRST in the list of nine?",
        questionMl = "ആത്മാവിന്റെ ഫലത്തിൽ (ഗലാത്യർ 5) ഒമ്പത് ഗുണങ്ങളുടെ പട്ടികയിൽ ഏറ്റവും ആദ്യം വരുന്നത് ഏതാണ്?",
        options = listOf("Joy", "Peace", "Love", "Kindness"),
        optionsMl = listOf("സന്തോഷം", "സമാധാനം", "സ്നേഹം", "ദയ"),
        correctIndex = 2,
        verseRef = "Galatians 5:22",
        verseRefMl = "ഗലാത്യർ 5:22"
    ),
    BibleQuizQuestion(
        id = 31, level = 3,
        question = "Which Jewish queen risked her life appearing uninvited before King Xerxes to save her nation?",
        questionMl = "തന്റെ ജനത്തെ രക്ഷിക്കാൻ അഹശ്വേരോശ് രാജാവിന്റെ മുന്നിൽ ക്ഷണിക്കപ്പെടാതെ ജീവൻ പണയപ്പെടുത്തി ചെന്ന യഹൂദ രാജ്ഞി ആര്?",
        options = listOf("Vashti", "Esther", "Bathsheba", "Deborah"),
        optionsMl = listOf("വശ്തി", "എസ്ഥേർ", "ബത്ത്-ശേബ", "ദെബോരാ"),
        correctIndex = 1,
        verseRef = "Esther 4:16",
        verseRefMl = "എസ്ഥേർ 4:16"
    ),
    BibleQuizQuestion(
        id = 32, level = 3,
        question = "According to 1 Corinthians 13:13, faith, hope, and love remain—which is the greatest?",
        questionMl = "1 കൊരിന്ത്യർ 13:13 പ്രകാരം വിശ്വാസം, പ്രത്യാശ, സ്നേഹം എന്നീ മൂന്നും നിലനിൽക്കുന്നു—ഇവയിൽ ഏറ്റവും വലിയത് ഏത്?",
        options = listOf("Faith", "Hope", "Peace", "Love"),
        optionsMl = listOf("വിശ്വാസം", "പ്രത്യാശ", "സമാധാനം", "സ്നേഹം"),
        correctIndex = 3,
        verseRef = "1 Corinthians 13:13",
        verseRefMl = "1 കൊരിന്ത്യർ 13:13"
    ),
    BibleQuizQuestion(
        id = 33, level = 3,
        question = "What were the Babylonian names of Daniel's three friends thrown into the fiery furnace?",
        questionMl = "തീച്ചൂളയിലേക്ക് എറിയപ്പെട്ട ദാനീയേലിന്റെ മൂന്ന് എബ്രായ സുഹൃത്തുക്കളുടെ ബാബിലോന്യ നാമങ്ങൾ എന്തൊക്കെയായിരുന്നു?",
        options = listOf("Shadrach, Meshach, Abednego", "Shem, Ham, Japheth", "Gad, Asher, Dan", "Benaiah, Zadok, Abiathar"),
        optionsMl = listOf("ശദ്രക്ക്, മേശക്ക്, അബേദ്-നെഗോ", "ശേം, ഹാം, യാഫെത്ത്", "ഗാദ്, ആശേർ, ദാൻ", "ബെനായാവ്, സാദോക്ക്, അബ്യാഥാർ"),
        correctIndex = 0,
        verseRef = "Daniel 3:19-20",
        verseRefMl = "ദാനീയേൽ 3:19-20"
    ),
    BibleQuizQuestion(
        id = 34, level = 3,
        question = "Which Apostle was a former tax collector seated at his booth in Capernaum when Jesus called him?",
        questionMl = "കഫർന്നഹൂമിൽ ചുങ്കസ്ഥലത്ത് ഇരിക്കുമ്പോൾ യേശു വിളിച്ച മുൻ ചുങ്കക്കാരനായ അപ്പൊസ്തലൻ ആര്?",
        options = listOf("Mark", "Luke", "Matthew", "Thaddaeus"),
        optionsMl = listOf("മർക്കൊസ്", "ലൂക്കൊസ്", "മത്തായി", "തദ്ദായി"),
        correctIndex = 2,
        verseRef = "Matthew 9:9",
        verseRefMl = "മത്തായി 9:9"
    ),
    BibleQuizQuestion(
        id = 35, level = 3,
        question = "Which disciple famously refused to believe Jesus rose until he touched His nail marks?",
        questionMl = "യേശുവിന്റെ കൈകളിലെ ആണിപ്പഴുതുകളിൽ തൊട്ടു കാണുന്നതുവരെ ഉയിർത്തെഴുന്നേല്പ് വിശ്വസിക്കാൻ വിസമ്മതിച്ച ശിഷ്യൻ ആര്?",
        options = listOf("Bartholomew", "Thomas", "Simon the Zealot", "Philip"),
        optionsMl = listOf("ബർത്തൊലൊമായി", "തോമാസ്", "തീവ്രവാദിയായ ശിമോൻ", "ഫിലിപ്പൊസ്"),
        correctIndex = 1,
        verseRef = "John 20:24-25",
        verseRefMl = "യോഹന്നാൻ 20:24-25"
    ),
    BibleQuizQuestion(
        id = 36, level = 3,
        question = "Who bathed in the Jordan River seven times to be healed of leprosy?",
        questionMl = "കുഷ്ഠരോഗത്തിൽ നിന്ന് സൗഖ്യമാകാൻ യോർദ്ദാൻ നദിയിൽ ഏഴു പ്രാവശ്യം മുങ്ങിക്കുളിച്ചത് ആര്?",
        options = listOf("Gehazi", "Ben-Hadad", "Hazael", "Naaman"),
        optionsMl = listOf("ഗേഹസി", "ബെൻ-ഹദദ്", "ഹസായേൽ", "നയമാൻ"),
        correctIndex = 3,
        verseRef = "2 Kings 5:14",
        verseRefMl = "2 രാജാക്കന്മാർ 5:14"
    ),

    // ==================== LEVEL 4: APOSTOLIC TRAIL (Moderate - 20s) ====================
    BibleQuizQuestion(
        id = 37, level = 4,
        question = "What manual trade did the Apostle Paul practice alongside Aquila and Priscilla in Corinth?",
        questionMl = "കൊരിന്തിൽ അക്വിലാസിനോടും പ്രിസ്കില്ലയോടും ഒപ്പം പൗലോസ് ചെയ്ത കൈത്തൊഴിൽ എന്തായിരുന്നു?",
        options = listOf("Fishing", "Carpentry", "Tentmaking", "Stone masonry"),
        optionsMl = listOf("മീൻപിടിത്തം", "തച്ചുപണി", "കൂടാരപ്പണി", "കല്പണി"),
        correctIndex = 2,
        verseRef = "Acts 18:3",
        verseRefMl = "പ്രവൃത്തികൾ 18:3"
    ),
    BibleQuizQuestion(
        id = 38, level = 4,
        question = "In which Syrian city were the disciples first called 'Christians'?",
        questionMl = "ഏത് സുറിയൻ നഗരത്തിലാണ് ശിഷ്യന്മാർ ആദ്യമായി 'ക്രിസ്ത്യാനികൾ' എന്ന് വിളിക്കപ്പെട്ടത്?",
        options = listOf("Antioch", "Damascus", "Jerusalem", "Ephesus"),
        optionsMl = listOf("അന്ത്യൊക്യ", "ദമസ്കൊസ്", "യെരൂശലേം", "എഫെസൊസ്"),
        correctIndex = 0,
        verseRef = "Acts 11:26",
        verseRefMl = "പ്രവൃത്തികൾ 11:26"
    ),
    BibleQuizQuestion(
        id = 39, level = 4,
        question = "Which Pharisee and member of the Sanhedrin visited Jesus by night to learn of rebirth?",
        questionMl = "വീണ്ടും ജനിക്കുന്നതിനെക്കുറിച്ച് പഠിക്കാൻ രാത്രിയിൽ യേശുവിന്റെ അടുക്കൽ വന്ന പരീശനും സൻഹെദ്രിൻ അംഗവുമായ വ്യക്തി ആര്?",
        options = listOf("Caiaphas", "Gamaliel", "Nicodemus", "Joseph of Arimathea"),
        optionsMl = listOf("കയ്യഫാസ്", "ഗമാലിയേൽ", "നിക്കൊദേമൊസ്", "അരിമഥ്യയിലെ യോസേഫ്"),
        correctIndex = 2,
        verseRef = "John 3:1-3",
        verseRefMl = "യോഹന്നാൻ 3:1-3"
    ),
    BibleQuizQuestion(
        id = 40, level = 4,
        question = "To which Aegean island was the Apostle John exiled when he received Revelation?",
        questionMl = "വെളിപ്പാട് ദർശനങ്ങൾ ലഭിച്ചപ്പോൾ അപ്പൊസ്തലനായ യോഹന്നാൻ ഏത് ഈജിയൻ ദ്വീപിലേക്ക് നാടുകടത്തപ്പെട്ടിരുന്നു?",
        options = listOf("Cyprus", "Crete", "Malta", "Patmos"),
        optionsMl = listOf("കുപ്രൊസ്", "ക്രേത്ത", "മെലിത്ത", "പത്മൊസ്"),
        correctIndex = 3,
        verseRef = "Revelation 1:9",
        verseRefMl = "വെളിപ്പാട് 1:9"
    ),
    BibleQuizQuestion(
        id = 41, level = 4,
        question = "How many books make up the Protestant Bible canon (39 OT + 27 NT)?",
        questionMl = "പ്രൊട്ടസ്റ്റന്റ് ബൈബിൾ കാനോനിൽ (39 പഴയ നിയമം + 27 പുതിയ നിയമം) ആകെ എത്ര പുസ്തകങ്ങളുണ്ട്?",
        options = listOf("62", "66", "70", "73"),
        optionsMl = listOf("62", "66", "70", "73"),
        correctIndex = 1,
        verseRef = "Biblical Canon",
        verseRefMl = "ബൈബിൾ കാനോൻ"
    ),
    BibleQuizQuestion(
        id = 42, level = 4,
        question = "In the Beatitudes (Matthew 5), whom does Jesus promise 'will inherit the earth'?",
        questionMl = "ഗിരിപ്രഭാഷണത്തിലെ ഭാഗ്യവചനങ്ങളിൽ (മത്തായി 5) ആരാണ് 'ഭൂമിയെ അവകാശമാക്കും' എന്ന് യേശു വാഗ്ദാനം ചെയ്യുന്നത്?",
        options = listOf("The merciful", "The pure in heart", "The meek", "The persecuted"),
        optionsMl = listOf("കരുണയുള്ളവർ", "ഹൃദയശുദ്ധിയുള്ളവർ", "സൗമ്യതയുള്ളവർ", "പീഡിപ്പിക്കപ്പെടുന്നവർ"),
        correctIndex = 2,
        verseRef = "Matthew 5:5",
        verseRefMl = "മത്തായി 5:5"
    ),
    BibleQuizQuestion(
        id = 43, level = 4,
        question = "Which angel appeared to Zechariah by the incense altar to announce John the Baptist's birth?",
        questionMl = "യോഹന്നാൻ സ്നാപകന്റെ ജനനം അറിയിക്കാൻ ധൂപപീഠത്തിനരികെ സെഖര്യാവിന് പ്രത്യക്ഷനായ ദൂതൻ ആര്?",
        options = listOf("Gabriel", "Michael", "Raphael", "Uriel"),
        optionsMl = listOf("ഗബ്രിയേൽ", "മീഖായേൽ", "റഫായേൽ", "ഊരിയേൽ"),
        correctIndex = 0,
        verseRef = "Luke 1:19",
        verseRefMl = "ലൂക്കൊസ് 1:19"
    ),
    BibleQuizQuestion(
        id = 44, level = 4,
        question = "Who was the prophetess and only female Judge recorded in the Book of Judges?",
        questionMl = "ന്യായാധിപന്മാരുടെ പുസ്തകത്തിൽ രേഖപ്പെടുത്തിയിട്ടുള്ള പ്രവാചകിയും ഏക വനിതാ ന്യായാധിപയും ആരായിരുന്നു?",
        options = listOf("Jael", "Deborah", "Miriam", "Huldah"),
        optionsMl = listOf("യാവേൽ", "ദെബോരാ", "മിര്യാം", "ഹുൽദാ"),
        correctIndex = 1,
        verseRef = "Judges 4:4",
        verseRefMl = "ന്യായാധിപന്മാർ 4:4"
    ),
    BibleQuizQuestion(
        id = 45, level = 4,
        question = "Which Gospel alone records both the Good Samaritan and the Prodigal Son?",
        questionMl = "നല്ല ശമര്യക്കാരൻ, മുടിയനായ പുത്രൻ എന്നീ രണ്ട് ഉപമകളും രേഖപ്പെടുത്തിയിട്ടുള്ള ഏക സുവിശേഷം ഏതാണ്?",
        options = listOf("Matthew", "Mark", "John", "Luke"),
        optionsMl = listOf("മത്തായി", "മർക്കൊസ്", "യോഹന്നാൻ", "ലൂക്കൊസ്"),
        correctIndex = 3,
        verseRef = "Luke 10:30; 15:11",
        verseRefMl = "ലൂക്കൊസ് 10:30; 15:11"
    ),
    BibleQuizQuestion(
        id = 46, level = 4,
        question = "On the road to Damascus, which believer was sent to restore Saul's sight?",
        questionMl = "ദമസ്കൊസിലേക്കുള്ള വഴിയിൽ ശൗലിന് കാഴ്ച തിരികെ ലഭിക്കാൻ അയക്കപ്പെട്ട വിശ്വാസി ആരായിരുന്നു?",
        options = listOf("Ananias", "Philip", "Barnabas", "Silas"),
        optionsMl = listOf("അനന്യാസ്", "ഫിലിപ്പൊസ്", "ബർന്നബാസ്", "ശീലാസ്"),
        correctIndex = 0,
        verseRef = "Acts 9:17",
        verseRefMl = "പ്രവൃത്തികൾ 9:17"
    ),
    BibleQuizQuestion(
        id = 47, level = 4,
        question = "In Ephesians 6, what is called the 'sword of the Spirit' in the Armor of God?",
        questionMl = "എഫെസ്യർ 6-ലെ സർവ്വായുധവർഗ്ഗത്തിൽ 'ആത്മാവിന്റെ വാൾ' എന്ന് വിളിക്കുന്നത് എന്തിനെയാണ്?",
        options = listOf("Righteousness", "The word of God", "Faith", "Truth"),
        optionsMl = listOf("നീതി", "ദൈവവചനം", "വിശ്വാസം", "സത്യം"),
        correctIndex = 1,
        verseRef = "Ephesians 6:17",
        verseRefMl = "എഫെസ്യർ 6:17"
    ),
    BibleQuizQuestion(
        id = 48, level = 4,
        question = "Who offered his own newly hewn rock tomb for the burial of Jesus?",
        questionMl = "യേശുവിന്റെ സംസ്കാരത്തിനായി പാറയിൽ വെട്ടിയ തന്റെ സ്വന്തം പുതിയ കല്ലറ വിട്ടുകൊടുത്തത് ആര്?",
        options = listOf("Simon of Cyrene", "Nicodemus", "Joseph of Arimathea", "Cleopas"),
        optionsMl = listOf("കുറേനക്കാരൻ ശിമോൻ", "നിക്കൊദേമൊസ്", "അരിമഥ്യയിലെ യോസേഫ്", "ക്ലെയൊപ്പാസ്"),
        correctIndex = 2,
        verseRef = "Matthew 27:57-60",
        verseRefMl = "മത്തായി 27:57-60"
    ),

    // ==================== LEVEL 5: PROPHETIC VOICES (Intermediate - 18s) ====================
    BibleQuizQuestion(
        id = 49, level = 5,
        question = "How many stone water jars used for ceremonial washing did Jesus turn to wine at Cana?",
        questionMl = "കാനായിലെ കല്യാണവിരുന്നിൽ ആചാരപരമായ ശുദ്ധീകരണത്തിനുള്ള എത്ര കല്പാത്രങ്ങളിലാണ് യേശു വെള്ളം വീഞ്ഞാക്കിയത്?",
        options = listOf("Three", "Six", "Seven", "Twelve"),
        optionsMl = listOf("മൂന്ന്", "ആറ്", "ഏഴ്", "പന്ത്രണ്ട്"),
        correctIndex = 1,
        verseRef = "John 2:6",
        verseRefMl = "യോഹന്നാൻ 2:6"
    ),
    BibleQuizQuestion(
        id = 50, level = 5,
        question = "What was the name of the dealer in purple cloth from Thyatira baptized by Paul at Philippi?",
        questionMl = "ഫിലിപ്പിയിൽ വെച്ച് പൗലോസിൽ നിന്ന് സ്നാനം സ്വീകരിച്ച തുയഥൈരയിൽ നിന്നുള്ള രക്താംബരം വില്ക്കുന്ന സ്ത്രീയുടെ പേര് എന്തായിരുന്നു?",
        options = listOf("Lydia", "Priscilla", "Phoebe", "Chloe"),
        optionsMl = listOf("ലുദിയ", "പ്രിസ്കില്ല", "ഫേബ", "ക്ലോവ"),
        correctIndex = 0,
        verseRef = "Acts 16:14-15",
        verseRefMl = "പ്രവൃത്തികൾ 16:14-15"
    ),
    BibleQuizQuestion(
        id = 51, level = 5,
        question = "To how many men did God reduce Gideon's army after testing how they lapped water?",
        questionMl = "വെള്ളം നക്കിക്കുടിച്ച വിധം പരിശോധിച്ച ശേഷം ദൈവം ഗിദെയോന്റെ സൈന്യത്തെ എത്ര പേരായി ചുരുക്കി?",
        options = listOf("1,000", "500", "300", "120"),
        optionsMl = listOf("1,000", "500", "300", "120"),
        correctIndex = 2,
        verseRef = "Judges 7:7",
        verseRefMl = "ന്യായാധിപന്മാർ 7:7"
    ),
    BibleQuizQuestion(
        id = 52, level = 5,
        question = "On which mountain peak did Elijah defeat the 450 prophets of Baal?",
        questionMl = "ബാലിന്റെ 450 പ്രവാചകന്മാരെ ഏലിയാവ് തോൽപ്പിച്ചത് ഏത് പർവ്വത ശിഖരത്തിൽ വെച്ചാണ്?",
        options = listOf("Mount Horeb", "Mount Hermon", "Mount Ebal", "Mount Carmel"),
        optionsMl = listOf("ഹോരേബ് പർവ്വതം", "ഹെർമ്മോൻ പർവ്വതം", "ഏബാൽ പർവ്വതം", "കർമ്മേൽ പർവ്വതം"),
        correctIndex = 3,
        verseRef = "1 Kings 18:19-20",
        verseRefMl = "1 രാജാക്കന്മാർ 18:19-20"
    ),
    BibleQuizQuestion(
        id = 53, level = 5,
        question = "Which runaway slave from Colossae did Paul send back as a 'dear brother' to Philemon?",
        questionMl = "കൊലൊസ്സ്യയിൽ നിന്ന് ഓടിപ്പോയ ഏത് അടിമയെയാണ് 'പ്രിയ സഹോദരനായി' സ്വീകരിക്കാൻ പൗലോസ് ഫിലേമോനോട് അപേക്ഷിച്ചത്?",
        options = listOf("Onesimus", "Tychicus", "Epaphras", "Demas"),
        optionsMl = listOf("ഒനേസിമൊസ്", "തുഹിക്കൊസ്", "എപ്പഫ്രാസ്", "ദേമാസ്"),
        correctIndex = 0,
        verseRef = "Philemon 1:15-16",
        verseRefMl = "ഫിലേമോൻ 1:15-16"
    ),
    BibleQuizQuestion(
        id = 54, level = 5,
        question = "What was the name of the blind son of Timaeus healed by Jesus outside Jericho?",
        questionMl = "യെരീഹോയ്ക്ക് പുറത്ത് യേശു സൗഖ്യമാക്കിയ തീമായിയുടെ അന്ധനായ മകന്റെ പേര് എന്തായിരുന്നു?",
        options = listOf("Malchus", "Bartimaeus", "Aeneas", "Cleopas"),
        optionsMl = listOf("മൽക്കൊസ്", "ബർതിമായി", "ഐനെയാസ്", "ക്ലെയൊപ്പാസ്"),
        correctIndex = 1,
        verseRef = "Mark 10:46-47",
        verseRefMl = "മർക്കൊസ് 10:46-47"
    ),
    BibleQuizQuestion(
        id = 55, level = 5,
        question = "Which pre-flood patriarch 'walked faithfully with God; then he was no more, because God took him away'?",
        questionMl = "'ദൈവത്തോടു കൂടെ നടന്നു; ദൈവം അവനെ എടുക്കയാൽ കാണാതെയായി' എന്ന് പറയപ്പെടുന്ന പ്രളയപൂർവ്വ പിതാവ് ആര്?",
        options = listOf("Methuselah", "Lamech", "Enosh", "Enoch"),
        optionsMl = listOf("മെഥൂശലഹ്", "ലാമെക്ക്", "എനോശ്", "ഹനോക്ക്"),
        correctIndex = 3,
        verseRef = "Genesis 5:24",
        verseRefMl = "ഉല്പത്തി 5:24"
    ),
    BibleQuizQuestion(
        id = 56, level = 5,
        question = "Which Roman centurion in Caesarea received the Holy Spirit as Peter preached to his Gentile household?",
        questionMl = "പത്രൊസ് പ്രസംഗിക്കവേ തന്റെ വിജാതീയ കുടുംബത്തോടൊപ്പം പരിശുദ്ധാത്മാവിനെ പ്രാപിച്ച കൈസര്യയിലെ റോമൻ ശതാധിപൻ ആര്?",
        options = listOf("Julius", "Claudius Lysias", "Cornelius", "Publius"),
        optionsMl = listOf("യൂലിയൊസ്", "ക്ലൗദ്യൊസ് ലുസിയാസ്", "കൊർന്നേല്യൊസ്", "പുബ്ലിയൊസ്"),
        correctIndex = 2,
        verseRef = "Acts 10:1-44",
        verseRefMl = "പ്രവൃത്തികൾ 10:1-44"
    ),
    BibleQuizQuestion(
        id = 57, level = 5,
        question = "Who was chosen by lot in Acts chapter 1 to take Judas Iscariot's place among the Twelve?",
        questionMl = "പ്രവൃത്തികൾ 1-ൽ പന്ത്രണ്ടുപേരുടെ കൂട്ടത്തിൽ യൂദാ ഇസ്കരിയോത്തായുടെ സ്ഥാനത്തേക്ക് ചീട്ടിട്ട് തിരഞ്ഞെടുക്കപ്പെട്ടത് ആര്?",
        options = listOf("Matthias", "Barsabbas", "Silas", "Stephen"),
        optionsMl = listOf("മത്ഥിയാസ്", "ബർശബ്ബാസ്", "ശീലാസ്", "സ്തെഫാനൊസ്"),
        correctIndex = 0,
        verseRef = "Acts 1:23-26",
        verseRefMl = "പ്രവൃത്തികൾ 1:23-26"
    ),
    BibleQuizQuestion(
        id = 58, level = 5,
        question = "Which Old Testament historical book never mentions the explicit name of God even once?",
        questionMl = "ദൈവത്തിന്റെ നാമം ഒരിടത്തും നേരിട്ട് പരാമർശിക്കാത്ത പഴയ നിയമ ചരിത്ര പുസ്തകം ഏതാണ്?",
        options = listOf("Ruth", "Esther", "Ezra", "Nehemiah"),
        optionsMl = listOf("രൂത്ത്", "എസ്ഥേർ", "എസ്രാ", "നെഹെമ്യാവ്"),
        correctIndex = 1,
        verseRef = "Book of Esther",
        verseRefMl = "എസ്ഥേറിന്റെ പുസ്തകം"
    ),
    BibleQuizQuestion(
        id = 59, level = 5,
        question = "Inside which wilderness cave region did David secretly cut off a corner of King Saul's robe?",
        questionMl = "ഏത് മരുഭൂമിയിലെ ഗുഹയ്ക്കുള്ളിൽ വെച്ചാണ് ദാവീദ് രഹസ്യമായി ശൗൽ രാജാവിന്റെ അങ്കിയുടെ അറ്റം മുറിച്ചെടുത്തത്?",
        options = listOf("Adullam", "En Gedi", "Ziph", "Maon"),
        optionsMl = listOf("അദുല്ലാം", "ഏൻ-ഗെദി", "സീഫ്", "മാവോൻ"),
        correctIndex = 1,
        verseRef = "1 Samuel 24:1-4",
        verseRefMl = "1 ശമൂവേൽ 24:1-4"
    ),
    BibleQuizQuestion(
        id = 60, level = 5,
        question = "Which epistle likens the human tongue to a ship's small rudder and a spark setting a forest on fire?",
        questionMl = "മനുഷ്യന്റെ നാവിനെ കപ്പലിലെ ചെറിയ ചുക്കാനിനോടും വലിയ കാടിന് തീ വെക്കുന്ന തീപ്പൊരിയോടും ഉപമിക്കുന്ന ലേഖനം ഏതാണ്?",
        options = listOf("1 Peter", "Hebrews", "James", "Jude"),
        optionsMl = listOf("1 പത്രൊസ്", "എബ്രായർ", "യാക്കോബ്", "യൂദാ"),
        correctIndex = 2,
        verseRef = "James 3:4-5",
        verseRefMl = "യാക്കോബ് 3:4-5"
    )
)
