package nu.olivertwistor.todolisttools.rtmapi;

/**
 * This exception is thrown when there is a problem with REST request
 * signatures.
 *
 * @since 1.0.0
 */
class RequestSignatureException extends RuntimeException
{
    private static final long serialVersionUID = -5608575382636692379L;

    /**
     * A request signature exception with a message and a cause.
     *
     * @param message exception message to show
     * @param cause   the cause of this exception
     *
     * @since 1.0.0
     */
    RequestSignatureException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}
