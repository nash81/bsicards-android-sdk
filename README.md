# BSICARDS Android SDK

A comprehensive Android SDK for integrating with the BSICARDS Card Issuance API. Create and manage Mastercard, Visa, and Digital Wallet cards with ease.

[![Android](https://img.shields.io/badge/Android-5.0+-brightgreen)](https://www.android.com/)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.8+-purple)](https://kotlinlang.org/)
[![License](https://img.shields.io/badge/license-MIT-blue)](LICENSE)

## Features

- ✅ **MasterCard Issuance** - Create and manage MasterCards
- ✅ **Visa Card Issuance** - Create and manage Visa Cards
- ✅ **Digital Wallet Cards** - Create and manage Digital Wallet cards
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

### Via Gradle

Add to your project's `build.gradle`:

```gradle
dependencies {
    implementation 'tech.bsigroup:bsicards-android-sdk:1.0.0'
}
```

### Manual Setup

1. Clone the repository
2. Add the `bsicards` module to your Android project
3. Sync Gradle files

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

## Quick Start

### Create a MasterCard

```kotlin
lifecycleScope.launch {
    try {
        val response = client.createMastercard(
            userEmail = "user@example.com",
            nameOnCard = "John Doe",
            pin = "1234"
        )

        if (response.code == 200) {
            Toast.makeText(this@MainActivity, response.message, Toast.LENGTH_SHORT).show()
        }
    } catch (e: Exception) {
        Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}
```

### Get All Visa Cards

```kotlin
lifecycleScope.launch {
    try {
        val response = client.getAllVisaCards("user@example.com")
        val cards = response.data

        cards?.forEach { card ->
            Log.d("BSICARDS", "Card: ${card.cardNumber}")
        }
    } catch (e: Exception) {
        Log.e("BSICARDS", "Error: ${e.message}")
    }
}
```

### Fund a Card

```kotlin
lifecycleScope.launch {
    try {
        val response = client.fundMastercard(
            cardId = "card-123",
            amount = "50.00"
        )

        Log.d("BSICARDS", response.message)
    } catch (e: Exception) {
        Log.e("BSICARDS", "Error: ${e.message}")
    }
}
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

// Change PIN
client.changeMastercardPin(cardId, newPin)

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

// Get card details
client.getVisaCardDetails(cardId)

// Get transactions
client.getVisaCardTransactions(cardId)

// Freeze/Unfreeze
client.freezeVisaCard(cardId)
client.unfreezeVisaCard(cardId)

// Fund card
client.fundVisaCard(cardId, amount)
```

### Digital Wallet Operations

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

// Get USDT address
client.getUSDTAddress(userEmail)

// Fund card
client.fundVirtualCard(cardId, amount)

// Freeze/Unfreeze
client.freezeVirtualCard(cardId)
client.unfreezeVirtualCard(cardId)
```

### Utility Operations

```kotlin
// Get wallet balance
client.getWalletBalance(userEmail)

// Get deposits
client.getDeposits(userEmail)

// Get all transactions
client.getAllTransactions(userEmail)
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

