package tech.bsigroup.cards

import com.google.gson.annotations.SerializedName

// ============ Request/Response Models ============

/**
 * Card creation request
 */
data class CardRequest(
    @SerializedName("useremail")
    val userEmail: String,
    @SerializedName("nameoncard")
    val nameOnCard: String,
    val pin: String
)

/**
 * Visa card creation request
 */
data class VisaCardRequest(
    @SerializedName("useremail")
    val userEmail: String,
    val name: String,
    @SerializedName("nationalid")
    val nationalId: String,
    @SerializedName("idurl")
    val idUrl: String,
    @SerializedName("photourl")
    val photoUrl: String,
    @SerializedName("dob")
    val dateOfBirth: String
)

/**
 * Virtual card creation request
 */
data class VirtualCardRequest(
    @SerializedName("useremail")
    val userEmail: String,
    @SerializedName("firstname")
    val firstName: String,
    @SerializedName("lastname")
    val lastName: String,
    @SerializedName("dob")
    val dateOfBirth: String,
    val address: String,
    @SerializedName("postalcode")
    val postalCode: String,
    val city: String,
    @SerializedName("countrycode")
    val countryCode: String,
    val state: String,
    @SerializedName("countryphone")
    val countryPhone: String,
    val phone: String
)

/**
 * Digital Visa Wallet create card request
 */
data class DigitalVisaWalletCreateCardRequest(
    @SerializedName("useremail")
    val userEmail: String,
    @SerializedName("firstname")
    val firstName: String,
    @SerializedName("lastname")
    val lastName: String
)

/**
 * Digital Visa Wallet user request
 */
data class DigitalVisaWalletUserRequest(
    @SerializedName("useremail")
    val userEmail: String
)

/**
 * Digital Visa Wallet card request
 */
data class DigitalVisaWalletCardRequest(
    @SerializedName("useremail")
    val userEmail: String,
    @SerializedName("cardid")
    val cardId: String
)

/**
 * Digital Visa Wallet fund card request
 */
data class DigitalVisaWalletFundRequest(
    @SerializedName("useremail")
    val userEmail: String,
    @SerializedName("cardid")
    val cardId: String,
    val amount: String
)

/**
 * API Response wrapper
 */
data class ApiResponse<T>(
    val code: Int,
    val status: String,
    val message: String,
    val data: T? = null
)

/**
 * Card response from creation
 */
data class CardResponse(
    val cardId: String? = null,
    val status: String? = null,
    val createdAt: String? = null
)

/**
 * Digital Visa Wallet card response from creation
 */
data class DigitalVisaWalletCardResponse(
    val id: String? = null,
    val cardName: String? = null,
    val last4Digits: String? = null,
    val currencyCode: String? = null,
    val balance: String? = null,
    val paymentSystem: String? = null,
    val status: String? = null,
    val expiresAt: String? = null,
    val createdAt: String? = null
)

/**
 * Card details
 */
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

/**
 * Digital Visa Wallet card summary
 */
data class DigitalVisaWalletCardSummary(
    @SerializedName("cardid")
    val cardId: String,
    @SerializedName("nameoncard")
    val nameOnCard: String,
    @SerializedName("lastfour")
    val lastFour: String,
    val brand: String,
    val type: String
)

/**
 * Digital Visa Wallet card details
 */
data class DigitalVisaWalletCardDetails(
    @SerializedName("cardid")
    val cardId: String,
    @SerializedName("nameoncard")
    val nameOnCard: String,
    @SerializedName("card_number")
    val cardNumber: String,
    val type: String,
    val brand: String,
    val status: String,
    @SerializedName("expiry_year")
    val expiryYear: String,
    @SerializedName("expiry_month")
    val expiryMonth: String,
    val cvv: String,
    @SerializedName("useremail")
    val userEmail: String,
    val balance: String,
    @SerializedName("isaddon")
    val isAddon: Int,
    val transactions: DigitalVisaWalletTransactions? = null
)

data class DigitalVisaWalletTransactions(
    @SerializedName("data")
    val items: List<Transaction>?,
    val total: Int,
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    val totalPages: Int
)

/**
 * Digital Visa Wallet OTP response payload
 */
data class DigitalVisaWalletOtpResponse(
    val otp: String? = null,
    @SerializedName("cardid")
    val cardId: String? = null,
    val expiresAt: String? = null
)

/**
 * Transaction details
 */
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

/**
 * PIN change request
 */
data class ChangePinRequest(
    @SerializedName("newpin")
    val newPin: String
)

/**
 * Fund card request
 */
data class FundRequest(
    val amount: String
)

/**
 * Generic message response
 */
data class MessageResponse(
    val message: String
)

/**
 * Check 3DS request
 */
data class Check3DSRequest(
    @SerializedName("useremail")
    val userEmail: String,
    @SerializedName("cardid")
    val cardId: String
)

/**
 * Approve 3DS request
 */
data class Approve3DSRequest(
    @SerializedName("useremail")
    val userEmail: String,
    @SerializedName("cardid")
    val cardId: String,
    @SerializedName("eventId")
    val eventId: String
)

/**
 * Digital card operations request
 */
data class DigitalCardRequest(
    @SerializedName("useremail")
    val userEmail: String,
    @SerializedName("cardid")
    val cardId: String
)

/**
 * Admin card listing request
 */
data class AdminCardRequest(
    @SerializedName("useremail")
    val userEmail: String
)

/**
 * Loyalty points response
 */
data class LoyaltyPointsResponse(
    val points: List<LoyaltyPoint>?,
    val balance: String? = null,
    val code: String? = null
)

data class LoyaltyPoint(
    val id: Int? = null,
    val cardid: String? = null,
    val balance: String? = null,
    val type: String? = null,
    val details: String? = null,
    val points: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null
)

/**
 * Wallet balance response
 */
data class BalanceResponse(
    val balance: String,
    val currency: String,
    val lastUpdated: String
)

/**
 * Deposit details
 */
data class Deposit(
    val depositId: String,
    val amount: String,
    val currency: String,
    val status: String,
    val createdAt: String
)

/**
 * USDT address response
 */
data class USDTAddressResponse(
    @SerializedName("usdtaddress")
    val usdtAddress: String,
    val network: String,
    val qrCode: String? = null
)

// ============ Wallet As A Service Models ============

data class SwapCurrency(
    val symbol: String,
    val name: String,
    val network: String
)

data class SwapStatus(
    val transactionId: String,
    val status: String,
    val from: String,
    val to: String,
    val amount: String,
    val createdAt: String,
    val updatedAt: String?
)

data class SwapEstimateRequest(
    val from: String,
    val to: String,
    val network_from: String,
    val network_to: String,
    val amount: Double
)

data class SwapEstimate(
    val estimatedAmount: String,
    val rate: String,
    val fee: String
)

data class SwapCreateRequest(
    val coin_from: String,
    val coin_to: String,
    val network_from: String,
    val network_to: String,
    val deposit_amount: Double,
    val withdrawal: String,
    val withdrawal_extra_id: String? = null
)

data class SwapCreateResponse(
    val transactionId: String,
    val depositAddress: String,
    val amount: String,
    val status: String
)

data class WalletCreateAddressRequest(
    val useremail: String,
    val coin: String
)

data class WalletAddressResponse(
    val uuid: String,
    val address: String,
    val coin: String,
    val useremail: String,
    val mnemonic: String? = null,
    val private_key: String? = null,
    val created_at: String? = null
)

data class WalletBalanceResponse(
    val balances: Map<String, String>
)

data class WalletWithdrawRequest(
    val uuid: String,
    val to_address: String,
    val amount: String,
    val coin: String,
    val useremail: String,
    val memo: String? = null
)

data class WalletWithdrawalFeeResponse(
    val fee: String,
    val coin: String
)

data class WalletWithdrawResponse(
    val tx_hash: String,
    val status: String
)

data class WalletWithdrawalStatusRequest(
    val tx_hash: String,
    val coin: String
)

data class WalletWithdrawalStatusResponse(
    val status: String,
    val confirmations: Int? = null,
    val tx_hash: String? = null
)
