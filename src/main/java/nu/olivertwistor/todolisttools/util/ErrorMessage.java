package nu.olivertwistor.todolisttools.util;

import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NonNls;

public enum ErrorMessage
{
    FAILED_TO_READ_USER_INPUT("Failed to read user input."),
    FAILED_TO_COMMUNICATE_WITH_REMEMBER_THE_MILK("Failed to communicate with " +
            "Remember The Milk.");

    private final String message;

    ErrorMessage(final @NonNls String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return this.message;
    }

    /**
     * Prints a message to System.out and logs the same message (and a
     * throwable) at the ERROR level.
     *
     * @param logger    the Logger object on which to log
     * @param message   the error message to print and log
     * @param throwable a throwable to log
     *
     * @since 1.0.0
     */
    public static void printAndLogError(final Logger logger,
                                        final ErrorMessage message,
                                        final Throwable throwable)
    {
        System.out.println(message.getMessage());
        logger.error(message, throwable);
    }

    @Override
    public String toString()
    {
        return "ErrorMessage{" +
                "message='" + message + '\'' +
                '}';
    }
}
