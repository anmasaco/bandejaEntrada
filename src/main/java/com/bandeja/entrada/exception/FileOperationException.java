package com.bandeja.entrada.exception;
/**
 * Esta clase esta encargada de notificar cualquier error
 * relacionado con el manejo de archivos en el sistema de
 * archivos
 * @author UT-SunGemini-Zabala II
 *
 */
public class FileOperationException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *  Constructs a new runtime exception with null as its detail message.
     */
    public FileOperationException() {
        super();
    }

    /**
     * Constructs a new runtime exception with the specified detail message and cause.
     * @param message
     * @param cause
     */
    public FileOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * @param message
     */
    public FileOperationException(String message) {
        super(message);
    }

    /**
     * Constructs a new runtime exception with the specified cause and a
     * detail message of (cause==null ? null : cause.toString())
     * (which typically contains the class and detail message of cause).
     * @param cause
     */
    public FileOperationException(Throwable cause) {
        super(cause);
    }
}
