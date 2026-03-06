package tech.bsigroup.cards

/**
 * Exception for API errors
 */
class BSICardsException(
    message: String,
    val code: Int = -1,
    cause: Throwable? = null
) : Exception(message, cause)

/**
 * Exception for network errors
 */
class NetworkException(
    message: String,
    cause: Throwable? = null
) : Exception(message, cause)

/**
 * Exception for validation errors
 */
class ValidationException(
    message: String
) : Exception(message)

