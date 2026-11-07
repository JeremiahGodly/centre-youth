#!/usr/bin/env bash
set -e

KEYSTORE_PATH="android/release.keystore"
ALIAS="centreyouth"
PASSWORD="centreYouth2026"
DNAME="CN=Jeremiah Godly Varghese, OU=Centre Youth, O=Mar Thoma Yuvajana Sakhyam, L=Keezhillam, ST=Kerala, C=IN"

if [ -f "$KEYSTORE_PATH" ]; then
    echo "⚠️ Keystore already exists at $KEYSTORE_PATH"
    echo "To regenerate, delete the existing file first."
    exit 0
fi

echo "🔐 Generating release keystore for Centre Youth app..."
keytool -genkeypair -v \
  -keystore "$KEYSTORE_PATH" \
  -alias "$ALIAS" \
  -keyalg RSA \
  -keysize 2048 \
  -validity 10000 \
  -storepass "$PASSWORD" \
  -keypass "$PASSWORD" \
  -dname "$DNAME"

echo "✅ Successfully created release keystore at: $KEYSTORE_PATH"
echo "Keystore Alias: $ALIAS"
echo "Keystore Password: $PASSWORD"
echo ""
echo "ℹ️ For GitHub Actions (Optional):"
echo "Base64 encoded keystore for GitHub Secret 'KEYSTORE_BASE64':"
base64 "$KEYSTORE_PATH" | tr -d '\n'
echo ""
