package com.bandeja.entrada.exception;

import javax.ejb.ApplicationException;
@ApplicationException(rollback = true)
public class SiriException extends Exception {
    /**
     * Campo
     * @author Camilo Cruz 17/06/2010 12:04:55
     */
    private static final long serialVersionUID = 6828503489866937588L;
    private String publicMsg;

    public SiriException(String message, Throwable cause) {
        super(message, cause);
        publicMsg = message;
    }

    public SiriException(String message) {
        super(message);
        publicMsg = message;
    }

    public String getPublicMsg() {
        return publicMsg;
    }

    public void setPublicMsg(String publicMsg) {
        this.publicMsg = publicMsg;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Throwable#toString()
     */
    @Override
    public String toString() {
        return publicMsg;
    }
}
