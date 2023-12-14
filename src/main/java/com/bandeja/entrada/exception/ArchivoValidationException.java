package com.bandeja.entrada.exception;

/**
 * Esta Exception tiene la responsabilidad de emitir excepciones relacionadas
 * cuando la informacion almacenada en la entidad no corresponde con la manejada
 * en el sistema de archivos durante la persistencia
 *
 *  @author UT-SunGemini-Zabala II
 *
 */
public class ArchivoValidationException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *  Constructs a new runtime exception with null as its detail message.
     */
    public ArchivoValidationException() {
        super();
    }

    /**
     *  Constructs a new runtime exception with the specified detail message and cause.
     * @param message
     * @param cause
     */
    public ArchivoValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * @param message
     */
    public ArchivoValidationException(String message) {
        super(message);
    }

    /**
     *   Constructs a new runtime exception with the specified cause and a
     *   detail message of (cause==null ? null : cause.toString())
     *   (which typically contains the class and detail message of cause).
     * @param cause
     */
    public ArchivoValidationException(Throwable cause) {
        super(cause);
    }


}
