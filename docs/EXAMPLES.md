# BSICARDS Android SDK - Quick Examples

## Table of Contents
- [Authentication](#authentication)
- [MasterCard Examples](#mastercard-examples)
- [Visa Card Examples](#visa-card-examples)
- [Digital Wallet Examples](#digital-wallet-examples)
- [Error Handling](#error-handling)

## Authentication

### Initialize the Client

```kotlin
import tech.bsigroup.cards.BSICardsClient
import android.content.Context

class MyActivity : AppCompatActivity() {
    private lateinit var bsiCards: BSICardsClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize with your credentials
        bsiCards = BSICardsClient(
            context = this,
            publicKey = "your_public_key",
            secretKey = "your_secret_key"
        )
    }
}
```

## MasterCard Examples

### Create a MasterCard

```kotlin
lifecycleScope.launch {
    try {
        val response = bsiCards.createMastercard(
            userEmail = "john@example.com",
            nameOnCard = "John Doe",
            pin = "1234"
        )

        if (response.code == 200) {
            Toast.makeText(
                this@MainActivity,
                response.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    } catch (e: Exception) {
        Log.e("BSICARDS", "Error: ${e.message}")
    }
}
```

### Get All MasterCards for a User

```kotlin
lifecycleScope.launch {
    try {
        val response = bsiCards.getAllMastercards("john@example.com")

        response.data?.forEach { card ->
            Log.d("BSICARDS", """
                Card ID: ${card.cardId}
                Holder: ${card.cardholderName}
                Balance: ${card.balance}
                Status: ${card.status}
            """.trimIndent())
        }
    } catch (e: Exception) {
        Log.e("BSICARDS", "Error: ${e.message}")
    }
}
```

### Get Card Details

```kotlin
lifecycleScope.launch {
    try {
        val cardId = "card-12345"
        val response = bsiCards.getMastercardDetails(cardId)

        response.data?.let { card ->
            Log.d("BSICARDS", """
                Card Number: ${card.cardNumber}
                Expiry: ${card.expiryDate}
                Available Balance: ${card.availableBalance}
            """.trimIndent())
        }
    } catch (e: Exception) {
        Log.e("BSICARDS", "Error: ${e.message}")
    }
}
```

### Get Card Transactions

```kotlin
lifecycleScope.launch {
    try {
        val response = bsiCards.getMastercardTransactions("card-12345")

        response.data?.transactions?.forEach { txn ->
            Log.d("BSICARDS", """
                Transaction ID: ${txn.transactionId}
                Amount: ${txn.amount} ${txn.currency}
                Type: ${txn.type}
                Date: ${txn.timestamp}
            """.trimIndent())
        }
    } catch (e: Exception) {
        Log.e("BSICARDS", "Error: ${e.message}")
    }
}
```



### Freeze and Unfreeze Card

```kotlin
lifecycleScope.launch {
    try {
        // Freeze card
        val freezeResponse = bsiCards.freezeMastercard("card-12345")
        Toast.makeText(this, freezeResponse.message, Toast.LENGTH_SHORT).show()

        // Unfreeze card
        val unfreezeResponse = bsiCards.unfreezeMastercard("card-12345")
        Toast.makeText(this, unfreezeResponse.message, Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        Log.e("BSICARDS", "Error: ${e.message}")
    }
}
```

### Fund MasterCard

```kotlin
lifecycleScope.launch {
    try {
        val response = bsiCards.fundMastercard(
            cardId = "card-12345",
            amount = "50.00"  // Minimum $10.00
        )
        Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        Log.e("BSICARDS", "Error: ${e.message}")
    }
}
```

## Visa Card Examples

### Create a Visa Card

```kotlin
lifecycleScope.launch {
    try {
        val response = bsiCards.createVisaCard(
            userEmail = "john@example.com",
            name = "John Doe",
            nationalId = "ABC123456",
            idUrl = "https://example.com/id.jpg",
            photoUrl = "https://example.com/photo.jpg",
            dateOfBirth = "1990-01-15"
        )

        Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        Log.e("BSICARDS", "Error: ${e.message}")
    }
}
```

### Get All Visa Cards

```kotlin
lifecycleScope.launch {
    try {
        val response = bsiCards.getAllVisaCards("john@example.com")

        response.data?.forEach { card ->
            Log.d("BSICARDS", "Visa Card: ${card.cardNumber}")
        }
    } catch (e: Exception) {
        Log.e("BSICARDS", "Error: ${e.message}")
    }
}
```

### Fund Visa Card

```kotlin
lifecycleScope.launch {
    try {
        val response = bsiCards.fundVisaCard(
            cardId = "visa-12345",
            amount = "100.00"
        )
        Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        Log.e("BSICARDS", "Error: ${e.message}")
    }
}
```

## Digital Wallet Examples

### Create Virtual Card

```kotlin
lifecycleScope.launch {
    try {
        val response = bsiCards.createVirtualCard(
            userEmail = "john@example.com",
            firstName = "John",
            lastName = "Doe",
            dateOfBirth = "1990-01-15",
            address = "123 Main St",
            postalCode = "12345",
            city = "New York",
            countryCode = "US",
            state = "NY",
            countryPhone = "+1",
            phone = "2125551234"
        )

        Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        Log.e("BSICARDS", "Error: ${e.message}")
    }
}
```

### Get USDT Address

```kotlin
lifecycleScope.launch {
    try {
        val response = bsiCards.getUSDTAddress("john@example.com")

        response.data?.let { data ->
            Log.d("BSICARDS", "USDT Address: ${data.usdtAddress}")
            Log.d("BSICARDS", "Network: ${data.network}")
        }
    } catch (e: Exception) {
        Log.e("BSICARDS", "Error: ${e.message}")
    }
}
```

### Fund Virtual Card

```kotlin
lifecycleScope.launch {
    try {
        val response = bsiCards.fundVirtualCard(
            cardId = "virtual-12345",
            amount = "75.00"
        )
        Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
    } catch (e: Exception) {
        Log.e("BSICARDS", "Error: ${e.message}")
    }
}
```

## Utility Examples

### Get Wallet Balance

```kotlin
lifecycleScope.launch {
    try {
        val response = bsiCards.getWalletBalance("john@example.com")

        response.data?.let { balance ->
            Log.d("BSICARDS", """
                Balance: ${balance.balance}
                Currency: ${balance.currency}
                Last Updated: ${balance.lastUpdated}
            """.trimIndent())
        }
    } catch (e: Exception) {
        Log.e("BSICARDS", "Error: ${e.message}")
    }
}
```

### Get Deposits

```kotlin
lifecycleScope.launch {
    try {
        val response = bsiCards.getDeposits("john@example.com")

        response.data?.forEach { deposit ->
            Log.d("BSICARDS", """
                Deposit ID: ${deposit.depositId}
                Amount: ${deposit.amount}
                Status: ${deposit.status}
            """.trimIndent())
        }
    } catch (e: Exception) {
        Log.e("BSICARDS", "Error: ${e.message}")
    }
}
```

### Get All Transactions

```kotlin
lifecycleScope.launch {
    try {
        val response = bsiCards.getAllTransactions("john@example.com")

        response.data?.transactions?.forEach { txn ->
            Log.d("BSICARDS", "${txn.amount} ${txn.currency} - ${txn.status}")
        }
    } catch (e: Exception) {
        Log.e("BSICARDS", "Error: ${e.message}")
    }
}
```

## Error Handling

### Complete Error Handling Example

```kotlin
import tech.bsigroup.cards.*

lifecycleScope.launch {
    try {
        val response = bsiCards.createMastercard(
            userEmail = "john@example.com",
            nameOnCard = "John Doe",
            pin = "1234"
        )
    } catch (e: ValidationException) {
        // Handle validation errors
        Toast.makeText(
            this@MainActivity,
            "Validation Error: ${e.message}",
            Toast.LENGTH_SHORT
        ).show()
    } catch (e: NetworkException) {
        // Handle network errors
        Toast.makeText(
            this@MainActivity,
            "Network Error: Check your connection",
            Toast.LENGTH_SHORT
        ).show()
    } catch (e: BSICardsException) {
        // Handle API errors
        Toast.makeText(
            this@MainActivity,
            "API Error: ${e.message}",
            Toast.LENGTH_SHORT
        ).show()
    } catch (e: Exception) {
        // Handle unexpected errors
        Log.e("BSICARDS", "Unexpected error: ${e.message}")
    }
}
```

## Tips & Best Practices

1. **Use ViewModels** for lifecycle-aware operations
2. **Implement error UI** to show errors to users
3. **Use try-catch** for all API calls
4. **Log errors** for debugging
5. **Validate input** before sending
6. **Test with sandbox** credentials first
7. **Store credentials securely** using EncryptedSharedPreferences

