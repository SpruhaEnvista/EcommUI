package com.envista.msi.api.web.rest.errors;

/**
 * Created by Sujit kumar on 17/05/2017.
 */
public class InvalidUserException extends RuntimeException{
    private Exception exception = null;
    public InvalidUserException() {
        super();
    }

    public InvalidUserException(String message) {
        super(message);
    }

    public InvalidUserException(String message, Exception exception) {
        super(message,exception);
        this.exception = exception;
    }

    public InvalidUserException(Exception exception) {
        super(exception);
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}
