# BSICARDS Android SDK

A comprehensive Android SDK for integrating with the BSICARDS Card Issuance API. Create and manage Mastercard, Visa, and Digital Wallet cards with ease.

[![Android](https://img.shields.io/badge/Android-5.0+-brightgreen)](https://www.android.com/)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.8+-purple)](https://kotlinlang.org/)
[![License](https://img.shields.io/badge/license-MIT-blue)](LICENSE)
[![JitPack](https://jitpack.io/v/nash81/bsicards-android-sdk.svg)](https://jitpack.io/#nash81/bsicards-android-sdk)

## Features

- ✅ **MasterCard Issuance** - Create and manage MasterCards
- ✅ **Visa Card Issuance** - Create and manage Visa Cards
- ✅ **Digital Wallet Cards** - Create and manage Digital Wallet cards
- ✅ **Digital Visa Wallet Cards** - Create, fund, and control Digital Visa Wallet cards
- ✅ **Card Management** - Freeze, unfreeze, change PIN, view transactions
- ✅ **Card Funding** - Fund cards with minimum $10.00
- ✅ **Transaction History** - View detailed transaction records
- ✅ **Kotlin Coroutines** - Async operations with suspend functions
- ✅ **Type Safe** - Full Kotlin type safety with data classes
- ✅ **Error Handling** - Custom exceptions for API errors
- ✅ **Retrofit 2** - Modern REST API integration

## Requirements

- Android 5.0 (API 21) or higher
- Kotlin 1.8+
- Android Gradle Plugin 7.0+

## Installation

### Option 1: Install from JitPack (Recommended)

1. Add JitPack repository.

`settings.gradle`:

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

2. Add SDK dependency in your app module `build.gradle`.

```gradle
dependencies {
    implementation 'com.github.nash81:bsicards-android-sdk:1.0.0'
}
```

Use a GitHub tag (for example `1.0.0`) as the version.

### Option 2: Install from GitHub Source

1. Clone repository.

```bash
git clone https://github.com/nash81/bsicards-android-sdk.git
```

2. Add module in your project's `settings.gradle`.

```gradle
include ':bsicards'
project(':bsicards').projectDir = new File(rootDir, 'bsicards-android-sdk/bsicards')
```

3. Add module dependency in your app module `build.gradle`.

```gradle
dependencies {
    implementation project(':bsicards')
}
```

### GitHub + JitPack Release Flow (Maintainers)

1. Commit and push to `main`.
2. Create and push a version tag.

```bash
git tag 1.0.1
git push origin 1.0.1
```

3. Developers can then install:

```gradle
implementation 'com.github.nash81:bsicards-android-sdk:1.0.1'
```

## Configuration

### Environment Setup

Add your credentials to your app's `local.properties` or secure storage:

```properties
BSICARDS_PUBLIC_KEY=your_public_key_here
BSICARDS_SECRET_KEY=your_secret_key_here
```

### Initialize the Client

```kotlin
import tech.bsigroup.cards.BSICardsClient

val client = BSICardsClient(
    context = context,
    publicKey = "your_public_key",
    secretKey = "your_secret_key"
)
```


## API Methods

### MasterCard Operations

```kotlin
// Create a MasterCard
client.createMastercard(userEmail, nameOnCard, pin)

// Get all MasterCards
client.getAllMastercards(userEmail)

// Get pending MasterCards
client.getPendingMastercards(userEmail)

// Get card details
client.getMastercardDetails(cardId)

// Get transactions
client.getMastercardTransactions(cardId)

// Freeze card
client.freezeMastercard(cardId)

// Unfreeze card
client.unfreezeMastercard(cardId)

// Fund card
client.fundMastercard(cardId, amount)
```

### Visa Card Operations

```kotlin
// Create Visa card
client.createVisaCard(userEmail, name, nationalId, idUrl, photoUrl, dob)

// Get all Visa cards
client.getAllVisaCards(userEmail)

// Get pending Visa cards
client.getPendingVisaCards(userEmail)

// Get card details
client.getVisaCardDetails(cardId)

// Get transactions
client.getVisaCardTransactions(cardId)

// Freeze card
client.freezeVisaCard(cardId)

// Unfreeze card
client.unfreezeVisaCard(cardId)

// Fund card
client.fundVisaCard(cardId, amount)
```

### Digital Wallet / Virtual Card Operations

```kotlin
// Create virtual card
client.createVirtualCard(
    userEmail, firstName, lastName, dob,
    address, postalCode, city, countryCode,
    state, countryPhone, phone
)

// Get all virtual cards
client.getAllVirtualCards(userEmail)

// Get card details
client.getVirtualCardDetails(cardId)

// Get transactions
client.getVirtualCardTransactions(cardId)

// Freeze card
client.freezeVirtualCard(cardId)

// Unfreeze card
client.unfreezeVirtualCard(cardId)

// Fund card
client.fundVirtualCard(cardId, amount)

// Check 3DS
client.check3DS(userEmail, cardId)

// Approve 3DS
client.approve3DS(userEmail, cardId, eventId)

// Create addon card
client.createAddonCard(userEmail, cardId)

// Terminate card
client.terminateDigitalCard(userEmail, cardId)

// Get loyalty points
client.getLoyaltyPoints(userEmail, cardId)

// Redeem points
client.redeemPoints(userEmail, cardId)
```

### Digital Visa Wallet Card Operations

```kotlin
// Create Digital Visa Wallet card
client.createDigitalVisaWalletCard(userEmail, firstName, lastName)

// Get all Digital Visa Wallet cards
client.getAllDigitalVisaWalletCards(userEmail)

// Get Digital Visa Wallet card details
client.getDigitalVisaWalletCard(userEmail, cardId)

// Fund Digital Visa Wallet card (minimum $5.00)
client.fundDigitalVisaWalletCard(userEmail, cardId, amount)

// Get OTP for wallet verification
client.getDigitalVisaWalletOtp(userEmail, cardId)

// Block card
client.blockDigitalVisaWalletCard(userEmail, cardId)

// Unblock card
client.unblockDigitalVisaWalletCard(userEmail, cardId)
```

### Administrator Operations

```kotlin
// Get wallet balance
client.getWalletBalance(userEmail)

// Get deposits
client.getDeposits(userEmail)

// Get all transactions
client.getAllTransactions(userEmail)

// Get all MasterCards (admin view)
client.getAllMastercardsByAdmin(userEmail)

// Get all Visa Cards (admin view)
client.getAllVisaCardsByAdmin(userEmail)

// Get all Digital Cards (admin view)
client.getAllDigitalCardsByAdmin(userEmail)
```

## Error Handling

```kotlin
import tech.bsigroup.cards.BSICardsException
import tech.bsigroup.cards.ValidationException
import tech.bsigroup.cards.NetworkException

lifecycleScope.launch {
    try {
        val response = client.createMastercard(email, name, pin)
    } catch (e: ValidationException) {
        // Handle validation error
        Log.e("BSICARDS", "Validation error: ${e.message}")
    } catch (e: NetworkException) {
        // Handle network error
        Log.e("BSICARDS", "Network error: ${e.message}")
    } catch (e: BSICardsException) {
        // Handle API error
        Log.e("BSICARDS", "API error: ${e.message}, Code: ${e.code}")
    } catch (e: Exception) {
        // Handle other errors
        Log.e("BSICARDS", "Error: ${e.message}")
    }
}
```

## Response Format

All API responses follow this format:

```json
{
    "code": 200,
    "status": "success",
    "message": "Descriptive message",
    "data": {}
}
```

## Base URL

The SDK automatically uses:

```
https://cards.bsigroup.tech/api/
```

## Best Practices

1. **Never hardcode credentials** - Always use secure storage
2. **Always use lifecycleScope** - For coroutines in Activities/Fragments
3. **Handle exceptions** - Catch and handle all exceptions
4. **Validate input** - Check data before sending
5. **Use ViewModels** - For lifecycle-aware data management
6. **Implement retry logic** - For network operations

## Examples

See the [examples](docs/EXAMPLES.md) directory for complete code samples.

## Documentation

- [Installation Guide](docs/INSTALLATION.md)
- [API Reference](docs/API.md)
- [Code Examples](docs/EXAMPLES.md)
- [Troubleshooting](docs/TROUBLESHOOTING.md)

## Contributing

Contributions are welcome! Please see [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

## License

This SDK is released under the MIT License. See [LICENSE](LICENSE) for details.

## Support

For issues, questions, or support:

- Email: cs@bsigroup.tech
- Website: https://www.bsigroup.tech
- GitHub: https://github.com/nash81/bsicards-android-sdk

## Changelog

See [CHANGELOG.md](CHANGELOG.md) for version history.

## Disclaimer

This SDK is provided as-is. Always test in a sandbox environment before production use.

