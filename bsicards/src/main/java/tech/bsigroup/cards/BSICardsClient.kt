package tech.bsigroup.cards

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * BSICARDS Android SDK Client
 * Main entry point for all card operations
 */
class BSICardsClient(
    context: Context,
    publicKey: String,
    secretKey: String,
    baseUrl: String = "https://cards.bsigroup.tech/api/"
) {

    private var publicKey: String = publicKey
    private var secretKey: String = secretKey
    private val context: Context = context
    private val api: BSICardsAPI
    private val sharedPrefs: SharedPreferences

    companion object {
        const val PREFS_NAME = "bsicards_prefs"
        const val KEY_PUBLIC_KEY = "public_key"
        const val KEY_SECRET_KEY = "secret_key"
    }

    init {
        // Initialize SharedPreferences for credential storage
        sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        // Store credentials securely
        storeCredentials()

        // Initialize OkHttpClient with timeouts
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(BSICardsAPI::class.java)
    }

    /**
     * Store credentials in SharedPreferences (for testing only - use secure storage in production)
     */
    private fun storeCredentials() {
        sharedPrefs.edit().apply {
            putString(KEY_PUBLIC_KEY, publicKey)
            putString(KEY_SECRET_KEY, secretKey)
            apply()
        }
    }

    /**
     * Set public key
     */
    fun setPublicKey(key: String) {
        this.publicKey = key
        storeCredentials()
    }

    /**
     * Set secret key
     */
    fun setSecretKey(key: String) {
        this.secretKey = key
        storeCredentials()
    }

    /**
     * Get public key
     */
    fun getPublicKey(): String = publicKey

    /**
     * Get secret key
     */
    fun getSecretKey(): String = secretKey

    // ============ MasterCard Operations ============

    /**
     * Create a MasterCard
     */
    suspend fun createMastercard(
        userEmail: String,
        nameOnCard: String,
        pin: String
    ): ApiResponse<CardResponse> = withContext(Dispatchers.IO) {
        validateInput(userEmail, nameOnCard, pin)
        api.createMastercard(
            publicKey,
            secretKey,
            CardRequest(userEmail, nameOnCard, pin)
        )
    }

    /**
     * Get all MasterCards for a user
     */
    suspend fun getAllMastercards(userEmail: String): ApiResponse<List<CardDetails>> =
        withContext(Dispatchers.IO) {
            validateInput(userEmail)
            api.getAllMastercards(publicKey, secretKey, userEmail)
        }

    /**
     * Get pending MasterCards
     */
    suspend fun getPendingMastercards(userEmail: String): ApiResponse<List<CardDetails>> =
        withContext(Dispatchers.IO) {
            validateInput(userEmail)
            api.getPendingMastercards(publicKey, secretKey, userEmail)
        }

    /**
     * Get MasterCard details
     */
    suspend fun getMastercardDetails(cardId: String): ApiResponse<CardDetails> =
        withContext(Dispatchers.IO) {
            validateInput(cardId)
            api.getMastercardDetails(publicKey, secretKey, cardId)
        }

    /**
     * Get MasterCard transactions
     */
    suspend fun getMastercardTransactions(cardId: String): ApiResponse<TransactionResponse> =
        withContext(Dispatchers.IO) {
            validateInput(cardId)
            api.getMastercardTransactions(publicKey, secretKey, cardId)
        }


    /**
     * Freeze MasterCard
     */
    suspend fun freezeMastercard(cardId: String): ApiResponse<MessageResponse> =
        withContext(Dispatchers.IO) {
            validateInput(cardId)
            api.freezeMastercard(publicKey, secretKey, cardId)
        }

    /**
     * Unfreeze MasterCard
     */
    suspend fun unfreezeMastercard(cardId: String): ApiResponse<MessageResponse> =
        withContext(Dispatchers.IO) {
            validateInput(cardId)
            api.unfreezeMastercard(publicKey, secretKey, cardId)
        }

    /**
     * Fund MasterCard
     */
    suspend fun fundMastercard(cardId: String, amount: String): ApiResponse<MessageResponse> =
        withContext(Dispatchers.IO) {
            validateInput(cardId, amount)
            validateAmount(amount)
            api.fundMastercard(publicKey, secretKey, cardId, FundRequest(amount))
        }

    // ============ Visa Card Operations ============

    /**
     * Create a Visa card
     */
    suspend fun createVisaCard(
        userEmail: String,
        name: String,
        nationalId: String,
        idUrl: String,
        photoUrl: String,
        dateOfBirth: String
    ): ApiResponse<CardResponse> = withContext(Dispatchers.IO) {
        validateInput(userEmail, name, nationalId, idUrl, photoUrl, dateOfBirth)
        api.createVisaCard(
            publicKey,
            secretKey,
            VisaCardRequest(userEmail, name, nationalId, idUrl, photoUrl, dateOfBirth)
        )
    }

    /**
     * Get all Visa cards
     */
    suspend fun getAllVisaCards(userEmail: String): ApiResponse<List<CardDetails>> =
        withContext(Dispatchers.IO) {
            validateInput(userEmail)
            api.getAllVisaCards(publicKey, secretKey, userEmail)
        }

    /**
     * Get pending Visa cards
     */
    suspend fun getPendingVisaCards(userEmail: String): ApiResponse<List<CardDetails>> =
        withContext(Dispatchers.IO) {
            validateInput(userEmail)
            api.getPendingVisaCards(publicKey, secretKey, userEmail)
        }

    /**
     * Get Visa card details
     */
    suspend fun getVisaCardDetails(cardId: String): ApiResponse<CardDetails> =
        withContext(Dispatchers.IO) {
            validateInput(cardId)
            api.getVisaCardDetails(publicKey, secretKey, cardId)
        }

    /**
     * Get Visa card transactions
     */
    suspend fun getVisaCardTransactions(cardId: String): ApiResponse<TransactionResponse> =
        withContext(Dispatchers.IO) {
            validateInput(cardId)
            api.getVisaCardTransactions(publicKey, secretKey, cardId)
        }

    /**
     * Freeze Visa card
     */
    suspend fun freezeVisaCard(cardId: String): ApiResponse<MessageResponse> =
        withContext(Dispatchers.IO) {
            validateInput(cardId)
            api.freezeVisaCard(publicKey, secretKey, cardId)
        }

    /**
     * Unfreeze Visa card
     */
    suspend fun unfreezeVisaCard(cardId: String): ApiResponse<MessageResponse> =
        withContext(Dispatchers.IO) {
            validateInput(cardId)
            api.unfreezeVisaCard(publicKey, secretKey, cardId)
        }

    /**
     * Fund Visa card
     */
    suspend fun fundVisaCard(cardId: String, amount: String): ApiResponse<MessageResponse> =
        withContext(Dispatchers.IO) {
            validateInput(cardId, amount)
            validateAmount(amount)
            api.fundVisaCard(publicKey, secretKey, cardId, FundRequest(amount))
        }

    // ============ Digital Wallet Operations ============

    /**
     * Create virtual card
     */
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
    ): ApiResponse<CardResponse> = withContext(Dispatchers.IO) {
        validateInput(userEmail, firstName, lastName, dateOfBirth, address, postalCode, city, countryCode, state, countryPhone, phone)
        api.createVirtualCard(
            publicKey,
            secretKey,
            VirtualCardRequest(userEmail, firstName, lastName, dateOfBirth, address, postalCode, city, countryCode, state, countryPhone, phone)
        )
    }

    /**
     * Get all virtual cards
     */
    suspend fun getAllVirtualCards(userEmail: String): ApiResponse<List<CardDetails>> =
        withContext(Dispatchers.IO) {
            validateInput(userEmail)
            api.getAllVirtualCards(publicKey, secretKey, userEmail)
        }

    /**
     * Get virtual card details
     */
    suspend fun getVirtualCardDetails(cardId: String): ApiResponse<CardDetails> =
        withContext(Dispatchers.IO) {
            validateInput(cardId)
            api.getVirtualCardDetails(publicKey, secretKey, cardId)
        }

    /**
     * Get virtual card transactions
     */
    suspend fun getVirtualCardTransactions(cardId: String): ApiResponse<TransactionResponse> =
        withContext(Dispatchers.IO) {
            validateInput(cardId)
            api.getVirtualCardTransactions(publicKey, secretKey, cardId)
        }


    /**
     * Fund virtual card
     */
    suspend fun fundVirtualCard(cardId: String, amount: String): ApiResponse<MessageResponse> =
        withContext(Dispatchers.IO) {
            validateInput(cardId, amount)
            validateAmount(amount)
            api.fundVirtualCard(publicKey, secretKey, cardId, FundRequest(amount))
        }

    /**
     * Freeze virtual card
     */
    suspend fun freezeVirtualCard(cardId: String): ApiResponse<MessageResponse> =
        withContext(Dispatchers.IO) {
            validateInput(cardId)
            api.freezeVirtualCard(publicKey, secretKey, cardId)
        }

    /**
     * Unfreeze virtual card
     */
    suspend fun unfreezeVirtualCard(cardId: String): ApiResponse<MessageResponse> =
        withContext(Dispatchers.IO) {
            validateInput(cardId)
            api.unfreezeVirtualCard(publicKey, secretKey, cardId)
        }

    // ============ Administrator Operations ============

    /**
     * Get wallet balance
     */
    suspend fun getWalletBalance(userEmail: String): ApiResponse<BalanceResponse> =
        withContext(Dispatchers.IO) {
            validateInput(userEmail)
            api.getWalletBalance(publicKey, secretKey, userEmail)
        }

    /**
     * Get deposits
     */
    suspend fun getDeposits(userEmail: String): ApiResponse<List<Deposit>> =
        withContext(Dispatchers.IO) {
            validateInput(userEmail)
            api.getDeposits(publicKey, secretKey, userEmail)
        }

    /**
     * Get all transactions
     */
    suspend fun getAllTransactions(userEmail: String): ApiResponse<TransactionResponse> =
        withContext(Dispatchers.IO) {
            validateInput(userEmail)
            api.getAllTransactions(publicKey, secretKey, userEmail)
        }

    /**
     * Get all MasterCards (admin view)
     */
    suspend fun getAllMastercardsByAdmin(userEmail: String): ApiResponse<List<CardDetails>> =
        withContext(Dispatchers.IO) {
            validateInput(userEmail)
            api.getAllMastercardsByAdmin(publicKey, secretKey, AdminCardRequest(userEmail))
        }

    /**
     * Get all Visa Cards (admin view)
     */
    suspend fun getAllVisaCardsByAdmin(userEmail: String): ApiResponse<List<CardDetails>> =
        withContext(Dispatchers.IO) {
            validateInput(userEmail)
            api.getAllVisaCardsByAdmin(publicKey, secretKey, AdminCardRequest(userEmail))
        }

    /**
     * Get all Digital Cards (admin view)
     */
    suspend fun getAllDigitalCardsByAdmin(userEmail: String): ApiResponse<List<CardDetails>> =
        withContext(Dispatchers.IO) {
            validateInput(userEmail)
            api.getAllDigitalCardsByAdmin(publicKey, secretKey, AdminCardRequest(userEmail))
        }

    // ============ Validation Methods ============

    /**
     * Check 3DS for digital card
     */
    suspend fun check3DS(userEmail: String, cardId: String): ApiResponse<MessageResponse> =
        withContext(Dispatchers.IO) {
            validateInput(userEmail, cardId)
            api.check3DS(publicKey, secretKey, Check3DSRequest(userEmail, cardId))
        }

    /**
     * Approve 3DS for digital card
     */
    suspend fun approve3DS(userEmail: String, cardId: String, eventId: String): ApiResponse<MessageResponse> =
        withContext(Dispatchers.IO) {
            validateInput(userEmail, cardId, eventId)
            api.approve3DS(publicKey, secretKey, Approve3DSRequest(userEmail, cardId, eventId))
        }

    /**
     * Create addon card
     */
    suspend fun createAddonCard(userEmail: String, cardId: String): ApiResponse<CardResponse> =
        withContext(Dispatchers.IO) {
            validateInput(userEmail, cardId)
            api.createAddonCard(publicKey, secretKey, DigitalCardRequest(userEmail, cardId))
        }

    /**
     * Terminate digital card
     */
    suspend fun terminateDigitalCard(userEmail: String, cardId: String): ApiResponse<MessageResponse> =
        withContext(Dispatchers.IO) {
            validateInput(userEmail, cardId)
            api.terminateDigitalCard(publicKey, secretKey, DigitalCardRequest(userEmail, cardId))
        }

    /**
     * Get loyalty points for digital card
     */
    suspend fun getLoyaltyPoints(userEmail: String, cardId: String): ApiResponse<LoyaltyPointsResponse> =
        withContext(Dispatchers.IO) {
            validateInput(userEmail, cardId)
            api.getLoyaltyPoints(publicKey, secretKey, DigitalCardRequest(userEmail, cardId))
        }

    /**
     * Redeem loyalty points
     */
    suspend fun redeemPoints(userEmail: String, cardId: String): ApiResponse<MessageResponse> =
        withContext(Dispatchers.IO) {
            validateInput(userEmail, cardId)
            api.redeemPoints(publicKey, secretKey, DigitalCardRequest(userEmail, cardId))
        }

    // ============ Validation Methods ============

    /**
     * Validate input parameters
     */
    private fun validateInput(vararg inputs: String) {
        for (input in inputs) {
            if (input.isBlank()) {
                throw ValidationException("Input cannot be empty")
            }
        }
    }

    /**
     * Validate amount format
     */
    private fun validateAmount(amount: String) {
        try {
            val value = amount.toDouble()
            if (value < 10.0) {
                throw ValidationException("Minimum amount is $10.00")
            }
        } catch (e: NumberFormatException) {
            throw ValidationException("Invalid amount format")
        }
    }
}

