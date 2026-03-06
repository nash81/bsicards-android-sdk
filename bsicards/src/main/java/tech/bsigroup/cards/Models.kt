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

