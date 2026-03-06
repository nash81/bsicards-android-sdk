# BSICARDS Android SDK - Installation Guide

## System Requirements

- **Android**: 5.0 (API 21) or higher
- **Kotlin**: 1.8+
- **Gradle**: 7.0+
- **Java**: JDK 8+

## Installation Methods

### Method 1: Using Gradle (Recommended)

Add to your app's `build.gradle`:

```gradle
dependencies {
    implementation 'tech.bsigroup:bsicards-android-sdk:1.0.0'
}
```

Sync Gradle and you're ready to use!

### Method 2: Clone and Local Module

1. Clone the repository:
```bash
git clone https://github.com/nash81/bsicards-android-sdk.git
```

2. Add to your project's `settings.gradle`:
```gradle
include ':bsicards'
project(':bsicards').projectDir = new File(rootDir, 'bsicards-android-sdk/bsicards')
```

3. Add to your app's `build.gradle`:
```gradle
dependencies {
    implementation project(':bsicards')
}
```

## Configuration

### Step 1: Add Permissions

Add to your `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

### Step 2: Store Credentials

Store your API keys securely (never hardcode them):

**Option A: Using SharedPreferences (for testing)**
```kotlin
val sharedPref = context.getSharedPreferences("bsicards", Context.MODE_PRIVATE)
sharedPref.edit().apply {
    putString("public_key", "your_public_key")
    putString("secret_key", "your_secret_key")
    apply()
}
```

**Option B: Using EncryptedSharedPreferences (recommended for production)**
```kotlin
val masterKey = MasterKey.Builder(context)
    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
    .build()

val encryptedSharedPref = EncryptedSharedPreferences.create(
    context,
    "bsicards",
    masterKey,
    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
)

encryptedSharedPref.edit().apply {
    putString("public_key", "your_public_key")
    putString("secret_key", "your_secret_key")
    apply()
}
```

### Step 3: Initialize the Client

```kotlin
import tech.bsigroup.cards.BSICardsClient

class MainActivity : AppCompatActivity() {
    private lateinit var bsiCardsClient: BSICardsClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the SDK
        val sharedPref = getSharedPreferences("bsicards", Context.MODE_PRIVATE)
        val publicKey = sharedPref.getString("public_key", "") ?: ""
        val secretKey = sharedPref.getString("secret_key", "") ?: ""

        bsiCardsClient = BSICardsClient(
            context = this,
            publicKey = publicKey,
            secretKey = secretKey
        )
    }
}
```

## Verify Installation

Create a test function to verify everything works:

```kotlin
private fun testSDK() {
    lifecycleScope.launch {
        try {
            val balance = bsiCardsClient.getWalletBalance("test@bsigroup.tech")
            Log.d("BSICARDS", "✓ SDK initialized successfully!")
            Log.d("BSICARDS", "Balance: ${balance.data?.balance}")
        } catch (e: Exception) {
            Log.e("BSICARDS", "✗ Error: ${e.message}")
        }
    }
}
```

## Troubleshooting

### "Internet Permission Denied" Error

Ensure you added the required permissions to `AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

### "ClassNotFoundException" Error

Make sure you're importing from the correct package:
```kotlin
import tech.bsigroup.cards.BSICardsClient
```

### "API Key Invalid" Error

Verify your API keys are correct and not expired:
```kotlin
Log.d("BSICARDS", "Public Key: ${bsiCardsClient.getPublicKey()}")
Log.d("BSICARDS", "Secret Key: ${bsiCardsClient.getSecretKey()}")
```

### Network Timeouts

Increase timeout settings or check your network connection:
```kotlin
// Timeout is set to 30 seconds by default
// Modify BSICardsClient.kt if needed
```

### ProGuard/R8 Issues

Make sure ProGuard rules are applied (`proguard-rules.pro`):
```
-keep class tech.bsigroup.cards.** { *; }
-keep interface tech.bsigroup.cards.** { *; }
```

## Next Steps

1. Read [README.md](../README.md) for API overview
2. Check [API.md](./API.md) for endpoint documentation
3. See [EXAMPLES.md](./EXAMPLES.md) for code samples
4. Review [TROUBLESHOOTING.md](./TROUBLESHOOTING.md) for issues

## Getting Help

- Email: cs@bsigroup.tech
- Website: https://www.bsigroup.tech
- GitHub Issues: https://github.com/nash81/bsicards-android-sdk/issues

