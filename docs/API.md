# BSICARDS Android SDK - API Reference

## Overview

The BSICARDS Android SDK provides a complete Kotlin interface for the BSICARDS Card Issuance API. All functions are suspend functions that should be called within a coroutine context (e.g., `lifecycleScope.launch`).

## Table of Contents

- [MasterCard Operations](#mastercard-operations)
- [Visa Card Operations](#visa-card-operations)
- [Digital Wallet Operations](#digital-wallet-operations)
- [Digital Visa Wallet Card Operations](#digital-visa-wallet-card-operations)
- [Administrator Operations](#administrator-operations)
- [Data Models](#data-models)
- [Exceptions](#exceptions)
- [Wallet As A Service](#wallet-as-a-service)

---

## MasterCard Operations

### createMastercard

Creates a new MasterCard for a user.

**Signature:**
```kotlin
suspend fun createMastercard(
    userEmail: String,
    nameOnCard: String,
    pin: String
): ApiResponse<CardResponse>
```

**Parameters:**
- `userEmail` (String): User's email address
- `nameOnCard` (String): Name to be printed on card
- `pin` (String): 4-digit PIN for the card

**Returns:** `ApiResponse<CardResponse>` with created card details

**Example:**
```kotlin
val response = bsiCards.createMastercard(
    userEmail = "user@example.com",
    nameOnCard = "John Doe",
    pin = "1234"
)
```

### getAllMastercards

Retrieves all MasterCards for a specific user.

**Signature:**
```kotlin
suspend fun getAllMastercards(
    userEmail: String
): ApiResponse<List<CardDetails>>
```

**Parameters:**
- `userEmail` (String): User's email address

**Returns:** `ApiResponse<List<CardDetails>>` with all user's MasterCards

### getPendingMastercards

Retrieves pending MasterCards for a user.

**Signature:**
```kotlin
suspend fun getPendingMastercards(
    userEmail: String
): ApiResponse<List<CardDetails>>
```

**Parameters:**
- `userEmail` (String): User's email address

**Returns:** `ApiResponse<List<CardDetails>>` with pending MasterCards

### getMastercardDetails

Gets detailed information about a specific MasterCard.

**Signature:**
```kotlin
suspend fun getMastercardDetails(
    cardId: String
): ApiResponse<CardDetails>
```

**Parameters:**
- `cardId` (String): Unique card identifier

**Returns:** `ApiResponse<CardDetails>` with card information

### getMastercardTransactions

Retrieves transaction history for a MasterCard.

**Signature:**
```kotlin
suspend fun getMastercardTransactions(
    cardId: String
): ApiResponse<TransactionResponse>
```

**Parameters:**
- `cardId` (String): Unique card identifier

**Returns:** `ApiResponse<TransactionResponse>` with transactions list


### freezeMastercard

Freezes a MasterCard to prevent transactions.

**Signature:**
```kotlin
suspend fun freezeMastercard(
    cardId: String
): ApiResponse<MessageResponse>
```

**Parameters:**
- `cardId` (String): Unique card identifier

**Returns:** `ApiResponse<MessageResponse>` with confirmation

### unfreezeMastercard

Unfreezes a previously frozen MasterCard.

**Signature:**
```kotlin
suspend fun unfreezeMastercard(
    cardId: String
): ApiResponse<MessageResponse>
```

**Parameters:**
- `cardId` (String): Unique card identifier

**Returns:** `ApiResponse<MessageResponse>` with confirmation

### fundMastercard

Funds a MasterCard with specified amount.

**Signature:**
```kotlin
suspend fun fundMastercard(
    cardId: String,
    amount: String
): ApiResponse<MessageResponse>
```

**Parameters:**
- `cardId` (String): Unique card identifier
- `amount` (String): Amount to fund (minimum $10.00)

**Returns:** `ApiResponse<MessageResponse>` with confirmation

---

## Visa Card Operations

### createVisaCard

Creates a new Visa card.

**Signature:**
```kotlin
suspend fun createVisaCard(
    userEmail: String,
    name: String,
    nationalId: String,
    idUrl: String,
    photoUrl: String,
    dateOfBirth: String
): ApiResponse<CardResponse>
```

**Parameters:**
- `userEmail` (String): User's email
- `name` (String): Cardholder's name
- `nationalId` (String): National ID number
- `idUrl` (String): URL to national ID image
- `photoUrl` (String): URL to cardholder photo
- `dateOfBirth` (String): Date of birth (YYYY-MM-DD format)

### getAllVisaCards

Retrieves all Visa cards for a user.

**Signature:**
```kotlin
suspend fun getAllVisaCards(
    userEmail: String
): ApiResponse<List<CardDetails>>
```

### getPendingVisaCards

Retrieves pending Visa cards.

**Signature:**
```kotlin
suspend fun getPendingVisaCards(
    userEmail: String
): ApiResponse<List<CardDetails>>
```

### getVisaCardDetails

Gets Visa card details.

**Signature:**
```kotlin
suspend fun getVisaCardDetails(
    cardId: String
): ApiResponse<CardDetails>
```

### getVisaCardTransactions

Retrieves Visa card transactions.

**Signature:**
```kotlin
suspend fun getVisaCardTransactions(
    cardId: String
): ApiResponse<TransactionResponse>
```

### freezeVisaCard

Freezes a Visa card.

**Signature:**
```kotlin
suspend fun freezeVisaCard(
    cardId: String
): ApiResponse<MessageResponse>
```

### unfreezeVisaCard

Unfreezes a Visa card.

**Signature:**
```kotlin
suspend fun unfreezeVisaCard(
    cardId: String
): ApiResponse<MessageResponse>
```

### fundVisaCard

Funds a Visa card.

**Signature:**
```kotlin
suspend fun fundVisaCard(
    cardId: String,
    amount: String
): ApiResponse<MessageResponse>
```

---

## Digital Wallet Operations

### createVirtualCard

Creates a virtual card for digital wallets.

**Signature:**
```kotlin
suspend fun createVirtualCard(
    userEmail: String,
    firstName: String,
    lastName: String,
    dateOfBirth: String,
    address: String,
    postalCode: String,
    city: String,
    countryCode: String,
    state: String,
    countryPhone: String,
    phone: String
): ApiResponse<CardResponse>
```

### getAllVirtualCards

Retrieves all virtual cards.

**Signature:**
```kotlin
suspend fun getAllVirtualCards(
    userEmail: String
): ApiResponse<List<CardDetails>>
```

### getVirtualCardDetails

Gets virtual card details.

**Signature:**
```kotlin
suspend fun getVirtualCardDetails(
    cardId: String
): ApiResponse<CardDetails>
```

### getVirtualCardTransactions

Retrieves virtual card transactions.

**Signature:**
```kotlin
suspend fun getVirtualCardTransactions(
    cardId: String
): ApiResponse<TransactionResponse>
```

### fundVirtualCard

Funds a virtual card.

**Signature:**
```kotlin
suspend fun fundVirtualCard(
    cardId: String,
    amount: String
): ApiResponse<MessageResponse>
```

### freezeVirtualCard

Freezes a virtual card.

**Signature:**
```kotlin
suspend fun freezeVirtualCard(
    cardId: String
): ApiResponse<MessageResponse>
```

### unfreezeVirtualCard

Unfreezes a virtual card.

**Signature:**
```kotlin
suspend fun unfreezeVirtualCard(
    cardId: String
): ApiResponse<MessageResponse>
```

---

## Digital Visa Wallet Card Operations

### createDigitalVisaWalletCard

Creates a Digital Visa Wallet card.

**Signature:**
```kotlin
suspend fun createDigitalVisaWalletCard(
    userEmail: String,
    firstName: String,
    lastName: String
): ApiResponse<DigitalVisaWalletCardResponse>
```

### getAllDigitalVisaWalletCards

Retrieves all Digital Visa Wallet cards for a user.

**Signature:**
```kotlin
suspend fun getAllDigitalVisaWalletCards(
    userEmail: String
): ApiResponse<List<DigitalVisaWalletCardSummary>>
```

### getDigitalVisaWalletCard

Gets a specific Digital Visa Wallet card.

**Signature:**
```kotlin
suspend fun getDigitalVisaWalletCard(
    userEmail: String,
    cardId: String
): ApiResponse<DigitalVisaWalletCardDetails>
```

### fundDigitalVisaWalletCard

Funds a Digital Visa Wallet card.

**Signature:**
```kotlin
suspend fun fundDigitalVisaWalletCard(
    userEmail: String,
    cardId: String,
    amount: String
): ApiResponse<MessageResponse>
```

### getDigitalVisaWalletOtp

Retrieves OTP for wallet verification when adding card to Apple Pay/Google Pay.

**Signature:**
```kotlin
suspend fun getDigitalVisaWalletOtp(
    userEmail: String,
    cardId: String
): ApiResponse<DigitalVisaWalletOtpResponse>
```

### blockDigitalVisaWalletCard

Blocks a Digital Visa Wallet card.

**Signature:**
```kotlin
suspend fun blockDigitalVisaWalletCard(
    userEmail: String,
    cardId: String
): ApiResponse<MessageResponse>
```

### unblockDigitalVisaWalletCard

Unblocks a Digital Visa Wallet card.

**Signature:**
```kotlin
suspend fun unblockDigitalVisaWalletCard(
    userEmail: String,
    cardId: String
): ApiResponse<MessageResponse>
```

---

## Administrator Operations

### getWalletBalance

Retrieves user's wallet balance.

**Signature:**
```kotlin
suspend fun getWalletBalance(
    userEmail: String
): ApiResponse<BalanceResponse>
```

### getDeposits

Retrieves user's deposits.

**Signature:**
```kotlin
suspend fun getDeposits(
    userEmail: String
): ApiResponse<List<Deposit>>
```

### getAllTransactions

Retrieves all user transactions.

**Signature:**
```kotlin
suspend fun getAllTransactions(
    userEmail: String
): ApiResponse<TransactionResponse>
```

### getAllMastercardsByAdmin

Retrieves all MasterCards (admin view) for a user.

**Signature:**
```kotlin
suspend fun getAllMastercardsByAdmin(
    userEmail: String
): ApiResponse<List<CardDetails>>
```

**Parameters:**
- `userEmail` (String): User's email address

**Returns:** `ApiResponse<List<CardDetails>>` with all user's MasterCards in admin view

### getAllVisaCardsByAdmin

Retrieves all Visa Cards (admin view) for a user.

**Signature:**
```kotlin
suspend fun getAllVisaCardsByAdmin(
    userEmail: String
): ApiResponse<List<CardDetails>>
```

**Parameters:**
- `userEmail` (String): User's email address

**Returns:** `ApiResponse<List<CardDetails>>` with all user's Visa Cards in admin view

### getAllDigitalCardsByAdmin

Retrieves all Digital Cards (admin view) for a user.

**Signature:**
```kotlin
suspend fun getAllDigitalCardsByAdmin(
    userEmail: String
): ApiResponse<List<CardDetails>>
```

**Parameters:**
- `userEmail` (String): User's email address

**Returns:** `ApiResponse<List<CardDetails>>` with all user's Digital Cards in admin view

---

## Data Models

### ApiResponse

Generic API response wrapper.

```kotlin
data class ApiResponse<T>(
    val code: Int,
    val status: String,
    val message: String,
    val data: T? = null
)
```

### CardDetails

Complete card information.

```kotlin
data class CardDetails(
    val cardId: String,
    val cardNumber: String,
    val cardholderName: String,
    val expiryDate: String,
    val status: String,
    val balance: String,
    val availableBalance: String,
    val createdAt: String,
    val updatedAt: String
)
```

### TransactionResponse

Transaction response with list.

```kotlin
data class TransactionResponse(
    val totalTransactions: Int,
    val transactions: List<Transaction>?
)

data class Transaction(
    val transactionId: String,
    val amount: String,
    val currency: String,
    val type: String,
    val description: String,
    val timestamp: String,
    val status: String
)
```

### BalanceResponse

Wallet balance information.

```kotlin
data class BalanceResponse(
    val balance: String,
    val currency: String,
    val lastUpdated: String
)
```

---

## Exceptions

### BSICardsException

Main exception for API errors.

```kotlin
class BSICardsException(
    message: String,
    val code: Int = -1,
    cause: Throwable? = null
) : Exception(message, cause)
```

### ValidationException

Exception for input validation errors.

```kotlin
class ValidationException(
    message: String
) : Exception(message)
```

### NetworkException

Exception for network errors.

```kotlin
class NetworkException(
    message: String,
    cause: Throwable? = null
) : Exception(message, cause)
```

---

## Base URL

```
https://cards.bsigroup.tech/api/
```

## Response Codes

- **200** - Success
- **400** - Bad Request
- **401** - Unauthorized
- **403** - Forbidden
- **404** - Not Found
- **500** - Server Error

## Rate Limiting

API requests are rate-limited. Please implement retry logic for production apps.

## Best Practices

1. Always call SDK functions within a coroutine context
2. Implement proper error handling
3. Validate input before sending
4. Store credentials securely
5. Use appropriate timeout values
6. Implement retry logic for network failures
7. Log errors for debugging

## Support

For questions or issues, contact:
- Email: cs@bsigroup.tech
- GitHub: https://github.com/nash81/bsicards-android-sdk

---

## Wallet As A Service

### Swap

#### getSwapCurrencies

Retrieves supported swap currencies.

**Signature:**
```kotlin
suspend fun getSwapCurrencies(): ApiResponse<List<SwapCurrency>>
```

#### getSwapStatus

Retrieves swap status by transaction ID.

**Signature:**
```kotlin
suspend fun getSwapStatus(transactionId: String): ApiResponse<SwapStatus>
```

#### getSwapEstimate

Gets swap estimate for a currency pair and amount.

**Signature:**
```kotlin
suspend fun getSwapEstimate(request: SwapEstimateRequest): ApiResponse<SwapEstimate>
```

#### createSwap

Creates a swap transaction.

**Signature:**
```kotlin
suspend fun createSwap(request: SwapCreateRequest): ApiResponse<SwapCreateResponse>
```

### Wallet

#### createWalletAddress

Creates a new wallet address for a user and coin.

**Signature:**
```kotlin
suspend fun createWalletAddress(userEmail: String, coin: String): ApiResponse<WalletAddressResponse>
```

#### getAllWalletAddresses

Retrieves all wallet addresses for a user.

**Signature:**
```kotlin
suspend fun getAllWalletAddresses(userEmail: String): ApiResponse<List<WalletAddressResponse>>
```

#### getWalletAddress

Retrieves a specific wallet address by UUID and user email.

**Signature:**
```kotlin
suspend fun getWalletAddress(uuid: String, userEmail: String): ApiResponse<WalletAddressResponse>
```

#### getWalletBalanceByUuid

Retrieves wallet balance by UUID and user email.

**Signature:**
```kotlin
suspend fun getWalletBalanceByUuid(uuid: String, userEmail: String): ApiResponse<WalletBalanceResponse>
```

#### getWalletWithdrawalFee

Gets withdrawal fee for a withdrawal request.

**Signature:**
```kotlin
suspend fun getWalletWithdrawalFee(uuid: String, toAddress: String, amount: String, coin: String, userEmail: String): ApiResponse<WalletWithdrawalFeeResponse>
```

#### withdrawFromWallet

Withdraws funds from a wallet.

**Signature:**
```kotlin
suspend fun withdrawFromWallet(uuid: String, toAddress: String, amount: String, coin: String, userEmail: String, memo: String? = null): ApiResponse<WalletWithdrawResponse>
```

#### getWalletWithdrawalStatus

Gets withdrawal status by transaction hash and coin.

**Signature:**
```kotlin
suspend fun getWalletWithdrawalStatus(txHash: String, coin: String): ApiResponse<WalletWithdrawalStatusResponse>
```

---
