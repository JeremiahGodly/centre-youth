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
  ]
};
