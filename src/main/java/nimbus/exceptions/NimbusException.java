package nimbus.exceptions;

/**
 * Represents an exception specific to the Nimbus application.
 */
public class NimbusException extends Exception {

    /**
     * Constructs a NimbusException with the specified detail message.
     *
     * @param message The detail message indicating what went wrong.
     */
    public NimbusException(String message) {
        super(message);
    }

}
