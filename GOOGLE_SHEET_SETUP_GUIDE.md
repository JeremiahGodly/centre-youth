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
Put these headers across **Row 1 (A to L)**:

| Col | Header Name | Description | Example |
|---|---|---|---|
| **A** | `Name` | Parish Name (English) | `Keezhillam Nazareth Mar Thoma Church` |
| **B** | `NameMl` | Parish Name (Malayalam) | `കീഴില്ലം നസ്രേത്ത് മാർത്തോമ്മാ ഇടവക` |
| **C** | `Location` | Place / Area | `Keezhillam, Ernakulam` |
| **D** | `President` | President (Vicar) Name | `Rev. James George` |
| **E** | `VicePresident` | Vice President (V.P.) Name | `Parish Vice President` |
| **F** | `Secretary` | Secretary (Secre.) Name | `Ashin K Santhosh` |
| **G** | `Treasurer` | Treasurer (Trea.) Name | `Parish Treasurer` |
| **H** | `Phone` | Contact Phone / Secretary Phone | `9497882179` |
| **I** | `MapUrl` | Google Maps Directions Link | `https://maps.google.com/?q=Keezhillam...` |
| **J** | `Badge` | Tag / Badge | `Vibrant Youth Wing` |
| **K** | `Tag` | Category | `Centre Parish` |
| **L** | `Activities` | Quick Activities (separated by \|) | `Weekly Youth Fellowship - Sundays 11:30 AM \| Cottage Prayer - Saturdays 6:30 PM` |

> 💡 **Leadership Note:**  
> - **`President`**: Replaced *Vicar* with *President* (Vicar serves as President of the Sakhyam).  
> - **`VicePresident`**, **`Secretary`**, **`Treasurer`**: Shows the full parish youth leadership team in the app's collapsible window with one-tap phone call buttons.
> - The app automatically sorts all parishes in **Alphabetical Order (A to Z)** regardless of the order entered in the sheet!

---

## 4. Setting up Tab 4: `ParishActivities` (Collapsible Window Events)

Click **`+` (Add Sheet)** and name the tab: **`ParishActivities`**.  
This tab allows parish secretaries or centre leaders to add specific activities and events that appear inside each parish's collapsible card in the app:

| Col | Header Name | Description | Example |
|---|---|---|---|
| **A** | `Parish` | Name of Parish (or key word) | `Keezhillam` (or `Perumbavoor`, `Valakom`, etc.) |
| **B** | `Title` | Activity / Event Title | `Youth Fellowship & Bible Study` |
| **C** | `Date` | Date or Frequency | `Every Sunday` (or `Nov 22, 2026`) |
| **D** | `Time` | Time | `11:30 AM` |
| **E** | `Venue` | Venue | `Parish Hall` |
| **F** | `Description` | Activity highlights | `Discussion on Romans 12:2, choir rehearsal & games.` |
| **G** | `Coordinator` | Contact Person | `Ashin K Santhosh (9497882179)` |
| **H** | `Category` | Category tag | `Fellowship` (or `Prayer`, `Sports`, `Kalamela`) |
| **I** | `IsActive` | Visible in app? | `TRUE` |

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
 *  - Parishes (15 Parishes in Alphabetical order, Vicars, Maps, Contacts)
 *  - ParishActivities (Activities & Events under each Parish collapsible window)
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

  // Default: Return combined payload
  return createJsonResponse({
    programs: programs,
    leaders: leaders,
    parishes: parishes
  });
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

      // Leadership: President (formerly Vicar), V.P, Secretary, Treasurer
      var president = pObj.president || pObj.presidenten || pObj.vicar || pObj.vicaren || "";
      var vicePresident = pObj.vicepresident || pObj.vp || "";
      var secretary = pObj.secretary || pObj.secre || pObj.sec || "";
      var treasurer = pObj.treasurer || pObj.trea || pObj.tres || "";
      var secPhone = pObj.phone || pObj.secretaryphone || pObj.mobile || pObj.contact || "";

      var pData = {
        id: pId,
        nameEn: nameEn,
        nameMl: pObj.nameml || "",
        location: pObj.location || pObj.place || pObj.placeen || "",
        placeEn: pObj.location || pObj.place || pObj.placeen || "",
        presidentEn: president,
        vicarEn: president, // backward compatibility
        presidentMl: pObj.presidentml || pObj.vicarml || "",
        vicePresident: vicePresident,
        secretary: secretary,
        treasurer: treasurer,
        secretaryPhone: secPhone,
        phone: secPhone,
        mapUrl: pObj.mapurl || pObj.mapslink || ("https://maps.google.com/?q=" + encodeURIComponent(nameEn)),
        tag: pObj.tag || "Centre Parish",
        badge: pObj.badge || "Youth Fellowship",
        activities: inlineActivities
      };

      parishes.push(pData);
      parishMap[pId] = pData;
      parishMap[nameEn.toLowerCase()] = pData;
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
      if (!title) continue;

      var isActive = (aObj.isactive !== undefined && aObj.isactive !== "")
        ? String(aObj.isactive).toUpperCase() !== "FALSE"
        : true;
      if (!isActive) continue;

      var activityItem = {
        id: "act-" + k,
        title: title,
        date: aObj.date || aObj.datetime || "",
        time: aObj.time || "",
        venue: aObj.venue || aObj.location || "",
        description: aObj.description || aObj.desc || aObj.details || "",
        coordinator: aObj.coordinator || aObj.contact || aObj.leader || "",
        category: aObj.category || aObj.type || "Activity"
      };

      // Match target parish
      var matched = false;
      var q = targetParish.toLowerCase().trim();
      if (q && parishMap[q]) {
        parishMap[q].activities.push(activityItem);
        matched = true;
      } else if (q) {
        for (var m = 0; m < parishes.length; m++) {
          var pName = parishes[m].nameEn.toLowerCase();
          var pId = parishes[m].id.toLowerCase();
          if (pName.indexOf(q) !== -1 || q.indexOf(pId) !== -1) {
            parishes[m].activities.push(activityItem);
            matched = true;
            break;
          }
        }
      }

      // If no match, add to first parish
      if (!matched && parishes.length > 0) {
        parishes[0].activities.push(activityItem);
      }
    }
  }

  // Sort parishes ALPHABETICALLY (A to Z)
  parishes.sort(function(a, b) {
    return a.nameEn.localeCompare(b.nameEn);
  });

  return parishes;
}

function createJsonResponse(data) {
  var output = ContentService.createTextOutput(JSON.stringify(data));
  output.setMimeType(ContentService.MimeType.JSON);
  return output;
}
```

---

## 7. How to Deploy the Updated Web App
1. In Google Apps Script, click **Deploy** > **Manage deployments**.
2. Click the **pencil icon (Edit)** next to your active Web app deployment.
3. Under **Version**, select **New version**.
4. Click **Deploy**.
   > Your Web App URL remains the exact same, and both the Android App and website will immediately start syncing all Parishes and Parish Activities!
