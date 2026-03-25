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

    // ============ Digital Visa Wallet Card Operations ============

    /**
     * Create Digital Visa Wallet card
     */
    @POST("digital-wallet-visa/create-card")
    suspend fun createDigitalVisaWalletCard(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: DigitalVisaWalletCreateCardRequest
    ): ApiResponse<DigitalVisaWalletCardResponse>

    /**
     * Get all Digital Visa Wallet cards
     */
    @POST("digital-wallet-visa/get-all-cards")
    suspend fun getAllDigitalVisaWalletCards(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: DigitalVisaWalletUserRequest
    ): ApiResponse<List<DigitalVisaWalletCardSummary>>

    /**
     * Get Digital Visa Wallet card
     */
    @POST("digital-wallet-visa/get-card")
    suspend fun getDigitalVisaWalletCard(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: DigitalVisaWalletCardRequest
    ): ApiResponse<DigitalVisaWalletCardDetails>

    /**
     * Fund Digital Visa Wallet card
     */
    @POST("digital-wallet-visa/fund-card")
    suspend fun fundDigitalVisaWalletCard(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: DigitalVisaWalletFundRequest
    ): ApiResponse<MessageResponse>

    /**
     * Get Digital Visa Wallet OTP
     */
    @POST("digital-wallet-visa/get-otp")
    suspend fun getDigitalVisaWalletOtp(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: DigitalVisaWalletCardRequest
    ): ApiResponse<DigitalVisaWalletOtpResponse>

    /**
     * Block Digital Visa Wallet card
     */
    @POST("digital-wallet-visa/block-card")
    suspend fun blockDigitalVisaWalletCard(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: DigitalVisaWalletCardRequest
    ): ApiResponse<MessageResponse>

    /**
     * Unblock Digital Visa Wallet card
     */
    @POST("digital-wallet-visa/unblock-card")
    suspend fun unblockDigitalVisaWalletCard(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: DigitalVisaWalletCardRequest
    ): ApiResponse<MessageResponse>

    // ============ Administrator Operations ============

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

    /**
     * Get all MasterCards
     */
    @POST("getallcard")
    suspend fun getAllMastercardsByAdmin(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: AdminCardRequest
    ): ApiResponse<List<CardDetails>>

    /**
     * Get all Visa Cards
     */
    @POST("visagetallcard")
    suspend fun getAllVisaCardsByAdmin(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: AdminCardRequest
    ): ApiResponse<List<CardDetails>>

    /**
     * Get all Digital Cards
     */
    @POST("getalldigital")
    suspend fun getAllDigitalCardsByAdmin(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: AdminCardRequest
    ): ApiResponse<List<CardDetails>>

    // ============ Digital Wallet Operations ============

    /**
     * Check 3DS for digital card
     */
    @POST("check3ds")
    suspend fun check3DS(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: Check3DSRequest
    ): ApiResponse<MessageResponse>

    /**
     * Approve 3DS for digital card
     */
    @POST("approve3ds")
    suspend fun approve3DS(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: Approve3DSRequest
    ): ApiResponse<MessageResponse>

    /**
     * Create addon card
     */
    @POST("createaddon")
    suspend fun createAddonCard(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: DigitalCardRequest
    ): ApiResponse<CardResponse>

    /**
     * Terminate digital card
     */
    @POST("terminatedigital")
    suspend fun terminateDigitalCard(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: DigitalCardRequest
    ): ApiResponse<MessageResponse>

    /**
     * Get loyalty points for digital card
     */
    @POST("digitalcardpoints")
    suspend fun getLoyaltyPoints(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: DigitalCardRequest
    ): ApiResponse<LoyaltyPointsResponse>

    /**
     * Redeem loyalty points
     */
    @POST("redeempoints")
    suspend fun redeemPoints(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: DigitalCardRequest
    ): ApiResponse<MessageResponse>

    // ============ Wallet As A Service ============

    // --- Swap ---
    /** Get supported swap currencies */
    @GET("exchange/currencies")
    suspend fun getSwapCurrencies(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String
    ): ApiResponse<List<SwapCurrency>>

    /** Get swap status by transaction id */
    @GET("exchange/status")
    suspend fun getSwapStatus(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Query("transaction_id") transactionId: String
    ): ApiResponse<SwapStatus>

    /** Get swap estimate */
    @POST("exchange/estimate")
    suspend fun getSwapEstimate(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: SwapEstimateRequest
    ): ApiResponse<SwapEstimate>

    /** Create swap transaction */
    @POST("exchange/create")
    suspend fun createSwap(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: SwapCreateRequest
    ): ApiResponse<SwapCreateResponse>

    // --- Wallet ---
    /** Create a new wallet address */
    @POST("wallet/create-address")
    suspend fun createWalletAddress(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: WalletCreateAddressRequest
    ): ApiResponse<WalletAddressResponse>

    /** Get all wallet addresses for a user */
    @GET("wallet/addresses")
    suspend fun getAllWalletAddresses(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Query("useremail") userEmail: String
    ): ApiResponse<List<WalletAddressResponse>>

    /** Get a specific wallet address by uuid */
    @GET("wallet/address/{uuid}")
    suspend fun getWalletAddress(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Path("uuid") uuid: String,
        @Query("useremail") userEmail: String
    ): ApiResponse<WalletAddressResponse>

    /** Get wallet balance by uuid */
    @GET("wallet/balance")
    suspend fun getWalletBalanceByUuid(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Query("uuid") uuid: String,
        @Query("useremail") userEmail: String
    ): ApiResponse<WalletBalanceResponse>

    /** Get withdrawal fee */
    @POST("wallet/withdrawal-fee")
    suspend fun getWalletWithdrawalFee(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: WalletWithdrawRequest
    ): ApiResponse<WalletWithdrawalFeeResponse>

    /** Withdraw from wallet */
    @POST("wallet/withdraw")
    suspend fun withdrawFromWallet(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: WalletWithdrawRequest
    ): ApiResponse<WalletWithdrawResponse>

    /** Get withdrawal status */
    @POST("wallet/withdrawal-status")
    suspend fun getWalletWithdrawalStatus(
        @Header("publickey") publicKey: String,
        @Header("secretkey") secretKey: String,
        @Body request: WalletWithdrawalStatusRequest
    ): ApiResponse<WalletWithdrawalStatusResponse>
}
