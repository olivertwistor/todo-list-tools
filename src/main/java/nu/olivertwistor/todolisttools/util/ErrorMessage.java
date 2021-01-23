package nu.olivertwistor.todolisttools.util;

import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NonNls;

/**
 * This enum holds various error messages used throughout this application.
 * From each enum item, a translatable string meant for printing to the UI, and
 * a non-translatable string meant for logging can be extracted.
 *
 * There are also methods for logging
 * {@link #printAndLogError(Logger, ErrorMessage, Throwable) error} and
 * {@link #printAndLogFatal(Logger, ErrorMessage, Throwable) fatal} messages.
 *
 * @since 1.0.0
 */
@SuppressWarnings({"HardCodedStringLiteral", "StringConcatenation", "ClassUnconnectedToPackage"})
public enum ErrorMessage
{
    /**
     * When failing to read user input.
     *
     * @since 1.0.0
     */
    READ_USER_INPUT("Failed to read user input.", "Failed to read user input."),

    /**
     * When failing to communicate with Remember The Milk.
     *
     * @since 1.0.0
     */
    COMMUNICATE_WITH_RTM(
            "Failed to communicate with Remember The Milk.",
            "Failed to communicate with Remember The Milk."),

    /**
     * When failing to generate an authentication URL.
     *
     * @since 1.0.0
     */
    GENERATE_AUTH_URL(
            "Failed to generate an authentication URL.",
            "Failed to generate an authentication URL."),

    /**
     * When failing to write to a config file.
     *
     * @since 1.0.0
     */
    WRITE_TO_CONFIG_FILE(
            "Failed to write to config file.",
            "Failed to write to config file."),

    /**
     * When failing to load a configuration file.
     *
     * @since 1.0.0
     */
    LOAD_CONFIG_FILE(
            "Failed to load the configuration file.",
            "Failed to load the configuration file."),

    /**
     * When a CSV file does not exist.
     *
     * @since 1.0.0
     */
    CSV_FILE_NOT_FOUND(
            "Failed to find the specified CSV file.",
            "Failed to find the specified CSV file.");

    private final String printMessage;
    private final String logMessage;

    /**
     * Creates a new enum item with a message meant for the UI, and a message
     * meant for logging.
     *
     * @param printMessage message meant to be printed to the UI (should be a
     *                     translatable string)
     * @param logMessage   message meant to be logged (should be in English)
     *
     * @since 1.0.0
     */
    ErrorMessage(final String printMessage, final @NonNls String logMessage)
    {
        this.printMessage = printMessage;
        this.logMessage = logMessage;
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
        System.out.println("ERROR: " + message.printMessage);
        logger.error(message.logMessage, throwable);
    }

    /**
     * Prints a message to System.out and logs the same message (and a
     * throwable) at the FATAL level.
     *
     * @param logger    the Logger object on which to log
     * @param message   the error message to print and log
     * @param throwable a throwable to log
     *
     * @since 1.0.0
     */
    public static void printAndLogFatal(final Logger logger,
                                        final ErrorMessage message,
                                        final Throwable throwable)
    {
        System.out.println("FATAL: " + message.printMessage);
        logger.fatal(message.logMessage, throwable);
    }

    @Override
    public String toString()
    {
        return "ErrorMessage{" +
                "printMessage='" + this.printMessage + '\'' +
                ", logMessage='" + this.logMessage + '\'' +
                '}';
    }
}
