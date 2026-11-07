# 📋 Google Sheet Setup Guide for Centre Youth (Programs & Leadership)

This setup allows Centre Executives (President, Secretary, Conveners) to update **Upcoming Programs, C-Shore Camp specifications, Kalamela notices, and Leadership Office Bearers with photos** anytime from a smartphone or computer without rebuilding or pushing updates to Google Play!

---

## 🚀 Overview
In your single Google Spreadsheet, you can have **two tabs**:
1. **`Programs`** (Tab 1): For events, camps, kalamela, and flyers.
2. **`Leaders`** (Tab 2): For Executive Committee office bearers, photos, and contact info.

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

## 3. How to Add Photos / Posters (Google Drive)

1. Upload the profile picture or event flyer to **Google Drive**.
2. Right-click the file in Google Drive > **Share** > **Share**.
3. Under *General access*, select **"Anyone with the link can view"** > click **Copy link**.
4. Paste the link into the **`Photo`** column (for leaders) or **`PosterImage`** column (for programs).
   > The app automatically streams it via Google CDN with offline caching!

---

## 4. The Complete Apps Script Code

In your Google Sheet, open **Extensions** > **Apps Script**, replace everything in `Code.gs` with the following code, then click **Save**:

```javascript
function doGet(e) {
  var ss = SpreadsheetApp.getActiveSpreadsheet();
  
  // 1. Get Programs
  var progSheet = ss.getSheetByName("Programs") || ss.getSheets()[0];
  var programs = parseSheet(progSheet, "programs");

  // 2. Get Leaders
  var leaderSheet = ss.getSheetByName("Leaders");
  var leaders = leaderSheet ? parseSheet(leaderSheet, "leaders") : [];

  var tabParam = (e && e.parameter && e.parameter.tab) ? e.parameter.tab.toLowerCase().trim() : "";
  if (tabParam === "leaders") {
    return createJsonResponse(leaders);
  }
  if (tabParam === "programs") {
    return createJsonResponse(programs);
  }

  // If leaders tab exists and has rows, return both!
  if (leaders.length > 0) {
    return createJsonResponse({
      programs: programs,
      leaders: leaders
    });
  }

  return createJsonResponse(programs);
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
      // Programs
      result.push({
        id: item.id || ("evt-" + i),
        title: item.title || item.programtitle || item.program || "",
        titleMl: item.titleml || item.malayalamtitle || "",
        category: item.category || item.type || "Event",
        date: item.date || item.datetime || "",
        time: item.time || "",
        venue: item.venue || item.location || "",
        mapUrl: item.mapurl || item.mapslink || "",
        description: item.description || item.specifications || item.details || "",
        speakers: item.speakers || item.chiefguest || "",
        imageUrl: item.posterimage || item.poster || item.flyer || item.imageurl || "",
        registrationUrl: item.registrationurl || item.formlink || "",
        contactName: item.contactname || item.convener || "",
        contactPhone: item.contactphone || item.whatsapp || item.phone || "",
        isActive: item.isactive ? (item.isactive.toLowerCase() !== "false" && item.isactive.toLowerCase() !== "no") : true
      });
    }
  }
  return result;
}

function createJsonResponse(data) {
  return ContentService.createTextOutput(JSON.stringify(data))
    .setMimeType(ContentService.MimeType.JSON);
}
```

---

## 5. Deploy New Version (1 Click)
Whenever you update code in Apps Script:
1. Click **Deploy** > **Manage deployments**.
2. Click the **Edit (Pencil)** icon on your active deployment.
3. Under *Version*, select **New version**.
4. Click **Deploy**.
*(Your web app URL remains the exact same!)*

