# 📋 Google Sheet Setup Guide for Centre Youth Programs & Specifications

This setup allows Centre Executives (President, Secretary, Conveners) to update **Upcoming Programs, C-Shore Camp specifications, Kalamela notices, and poster flyers** anytime from a smartphone or computer without rebuilding or pushing updates to Google Play!

---

## 🚀 Overview in 3 Simple Steps
1. **Create the Google Sheet** with the program columns.
2. **Add a 15-line Apps Script** (Extensions > Apps Script) to convert the sheet into a live, secure JSON API.
3. **Deploy as Web App** and paste the URL into the app.

---

## Step 1: Create the Google Sheet

1. Go to [Google Sheets](https://sheets.new) and create a new spreadsheet.
2. Name it: **`Centre Youth Programs & Announcements`**
3. In **Row 1** (the header row), type the following column names across columns **A to N**:

| Column | Header Name | Description | Example Value |
|---|---|---|---|
| **A** | `Title` | Program / Event Name in English | `C-SHORE Youth Camp 2026` |
| **B** | `TitleMl` | Program Name in Malayalam | `സി-ഷോർ യൂത്ത് ക്യാമ്പ് 2026` |
| **C** | `Category` | Event Type (Camp, Kalamela, Meeting, Sports) | `Camp` |
| **D** | `Date` | Event Date or Date Range | `October 19, 20, 21, 2026` |
| **E** | `Time` | Time / Duration | `9:00 AM – 4:00 PM` |
| **F** | `Venue` | Location / Parish name | `MCMAT, Perumbavoor` |
| **G** | `MapUrl` | Google Maps Link for directions | `https://maps.app.goo.gl/...` |
| **H** | `Description` | Full specifications, schedule, theme, highlights | `3-day residential camp. Theme: Christ Shore. Music & talks.` |
| **I** | `Speakers` | Chief guests, main talk leaders | `Rev. Byju Thomas, Rev. Blesson Sam Koshy` |
| **J** | `PosterImage` | Google Drive link or web image URL | `https://drive.google.com/file/d/1XYZ...` |
| **K** | `RegistrationUrl` | Google Form link or website link | `https://forms.gle/xyz...` |
| **L** | `ContactName` | Convener or leader name | `Ashin K Santhosh (Secretary)` |
| **M** | `ContactPhone` | WhatsApp / Phone number | `9497882179` |
| **N** | `IsActive` | `TRUE` to show in app, `FALSE` to hide | `TRUE` |

---

## Step 2: How to Add Images / Posters (Google Drive)

1. Upload your event poster/flyer image into **Google Drive**.
2. Right-click the image in Google Drive > **Share** > **Share**.
3. Under *General access*, change to **"Anyone with the link can view"** and click **Copy link**.
4. Paste that link directly into the **`PosterImage`** column (**Column J**).
   > The Android app automatically converts standard Google Drive links into high-speed CDN image streams via Google's `lh3.googleusercontent.com/d/{id}` engine!

---

## Step 3: Add the Apps Script (Free 1-Click JSON Feed)

1. In your Google Sheet, click **Extensions** in the top menu > **Apps Script**.
2. Delete any code inside `Code.gs`, and paste the following:

```javascript
function doGet() {
  var sheet = SpreadsheetApp.getActiveSpreadsheet().getActiveSheet();
  var rows = sheet.getDataRange().getValues();
  if (rows.length < 2) {
    return ContentService.createTextOutput(JSON.stringify([])).setMimeType(ContentService.MimeType.JSON);
  }
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

    var normalized = {
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
    };
    result.push(normalized);
  }
  return ContentService.createTextOutput(JSON.stringify(result))
    .setMimeType(ContentService.MimeType.JSON);
}
```

3. Click the **Save** icon (disk icon).

---

## Step 4: Deploy as Web App

1. In the top right corner of Apps Script, click **Deploy** > **New deployment**.
2. Click the gear icon (⚙️) next to *Select type* and choose **Web app**.
3. Fill in:
   * **Description**: `Centre Youth API`
   * **Execute as**: `Me`
   * **Who has access**: **`Anyone`** *(Crucial: allows the mobile app to read the data without signing into Google)*.
4. Click **Deploy**.
5. Copy the **Web App URL** provided (it looks like `https://script.google.com/macros/s/AKfycb.../exec`).

---

## Step 5: Connect It to Your Android App

In `android/app/src/main/java/org/marthoma/centreyouth/data/CentreRepository.kt`:
Update the `DEFAULT_SHEET_URL` with your copied Web App URL:
```kotlin
private val DEFAULT_SHEET_URL = "https://script.google.com/macros/s/YOUR_ACTUAL_SCRIPT_ID/exec"
```

Now, every time leaders open their Google Sheet from their phone and add or modify an event:
* Within seconds, all users opening the app see the updated program specifications, schedule, and posters!
* No app rebuilds or Google Play updates needed.
