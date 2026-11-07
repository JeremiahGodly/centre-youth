# 🚀 Google Play Store Console Submission Guide

**App Name:** Centre Youth - Mar Thoma  
**Package Name:** `org.marthoma.centreyouth`  
**Developer:** Jeremiah Godly Varghese  
**Primary Email:** `jrmhgodly@gmail.com`  
**Live Privacy Policy URL:** `https://jeremiahgodly.github.io/centre-youth/privacy-policy.html`  
**Live Terms of Service URL:** `https://jeremiahgodly.github.io/centre-youth/terms.html`  

---

## 1. Store Listing Details

### App Title (Max 30 characters)
```text
Centre Youth - Mar Thoma
```

### Short Description (Max 80 characters)
```text
Official app of Keezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam.
```

### Full Description (Max 4,000 characters)
```text
Welcome to the official mobile application of Keezhillam - Munnar Centre Mar Thoma Yuvajana Sakhyam (Kottayam - Kochi Diocese, Malankara Mar Thoma Syrian Church).

Rooted in the four spiritual pillars of the Sakhyam — Worship, Study, Witness, and Service — and guided by our divine motto "Lighted to Lighten" (Romans 12:2), this application brings our entire centre fellowship directly to your Android device.

🌟 KEY FEATURES:

📖 LITURGICAL LECTIONARY:
• Complete liturgical calendar of the Mar Thoma Syrian Church.
• Full scripture readings for all Sundays (First Lesson, Second Lesson, Epistle, Gospel, and Evening readings).
• Automated upcoming Sunday detection with distinct visual glow.
• Fast bilingual search by passage reference, keyword, or Sunday name.
• One-tap scripture sharing via WhatsApp, SMS, and clipboard.

🎵 YOUTH PRAISE & HYMNS AUDIO:
• 50+ official Centre Sakhyam youth songs, camp tracks, and devotional hymns.
• Seamless background audio player with system media notification & lock-screen controls.
• Docked mini-player with quick play/pause while navigating.
• Full player sheet with progress seekbar, repeat, and favorites bookmarking.
• Audio continues playing smoothly when device is locked or using other apps.

⛪ CENTRE PARISHES DIRECTORY:
• Directory of all 15 Centre parishes across Kottayam and Kochi region.
• Vicar information, contact phone numbers, and location details.
• 1-tap Phone dialer intent to connect directly.
• 1-tap Google Maps navigation for driving directions to any parish.

👥 LEADERSHIP & OFFICE BEARERS:
• Official executive committee profiles (President Rev. James George, Secretary, Joint Secretary, Treasurer).
• Direct phone calling and 1-tap WhatsApp chat connectivity.

⛺ C-SHORE CAMP & LUMORA KALAMELA:
• C-SHORE Annual Youth Leadership & Spiritual Camp highlights and schedules.
• LUMORA 2026 Annual Cultural Kalamela event details, rules, and scoring guidelines.

✨ MODERN ANDROID EXPERIENCE:
• Material You Dynamic Theming: Adapts to your device's wallpaper palette on Android 12+.
• Edge-to-Edge immersive layout (Targeting Android 15 / API 35).
• Daily Morning Scripture Reflection notifications (7:00 AM) to start your day with God's word.
• Home Screen App Shortcuts: Quick access to Today's Lectionary and Songs.
• Bilingual Support: English and Malayalam (മലയാളം).
• 100% Offline Resilience: All liturgical readings, parish contacts, and song information work without an internet connection.

🔒 SAFE, PRIVATE & SECURE:
• Zero Personal Data Collection: No sign-in or account registration required.
• Completely Ad-Free: No commercial trackers or third-party ad networks.
• Family and youth safe: Adheres to Google Play Families and COPPA standards.

Designed & Developed with devotion by Jeremiah Godly Varghese.
"Do not conform to the pattern of this world, but be transformed by the renewing of your mind." — Romans 12:2
```

---

## 2. Graphic Assets Prepared in Project Root

1. **App Icon:** `playstore-icon-512.png` (512 × 512 px, 32-bit PNG, max 1MB)
2. **Feature Graphic:** `playstore-feature-graphic-1024x500.png` (1024 × 500 px, 24-bit PNG, max 15MB)
3. **Phone Screenshots:** Capture 2 to 8 screenshots from Android Studio emulator or device (Home, Lectionary, Songs player, Parishes, Leadership).

---

## 3. App Content & Policy Declarations (Step-by-Step Questionnaire Answers)

When completing the Google Play Console **App Content** section, select these exact answers to ensure immediate policy clearance:

### A. Privacy Policy
- **Privacy Policy URL:** `https://jeremiahgodly.github.io/centre-youth/privacy-policy.html`

### B. App Access
- **Question:** Does your app require login credentials or special access?
- **Answer:** **All functionality is available without special access** (No login or demo account required).

### C. Ads
- **Question:** Does your app contain ads?
- **Answer:** **No, my app does not contain ads**.

### D. Content Rating (IARC Questionnaire)
- **Category:** Reference, News, or Educational / Lifestyle
- **Violence:** No
- **Sexuality / Nudity:** No
- **Offensive Language:** No
- **Controlled Substances:** No
- **User Interaction:** No unrestricted chat (only standard device phone/WhatsApp intents).
- **Result:** **PEGI 3 / Everyone (All ages)**.

### E. Target Audience and Content
- **Target Age Groups:** Select **13–15, 16–17, 18 and over**.
- **Could your app appeal to children?** No (or neutral; app is suitable for families).

### F. News Apps
- **Question:** Is your app a news app?
- **Answer:** **No**.

### G. COVID-19 Contact Tracing & Status
- **Answer:** **My app is not a COVID-19 contact tracing or status app**.

### H. Data Safety Form Answers
Google Play requires filling out the Data Safety form. Use these exact answers:
1. **Does your app collect or share any user data?**
   - Select: **No**
2. **Is all user data collected by your app encrypted in transit?**
   - Select: **Yes** (All streaming requests use HTTPS)
3. **Do you provide a way for users to request data deletion?**
   - Select: **Not applicable** (No user accounts or personal data are collected)

### I. Financial Features, Government Apps, Health Apps
- **Financial Features:** **No**
- **Government App:** **No**
- **Health & Fitness App:** **No**

### J. Sensitive Permissions Rationale
If Google requests clarification on declared permissions:
- **`POST_NOTIFICATIONS`**: *"Used exclusively to deliver an optional daily morning scripture reminder (at 7:00 AM) and liturgical feast day alerts. User can disable this at any time."*
- **`FOREGROUND_SERVICE_MEDIA_PLAYBACK`**: *"Used solely for playback of official Centre youth fellowship audio recordings and liturgical songs while the application is in the background or device screen is turned off."*

---

## 4. How to Generate Release AAB via GitHub Actions

You don't need to install any Android SDK on your local computer!
1. Push your changes to GitHub:
   ```bash
   git push origin main
   ```
2. Open your GitHub repository in your browser: `https://github.com/JeremiahGodly/centre-youth/actions`
3. The **"Build Android App Bundle & APK"** workflow will start automatically.
4. When finished (typically 2–3 minutes), click on the completed run and scroll to **Artifacts**:
   - `Centre-Youth-PlayStore-AAB` (Download `app-release.aab` and upload directly to Google Play Console!)
   - `Centre-Youth-APKs` (Download APK to install and test directly on your Android phone!)
