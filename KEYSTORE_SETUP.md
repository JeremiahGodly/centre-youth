# 🔑 Keystore & Signing Setup Guide

Google Play requires that all Android App Bundles (`.aab`) and APKs be cryptographically signed with a private signing key.

---

## 1. Automated Signing (Zero Configuration Needed!)

The included GitHub Actions workflow (`.github/workflows/android-build.yml`) automatically configures a release keystore during builds if you haven't uploaded one yet.

This means you can trigger a build right away on GitHub Actions and receive a signed `.aab` file ready for the Google Play Console!

---

## 2. Using Your Own Private Keystore (Recommended for Production)

If you already have a release keystore or want to generate a permanent private key:

### Step 1: Generate the keystore
Run the included script:
```bash
./generate-keystore.sh
```
Or run `keytool` manually in terminal:
```bash
keytool -genkeypair -v \
  -keystore android/release.keystore \
  -alias centreyouth \
  -keyalg RSA \
  -keysize 2048 \
  -validity 10000 \
  -storepass "centreYouth2026" \
  -keypass "centreYouth2026" \
  -dname "CN=Jeremiah Godly Varghese, OU=Centre Youth, O=Mar Thoma Yuvajana Sakhyam, L=Keezhillam, ST=Kerala, C=IN"
```

> **⚠️ IMPORTANT:** Keep your keystore file and passwords safe! Back up `release.keystore` in a secure location. Google Play requires the same signing key for all future app updates.

---

### Step 2: Add Keystore to GitHub Secrets (Optional)

To keep your keystore private while building in the cloud:
1. Convert your keystore to base64:
   ```bash
   base64 android/release.keystore | tr -d '\n' | pbcopy
   ```
2. In your GitHub repository, navigate to:
   **Settings > Secrets and variables > Actions > New repository secret**
3. Create a secret named:
   - **`KEYSTORE_BASE64`**: Paste the copied base64 string.
4. The GitHub Actions workflow will automatically decode it during every build.

---

## 3. Building Locally with Android Studio

1. Open Android Studio.
2. Select **Open** and choose the `android` folder (`/Users/godlyvarghese/Desktop/centre youth/android`).
3. Android Studio will automatically sync the Gradle files and download dependencies.
4. Go to **Build > Generate Signed Bundle / APK**.
5. Select **Android App Bundle** and follow the prompt using `android/release.keystore`.
