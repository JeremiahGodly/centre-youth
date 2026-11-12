# 📋 Google Sheet Setup Guide for Centre Youth (Programs, Leaders & Parishes)

This setup allows Centre Executives and Parish Youth Secretaries to update **Upcoming Programs, C-Shore Camp specifications, Kalamela notices, Leadership Office Bearers, and Parish Activities/Events** anytime from a smartphone or computer without rebuilding or pushing updates to Google Play!

---

## 🚀 Overview of Google Sheet Tabs
In your single Google Spreadsheet, you can have up to **4 tabs**:
1. **`Programs`** (Tab 1): For Centre events, camps, kalamela, and flyers.
2. **`Leaders`** (Tab 2): For Executive Committee office bearers, photos, and contact info.
3. **`Parishes`** (Tab 3): For the 15 Centre Parishes (names, vicars, contacts, map links).
4. **`ParishActivities`** (Tab 4): For activities and events displayed inside each parish's collapsible window in the app.

---

## 1. Setting up Tab 1: `Programs`

Name the first sheet tab: **`Programs`**  
Put these headers across **Row 1 (A to N)**:

| Col | Header Name | Description | Example |
|---|---|---|---|
| **A** | `Title` | Event Title (English) | `LUMORA 2026 (Centre Kalamela)` |
| **B** | `TitleMl` | Event Title (Malayalam) | `ലുമോറ 2026 - സെന്റർ യുവജന കലാമേള` |
| **C** | `Category` | Category tag | `Kalamela` |
| **D** | `Date` | Date | `November 14, 2026 (Saturday)` |
| **E** | `Time` | Time | `9:00 AM Onwards` |
| **F** | `Venue` | Venue | `St. Paul's Mar Thoma Church, Perumbavoor` |
| **G** | `MapUrl` | Google Maps Link | `https://maps.app.goo.gl/...` |
| **H** | `Description` | Specifications / schedule | `18 Competition items across 4 stages adhering to MTYS manual.` |
| **I** | `Speakers` | Chief Guests / Judges | `Executive Committee MTYS & Special Judges` |
| **J** | `PosterImage` | Google Drive image link | `https://drive.google.com/file/d/1zWQ...` |
| **K** | `RegistrationUrl` | Google Form link | `https://forms.gle/...` |
| **L** | `ContactName` | Convener name | `Gibin Saji (Treasurer)` |
| **M** | `ContactPhone` | WhatsApp / Phone | `9061391963` |
| **N** | `IsActive` | Visible? | `TRUE` |

---

## 2. Setting up Tab 2: `Leaders`

Click the **`+` (Add Sheet)** button at the bottom of your Google Spreadsheet to create a 2nd tab and name it: **`Leaders`**.  
Put these headers across **Row 1 (A to J)**:

| Col | Header Name | Description | Example |
|---|---|---|---|
| **A** | `Role` | Office Bearer Role (English) | `President` |
| **B** | `RoleMl` | Role in Malayalam | `പ്രസിഡന്റ്` |
| **C** | `Name` | Leader Name (English) | `Rev. James George` |
| **D** | `NameMl` | Name in Malayalam | `റവ. ജെയിംസ് ജോർജ്` |
| **E** | `Parish` | Home Parish (English) | `Bethel MTC, Keezhillam` |
| **F** | `ParishMl` | Parish in Malayalam | `ബഥേൽ എം.ടി.സി, കീഴില്ലം` |
| **G** | `Phone` | Direct Phone Number | `9446906706` |
| **H** | `WhatsApp` | WhatsApp Number | `9446906706` |
| **I** | `Email` | Email Address | `president@marthoma.org` |
| **J** | `Photo` | Google Drive link to face photo | `https://drive.google.com/file/d/1ABC...` |

---

## 3. Setting up Tab 3: `Parishes`

Click the **`+` (Add Sheet)** button and name the tab: **`Parishes`**.  
Put these headers across **Row 1 (A to O)**:

| Col | Header Name | Description | Example |
|---|---|---|---|
| **A** | `Name` | Parish Name (English) | `Keezhillam Nazareth Mar Thoma Church` |
| **B** | `NameMl` | Parish Name (Malayalam) | `കീഴില്ലം നസ്രേത്ത് മാർത്തോമ്മാ ഇടവക` |
| **C** | `Location` | Place / Area | `Keezhillam, Ernakulam` |
| **D** | `President` | President (Vicar) Name | `Rev. James George` |
| **E** | `PresidentPhone` | President Direct Phone | `9446906706` |
| **F** | `VicePresident` | Vice President (V.P.) Name | `Parish Vice President` |
| **G** | `VicePresidentPhone` | Vice President Phone | `9847000000` |
| **H** | `Secretary` | Secretary (Secre.) Name | `Ashin K Santhosh` |
| **I** | `SecretaryPhone` | Secretary Phone | `9497882179` |
| **J** | `Treasurer` | Treasurer (Trea.) Name | `Parish Treasurer` |
| **K** | `TreasurerPhone` | Treasurer Phone | `9061000000` |
| **L** | `MapUrl` | Google Maps Directions Link | `https://maps.google.com/?q=Keezhillam...` |
| **M** | `Badge` | Tag / Badge | `Vibrant Youth Wing` |
| **N** | `Tag` | Category | `Centre Parish` |
| **O** | `Activities` | Quick Activities (separated by \|) | `Weekly Youth Fellowship - Sundays 11:30 AM \| Cottage Prayer - Saturdays 6:30 PM` |

> 💡 **Leadership Note:**  
> - **`President`**: Replaced *Vicar* with *President* (Vicar serves as President of the Sakhyam).  
> - **Phone Numbers for Each Leader**: Adding `PresidentPhone`, `VicePresidentPhone`, `SecretaryPhone`, and `TreasurerPhone` automatically displays a **one-tap direct dial call button (`📞`)** next to each leader's name in the app!
> - The app automatically sorts all parishes in **Alphabetical Order (A to Z)** regardless of the order entered in the sheet!

---

## 4. Setting up Tab 4: `ParishActivities` (Collapsible Window Events with Posters)

Click **`+` (Add Sheet)** and name the tab: **`ParishActivities`**.  
This tab allows parish secretaries or centre leaders to add specific activities and events (with flyers/posters) that appear inside each parish's collapsible card in the app:

| Col | Header Name | Description | Example |
|---|---|---|---|
| **A** | `Parish` | Name of Parish (or keyword) | `Keezhillam` (or `Perumbavoor`, `Valakom`, etc.) |
| **B** | `Title` | Activity / Event Title | `Youth Fellowship & Bible Study` |
| **C** | `Date` | Date or Frequency | `Every Sunday` (or `Nov 22, 2026`) |
| **D** | `Time` | Time | `11:30 AM` |
| **E** | `Venue` | Venue | `Parish Hall` |
| **F** | `Description` | Activity highlights | `Discussion on Romans 12:2, choir rehearsal & games.` |
| **G** | `Coordinator` | Contact Person | `Ashin K Santhosh (9497882179)` |
| **H** | `PosterUrl` | Google Drive link to event flyer/poster | `https://drive.google.com/file/d/1ABC...` |
| **I** | `Category` | Category tag | `Fellowship` (or `Prayer`, `Sports`, `Kalamela`) |
| **J** | `IsActive` | Visible in app? | `TRUE` |

> 🖼️ **Poster Display in App:**  
> When a Google Drive or web link is pasted into **`PosterUrl`**, the app displays the event poster banner under that parish's events. Tapping the poster opens the interactive full-screen viewer with pinch-to-zoom!

---

## 5. How to Add Photos / Posters (Google Drive)

1. Upload the profile picture or event flyer to **Google Drive**.
2. Right-click the file in Google Drive > **Share** > **Share**.
3. Under *General access*, select **"Anyone with the link can view"** > click **Copy link**.
4. Paste the link into the **`Photo`** column (for leaders) or **`PosterImage`** column (for programs).
   > The app automatically streams it via Google CDN with offline caching!

---

## 6. The Complete Apps Script Code (`Code.gs`)

In your Google Sheet, open **Extensions** > **Apps Script**, replace everything in `Code.gs` with the following code, then click **Save**:

```javascript
/**
 * Keezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam
 * Google Sheet API for Android App & Website
 * Supports:
 *  - Programs (Events, Kalamela, Camps, Posters)
 *  - Leaders (Office Bearers, Photos, Contact Info)
 *  - Parishes (15 Parishes in Alphabetical order, Presidents, Maps, Contacts)
 *  - ParishActivities (Activities & Events under each Parish collapsible window)
 *  - PrayerWall (Shared prayer requests + live POST submissions & pray counter)
 *  - Gallery (Google Drive image links & entire Google Drive Folders)
 *  - Leaderboard (Centre Bible Quiz high scores)
 */

function doGet(e) {
  var ss = SpreadsheetApp.getActiveSpreadsheet();
  
  // 1. Get Programs
  var progSheet = ss.getSheetByName("Programs") || ss.getSheetByName("Events") || ss.getSheets()[0];
  var programs = parseSheet(progSheet, "programs");

  // 2. Get Leaders
  var leaderSheet = ss.getSheetByName("Leaders") || ss.getSheetByName("OfficeBearers");
  var leaders = leaderSheet ? parseSheet(leaderSheet, "leaders") : [];

  // 3. Get Parishes & Parish Activities
  var parishSheet = ss.getSheetByName("Parishes") || ss.getSheetByName("Parish") || ss.getSheetByName("Churches");
  var parishEventsSheet = ss.getSheetByName("ParishActivities") || ss.getSheetByName("ParishEvents") || ss.getSheetByName("Activities");
  var parishes = parseParishes(parishSheet, parishEventsSheet);

  // 4. Get Prayer Wall, Gallery & Leaderboard
  var prayerSheet = ss.getSheetByName("PrayerWall") || ss.getSheetByName("Prayers");
  var prayerWall = prayerSheet ? parsePrayerWall(prayerSheet) : [];

  var gallerySheet = ss.getSheetByName("Gallery") || ss.getSheetByName("Photos");
  var gallery = gallerySheet ? parseGallery(gallerySheet) : [];

  var leaderboardSheet = ss.getSheetByName("Leaderboard") || ss.getSheetByName("QuizScores");
  var leaderboard = leaderboardSheet ? parseLeaderboard(leaderboardSheet) : [];

  var tabParam = (e && e.parameter && e.parameter.tab) ? e.parameter.tab.toLowerCase().trim() : "";
  if (tabParam === "leaders") {
    return createJsonResponse(leaders);
  }
  if (tabParam === "programs" || tabParam === "events") {
    return createJsonResponse(programs);
  }
  if (tabParam === "parishes" || tabParam === "parish") {
    return createJsonResponse(parishes);
  }
  if (tabParam === "prayerwall" || tabParam === "prayers") {
    return createJsonResponse(prayerWall);
  }
  if (tabParam === "gallery" || tabParam === "photos") {
    return createJsonResponse(gallery);
  }
  if (tabParam === "leaderboard" || tabParam === "scores") {
    return createJsonResponse(leaderboard);
  }

  // Default: Return combined payload
  return createJsonResponse({
    programs: programs,
    leaders: leaders,
    parishes: parishes,
    prayerWall: prayerWall,
    gallery: gallery,
    leaderboard: leaderboard
  });
}

function doPost(e) {
  try {
    var ss = SpreadsheetApp.getActiveSpreadsheet();
    var body = JSON.parse(e.postData.contents || "{}");
    var action = body.action || "";

    if (action === "submitPrayer") {
      var sheet = ss.getSheetByName("PrayerWall");
      if (!sheet) {
        sheet = ss.insertSheet("PrayerWall");
        sheet.appendRow(["Id", "Name", "Parish", "Category", "Request", "Date", "PrayedCount", "IsActive"]);
      }
      sheet.insertRowBefore(2);
      sheet.getRange(2, 1, 1, 8).setValues([[
        body.id || ("pw-" + Date.now()),
        body.name || "Anonymous",
        body.parish || "Centre General",
        body.category || "General",
        body.request || "",
        body.date || Utilities.formatDate(new Date(), Session.getScriptTimeZone(), "yyyy-MM-dd"),
        1,
        true
      ]]);
      return createJsonResponse({ status: "ok", action: action });
    }

    if (action === "prayIncrement") {
      var pSheet = ss.getSheetByName("PrayerWall");
      if (pSheet && pSheet.getLastRow() >= 2) {
        var rows = pSheet.getDataRange().getValues();
        for (var i = 1; i < rows.length; i++) {
          if (String(rows[i][0]).trim() === String(body.id).trim()) {
            var current = parseInt(rows[i][6], 10) || 0;
            pSheet.getRange(i + 1, 7).setValue(current + 1);
            break;
          }
        }
      }
      return createJsonResponse({ status: "ok", action: action });
    }

    if (action === "submitScore") {
      var lbSheet = ss.getSheetByName("Leaderboard");
      if (!lbSheet) {
        lbSheet = ss.insertSheet("Leaderboard");
        lbSheet.appendRow(["Name", "Parish", "Score", "Stars", "HighestLevel", "UpdatedAt"]);
      }
      var targetName = String(body.name || "").trim();
      var targetParish = String(body.parish || "").trim();
      var newScore = parseInt(body.score, 10) || 0;
      var newStars = parseInt(body.stars, 10) || 0;
      var newLvl = parseInt(body.highestLevel, 10) || 1;
      var updated = Utilities.formatDate(new Date(), Session.getScriptTimeZone(), "yyyy-MM-dd");

      var lbRows = lbSheet.getDataRange().getValues();
      var foundRow = -1;
      for (var r = 1; r < lbRows.length; r++) {
        if (String(lbRows[r][0]).trim().toLowerCase() === targetName.toLowerCase() &&
            String(lbRows[r][1]).trim().toLowerCase() === targetParish.toLowerCase()) {
          foundRow = r + 1;
          break;
        }
      }
      if (foundRow > 0) {
        lbSheet.getRange(foundRow, 3, 1, 4).setValues([[newScore, newStars, newLvl, updated]]);
      } else {
        lbSheet.appendRow([targetName, targetParish, newScore, newStars, newLvl, updated]);
      }
      return createJsonResponse({ status: "ok", action: action });
    }

    return createJsonResponse({ status: "ignored", message: "Unknown action" });
  } catch (err) {
    return createJsonResponse({ status: "error", message: String(err) });
  }
}

function parseSheet(sheet, type) {
  if (!sheet) return [];
  var rows = sheet.getDataRange().getValues();
  if (rows.length < 2) return [];

  var rawHeaders = rows[0];
  var headers = rawHeaders.map(function(h) {
    return String(h).trim().toLowerCase().replace(/[^a-z0-9]/g, '');
  });

  var result = [];
  for (var i = 1; i < rows.length; i++) {
    var row = rows[i];
    if (!row[0] && !row[1]) continue;
    var item = {};
    for (var j = 0; j < headers.length; j++) {
      var key = headers[j];
      var val = row[j];
      if (val instanceof Date) {
        val = Utilities.formatDate(val, Session.getScriptTimeZone(), "yyyy-MM-dd");
      }
      item[key] = String(val).trim();
    }

    if (type === "leaders") {
      result.push({
        id: item.id || ("leader-" + i),
        roleEn: item.role || item.roleen || item.designation || "",
        roleMl: item.roleml || item.malayalamrole || "",
        nameEn: item.name || item.nameen || item.leadername || "",
        nameMl: item.nameml || item.malayalamname || "",
        parishEn: item.parish || item.parishen || item.homeparish || "",
        parishMl: item.parishml || "",
        phone: item.phone || item.mobile || item.contact || "",
        whatsapp: item.whatsapp || item.phone || "",
        email: item.email || "",
        image: item.photo || item.image || item.picture || item.imageurl || ""
      });
    } else {
      // Programs / Events
      var isActiveVal = (item.isactive !== undefined && item.isactive !== "") 
        ? String(item.isactive).toUpperCase() !== "FALSE" 
        : true;

      result.push({
        id: item.id || ("evt-" + i),
        title: item.title || item.programtitle || item.program || "",
        titleMl: item.titleml || item.malayalamtitle || "",
        category: item.category || item.type || "Event",
        date: item.date || item.datetime || "",
        time: item.time || "",
        venue: item.venue || item.location || "",
        mapUrl: item.mapurl || item.mapslink || "",
        description: item.description || item.details || item.desc || "",
        speakers: item.speakers || item.guests || "",
        imageUrl: item.posterimage || item.poster || item.image || item.photo || "",
        registrationUrl: item.registrationurl || item.formurl || item.link || "",
        contactName: item.contactname || item.convener || "",
        contactPhone: item.contactphone || item.convenerphone || item.phone || "",
        isActive: isActiveVal
      });
    }
  }
  return result;
}

/**
 * Parses Parishes and merges individual Parish Activities
 * Returns parishes sorted in ALPHABETICAL ORDER (A to Z)
 */
function parseParishes(parishSheet, activitiesSheet) {
  // Default list of 15 Centre Parishes (fallback if sheet is blank or missing columns)
  var defaultParishes = [
    { id: "adimali", nameEn: "Adimali Mar Thoma Church", nameMl: "അടിമാലി മാർത്തോമ്മാ ഇടവക", location: "Adimali, Idukki", tag: "High Range Parish", badge: "Energetic Fellowship", presidentEn: "Rev. Vicar", vicePresident: "", secretary: "", treasurer: "" },
    { id: "illithode", nameEn: "Illithode Mar Thoma Church & Mission Field", nameMl: "ഇല്ലിത്തോട് മാർത്തോമ്മാ ഇടവക / മിഷൻ ഫീൽഡ്", location: "Illithode, Malayattoor Forest Border", tag: "Mission Outpost", badge: "Mission Outreach", presidentEn: "Rev. Vicar", vicePresident: "", secretary: "", treasurer: "" },
    { id: "keezhillam", nameEn: "Keezhillam Nazareth Mar Thoma Church", nameMl: "കീഴില്ലം നസ്രേത്ത് മാർത്തോമ്മാ ഇടവക", location: "Keezhillam, Ernakulam", tag: "Centre Parish", badge: "Vibrant Youth Wing", presidentEn: "Rev. James George", vicePresident: "Parish Vice President", secretary: "Ashin K Santhosh", secretaryPhone: "9497882179", treasurer: "Parish Treasurer", phone: "9446906706" },
    { id: "kolenchery", nameEn: "Kolenchery St. Thomas Mar Thoma Church", nameMl: "കോലഞ്ചേരി സെന്റ് തോമസ് മാർത്തോമ്മാ ഇടവക", location: "Kolenchery, Ernakulam", tag: "Centre Hub", badge: "Dynamic Youth", presidentEn: "Rev. Vicar", vicePresident: "", secretary: "Youth Secretary", treasurer: "" },
    { id: "kothamangalam", nameEn: "Kothamangalam St. Thomas Mar Thoma Church", nameMl: "കോതമംഗലം സെന്റ് തോമസ് മാർത്തോമ്മാ ഇടവക", location: "Kothamangalam", tag: "Gateway Parish", badge: "Active Participation", presidentEn: "Rev. Vicar", vicePresident: "", secretary: "", treasurer: "" },
    { id: "kuttampuzha", nameEn: "Kuttampuzha St. Stephen's Mar Thoma Church", nameMl: "കുട്ടമ്പുഴ സെന്റ് സ്റ്റീഫൻസ് മാർത്തോമ്മാ ഇടവക", location: "Kuttampuzha, High Ranges", tag: "Parish Fellowship", badge: "Scenic Foothills", presidentEn: "Rev. Vicar", vicePresident: "", secretary: "", treasurer: "" },
    { id: "methala", nameEn: "Methala Mar Thoma Church", nameMl: "മേതല മാർത്തോമ്മാ ഇടവക", location: "Methala, Asamannoor", tag: "Parish Fellowship", badge: "Warm Fellowship", presidentEn: "Rev. Vicar", vicePresident: "", secretary: "", treasurer: "" },
    { id: "munnar", nameEn: "Munnar Christ Mar Thoma Church", nameMl: "മൂന്നാർ ക്രൈസ്റ്റ് മാർത്തോമ്മാ ഇടവക", location: "Munnar Hill Station, Idukki", tag: "High Range Flagship", badge: "Heritage Hills", presidentEn: "Rev. Vicar", vicePresident: "", secretary: "Munnar Youth Wing", treasurer: "" },
    { id: "nellimattam", nameEn: "Nellimattam Bethel Mar Thoma Church", nameMl: "നെല്ലിമറ്റം ബഥേൽ മാർത്തോമ്മാ ഇടവക", location: "Nellimattam, Kavalangad", tag: "Parish Fellowship", badge: "Enthusiastic Youth", presidentEn: "Rev. Vicar", vicePresident: "", secretary: "", treasurer: "" },
    { id: "oonjappara", nameEn: "Oonjappara Mar Thoma Church", nameMl: "ഊഞ്ഞാപ്പാറ മാർത്തോമ്മാ ഇടവക", location: "Oonjappara, Kothamangalam", tag: "Parish Fellowship", badge: "Faithful Community", presidentEn: "Rev. Vicar", vicePresident: "", secretary: "", treasurer: "" },
    { id: "paraikkathanam", nameEn: "Paraikkathanam St. Thomas Mar Thoma Church", nameMl: "പരയ്ക്കത്താനം സെന്റ് തോമസ് മാർത്തോമ്മാ ഇടവക", location: "Paraikkathanam, Ernakulam", tag: "Parish Fellowship", badge: "United Sakhyam", presidentEn: "Rev. Vicar", vicePresident: "", secretary: "", treasurer: "" },
    { id: "perumbavoor", nameEn: "Perumbavoor St. Paul's Mar Thoma Church", nameMl: "പെരുമ്പാവൂർ സെന്റ് പോൾസ് മാർത്തോമ്മാ ഇടവക", location: "Perumbavoor Town", tag: "Kalamela 2026 Venue", badge: "Historic Parish", presidentEn: "Rev. Vicar", vicePresident: "Parish Vice President", secretary: "Youth Secretary", secretaryPhone: "9061391963", treasurer: "Gibin Saji", phone: "9061391963" },
    { id: "poika", nameEn: "Poika Ebenezer Mar Thoma Church", nameMl: "പൊയ്ക എബനേസർ മാർത്തോമ്മാ ഇടവക", location: "Poika, Perumbavoor", tag: "Sports Champions Parish", badge: "Best Goalkeeper Award", presidentEn: "Rev. Vicar", vicePresident: "", secretary: "", treasurer: "" },
    { id: "rayamangalam", nameEn: "Rayamangalam Bethlehem Mar Thoma Church", nameMl: "രായമംഗലം ബേത്‌ലഹേം മാർത്തോമ്മാ ഇടവക", location: "Rayamangalam, Kuruppampady", tag: "Parish Fellowship", badge: "Devout Community", presidentEn: "Rev. Vicar", vicePresident: "", secretary: "", treasurer: "" },
    { id: "valakom", nameEn: "Valakom Mar Thoma Church", nameMl: "വാളകം മാർത്തോമ്മാ ഇടവക", location: "Valakom, Muvattupuzha", tag: "2026-27 Inauguration Venue", badge: "Active Sakhyam", presidentEn: "Rev. Vicar", vicePresident: "Parish Vice President", secretary: "Aleena Mariya Joy", secretaryPhone: "9656128738", treasurer: "Parish Treasurer", phone: "9656128738" }
  ];

  var parishes = [];
  var parishMap = {};

  if (parishSheet && parishSheet.getLastRow() >= 2) {
    var pRows = parishSheet.getDataRange().getValues();
    var pHeaders = pRows[0].map(function(h) {
      return String(h).trim().toLowerCase().replace(/[^a-z0-9]/g, '');
    });

    for (var i = 1; i < pRows.length; i++) {
      var r = pRows[i];
      if (!r[0] && !r[1]) continue;
      var pObj = {};
      for (var j = 0; j < pHeaders.length; j++) {
        pObj[pHeaders[j]] = String(r[j] || "").trim();
      }

      var nameEn = pObj.name || pObj.nameen || pObj.parish || pObj.parishname || "";
      if (!nameEn) continue;

      var pId = pObj.id || nameEn.toLowerCase().replace(/[^a-z0-9]/g, '');

      // Parse inline activities if provided as pipe '|' or newline separated in Parishes sheet
      var inlineActivities = [];
      var actStr = pObj.activities || pObj.events || "";
      if (actStr) {
        var actLines = actStr.split(/[\n|;]+/);
        for (var a = 0; a < actLines.length; a++) {
          var line = actLines[a].trim();
          if (line) {
            inlineActivities.push({
              id: pId + "-act-" + (a + 1),
              title: line,
              date: "Upcoming",
              time: "",
              venue: pObj.location || "",
              description: "",
              coordinator: "",
              category: "Activity"
            });
          }
        }
      }

      // Leadership: President (formerly Vicar), V.P, Secretary, Treasurer with individual phone numbers
      var president = pObj.president || pObj.presidenten || pObj.vicar || pObj.vicaren || "";
      var presidentPhone = pObj.presidentphone || pObj.vicarphone || pObj.presidentcontact || pObj.phone || "";
      var vicePresident = pObj.vicepresident || pObj.vp || "";
      var vicePresidentPhone = pObj.vicepresidentphone || pObj.vpphone || "";
      var secretary = pObj.secretary || pObj.secre || pObj.sec || "";
      var secretaryPhone = pObj.secretaryphone || pObj.secphone || "";
      var treasurer = pObj.treasurer || pObj.trea || pObj.tres || "";
      var treasurerPhone = pObj.treasurerphone || pObj.treaphone || "";

      var pData = {
        id: pId,
        nameEn: nameEn,
        nameMl: pObj.nameml || "",
        location: pObj.location || pObj.place || pObj.placeen || "",
        placeEn: pObj.location || pObj.place || pObj.placeen || "",
        presidentEn: president,
        vicarEn: president, // backward compatibility
        presidentMl: pObj.presidentml || pObj.vicarml || "",
        presidentPhone: presidentPhone,
        vicePresident: vicePresident,
        vicePresidentPhone: vicePresidentPhone,
        secretary: secretary,
        secretaryPhone: secretaryPhone,
        treasurer: treasurer,
        treasurerPhone: treasurerPhone,
        phone: secretaryPhone || presidentPhone || "",
        mapUrl: pObj.mapurl || pObj.mapslink || ("https://maps.google.com/?q=" + encodeURIComponent(nameEn)),
        tag: pObj.tag || "Centre Parish",
        badge: pObj.badge || "Youth Fellowship",
        activities: inlineActivities
      };

      parishes.push(pData);
      parishMap[pId] = pData;
      parishMap[nameEn.toLowerCase()] = pData;
      var firstWord = nameEn.split(/\s+/)[0].toLowerCase();
      parishMap[firstWord] = pData;
    }
  }

  // If no parishes found in sheet, use the 15 defaults
  if (parishes.length === 0) {
    parishes = defaultParishes.map(function(dp) {
      var copy = Object.assign({}, dp);
      copy.activities = [];
      copy.mapUrl = "https://maps.google.com/?q=" + encodeURIComponent(copy.nameEn);
      parishMap[copy.id] = copy;
      parishMap[copy.nameEn.toLowerCase()] = copy;
      var fWord = copy.nameEn.split(/\s+/)[0].toLowerCase();
      parishMap[fWord] = copy;
      return copy;
    });
  }

  // Parse separate ParishActivities / ParishEvents sheet if present
  if (activitiesSheet && activitiesSheet.getLastRow() >= 2) {
    var aRows = activitiesSheet.getDataRange().getValues();
    var aHeaders = aRows[0].map(function(h) {
      return String(h).trim().toLowerCase().replace(/[^a-z0-9]/g, '');
    });

    for (var k = 1; k < aRows.length; k++) {
      var aRow = aRows[k];
      if (!aRow[0] && !aRow[1]) continue;
      var aObj = {};
      for (var l = 0; l < aHeaders.length; l++) {
        var aVal = aRow[l];
        if (aVal instanceof Date) {
          aVal = Utilities.formatDate(aVal, Session.getScriptTimeZone(), "yyyy-MM-dd");
        }
        aObj[aHeaders[l]] = String(aVal || "").trim();
      }

      var targetParish = aObj.parish || aObj.church || aObj.parishname || "";
      var title = aObj.title || aObj.activity || aObj.event || "";
      if (!title || !targetParish) continue;

      var isActive = (aObj.isactive !== undefined && aObj.isactive !== "")
        ? String(aObj.isactive).toUpperCase() !== "FALSE"
        : true;
      if (!isActive) continue;

      var posterUrl = aObj.posterurl || aObj.posterimage || aObj.poster || aObj.image || aObj.imageurl || aObj.photo || "";

      var activityItem = {
        id: "act-" + k,
        title: title,
        date: aObj.date || aObj.datetime || "",
        time: aObj.time || "",
        venue: aObj.venue || aObj.location || "",
        description: aObj.description || aObj.desc || aObj.details || "",
        coordinator: aObj.coordinator || aObj.contact || aObj.leader || "",
        category: aObj.category || aObj.type || "Activity",
        posterUrl: posterUrl,
        imageUrl: posterUrl
      };

      // Exact or intelligent matching to target parish
      var q = targetParish.toLowerCase().trim();
      if (q === "all" || q === "centre" || q === "all parishes") {
        for (var n = 0; n < parishes.length; n++) {
          parishes[n].activities.push(activityItem);
        }
      } else if (parishMap[q]) {
        parishMap[q].activities.push(activityItem);
      } else {
        for (var m = 0; m < parishes.length; m++) {
          var pName = parishes[m].nameEn.toLowerCase();
          var pId = parishes[m].id.toLowerCase();
          var pLoc = (parishes[m].location || "").toLowerCase();
          if (pName.indexOf(q) !== -1 || q.indexOf(pId) !== -1 || pLoc.indexOf(q) !== -1) {
            parishes[m].activities.push(activityItem);
            break;
          }
        }
      }
    }
  }

  // Sort parishes ALPHABETICALLY (A to Z)
  parishes.sort(function(a, b) {
    return a.nameEn.localeCompare(b.nameEn);
  });

  return parishes;
}

function parsePrayerWall(sheet) {
  if (!sheet || sheet.getLastRow() < 2) return [];
  var rows = sheet.getDataRange().getValues();
  var headers = rows[0].map(function(h) { return String(h).trim().toLowerCase().replace(/[^a-z0-9]/g, ''); });
  var result = [];
  for (var i = 1; i < rows.length; i++) {
    var r = rows[i];
    if (!r[0] && !r[4]) continue;
    var obj = {};
    for (var j = 0; j < headers.length; j++) {
      var val = r[j];
      if (val instanceof Date) val = Utilities.formatDate(val, Session.getScriptTimeZone(), "yyyy-MM-dd");
      obj[headers[j]] = String(val || "").trim();
    }
    var reqText = obj.request || obj.prayer || obj.message || "";
    if (!reqText) continue;
    var isActive = (obj.isactive !== undefined && obj.isactive !== "") ? String(obj.isactive).toUpperCase() !== "FALSE" : true;
    if (!isActive) continue;
    result.push({
      id: obj.id || ("pw-" + i),
      name: obj.name || "Anonymous",
      parish: obj.parish || "Centre General",
      category: obj.category || "General",
      request: reqText,
      date: obj.date || "",
      prayedCount: parseInt(obj.prayedcount, 10) || 0,
      isActive: true
    });
  }
  return result;
}

function parseGallery(sheet) {
  if (!sheet || sheet.getLastRow() < 2) return [];
  var rows = sheet.getDataRange().getValues();
  var headers = rows[0].map(function(h) { return String(h).trim().toLowerCase().replace(/[^a-z0-9]/g, ''); });
  var result = [];
  for (var i = 1; i < rows.length; i++) {
    var r = rows[i];
    if (!r[0] && !r[1] && !r[4]) continue;
    var obj = {};
    for (var j = 0; j < headers.length; j++) {
      var val = r[j];
      if (val instanceof Date) val = Utilities.formatDate(val, Session.getScriptTimeZone(), "yyyy-MM-dd");
      obj[headers[j]] = String(val || "").trim();
    }
    var url = obj.imageurl || obj.drivelink || obj.url || obj.src || obj.folderurl || "";
    if (!url) continue;
    var isActive = (obj.isactive !== undefined && obj.isactive !== "") ? String(obj.isactive).toUpperCase() !== "FALSE" : true;
    if (!isActive) continue;

    // Automatically expand a Google Drive Folder link into individual images
    var folderMatch = url.match(/folders\/([a-zA-Z0-9_-]+)/);
    if (folderMatch && folderMatch[1]) {
      try {
        var folder = DriveApp.getFolderById(folderMatch[1]);
        var files = folder.getFiles();
        var count = 0;
        while (files.hasNext() && count < 40) {
          var file = files.next();
          if (file.getMimeType().indexOf("image/") === 0) {
            result.push({
              id: "gal-drv-" + file.getId(),
              title: file.getName().replace(/\.[^/.]+$/, ""),
              titleMl: obj.titleml || "",
              category: obj.category || "Drive Memories",
              date: obj.date || Utilities.formatDate(file.getDateCreated(), Session.getScriptTimeZone(), "yyyy-MM-dd"),
              imageUrl: "https://drive.google.com/file/d/" + file.getId() + "/view?usp=sharing",
              caption: obj.caption || folder.getName(),
              isActive: true
            });
            count++;
          }
        }
      } catch (err) {}
    } else {
      result.push({
        id: obj.id || ("gal-" + i),
        title: obj.title || obj.titleen || "Centre Memory",
        titleMl: obj.titleml || "",
        category: obj.category || "Memories",
        date: obj.date || "",
        imageUrl: url,
        caption: obj.caption || "",
        isActive: true
      });
    }
  }
  return result;
}

function parseLeaderboard(sheet) {
  if (!sheet || sheet.getLastRow() < 2) return [];
  var rows = sheet.getDataRange().getValues();
  var headers = rows[0].map(function(h) { return String(h).trim().toLowerCase().replace(/[^a-z0-9]/g, ''); });
  var result = [];
  for (var i = 1; i < rows.length; i++) {
    var r = rows[i];
    if (!r[0]) continue;
    var obj = {};
    for (var j = 0; j < headers.length; j++) {
      var val = r[j];
      if (val instanceof Date) val = Utilities.formatDate(val, Session.getScriptTimeZone(), "yyyy-MM-dd");
      obj[headers[j]] = String(val || "").trim();
    }
    var name = obj.name || "";
    if (!name) continue;
    result.push({
      id: "lb-" + i,
      name: name,
      parish: obj.parish || "Centre Youth",
      score: parseInt(obj.score, 10) || 0,
      stars: parseInt(obj.stars, 10) || 0,
      highestLevel: parseInt(obj.highestlevel, 10) || 1,
      updatedAt: obj.updatedat || ""
    });
  }
  result.sort(function(a, b) { return b.score - a.score; });
  return result;
}

function createJsonResponse(data) {
  var output = ContentService.createTextOutput(JSON.stringify(data));
  output.setMimeType(ContentService.MimeType.JSON);
  return output;
}
```

---

## 7. Optional New Tabs (`PrayerWall`, `Gallery`, `Leaderboard`)
You can add these optional tabs to your Sheet (`PrayerWall` and `Leaderboard` are also **auto-created automatically** by the app the very first time anyone submits a prayer or score!):

* **`Gallery` Tab Columns:** `Title` | `TitleMl` | `Category` | `Date` | `ImageUrl` (paste either a single Drive photo link OR an entire shared **Google Drive Folder link**!) | `Caption` | `IsActive`
* **`PrayerWall` Tab Columns:** `Id` | `Name` | `Parish` | `Category` | `Request` | `Date` | `PrayedCount` | `IsActive`
* **`Leaderboard` Tab Columns:** `Name` | `Parish` | `Score` | `Stars` | `HighestLevel` | `UpdatedAt`

---

## 8. How to Deploy the Updated Web App
1. In Google Apps Script, click **Deploy** > **Manage deployments**.
2. Click the **pencil icon (Edit)** next to your active Web app deployment.
3. Under **Version**, select **New version**.
4. Click **Deploy** (accept any Drive read permission prompt if using Drive folder auto-sync).
   > Your Web App URL remains the exact same, and all tabs (Programs, Leaders, Parishes, Parish Activities, Prayer Wall, Gallery, and Quiz Leaderboard) will sync live!
