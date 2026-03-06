# BSICARDS Android SDK - Troubleshooting Guide

## Common Issues and Solutions

### 1. Gradle Sync Issues

**Problem:** Gradle sync fails after adding the dependency

**Solutions:**
- Clear Gradle cache: `./gradlew clean`
- Invalidate Android Studio cache: File > Invalidate Caches > Invalidate and Restart
- Check `build.gradle` has correct dependency format
- Ensure minSdk is 21 or higher

```gradle
android {
    minSdkVersion 21
    targetSdkVersion 34
}
```

### 2. Permission Denied Errors

**Problem:** Getting "Permission Denied" when making API calls

**Solution:** Add required permissions to `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

### 3. ClassNotFoundException

**Problem:** `ClassNotFoundException: tech.bsigroup.cards.BSICardsClient`

**Solutions:**
- Ensure correct import statement:
```kotlin
import tech.bsigroup.cards.BSICardsClient
```
- Check that module is properly added to `settings.gradle`
- Rebuild project: Build > Rebuild Project

### 4. API Authentication Failures

**Problem:** Getting "Unauthorized" or "Invalid credentials" errors

**Solutions:**
- Verify public and secret keys are correct
- Check keys haven't expired
- Ensure keys are passed correctly:
```kotlin
val client = BSICardsClient(
    context = this,
    publicKey = "your_actual_public_key",  // Not placeholder
    secretKey = "your_actual_secret_key"    // Not placeholder
)
```

### 5. Network Timeout Errors

**Problem:** Getting timeout errors when making requests

**Solutions:**
- Check internet connectivity
- Verify API endpoint is reachable
- Increase timeout (if needed in production):
  - Edit `BSICardsClient.kt` and modify timeout values
- Implement retry logic:

```kotlin
suspend fun <T> retryIO(
    times: Int = 3,
    initialDelay: Long = 100,
    maxDelay: Long = 1000,
    factor: Double = 2.0,
    block: suspend () -> T
): T {
    var currentDelay = initialDelay
    repeat(times - 1) {
        try {
            return block()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        delay(currentDelay)
        currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelay)
    }
    return block() // Last attempt
}

// Usage
val response = retryIO {
    bsiCards.getAllMastercards("user@example.com")
}
```

### 6. Coroutine Context Issues

**Problem:** `IllegalStateException: "No coroutine context"`

**Solution:** Ensure you're calling SDK functions within a coroutine context:

```kotlin
// ❌ Wrong
val response = bsiCards.getAllMastercards("user@example.com")

// ✅ Correct
lifecycleScope.launch {
    val response = bsiCards.getAllMastercards("user@example.com")
}

// Or using viewModel
viewModelScope.launch {
    val response = bsiCards.getAllMastercards("user@example.com")
}
```

### 7. Serialization Errors

**Problem:** Getting JSON parsing errors or `SerializationException`

**Solution:** Ensure response models match API response format. Check that:
- Field names match API response (case-sensitive)
- Use `@SerializedName` annotation for different field names

```kotlin
data class CardRequest(
    @SerializedName("useremail")
    val userEmail: String
)
```

### 8. ProGuard/R8 Build Errors

**Problem:** SDK classes not found in release build

**Solution:** Add ProGuard rules to `proguard-rules.pro`:

```
-keep class tech.bsigroup.cards.** { *; }
-keep interface tech.bsigroup.cards.** { *; }
-keep class com.squareup.okhttp3.** { *; }
-keep class retrofit2.** { *; }
-keep class com.google.gson.** { *; }

-dontwarn com.squareup.okhttp3.**
-dontwarn retrofit2.**
-dontwarn com.google.gson.**
```

### 9. SharedPreferences Errors

**Problem:** Cannot read/write credentials to SharedPreferences

**Solution:** Use context correctly:

```kotlin
// ✅ Correct
val context = this  // In Activity/Fragment
val sharedPref = context.getSharedPreferences("bsicards", Context.MODE_PRIVATE)

// For fragments
val sharedPref = requireContext().getSharedPreferences("bsicards", Context.MODE_PRIVATE)
```

For production, use EncryptedSharedPreferences:

```gradle
implementation 'androidx.security:security-crypto:1.1.0-alpha06'
```

```kotlin
val masterKey = MasterKey.Builder(context)
    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
    .build()

val encryptedSharedPreferences = EncryptedSharedPreferences.create(
    context,
    "bsicards",
    masterKey,
    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
)
```

### 10. Memory Leaks

**Problem:** Memory leak warnings when creating multiple client instances

**Solution:** Use singleton pattern:

```kotlin
object BSICardsManager {
    private var client: BSICardsClient? = null

    fun getInstance(context: Context, publicKey: String, secretKey: String): BSICardsClient {
        if (client == null) {
            client = BSICardsClient(context, publicKey, secretKey)
        }
        return client!!
    }
}

// Usage
val bsiCards = BSICardsManager.getInstance(context, publicKey, secretKey)
```

### 11. Validation Errors

**Problem:** Getting "Input cannot be empty" errors

**Solution:** Validate input before passing:

```kotlin
fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isValidPin(pin: String): Boolean {
    return pin.length == 4 && pin.all { it.isDigit() }
}

// Before calling SDK
if (!isValidEmail(email)) {
    Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show()
    return
}

if (!isValidPin(pin)) {
    Toast.makeText(this, "PIN must be 4 digits", Toast.LENGTH_SHORT).show()
    return
}

val response = bsiCards.createMastercard(email, name, pin)
```

### 12. API Response Status Codes

**Problem:** Getting unexpected status codes

**Common Status Codes:**
- `200` - Success
- `400` - Bad Request (check input)
- `401` - Unauthorized (check credentials)
- `403` - Forbidden (check permissions)
- `404` - Not Found (check card ID)
- `500` - Server Error (contact support)

**Handling Status Codes:**

```kotlin
lifecycleScope.launch {
    try {
        val response = bsiCards.createMastercard(email, name, pin)

        when (response.code) {
            200 -> {
                // Success
                Toast.makeText(this@MainActivity, response.message, Toast.LENGTH_SHORT).show()
            }
            400 -> {
                // Bad request - check input
                Toast.makeText(this@MainActivity, "Invalid input", Toast.LENGTH_SHORT).show()
            }
            401 -> {
                // Unauthorized - check credentials
                Toast.makeText(this@MainActivity, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
            500 -> {
                // Server error
                Toast.makeText(this@MainActivity, "Server error. Try again later.", Toast.LENGTH_SHORT).show()
            }
        }
    } catch (e: Exception) {
        Log.e("BSICARDS", "Exception: ${e.message}")
    }
}
```

## Debug Logging

Enable detailed logging for troubleshooting:

```kotlin
import android.util.Log

// Add to BSICardsClient calls
Log.d("BSICARDS", "Public Key: ${bsiCards.getPublicKey()}")
Log.d("BSICARDS", "Secret Key length: ${bsiCards.getSecretKey().length}")

// Log responses
Log.d("BSICARDS", "Response Code: ${response.code}")
Log.d("BSICARDS", "Response Status: ${response.status}")
Log.d("BSICARDS", "Response Message: ${response.message}")
```

## Support

If you still have issues:
1. Check the [API.md](./API.md) documentation
2. Review [EXAMPLES.md](./EXAMPLES.md) for proper usage
3. Contact support at cs@bsigroup.tech
4. Open an issue on GitHub: https://github.com/nash81/bsicards-android-sdk/issues

