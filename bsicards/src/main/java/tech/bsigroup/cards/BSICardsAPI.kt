package tech.bsigroup.cards

import retrofit2.http.*

/**
 * BSICARDS REST API Interface
 * Defines all API endpoints for card operations
 */
interface BSICardsAPI {

    // ============ MasterCard Operations ============

    /**
     * Create a new MasterCard
     */
    @POST("newcard")
    suspend fun createMastercard(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: CardRequest
    ): ApiResponse<CardResponse>

    /**
     * Get all MasterCards for a user
     */
    @GET("admin/mastercards")
    suspend fun getAllMastercards(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Query("useremail") userEmail: String
    ): ApiResponse<List<CardDetails>>

    /**
     * Get pending MasterCards
     */
    @GET("admin/pending-mastercards")
    suspend fun getPendingMastercards(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Query("useremail") userEmail: String
    ): ApiResponse<List<CardDetails>>

    /**
     * Get MasterCard details
     */
    @GET("admin/mastercard/{cardId}")
    suspend fun getMastercardDetails(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Path("cardId") cardId: String
    ): ApiResponse<CardDetails>

    /**
     * Get MasterCard transactions
     */
    @GET("admin/mastercard/{cardId}/transactions")
    suspend fun getMastercardTransactions(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Path("cardId") cardId: String
    ): ApiResponse<TransactionResponse>

    /**
     * Change MasterCard PIN
     */
    @POST("admin/mastercard/{cardId}/change-pin")
    suspend fun changeMastercardPin(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Path("cardId") cardId: String,
        @Body request: ChangePinRequest
    ): ApiResponse<MessageResponse>

    /**
     * Freeze MasterCard
     */
    @POST("admin/mastercard/{cardId}/freeze")
    suspend fun freezeMastercard(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Path("cardId") cardId: String
    ): ApiResponse<MessageResponse>

    /**
     * Unfreeze MasterCard
     */
    @POST("admin/mastercard/{cardId}/unfreeze")
    suspend fun unfreezeMastercard(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Path("cardId") cardId: String
    ): ApiResponse<MessageResponse>

    /**
     * Fund MasterCard
     */
    @POST("admin/mastercard/{cardId}/fund")
    suspend fun fundMastercard(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Path("cardId") cardId: String,
        @Body request: FundRequest
    ): ApiResponse<MessageResponse>

    // ============ Visa Card Operations ============

    /**
     * Create a new Visa card
     */
    @POST("admin/create-visa")
    suspend fun createVisaCard(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: VisaCardRequest
    ): ApiResponse<CardResponse>

    /**
     * Get all Visa cards
     */
    @GET("admin/visacards")
    suspend fun getAllVisaCards(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Query("useremail") userEmail: String
    ): ApiResponse<List<CardDetails>>

    /**
     * Get pending Visa cards
     */
    @GET("admin/pending-visacards")
    suspend fun getPendingVisaCards(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Query("useremail") userEmail: String
    ): ApiResponse<List<CardDetails>>

    /**
     * Get Visa card details
     */
    @GET("admin/visacard/{cardId}")
    suspend fun getVisaCardDetails(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Path("cardId") cardId: String
    ): ApiResponse<CardDetails>

    /**
     * Get Visa card transactions
     */
    @GET("admin/visacard/{cardId}/transactions")
    suspend fun getVisaCardTransactions(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Path("cardId") cardId: String
    ): ApiResponse<TransactionResponse>

    /**
     * Freeze Visa card
     */
    @POST("admin/visacard/{cardId}/freeze")
    suspend fun freezeVisaCard(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Path("cardId") cardId: String
    ): ApiResponse<MessageResponse>

    /**
     * Unfreeze Visa card
     */
    @POST("admin/visacard/{cardId}/unfreeze")
    suspend fun unfreezeVisaCard(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Path("cardId") cardId: String
    ): ApiResponse<MessageResponse>

    /**
     * Fund Visa card
     */
    @POST("admin/visacard/{cardId}/fund")
    suspend fun fundVisaCard(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Path("cardId") cardId: String,
        @Body request: FundRequest
    ): ApiResponse<MessageResponse>

    // ============ Digital Wallet Operations ============

    /**
     * Create virtual card
     */
    @POST("admin/create-virtual-card")
    suspend fun createVirtualCard(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: VirtualCardRequest
    ): ApiResponse<CardResponse>

    /**
     * Get all virtual cards
     */
    @GET("admin/virtualcards")
    suspend fun getAllVirtualCards(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Query("useremail") userEmail: String
    ): ApiResponse<List<CardDetails>>

    /**
     * Get virtual card details
     */
    @GET("admin/virtualcard/{cardId}")
    suspend fun getVirtualCardDetails(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Path("cardId") cardId: String
    ): ApiResponse<CardDetails>

    /**
     * Get virtual card transactions
     */
    @GET("admin/virtualcard/{cardId}/transactions")
    suspend fun getVirtualCardTransactions(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Path("cardId") cardId: String
    ): ApiResponse<TransactionResponse>

    /**
     * Get USDT address
     */
    @GET("admin/usdt-address")
    suspend fun getUSDTAddress(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Query("useremail") userEmail: String
    ): ApiResponse<USDTAddressResponse>

    /**
     * Fund virtual card
     */
    @POST("admin/virtualcard/{cardId}/fund")
    suspend fun fundVirtualCard(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Path("cardId") cardId: String,
        @Body request: FundRequest
    ): ApiResponse<MessageResponse>

    /**
     * Freeze virtual card
     */
    @POST("admin/virtualcard/{cardId}/freeze")
    suspend fun freezeVirtualCard(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Path("cardId") cardId: String
    ): ApiResponse<MessageResponse>

    /**
     * Unfreeze virtual card
     */
    @POST("admin/virtualcard/{cardId}/unfreeze")
    suspend fun unfreezeVirtualCard(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Path("cardId") cardId: String
    ): ApiResponse<MessageResponse>

    // ============ Utility Operations ============

    /**
     * Get wallet balance
     */
    @GET("admin/wallet/balance")
    suspend fun getWalletBalance(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Query("useremail") userEmail: String
    ): ApiResponse<BalanceResponse>

    /**
     * Get deposits
     */
    @GET("admin/deposits")
    suspend fun getDeposits(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Query("useremail") userEmail: String
    ): ApiResponse<List<Deposit>>

    /**
     * Get all transactions
     */
    @GET("admin/transactions")
    suspend fun getAllTransactions(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Query("useremail") userEmail: String
    ): ApiResponse<TransactionResponse>
}

