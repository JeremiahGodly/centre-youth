// Keezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam Data Store

const SAKHYAM_DATA = {
  centreInfo: {
    nameEn: "Keezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam",
    nameMl: "കീഴില്ലം - മൂന്നാർ സെന്റർ മാർത്തോമ്മാ യുവജനസഖ്യം",
    nameMang: "Keezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam",
    dioceseEn: "Kottayam - Kochi Diocese",
    dioceseMl: "കോട്ടയം - കൊച്ചി ഭദ്രാസനം",
    dioceseMang: "Kottayam - Kochi Bhadrasanam",
    mottoEn: "Lighted to Lighten",
    mottoMl: "പ്രകാശിതരായി പ്രകാശിപ്പിക്കുക",
    mottoMang: "Prakashitharaayi Prakaashippikkuka",
    themeVerse: "Romans 12:2",
    themeQuoteEn: "Do not be conformed to this world, but be transformed by the renewal of your mind.",
    themeQuoteMl: "ഈ ലോകത്തിന് അനുരൂപരാകാതെ, നിങ്ങളുടെ മനസ്സിനെ നവീകരിച്ച് രൂപാന്തരപ്പെടുവിൻ.",
    themeQuoteMang: "Ee lokathinn anurooparaakaathe, ningalude manassine naveekarichu roopaantharappeduveen."
  },

  stats: [
    { id: "parishes", value: 15, suffix: "+", labelEn: "Centre Parishes", labelMl: "സെന്റർ ഇടവകകൾ", labelMang: "Centre Idavakakal", icon: "church" },
    { id: "members", value: 500, suffix: "+", labelEn: "Youth Members", labelMl: "യുവജന അംഗങ്ങൾ", labelMang: "Yuvajana Members", icon: "users" },
    { id: "competitions", value: 18, suffix: " Items", labelEn: "Kalamela Events", labelMl: "കലാമേള ഇനങ്ങൾ", labelMang: "Kalamela Inangal", icon: "sparkles" },
    { id: "trophies", value: 2, suffix: "x", labelEn: "Diocese Football Champions", labelMl: "ഭദ്രാസന ഫുട്ബോൾ ചാമ്പ്യൻസ്", labelMang: "Diocese Football Champions", icon: "trophy" },
    { id: "awards", value: 3, suffix: "rd", labelEn: "Best Centre Award 2025-26", labelMl: "മികച്ച സെന്റർ പുരസ്കാരം", labelMang: "Best Centre Puraskaram", icon: "award" }
  ],

  pillars: [
    {
      id: "worship",
      titleEn: "Worship",
      titleMl: "ആരാധന",
      titleMang: "Aaradhana",
      descEn: "Deepening spiritual roots through authentic liturgy, youth services, prayer fellowships, and communion with Jesus Christ.",
      descMl: "ആത്മീയ വേരുകൾ ഉറപ്പിച്ച്, ദൈവവചനത്തിലും കൂട്ടായ്മയിലും ആരാധനയിലും സജീവമായി പങ്കുചേരുന്നു.",
      descMang: "Aathmeeya verukal urappichu, Daivavachanathilum koottaymayilum aaraadhanayilum sajeevamaayi nilakollunnu.",
      icon: "flame"
    },
    {
      id: "study",
      titleEn: "Study",
      titleMl: "പഠനം",
      titleMang: "Padhanam",
      descEn: "Engaging biblical theology, socio-cultural reflections, leadership workshops, and youth conference study sessions.",
      descMl: "വേദശാസ്ത്ര ചിന്തകൾ, ആനുകാലിക പ്രശ്നങ്ങൾ, നേതൃത്വ പരിശീലനങ്ങൾ എന്നിവയിലൂടെ അറിവും വിവേകവും നേടുന്നു.",
      descMang: "Vedasasthra chinthakal, leadership thrainingukal, padhana classes vazhi arivum vivekavum nedunnu.",
      icon: "book-open"
    },
    {
      id: "witness",
      titleEn: "Witness",
      titleMl: "സാക്ഷ്യം",
      titleMang: "Saakshyam",
      descEn: "Standing bold as light and salt in contemporary society, embodying Christ's values against worldly conformity.",
      descMl: "ലോകത്തിന്റെ ഒഴുക്കിനെതിരെ ധൈര്യത്തോടെ നിന്ന്, ക്രിസ്തുവിന്റെ സ്നേഹത്തിനും സത്യത്തിനും ഉത്തമ സാക്ഷികളാകുന്നു.",
      descMang: "Lokathinte ozhukkinethire dhairyathode ninnu, Christhuvinte snehathinum sathyathinum nalla saakshikalaakunnu.",
      icon: "compass"
    },
    {
      id: "service",
      titleEn: "Service",
      titleMl: "സേവനം",
      titleMang: "Sevanam",
      descEn: "Active community welfare, flood relief kits, anti-drug youth rallies, medical aid, and reaching the marginalized.",
      descMl: "പ്രളയ ദുരിതാശ്വാസം, ലഹരിവിരുദ്ധ ജാഥകൾ, സാന്ത്വന പ്രവർത്തനങ്ങൾ എന്നിവയിലൂടെ പാവപ്പെട്ടവരിലേക്ക് കാരുണ്യം എത്തിക്കുന്നു.",
      descMang: "Pralaya durithaaswaasam, lahari-virudha rally, santhwana pravarthanangal vazhi society-ye sahayeekkunnu.",
      icon: "heart-handshake"
    }
  ],

  officeBearers: [
    {
      nameEn: "Rev. James George",
      nameMl: "റവ. ജെയിംസ് ജോർജ്ജ്",
      nameMang: "Rev. James George",
      roleEn: "President",
      roleMl: "പ്രസിഡന്റ്",
      roleMang: "President",
      designationEn: "Centre Vicar & Youth President",
      designationMl: "സെന്റർ പ്രസിഡന്റ്",
      designationMang: "Centre President",
      image: "assets/images/cshore-inauguration-stage.jpg",
      badge: "Clergy Leader"
    },
    {
      nameEn: "Adv. Varghese Sabu",
      nameMl: "അഡ്വ. വർഗീസ് സാബു",
      nameMang: "Adv. Varghese Sabu",
      roleEn: "Vice President",
      roleMl: "വൈസ് പ്രസിഡന്റ്",
      roleMang: "Vice President",
      designationEn: "Centre Vice President",
      designationMl: "സെന്റർ വൈസ് പ്രസിഡന്റ്",
      designationMang: "Centre Vice President",
      image: "assets/images/best-centre-trophy.jpg",
      badge: "Leadership"
    },
    {
      nameEn: "Sri. Ashin K Santhosh",
      nameMl: "ശ്രീ. ആഷിൻ കെ സന്തോഷ്",
      nameMang: "Sri. Ashin K Santhosh",
      roleEn: "Secretary",
      roleMl: "സെക്രട്ടറി",
      roleMang: "Secretary",
      designationEn: "Centre Secretary (2026-27)",
      designationMl: "സെന്റർ സെക്രട്ടറി",
      designationMang: "Centre Secretary",
      image: "assets/images/flood-relief-kit-giving.jpg",
      badge: "Executive"
    },
    {
      nameEn: "Sri. Aleena Maria Joy",
      nameMl: "ശ്രീ. അലീന മരിയ ജോയ്",
      nameMang: "Sri. Aleena Maria Joy",
      roleEn: "Joint Secretary",
      roleMl: "ജോയിന്റ് സെക്രട്ടറി",
      roleMang: "Joint Secretary",
      designationEn: "Former Secretary (25-26) & Joint Sec",
      designationMl: "സെന്റർ ജോയിന്റ് സെക്രട്ടറി",
      designationMang: "Centre Joint Secretary",
      image: "assets/images/best-centre-trophy.jpg",
      badge: "Executive"
    },
    {
      nameEn: "Sri. Jibin Saji",
      nameMl: "ശ്രീ. ജിബിൻ സജി",
      nameMang: "Sri. Jibin Saji",
      roleEn: "Treasurer & Convenor",
      roleMl: "ട്രഷറർ & കൺവീനർ",
      roleMang: "Treasurer & Convenor",
      designationEn: "Centre Treasurer & Kalamela Convenor",
      designationMl: "സെന്റർ ട്രഷറർ & കലാമേള കൺവീനർ",
      designationMang: "Centre Treasurer & Kalamela Convenor",
      image: "assets/images/inauguration-valakom.jpg",
      badge: "Finance & Event"
    },
    {
      nameEn: "Sri. Jerin Abraham",
      nameMl: "ശ്രീ. ജെറിൻ എബ്രഹാം",
      nameMang: "Sri. Jerin Abraham",
      roleEn: "Convenor",
      roleMl: "കൺവീനർ",
      roleMang: "Convenor",
      designationEn: "Kalamela Program Convenor",
      designationMl: "കലാമേള കൺവീനർ",
      designationMang: "Kalamela Convenor",
      image: "assets/images/inauguration-valakom.jpg",
      badge: "Event Lead"
    }
  ],

  cShoreCamp: {
    titleEn: "C-SHORE Youth Camp 2026",
    titleMl: "സി-ഷോർ യൂത്ത് ക്യാമ്പ് 2026",
    titleMang: "C-SHORE Youth Camp 2026",
    themeEn: "Theme: CHRIST SHORE",
    themeMl: "പ്രമേയം: ക്രിസ്തുവിന്റെ തീരം (Christ Shore)",
    themeMang: "Theme: Christ Shore",
    dateEn: "October 19, 20, 21, 2026",
    dateMl: "2026 ഒക്ടോബർ 19, 20, 21",
    dateMang: "2026 October 19, 20, 21",
    venueEn: "Mar Thoma College of Management & Technology (MCMAT), Perumbavoor",
    venueMl: "മാർത്തോമ്മാ കോളേജ് ഓഫ് മാനേജ്‌മെന്റ് & ടെക്‌നോളജി (MCMAT), പെരുമ്പാവൂർ",
    venueMang: "Mar Thoma College of Management & Technology (MCMAT), Perumbavoor",
    inauguratorEn: "Shri. Manoj Moothedan MLA (Perumbavoor Assembly Constituency)",
    inauguratorMl: "ശ്രീ. മനോജ് മൂത്തേടൻ എം.എൽ.എ (പെരുമ്പാവൂർ നിയോജകമണ്ഡലം)",
    inauguratorMang: "Shri. Manoj Moothedan MLA (Perumbavoor)",
    chiefGuestEn: "Rev. Byju Thomas (General Secretary, Kendriya MTYS)",
    chiefGuestMl: "റവ. ബൈജു തോമസ് (കേന്ദ്ര യുവജനസഖ്യം ജനറൽ സെക്രട്ടറി)",
    chiefGuestMang: "Rev. Byju Thomas (General Secretary, MTYS)",
    mainTalkEn: "Rev. Blesson Sam Koshy",
    mainTalkMl: "റവ. ബ്ലെസ്സൺ സാം കോശി",
    mainTalkMang: "Rev. Blesson Sam Koshy",
    musicEn: "Singing Session led by Mr. Sanju Abraham & Mrs. Jincy Joy",
    musicMl: "ഗാനശുശ്രൂഷ: ശ്രീ. സഞ്ജു എബ്രഹാം & ശ്രീമതി. ജിൻസി ജോയ്",
    musicMang: "Singing Session: Sanju Abraham & Jincy Joy",
    delegatesCount: "50+ Dedicated Youths",
    highlightsEn: [
      "Dynamic Bible discussions, life reflections & youth issues breakdown",
      "Praise & worship concerts in the vibrant MCMAT amphitheatre",
      "Team dynamics, icebreakers, creative workshops & camp campfire bonding",
      "Special guidance on youth mental health, carrier paths, and moral integrity"
    ],
    highlightsMl: [
      "ആത്മീയ ക്ലാസുകൾ, വേദചിന്തകൾ, യുവജന സംവാദങ്ങൾ",
      "MCMAT ഓഡിറ്റോറിയത്തിൽ സംഗീത സായാഹ്നവും സ്തുതിപ്പും",
      "ടീം ഗെയിമുകൾ, സർഗ്ഗാത്മക ശിൽപശാലകൾ, ക്യാമ്പ് സൗഹൃദങ്ങൾ",
      "യുവജന മാനസികാരോഗ്യം, കരിയർ, ആധുനിക വെല്ലുവിളികൾ എന്നിവയെക്കുറിച്ചുള്ള ചർച്ചകൾ"
    ],
    highlightsMang: [
      "Aathmeeya classukal, Bible chinthakal, youth discussions",
      "MCMAT auditorium-il sangeetha saayahnavum aaraadhanayum",
      "Team games, creative workshops, camp sauhridhangal",
      "Youth mental health, career, modern issues kurichulla sessions"
    ]
  },

  kalamela: {
    name: "LUMORA 2026",
    taglineEn: "Keezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam Talent Fest",
    taglineMl: "കീഴില്ലം - മൂന്നാർ സെന്റർ യുവജന കലാമേള 2026",
    taglineMang: "Keezhillam - Munnar Centre Yuvajana Kalamela 2026",
    dateEn: "November 14, 2026 (Saturday) - 9:00 AM Onwards",
    dateMl: "2026 നവംബർ 14 (ശനി) രാവിലെ 9:00 മണി മുതൽ",
    dateMang: "2026 November 14 (Shani) Ravile 9:00 AM Muthal",
    venueEn: "St. Paul's Mar Thoma Church, Perumbavoor",
    venueMl: "പെരുമ്പാവൂർ സെന്റ് പോൾസ് മാർത്തോമ്മാ ഇടവക",
    venueMang: "Perumbavoor St. Paul's Mar Thoma Church",
    manualEn: "Judged in strict accordance with the updated 2026 MTYS Prathibha Vedi Manual.",
    manualMl: "മാർത്തോമ്മാ യുവജനസഖ്യം പ്രതിഭാവേദി (2026 പരിഷ്കരിച്ച) കലാമേള മാന്വൽ അനുസരിച്ച് വിധിനിർണ്ണയം.",
    manualMang: "MTYS Prathibha Vedi (2026 revised) Kalamela manual anusarichu vidhinirnayam.",
    groups: [
      {
        nameEn: "Group I",
        nameMl: "ഗ്രൂപ്പ് I",
        nameMang: "Group I",
        criteriaEn: "Born on or after January 1, 2009",
        criteriaMl: "2009 ജനുവരി 1 നും അതിനു ശേഷവും ജനിച്ചവർ",
        criteriaMang: "2009 January 1-num athinu sheshavum janichavar"
      },
      {
        nameEn: "Group II",
        nameMl: "ഗ്രൂപ്പ് II",
        nameMang: "Group II",
        criteriaEn: "Born between January 1, 1992 and December 31, 2008",
        criteriaMl: "1992 ജനുവരി 1 നും 2008 ഡിസംബർ 31 നും മദ്ധ്യേ ജനിച്ചവർ",
        criteriaMang: "1992 January 1 muthal 2008 December 31 vare janichavar"
      }
    ],
    themes: [
      { eventEn: "Mime", eventMl: "മൈം", eventMang: "Mime", themeEn: "Creation", themeMl: "സൃഷ്ടി", themeMang: "Srishti" },
      { eventEn: "Choreography", eventMl: "കൊറിയോഗ്രാഫി", eventMang: "Choreography", themeEn: "Hope", themeMl: "പ്രത്യാശ", themeMang: "Prathyasha" },
      { eventEn: "Tableau", eventMl: "ടാബ്ലോ", eventMang: "Tableau", themeEn: "Miracle of Jesus", themeMl: "യേശുവിന്റെ അത്ഭുതങ്ങൾ", themeMang: "Yeshuvinte Albhuthangal" },
      { eventEn: "Bible Quiz", eventMl: "ബൈബിൾ ക്വിസ്", eventMang: "Bible Quiz", themeEn: "1 Chronicles, Philippians, Colossians, 1 & 2 Thessalonians", themeMl: "1 ദിനവൃത്താന്തം, ഫിലിപ്പിയർ, കൊലോസ്യർ, 1 & 2 തെസ്സലോനീക്യർ", themeMang: "1 Chronicles, Philippians, Colossians, 1 & 2 Thessalonians" }
    ],
    items: [
      { no: 1, nameEn: "Light Music Solo (Male & Female)", nameMl: "ലളിതഗാനം (പുരുഷൻ / സ്ത്രീ) - മലയാളം, ഇംഗ്ലീഷ്", nameMang: "Lalithagaanam Solo (M & F)", duration: "Standard", type: "Music" },
      { no: 2, nameEn: "Group Song (Samuhagaanam)", nameMl: "സമൂഹഗാനം", nameMang: "Samuhagaanam (Group Song)", duration: "Standard", type: "Music" },
      { no: 3, nameEn: "Instrumental Music (Wind / String / Keyboard / Percussion)", nameMl: "ഉപകരണ സംഗീതം (വിൻഡ്, സ്ട്രിംഗ്, കീബോർഡ്, തബല/മൃദംഗം/ഡ്രം)", nameMang: "Upakarana Sangeetham (Wind, String, Keys, Drums)", duration: "5 Mins", type: "Music" },
      { no: 4, nameEn: "Elocution (Malayalam & English)", nameMl: "പ്രസംഗം (മലയാളം, ഇംഗ്ലീഷ്)", nameMang: "Prasangam (Malayalam & English)", duration: "5 Mins", type: "Literary" },
      { no: 5, nameEn: "Bible Reading (Malayalam & English)", nameMl: "വേദപുസ്തക വായന (മലയാളം, ഇംഗ്ലീഷ്)", nameMang: "Veda Pusthaka Vayana", duration: "Standard", type: "Literary" },
      { no: 6, nameEn: "Essay Writing (Malayalam & English)", nameMl: "ഉപന്യാസ രചന (മലയാളം, ഇംഗ്ലീഷ്)", nameMang: "Upanyasa Rachana", duration: "30 Mins", type: "Writing" },
      { no: 7, nameEn: "Story Writing (Malayalam & English)", nameMl: "കഥാരചന (മലയാളം, ഇംഗ്ലീഷ്)", nameMang: "Katha Rachana", duration: "30 Mins", type: "Writing" },
      { no: 8, nameEn: "Poem Writing (Malayalam & English)", nameMl: "കവിതാ രചന (മലയാളം, ഇംഗ്ലീഷ്)", nameMang: "Kavitha Rachana", duration: "30 Mins", type: "Writing" },
      { no: 9, nameEn: "Recitation (Malayalam & English)", nameMl: "പദ്യപാരായണം (മലയാളം, ഇംഗ്ലീഷ്)", nameMang: "Padyapaaraayanam", duration: "7 Mins", type: "Literary" },
      { no: 10, nameEn: "Tableau (Theme: Miracle of Jesus)", nameMl: "ടാബ്ലോ (വിഷയം: യേശുവിന്റെ അത്ഭുതങ്ങൾ)", nameMang: "Tableau (Yeshuvinte Albhuthangal)", duration: "1 Min", type: "Stage" },
      { no: 11, nameEn: "Clay Modeling", nameMl: "ക്ലേ മോഡലിംഗ്", nameMang: "Clay Modeling", duration: "30 Mins", type: "Fine Arts" },
      { no: 12, nameEn: "Bible Quiz", nameMl: "ക്വിസ് (1 ദിനവൃത്താന്തം, ഫിലിപ്പിയർ, കൊലോസ്യർ, തെസ്സലോനീക്യർ)", nameMang: "Bible Quiz", duration: "30 Mins", type: "Literary" },
      { no: 13, nameEn: "Pencil Drawing", nameMl: "ചിത്രരചന (പെൻസിൽ ഡ്രോയിംഗ്)", nameMang: "Pencil Drawing", duration: "30 Mins", type: "Fine Arts" },
      { no: 14, nameEn: "Painting (Water Color)", nameMl: "ജലച്ഛായം (Painting)", nameMang: "Water Color Painting", duration: "45 Mins", type: "Fine Arts" },
      { no: 15, nameEn: "Cartoon Drawing (Pencil)", nameMl: "കാർട്ടൂൺ (പെൻസിൽ)", nameMang: "Cartoon (Pencil)", duration: "30 Mins", type: "Fine Arts" },
      { no: 16, nameEn: "Margamkali (Female)", nameMl: "മാർഗ്ഗംകളി (വനിതകൾ)", nameMang: "Margamkali (Female)", duration: "10 Mins", type: "Stage" },
      { no: 17, nameEn: "Choreography (Theme: Hope / പ്രത്യാശ)", nameMl: "കൊറിയോഗ്രാഫി (വിഷയം: പ്രത്യാശ)", nameMang: "Choreography (Theme: Prathyasha)", duration: "10 Mins", type: "Stage" },
      { no: 18, nameEn: "Mime (Theme: Creation / സൃഷ്ടി)", nameMl: "മൈം (വിഷയം: സൃഷ്ടി)", nameMang: "Mime (Theme: Srishti)", duration: "8 Mins", type: "Stage" }
    ]
  },

  parishes: [
    {
      id: "keezhillam",
      nameEn: "Keezhillam Nazareth Mar Thoma Church",
      nameMl: "കീഴില്ലം നസ്രേത്ത് മാർത്തോമ്മാ ഇടവക",
      nameMang: "Keezhillam Nazareth Mar Thoma Idavaka",
      location: "Keezhillam, Ernakulam",
      tag: "Centre Parish",
      badge: "Vibrant Youth Wing"
    },
    {
      id: "perumbavoor",
      nameEn: "Perumbavoor St. Paul's Mar Thoma Church",
      nameMl: "പെരുമ്പാവൂർ സെന്റ് പോൾസ് മാർത്തോമ്മാ ഇടവക",
      nameMang: "Perumbavoor St. Paul's Mar Thoma Idavaka",
      location: "Perumbavoor Town",
      tag: "Kalamela 2026 Venue",
      badge: "Historic Parish"
    },
    {
      id: "valakom",
      nameEn: "Valakom Mar Thoma Church",
      nameMl: "വാളകം മാർത്തോമ്മാ ഇടവക",
      nameMang: "Valakom Mar Thoma Idavaka",
      location: "Valakom, Muvattupuzha",
      tag: "2026-27 Inauguration Venue",
      badge: "Active Sakhyam"
    },
    {
      id: "kolenchery",
      nameEn: "Kolenchery St. Thomas Mar Thoma Church",
      nameMl: "കോലഞ്ചേരി സെന്റ് തോമസ് മാർത്തോമ്മാ ഇടവക",
      nameMang: "Kolenchery St. Thomas Mar Thoma Idavaka",
      location: "Kolenchery, Ernakulam",
      tag: "Centre Hub",
      badge: "Dynamic Youth"
    },
    {
      id: "kothamangalam",
      nameEn: "Kothamangalam St. Thomas Mar Thoma Church",
      nameMl: "കോതമംഗലം സെന്റ് തോമസ് മാർത്തോമ്മാ ഇടവക",
      nameMang: "Kothamangalam St. Thomas Mar Thoma Idavaka",
      location: "Kothamangalam",
      tag: "Gateway Parish",
      badge: "Active Participation"
    },
    {
      id: "rayamangalam",
      nameEn: "Rayamangalam Bethlehem Mar Thoma Church",
      nameMl: "രായമംഗലം ബേത്‌ലഹേം മാർത്തോമ്മാ ഇടവക",
      nameMang: "Rayamangalam Bethlehem Mar Thoma Idavaka",
      location: "Rayamangalam, Kuruppampady",
      tag: "Parish Fellowship",
      badge: "Devout Community"
    },
    {
      id: "nellimattam",
      nameEn: "Nellimattam Bethel Mar Thoma Church",
      nameMl: "നെല്ലിമറ്റം ബഥേൽ മാർത്തോമ്മാ ഇടവക",
      nameMang: "Nellimattam Bethel Mar Thoma Idavaka",
      location: "Nellimattam, Kavalangad",
      tag: "Parish Fellowship",
      badge: "Enthusiastic Youth"
    },
    {
      id: "paraikkathanam",
      nameEn: "Paraikkathanam St. Thomas Mar Thoma Church",
      nameMl: "പരയ്ക്കത്താനം സെന്റ് തോമസ് മാർത്തോമ്മാ ഇടവക",
      nameMang: "Paraikkathanam St. Thomas Mar Thoma Idavaka",
      location: "Paraikkathanam, Ernakulam",
      tag: "Parish Fellowship",
      badge: "United Sakhyam"
    },
    {
      id: "kuttampuzha",
      nameEn: "Kuttampuzha St. Stephen's Mar Thoma Church",
      nameMl: "കുട്ടമ്പുഴ സെന്റ് സ്റ്റീഫൻസ് മാർത്തോമ്മാ ഇടവക",
      nameMang: "Kuttampuzha St. Stephen's Mar Thoma Idavaka",
      location: "Kuttampuzha, High Ranges",
      tag: "Parish Fellowship",
      badge: "Scenic Foothills"
    },
    {
      id: "poika",
      nameEn: "Poika Ebenezer Mar Thoma Church",
      nameMl: "പൊയ്ക എബനേസർ മാർത്തോമ്മാ ഇടവക",
      nameMang: "Poika Ebenezer Mar Thoma Idavaka",
      location: "Poika, Perumbavoor",
      tag: "Sports Champions Parish",
      badge: "Best Goalkeeper Award"
    },
    {
      id: "oonjappara",
      nameEn: "Oonjappara Mar Thoma Church",
      nameMl: "ഊഞ്ഞാപ്പാറ മാർത്തോമ്മാ ഇടവക",
      nameMang: "Oonjappara Mar Thoma Idavaka",
      location: "Oonjappara, Kothamangalam",
      tag: "Parish Fellowship",
      badge: "Faithful Community"
    },
    {
      id: "munnar",
      nameEn: "Munnar Christ Mar Thoma Church",
      nameMl: "മൂന്നാർ ക്രൈസ്റ്റ് മാർത്തോമ്മാ ഇടവക",
      nameMang: "Munnar Christ Mar Thoma Idavaka",
      location: "Munnar Hill Station, Idukki",
      tag: "High Range Flagship",
      badge: "Heritage Hills"
    },
    {
      id: "adimali",
      nameEn: "Adimali Mar Thoma Church",
      nameMl: "അടിമാലി മാർത്തോമ്മാ ഇടവക",
      nameMang: "Adimali Mar Thoma Idavaka",
      location: "Adimali, Idukki",
      tag: "High Range Parish",
      badge: "Energetic Fellowship"
    },
    {
      id: "methala",
      nameEn: "Methala Mar Thoma Church",
      nameMl: "മേതല മാർത്തോമ്മാ ഇടവക",
      nameMang: "Methala Mar Thoma Idavaka",
      location: "Methala, Asamannoor",
      tag: "Parish Fellowship",
      badge: "Warm Fellowship"
    },
    {
      id: "illithode",
      nameEn: "Illithode Mar Thoma Church & Mission Field",
      nameMl: "ഇല്ലിത്തോട് മാർത്തോമ്മാ ഇടവക / മിഷൻ ഫീൽഡ്",
      nameMang: "Illithode Mar Thoma Idavaka / Mission Field",
      location: "Illithode, Malayattoor Forest Border",
      tag: "Mission Outpost",
      badge: "Mission Outreach"
    }
  ],

  achievements: [
    {
      titleEn: "Diocese Best Centre Award (2025-26 & 2024)",
      titleMl: "ഭദ്രാസന മികച്ച സെന്റർ പുരസ്കാരം (2025-26 മൂന്നാം സ്ഥാനം & 2024)",
      titleMang: "Diocese Best Centre Puraskaram (2025-26 & 2024)",
      descEn: "Recognized by Kottayam - Kochi Diocese as one of the finest centres for exemplary leadership, grassroots youth participation, and holistic ministry.",
      descMl: "കോട്ടയം കൊച്ചി ഭദ്രാസനത്തിൽ സമഗ്രമായ യുവജന പ്രവർത്തനങ്ങൾ കാഴ്ചവെച്ചതിന് അഭിമാനകരമായ അംഗീകാരം.",
      descMang: "Kottayam Kochi Bhadrasanathil best youth activities nadathiyathinnu labhicha prestigious award.",
      image: "assets/images/best-centre-trophy.jpg",
      badge: "Diocesan Honor"
    },
    {
      titleEn: "Diocesan 7s Football Tournament Back-to-Back Champions",
      titleMl: "ഭദ്രാസന ഫുട്ബോൾ ടൂർണമെന്റ് തുടർച്ചയായ രണ്ടാം തവണയും ചാമ്പ്യന്മാർ!",
      titleMang: "Bhadrasana Football Tournament Back-to-Back Champions",
      descEn: "Keezhillam - Munnar Centre clinched the championship trophy at MT Seminary HSS Ground. Robin awarded Best Player, Jolly Joseph (Poika MTC) Best Goalkeeper, Arun Best Defender.",
      descMl: "കോട്ടയം എംടി സെമിനാരി ഗ്രൗണ്ടിൽ നടന്ന ഫുട്ബോൾ ടൂർണമെന്റിൽ കിരീടം ചൂടി. റോബിൻ ബെസ്റ്റ് പ്ലെയർ, ജോളി ബെസ്റ്റ് ഗോളി, അരുൺ ബെസ്റ്റ് ഡിഫെൻഡർ പുരസ്കാരങ്ങൾ നേടി.",
      descMang: "MT Seminary ground-il vechu nadanna football tournament-il championship kireedam. Robin, Jolly, Arun prathyeka awards nedi.",
      image: "assets/images/football-champions-team.jpg",
      badge: "Sports Victory"
    },
    {
      titleEn: "KCC Executive Committee Representation",
      titleMl: "കേരള കൗൺസിൽ ഓഫ് ചർച്ചസ് (KCC) എക്സിക്യൂട്ടീവ് അംഗമായി അഡ്വ. തേജസ് തോമസ് കാറ്റാടി",
      titleMang: "KCC Executive Committee Representation - Adv. Thejas Thomas Kattady",
      descEn: "Adv. Thejas Thomas Kattady elected as Executive Committee Member of Kerala Council of Churches representing Malankara Mar Thoma Syrian Church.",
      descMl: "മാർത്തോമ്മാ സഭയെ പ്രതിനിധീകരിച്ച് കെസിസി എക്സിക്യൂട്ടീവ് കമ്മിറ്റി അംഗമായി തിരഞ്ഞെടുക്കപ്പെട്ടതിൽ അഭിമാനം കൊള്ളുന്നു.",
      descMang: "Mar Thoma Church-ine represent cheythu KCC executive committee-yil theeranjedukkappetta Adv. Thejas Kattady-kku aashamsakal.",
      image: "assets/images/thejas-kcc-election.jpg",
      badge: "Ecumenical Leadership"
    },
    {
      titleEn: "Muvattupuzha Flood Relief & Social Action",
      titleMl: "മൂവാറ്റുപുഴയാർ തീരത്തെ പ്രളയ ദുരിതാശ്വാസ പ്രവർത്തനങ്ങൾ",
      titleMang: "Muvattupuzha Pralaya Durithaaswaasa Sevanangal",
      descEn: "Distributed food kits and relief supplies to affected families in Karukappally & Kadakkanad in association with Poothrikka Grama Panchayat.",
      descMl: "പ്രളയക്കെടുതി അനുഭവിക്കുന്ന നിർധന കുടുംബങ്ങളിലേക്ക് ഭക്ഷ്യകിറ്റുകളും അവശ്യസാധനങ്ങളും നേരിട്ടെത്തിച്ചു നൽകി യുവജനസഖ്യം കാരുണ്യ മാതൃകയായി.",
      descMang: "Pralaya badhitha koodumbangalil food kits and emergency items nalki Keezhillam Munnar Centre youth team.",
      image: "assets/images/flood-relief-kit-giving.jpg",
      badge: "Social Compassion"
    }
  ],

  gallery: [
    {
      id: 1,
      titleEn: "C-SHORE Youth Camp 2026 Grand Delegates Photo",
      titleMl: "സി-ഷോർ യൂത്ത് ക്യാമ്പ് 2026 - പ്രതിനിധി സമ്മേളനം",
      titleMang: "C-SHORE Youth Camp 2026 Grand Photo",
      category: "camp",
      src: "assets/images/cshore-camp-grand-group.jpg"
    },
    {
      id: 2,
      titleEn: "C-SHORE Inauguration Stage with Shri. Manoj Moothedan MLA & Achens",
      titleMl: "ശ്രീ. മനോജ് മൂത്തേടൻ എം.എൽ.എ യും ബഹു. അച്ചന്മാരും വേദിയിൽ",
      titleMang: "C-SHORE Inauguration Stage with MLA & Achens",
      category: "camp",
      src: "assets/images/cshore-inauguration-stage.jpg"
    },
    {
      id: 3,
      titleEn: "C-SHORE Stage Fellowship with Dignitaries & Youth",
      titleMl: "ക്യാമ്പ് വേദിയിലെ ആത്മീയ കൂട്ടായ്മ",
      titleMang: "C-SHORE Stage Fellowship & Talks",
      category: "camp",
      src: "assets/images/cshore-stage-group.jpg"
    },
    {
      id: 4,
      titleEn: "C-SHORE Courtyard High Angle Fellowship",
      titleMl: "ക്യാമ്പ് അംഗങ്ങളുടെ മനോഹരമായ നിമിഷങ്ങൾ",
      titleMang: "C-SHORE Courtyard Fellowship",
      category: "camp",
      src: "assets/images/cshore-courtyard-group.jpg"
    },
    {
      id: 5,
      titleEn: "LUMORA Kalamela 2026 Contest Official Poster",
      titleMl: "കലാമേള 'പേരിടാം സമ്മാനം നേടാം' പോസ്റ്റർ",
      titleMang: "LUMORA Kalamela 2026 Contest Poster",
      category: "kalamela",
      src: "assets/images/kalamela-name-contest.jpg"
    },
    {
      id: 6,
      titleEn: "LUMORA Kalamela 2026 Official Circular & Manual",
      titleMl: "കലാമേള 2026 ഔദ്യോഗിക വിജ്ഞാപനം",
      titleMang: "LUMORA Kalamela 2026 Circular & Manual",
      category: "kalamela",
      src: "assets/images/lumora-kalamela-page1.png"
    },
    {
      id: 7,
      titleEn: "Consecutive Diocesan Football Champions Team",
      titleMl: "തുടർച്ചയായി രണ്ടാം തവണയും ഫുട്ബോൾ ചാമ്പ്യന്മാർ!",
      titleMang: "Consecutive Football Champions Team with Trophy",
      category: "sports",
      src: "assets/images/football-champions-team.jpg"
    },
    {
      id: 8,
      titleEn: "Diocesan Football Tournament Live Match Action",
      titleMl: "ഭദ്രാസന ഫുട്ബോൾ ടൂർണമെന്റ് മത്സര നിമിഷം",
      titleMang: "Diocese Football Match Action Shot",
      category: "sports",
      src: "assets/images/football-match-action.jpg"
    },
    {
      id: 9,
      titleEn: "Diocesan 3rd Best Centre Award Trophy Celebration",
      titleMl: "ഭദ്രാസന മികച്ച സെന്റർ പുരസ്കാര നേട്ടം",
      titleMang: "Diocesan 3rd Best Centre Award Trophy",
      category: "awards",
      src: "assets/images/best-centre-trophy.jpg"
    },
    {
      id: 10,
      titleEn: "2026-27 Activity Year Inauguration at Valakom MTC",
      titleMl: "2026-27 പ്രവർത്തന വർഷ ഉദ്ഘാടനം - വാളകം മാർത്തോമ്മാ പള്ളി",
      titleMang: "2026-27 Activity Inauguration at Valakom MTC",
      category: "camp",
      src: "assets/images/inauguration-valakom.jpg"
    },
    {
      id: 11,
      titleEn: "Muvattupuzha Flood Relief Kit Giving to Elders",
      titleMl: "പ്രളയ ദുരിതബാധിതർക്ക് ഭക്ഷ്യകിറ്റുകൾ നേരിട്ട് നൽകുന്നു",
      titleMang: "Flood Relief Kit Handover to Affected Families",
      category: "service",
      src: "assets/images/flood-relief-kit-giving.jpg"
    },
    {
      id: 12,
      titleEn: "Flood Relief Field Walk Team in Karukappally & Kadakkanad",
      titleMl: "ദുരിതാശ്വാസ പ്രവർത്തനങ്ങളുമായി സഖ്യം സംഘം",
      titleMang: "Flood Relief Team Walking through Villages",
      category: "service",
      src: "assets/images/flood-relief-walk.jpg"
    },
    {
      id: 13,
      titleEn: "Anti-Drug Vehicle Campaign Rally (Solidarity with Toofan)",
      titleMl: "ലഹരിവിരുദ്ധ വാഹന പ്രചരണ ജാഥ വിജ്ഞാപനം",
      titleMang: "Anti-Drug Vehicle Rally Poster (Narco Hunt)",
      category: "service",
      src: "assets/images/anti-drug-bike-rally.png"
    },
    {
      id: 14,
      titleEn: "Contra Mundum - Diocesan Handwritten Magazine",
      titleMl: "കോൺട്രാ മുണ്ടം - കയ്യെഴുത്തു മാസിക ലേഖന ക്ഷണം",
      titleMang: "Contra Mundum Handwritten Magazine Poster",
      category: "service",
      src: "assets/images/contra-mundum-page1.png"
    }
  ],

  // Mar Thoma Church Liturgical Lectionary (വേദഭാഗ ക്രമം)
  lectionary: [
    {
      id: "2026-11-01",
      date: "2026-11-01",
      displayDateEn: "Sunday, Nov 1, 2026",
      displayDateMl: "2026 നവംബർ 1, ഞായർ",
      displayDateMang: "2026 November 1, Njayar",
      month: "November 2026",
      monthKey: "2026-11",
      seasonEn: "Sanctification Season",
      seasonMl: "കൂദോശ് ഈത്തോ കാലം",
      seasonMang: "Koodosh Eetho Kaalam",
      titleEn: "Sanctification of the Church (Koodosh Eetho)",
      titleMl: "സഭാ ശുദ്ധീകരണം (കൂദോശ് ഈത്തോ)",
      titleMang: "Sabha Shudheekaranam (Koodosh Eetho)",
      themeEn: "Church Sanctification and Spiritual Renewal",
      themeMl: "സഭയുടെ വിശുദ്ധീകരണവും ആത്മീയ നവീകരണവും",
      themeMang: "Sabhayude vishudheekaranavum aathmeeya naveekaranavum",
      readings: {
        lesson1: "Exodus 3:1-10",
        lesson2: "1 Peter 2:1-10",
        epistle: "1 Corinthians 3:16-17",
        gospel: "John 2:13-22",
        evening1: "1 Kings 8:22-30",
        evening2: "Psalms 84"
      }
    },
    {
      id: "2026-11-08",
      date: "2026-11-08",
      displayDateEn: "Sunday, Nov 8, 2026",
      displayDateMl: "2026 നവംബർ 8, ഞായർ",
      displayDateMang: "2026 November 8, Njayar",
      month: "November 2026",
      monthKey: "2026-11",
      seasonEn: "Renewal Season",
      seasonMl: "ഹുദോസ് ഈത്തോ കാലം",
      seasonMang: "Hudos Eetho Kaalam",
      titleEn: "Renewal of the Church (Hudos Eetho) / Unity Festival",
      titleMl: "ഹുദോസ് ഈത്തോ - ക്രിസ്തുവിൽ ഒരുമയോടെ ലോകത്തിൽ സാക്ഷികളാകുക",
      titleMang: "Hudos Eetho - Christhuvil Orumayode Lokathil Saakshikalaakuka",
      themeEn: "United in Christ, witnessing to the world; C.S.I., C.N.I, Mar Thoma Churches Unity Festival",
      themeMl: "ക്രിസ്തുവിൽ ഒരുമയോടെ ലോകത്തിൽ സാക്ഷികളാകുക; C.S.I., C.N.I., മാർത്തോമ്മാ സഭകളുടെ ഐക്യ ആഘോഷം",
      themeMang: "Christhuvil orumayode lokathil saakshikalaakuka; CSI, CNI, Mar Thoma Sabhakalude Aikya Aaghosham",
      readings: {
        lesson1: "Isaiah 2:2-4",
        lesson2: "1 Corinthians 1:10-17",
        epistle: "2 Corinthians 13:5-11",
        gospel: "Matthew 18:18-20",
        evening1: "Nehemiah 2:17-20",
        evening2: "Psalms 133"
      }
    },
    {
      id: "2026-11-15",
      date: "2026-11-15",
      displayDateEn: "Sunday, Nov 15, 2026",
      displayDateMl: "2026 നവംബർ 15, ഞായർ",
      displayDateMang: "2026 November 15, Njayar",
      month: "November 2026",
      monthKey: "2026-11",
      seasonEn: "Annunciation Season",
      seasonMl: "അറിയിപ്പ് കാലം",
      seasonMang: "Ariyippu Kaalam",
      titleEn: "Annunciation to Zechariah",
      titleMl: "സെഖര്യാവിനോടുള്ള അറിയിപ്പ്",
      titleMang: "Sekharyaavinodulla Ariyippu",
      themeEn: "God's Salvific Intervention",
      themeMl: "രക്ഷാകരമായ ദൈവിക ഇടപെടൽ",
      themeMang: "Rakshaakaramaya Daivika Idapedal",
      readings: {
        lesson1: "Judges 13:2-14",
        lesson2: "Hebrews 6:9-12",
        epistle: "Romans 10:9-15",
        gospel: "Luke 1:5-23",
        evening1: "Psalms 145",
        evening2: "Revelation 8:1-8"
      }
    },
    {
      id: "2026-11-22",
      date: "2026-11-22",
      displayDateEn: "Sunday, Nov 22, 2026",
      displayDateMl: "2026 നവംബർ 22, ഞായർ",
      displayDateMang: "2026 November 22, Njayar",
      month: "November 2026",
      monthKey: "2026-11",
      seasonEn: "Annunciation Season",
      seasonMl: "അറിയിപ്പ് കാലം",
      seasonMang: "Ariyippu Kaalam",
      titleEn: "Annunciation to Virgin Mary / Diaspora Sunday",
      titleMl: "കന്യക മറിയാമിനോടുള്ള അറിയിപ്പ് / പ്രവാസി ഞായർ",
      titleMang: "Kanyaka Mariyaaminodulla Ariyippu / Pravaasi Njayar",
      themeEn: "Call to be the mother of Jesus Christ, the Saviour of the world",
      themeMl: "ലോകരക്ഷകനായ യേശുക്രിസ്തുവിന്റെ അമ്മയാകാനുള്ള വിളി; പ്രവാസി ഞായർ",
      themeMang: "Lokarakshakanaaya Yeshukristhuvinte ammayaakaanulla vili; Pravaasi Njayar",
      readings: {
        lesson1: "Zechariah 2:10-13",
        lesson2: "Romans 1:1-7",
        epistle: "1 Corinthians 1:26-31",
        gospel: "Luke 1:26-38",
        evening1: "Genesis 18:1-18",
        evening2: "Acts 16:11-15"
      }
    },
    {
      id: "2026-11-29",
      date: "2026-11-29",
      displayDateEn: "Sunday, Nov 29, 2026",
      displayDateMl: "2026 നവംബർ 29, ഞായർ",
      displayDateMang: "2026 November 29, Njayar",
      month: "November 2026",
      monthKey: "2026-11",
      seasonEn: "Annunciation Season",
      seasonMl: "അറിയിപ്പ് കാലം",
      seasonMang: "Ariyippu Kaalam",
      titleEn: "Meeting of Virgin Mary and Elizabeth / CNI Formation Day",
      titleMl: "കന്യക മറിയയും എലീശബെത്തും തമ്മിലുള്ള സംഗമം",
      titleMang: "Kanyaka Mariyayum Elisabethem Thammilulla Sangamam",
      themeEn: "Transcending adversity with hope; Formation Day of Church of North India",
      themeMl: "പ്രതികൂലങ്ങളെ അതിജീവിക്കുന്ന പ്രത്യാശ; CNI സഭാ രൂപീകരണ ദിനം",
      themeMang: "Prathikoolangale athijeevikkunna prathyaasha; CNI Sabha Roopeekarana Dinam",
      readings: {
        lesson1: "Jeremiah 33:10-16",
        lesson2: "Romans 15:1-7",
        epistle: "2 Thessalonians 1:3-5",
        gospel: "Luke 1:39-45",
        evening1: "Genesis 18:1-5",
        evening2: "Psalms 68:11-20"
      }
    },
    {
      id: "2026-11-30",
      date: "2026-11-30",
      displayDateEn: "Monday, Nov 30, 2026",
      displayDateMl: "2026 നവംബർ 30, തിങ്കൾ",
      displayDateMang: "2026 November 30, Thinkal",
      month: "November 2026",
      monthKey: "2026-11",
      seasonEn: "Apostolic Memorial",
      seasonMl: "ശ്ലീഹാ അനുസ്മരണം",
      seasonMang: "Sleeha Anusmaranam",
      titleEn: "The Day of St. Andrew, the Apostle",
      titleMl: "വി. അന്ത്രയോസ് ശ്ലീഹാ നാൾ",
      titleMang: "Vi. Anthrayos Sleeha Naal",
      themeEn: "Called to bring others to Christ",
      themeMl: "ക്രിസ്തുവിലേക്ക് മറ്റുള്ളവരെ നയിക്കാനുള്ള അപ്പസ്തോലിക വിളി",
      themeMang: "Christhuvilekku mattullavare nayikkaanulla appastholika vili",
      readings: {
        lesson1: "Zechariah 8:20-23",
        lesson2: "Romans 8:1-11",
        epistle: "1 Corinthians 4:1-5",
        gospel: "John 1:35-42",
        evening1: "Psalms 19:1-6",
        evening2: "Acts 1:12-14"
      }
    },
    {
      id: "2026-12-01",
      date: "2026-12-01",
      displayDateEn: "Tuesday, Dec 1, 2026",
      displayDateMl: "2026 ഡിസംബർ 1, ചൊവ്വ",
      displayDateMang: "2026 December 1, Chovva",
      month: "December 2026",
      monthKey: "2026-12",
      seasonEn: "Advent Fast",
      seasonMl: "25 നോമ്പ് കാലം",
      seasonMang: "25 Nombu Kaalam",
      titleEn: "Beginning of 25 Days Lent",
      titleMl: "25 നോമ്പാരംഭം",
      titleMang: "25 Nombu Aarambham",
      themeEn: "Spiritual preparation and sanctification for Nativity",
      themeMl: "ക്രിസ്തുമസ് ഒരുക്കത്തിനായുള്ള നോമ്പും പ്രാർത്ഥനയും",
      themeMang: "Christmas orukkathinaayulla nombum praarthanayum",
      readings: {
        lesson1: "Isaiah 2:10-22",
        lesson2: "1 Thessalonians 4:1-12",
        epistle: "Galatians 2:12-20",
        gospel: "John 1:1-15",
        evening1: "Isaiah 40:1-5",
        evening2: "Psalms 51:1-12"
      }
    },
    {
      id: "2026-12-06",
      date: "2026-12-06",
      displayDateEn: "Sunday, Dec 6, 2026",
      displayDateMl: "2026 ഡിസംബർ 6, ഞായർ",
      displayDateMang: "2026 December 6, Njayar",
      month: "December 2026",
      monthKey: "2026-12",
      seasonEn: "Annunciation Season",
      seasonMl: "അറിയിപ്പ് കാലം",
      seasonMang: "Ariyippu Kaalam",
      titleEn: "Birth of John the Baptist / Bible Sunday",
      titleMl: "യോഹന്നാൻ സ്നാപകന്റെ ജനനം / വേദപുസ്തക ഞായർ",
      titleMang: "Yohannan Snapakante Jananam / Vedapusthaka Njayar",
      themeEn: "Word oriented wisdom and discernment",
      themeMl: "വേദപുസ്തക ഞായർ: വചനാധിഷ്ഠിത വിവേകവും വിവേചനവും",
      themeMang: "Vedapusthaka Njayar: Vachanaadhishtitha vivekavum vivechanavum",
      readings: {
        lesson1: "Joshua 24:15-21",
        lesson2: "Galatians 1:11-17",
        epistle: "2 John 1-6",
        gospel: "Luke 1:57-66",
        evening1: "Proverbs 6:20-23",
        evening2: "Psalms 119:105-112"
      }
    },
    {
      id: "2026-12-13",
      date: "2026-12-13",
      displayDateEn: "Sunday, Dec 13, 2026",
      displayDateMl: "2026 ഡിസംബർ 13, ഞായർ",
      displayDateMang: "2026 December 13, Njayar",
      month: "December 2026",
      monthKey: "2026-12",
      seasonEn: "Annunciation Season",
      seasonMl: "അറിയിപ്പ് കാലം",
      seasonMang: "Ariyippu Kaalam",
      titleEn: "Annunciation to Joseph",
      titleMl: "യോസേഫിനോടുള്ള അറിയിപ്പ്",
      titleMang: "Yosephinodulla Ariyippu",
      themeEn: "Embracing God's plan in obedience",
      themeMl: "ദൈവിക പദ്ധതിയെ കൈക്കൊള്ളുക",
      themeMang: "Daivika padhathiye kaikolluka",
      readings: {
        lesson1: "Judges 6:11-24",
        lesson2: "Acts 9:10-19",
        epistle: "1 Thessalonians 5:14-22",
        gospel: "Matthew 1:18-25",
        evening1: "Jeremiah 29:10-14",
        evening2: "Psalms 43"
      }
    },
    {
      id: "2026-12-20",
      date: "2026-12-20",
      displayDateEn: "Sunday, Dec 20, 2026",
      displayDateMl: "2026 ഡിസംബർ 20, ഞായർ",
      displayDateMang: "2026 December 20, Njayar",
      month: "December 2026",
      monthKey: "2026-12",
      seasonEn: "Nativity Season",
      seasonMl: "ജനന പെരുന്നാൾ കാലം",
      seasonMang: "Janana Perunnaal Kaalam",
      titleEn: "Incarnated Word",
      titleMl: "ജഡം ധരിക്കപ്പെട്ട വചനം",
      titleMang: "Jadam Dharikkappetta Vachanam",
      themeEn: "The eternal Word manifested in human history",
      themeMl: "മനുഷ്യകുലത്തിന്റെ രക്ഷയ്ക്കായി അവതരിച്ച വചനം",
      themeMang: "Manushyakulathinte rakshaykkaayi avatharicha vachanam",
      readings: {
        lesson1: "Isaiah 11:1-9",
        lesson2: "Hebrews 1:1-14",
        epistle: "Ephesians 2:13-22",
        gospel: "John 1:1-14",
        evening1: "Zechariah 2:10-13",
        evening2: "Revelation 1:12-20"
      }
    },
    {
      id: "2026-12-21",
      date: "2026-12-21",
      displayDateEn: "Monday, Dec 21, 2026",
      displayDateMl: "2026 ഡിസംബർ 21, തിങ്കൾ",
      displayDateMang: "2026 December 21, Thinkal",
      month: "December 2026",
      monthKey: "2026-12",
      seasonEn: "Church Commemoration",
      seasonMl: "മാർത്തോമ്മാ സഭാ ദിനം",
      seasonMang: "Mar Thoma Sabha Dinam",
      titleEn: "Mar Thoma Church Day",
      titleMl: "മാർത്തോമ്മാ സഭാ ദിനം",
      titleMang: "Mar Thoma Sabha Dinam",
      themeEn: "Grounded in apostolic heritage and reformed faith",
      themeMl: "ശ്ലൈഹിക പാരമ്പര്യത്തിലും നവീകരണ ദർശനത്തിലും അധിഷ്ഠിത സഭ",
      themeMang: "Shlaihika paarambaryathilum naveekarana darshanathilum adhishtitha sabha",
      readings: {
        lesson1: "Joel 2:11-18",
        lesson2: "1 Thessalonians 1:2-10",
        epistle: "2 Timothy 3:10-17",
        gospel: "John 11:5-16",
        evening1: "Psalms 46",
        evening2: "1 Peter 2:4-10"
      }
    },
    {
      id: "2026-12-25",
      date: "2026-12-25",
      displayDateEn: "Friday, Dec 25, 2026",
      displayDateMl: "2026 ഡിസംബർ 25, വെള്ളി",
      displayDateMang: "2026 December 25, Velli",
      month: "December 2026",
      monthKey: "2026-12",
      seasonEn: "Christmas / Nativity",
      seasonMl: "ക്രിസ്തുമസ് പെരുന്നാൾ",
      seasonMang: "Christmas Perunnaal",
      titleEn: "Christmas: The Light for All People",
      titleMl: "ക്രിസ്തുമസ്: സർവ്വജനതകൾക്കുമുള്ള പ്രകാശം",
      titleMang: "Christmas: Sarvvajanathakalkkumulla Prakaasham",
      themeEn: "Glory to God in the highest, and peace on earth",
      themeMl: "അത്യുന്നതങ്ങളിൽ ദൈവത്തിനു മഹത്വം, ഭൂമിയിൽ സന്മനസ്സുള്ളവർക്കു സമാധാനം",
      themeMang: "Athyunnathangalil Daivathinnu mahathwam, bhoomiyil samaadhaanam",
      readings: {
        lesson1: "Isaiah 60:1-7",
        lesson2: "1 John 1:5-10",
        epistle: "Philippians 2:1-11",
        gospel: "Luke 2:1-14",
        evening1: "Micah 5:2-5",
        evening2: "Titus 2:11-14"
      }
    },
    {
      id: "2026-12-27",
      date: "2026-12-27",
      displayDateEn: "Sunday, Dec 27, 2026",
      displayDateMl: "2026 ഡിസംബർ 27, ഞായർ",
      displayDateMang: "2026 December 27, Njayar",
      month: "December 2026",
      monthKey: "2026-12",
      seasonEn: "Post-Nativity",
      seasonMl: "ജനന പെരുന്നാളിന് ശേഷം",
      seasonMang: "Janana Perunnaalinnu Shesham",
      titleEn: "The Glorious Appearance of Our Lord",
      titleMl: "നമ്മുടെ കർത്താവിന്റെ മഹത്വപ്രത്യക്ഷത",
      titleMang: "Nammude Karthavinte Mahathwapratyakshatha",
      themeEn: "Watchfulness and Christian readiness for Christ's return",
      themeMl: "കർത്താവിന്റെ മഹത്വപ്രത്യക്ഷതയ്ക്കായി ഉണർവ്വോടെയുള്ള കാത്തിരിപ്പ്",
      themeMang: "Karthavinte mahathwapratyakshathaykkaayi unarvvodeyulla kaathirippu",
      readings: {
        lesson1: "Malachi 3:13-4:2",
        lesson2: "Acts 1:6-11",
        epistle: "1 Thessalonians 4:13-18",
        gospel: "Matthew 24:42-51",
        evening1: "Psalms 15",
        evening2: "Acts 1:6-11"
      }
    },
    {
      id: "2026-12-31",
      date: "2026-12-31",
      displayDateEn: "Thursday, Dec 31, 2026",
      displayDateMl: "2026 ഡിസംബർ 31, വ്യാഴം",
      displayDateMang: "2026 December 31, Vyazham",
      month: "December 2026",
      monthKey: "2026-12",
      seasonEn: "Year End Watchnight",
      seasonMl: "വർഷാവസാന ആരാധന",
      seasonMang: "Varshaavasana Aaradhana",
      titleEn: "Praising God's Faithfulness (Year End)",
      titleMl: "ദൈവത്തിന്റെ വിശ്വസ്തതയെ സ്തുതിക്കുക",
      titleMang: "Daivathinte Vishwasthathaye Sthuthikkuka",
      themeEn: "Recalling God's steadfast mercies and entering a new year in faith",
      themeMl: "കടന്നുപോയ നാളുകളിലെ ദൈവകൃപകളെ ഓർത്തു നന്ദി കരേറ്റുന്നു",
      themeMang: "Kadannupoya naalukalile Daivakrupakale orthu nandi karettunnu",
      readings: {
        lesson1: "Lamentations 3:21-31",
        lesson2: "1 Timothy 1:12-17",
        epistle: "1 Corinthians 1:1-9",
        gospel: "Luke 17:11-19",
        evening1: "Psalms 90:1-12",
        evening2: "Romans 8:31-39"
      }
    },
    {
      id: "2027-01-01",
      date: "2027-01-01",
      displayDateEn: "Friday, Jan 1, 2027",
      displayDateMl: "2027 ജനുവരി 1, വെള്ളി",
      displayDateMang: "2027 January 1, Velli",
      month: "January 2027",
      monthKey: "2027-01",
      seasonEn: "New Year",
      seasonMl: "പുതുവത്സരം",
      seasonMang: "Puthuvalsaram",
      titleEn: "New Year Service - Covenant with God",
      titleMl: "പുതുവത്സര ആരാധന - ദൈവവുമായുള്ള ഉടമ്പടി",
      titleMang: "Puthuvalsara Aaradhana - Daivavumaayulla Udampadi",
      themeEn: "Walk before me and be blameless",
      themeMl: "പുതിയ വർഷത്തിലേക്ക് ദൈവകൃപയുടെ വഴികാട്ടലോടെ",
      themeMang: "Puthiya varshathilekku Daivakrupayude vazhikaattalode",
      readings: {
        lesson1: "Genesis 12:1-4",
        lesson2: "Revelation 21:1-7",
        epistle: "Colossians 3:12-17",
        gospel: "Luke 13:6-9",
        evening1: "Joshua 1:1-9",
        evening2: "Psalms 121"
      }
    },
    {
      id: "2027-01-03",
      date: "2027-01-03",
      displayDateEn: "Sunday, Jan 3, 2027",
      displayDateMl: "2027 ജനുവരി 3, ഞായർ",
      displayDateMang: "2027 January 3, Njayar",
      month: "January 2027",
      monthKey: "2027-01",
      seasonEn: "Epiphany / Danaha",
      seasonMl: "ദനഹാ കാലം",
      seasonMang: "Danaha Kaalam",
      titleEn: "Epiphany (Danaha) - Baptism of Jesus",
      titleMl: "ദനഹാ പെരുന്നാൾ - യേശുവിന്റെ സ്നാനം",
      titleMang: "Danaha Perunnaal - Yeshuvinte Snaanam",
      themeEn: "The divine manifestation of the Holy Trinity",
      themeMl: "ക്രിസ്തുവിന്റെ പരസ്യ ശുശ്രൂഷാ ആരംഭവും വെളിപ്പാടും",
      themeMang: "Christhuvinte parashya shushroosha aarambhavum velippadum",
      readings: {
        lesson1: "Isaiah 42:1-9",
        lesson2: "Acts 19:1-7",
        epistle: "Titus 3:4-7",
        gospel: "Matthew 3:13-17",
        evening1: "Exodus 14:15-31",
        evening2: "Psalms 29"
      }
    },
    {
      id: "2027-01-17",
      date: "2027-01-17",
      displayDateEn: "Sunday, Jan 17, 2027",
      displayDateMl: "2027 ജനുവരി 17, ഞായർ",
      displayDateMang: "2027 January 17, Njayar",
      month: "January 2027",
      monthKey: "2027-01",
      seasonEn: "Epiphany Season",
      seasonMl: "ദനഹാ കാലം",
      seasonMang: "Danaha Kaalam",
      titleEn: "Ecumenical Sunday - Unity of the Body",
      titleMl: "സഭൈക്യ ഞായർ - ക്രിസ്തുവിൽ ഒന്നാകുക",
      titleMang: "Sabhaikya Njayar - Christhuvil Onnaakuka",
      themeEn: "One Lord, one faith, one baptism",
      themeMl: "ഒരു കർത്താവ്, ഒരു വിശ്വാസം, ഒരു സ്നാനം",
      themeMang: "Oru Karthavu, oru vishwasam, oru snaanam",
      readings: {
        lesson1: "Ezekiel 37:15-28",
        lesson2: "Ephesians 4:1-16",
        epistle: "1 Corinthians 12:12-27",
        gospel: "John 17:15-23",
        evening1: "Psalms 133",
        evening2: "Philippians 2:1-4"
      }
    },
    {
      id: "2027-02-07",
      date: "2027-02-07",
      displayDateEn: "Sunday, Feb 7, 2027",
      displayDateMl: "2027 ഫെബ്രുവരി 7, ഞായർ",
      displayDateMang: "2027 February 7, Njayar",
      month: "February 2027",
      monthKey: "2027-02",
      seasonEn: "Maramon Convention",
      seasonMl: "മാരാമൺ കൺവെൻഷൻ",
      seasonMang: "Maramon Convention",
      titleEn: "Maramon Convention Sunday",
      titleMl: "മാരാമൺ കൺവെൻഷൻ ഞായർ",
      titleMang: "Maramon Convention Njayar",
      themeEn: "Proclaiming the Gospel of the Kingdom",
      themeMl: "രാജ്യത്തിന്റെ സുവിശേഷം സകല ജനത്തോടും ഘോഷിക്കുക",
      themeMang: "Raajyathinte suvishesham sakala janathodum ghoshippin",
      readings: {
        lesson1: "Isaiah 55:1-11",
        lesson2: "Acts 8:26-40",
        epistle: "2 Corinthians 5:14-21",
        gospel: "Luke 4:16-22",
        evening1: "Psalms 96",
        evening2: "Romans 10:14-17"
      }
    },
    {
      id: "2027-02-14",
      date: "2027-02-14",
      displayDateEn: "Sunday, Feb 14, 2027",
      displayDateMl: "2027 ഫെബ്രുവരി 14, ഞായർ",
      displayDateMang: "2027 February 14, Njayar",
      month: "February 2027",
      monthKey: "2027-02",
      seasonEn: "Great Lent",
      seasonMl: "വലിയ നോമ്പ് കാലം",
      seasonMang: "Valiya Nombu Kaalam",
      titleEn: "Pethrutha - Beginning of 50 Days Great Lent",
      titleMl: "പെത്രുത്താ - വലിയ നോമ്പ് ആരംഭം",
      titleMang: "Pethrutha - Valiya Nombu Aarambham",
      themeEn: "Wedding at Cana: Transforming joy and renewal",
      themeMl: "കാനാവിൽ വെച്ചുള്ള അത്ഭുതം: ജീവനവീകരണവും അനുസരണവും",
      themeMang: "Kanaavil vechulla athbhutham: jeeva naveekaranavum anusaranavum",
      readings: {
        lesson1: "Exodus 15:22-26",
        lesson2: "Hebrews 2:1-11",
        epistle: "Romans 12:1-8",
        gospel: "John 2:1-11",
        evening1: "Joel 2:12-19",
        evening2: "Psalms 51"
      }
    },
    {
      id: "2027-03-28",
      date: "2027-03-28",
      displayDateEn: "Sunday, Mar 28, 2027",
      displayDateMl: "2027 മാർച്ച് 28, ഞായർ",
      displayDateMang: "2027 March 28, Njayar",
      month: "March 2027",
      monthKey: "2027-03",
      seasonEn: "Passion Week",
      seasonMl: "കഷ്ടാനുഭവ ആഴ്ച",
      seasonMang: "Kashtanubhava Aazhcha",
      titleEn: "Palm Sunday (Hosanna) - King of Peace",
      titleMl: "ഓശാന ഞായർ - സമാധാനത്തിന്റെ രാജാവ്",
      titleMang: "Oshana Njayar - Samaadhaanathinte Raajaavu",
      themeEn: "Blessed is He who comes in the name of the Lord",
      themeMl: "കർത്താവിന്റെ നാമത്തിൽ വരുന്നവൻ വാഴ്ത്തപ്പെട്ടവൻ",
      themeMang: "Karthavinte naamathil varunnavan vaazhthappettavan",
      readings: {
        lesson1: "Genesis 49:8-12",
        lesson2: "Zechariah 9:9-12",
        epistle: "Philippians 2:5-11",
        gospel: "Luke 19:28-40",
        evening1: "Psalms 118:19-29",
        evening2: "1 Peter 1:3-9"
      }
    },
    {
      id: "2027-04-04",
      date: "2027-04-04",
      displayDateEn: "Sunday, Apr 4, 2027",
      displayDateMl: "2027 ഏപ്രിൽ 4, ഞായർ",
      displayDateMang: "2027 April 4, Njayar",
      month: "April 2027",
      monthKey: "2027-04",
      seasonEn: "Easter / Resurrection",
      seasonMl: "ഈസ്റ്റർ / ഉയിർപ്പ് പെരുന്നാൾ",
      seasonMang: "Easter / Uyirppu Perunnaal",
      titleEn: "Easter Sunday (Kyomtha) - Victory Over Death",
      titleMl: "ഉയിർപ്പ് പെരുന്നാൾ (ക്യംതാ) - മരണത്തിന്മേൽ വിജയം",
      titleMang: "Uyirppu Perunnaal (Kyomtha) - Maranathinmel Vijayam",
      themeEn: "Christ is risen! He is risen indeed!",
      themeMl: "ക്രിസ്തു ഉയിർത്തെഴുന്നേറ്റു! അവൻ സത്യമായി ഉയിർത്തെഴുന്നേറ്റു!",
      themeMang: "Christhu uyirtthezhunnettu! Avan sathyamaayi uyirtthezhunnettu!",
      readings: {
        lesson1: "Isaiah 25:6-9",
        lesson2: "1 Corinthians 15:20-28",
        epistle: "Colossians 3:1-4",
        gospel: "John 20:1-18",
        evening1: "Psalms 16",
        evening2: "Revelation 1:17-18"
      }
    },
    {
      id: "2027-10-17",
      date: "2027-10-17",
      displayDateEn: "Sunday, Oct 17, 2027",
      displayDateMl: "2027 ഒക്ടോബർ 17, ഞായർ",
      displayDateMang: "2027 October 17, Njayar",
      month: "October 2027",
      monthKey: "2027-10",
      seasonEn: "Youth Sakhyam Day",
      seasonMl: "യുവജനസഖ്യ ദിനം",
      seasonMang: "Yuvajana Sakhyam Dinam",
      titleEn: "Mar Thoma Yuvajana Sakhyam Day",
      titleMl: "മാർത്തോമ്മാ യുവജനസഖ്യ ദിനം",
      titleMang: "Mar Thoma Yuvajana Sakhyam Dinam",
      themeEn: "Youth ignited to lighten the world in Christ",
      themeMl: "പ്രകാശിതരായി പ്രകാശിപ്പിക്കുവാൻ വിളിക്കപ്പെട്ട യുവതലമുറ",
      themeMang: "Prakashitharaayi prakaashippikkuvaan vilikkappetta yuvathalamura",
      readings: {
        lesson1: "Ecclesiastes 11:9-12:8",
        lesson2: "1 Timothy 4:6-16",
        epistle: "Romans 12:1-11",
        gospel: "Mark 10:17-27",
        evening1: "Psalms 119:9-16",
        evening2: "1 John 2:12-17"
      }
    }
  ]
};
