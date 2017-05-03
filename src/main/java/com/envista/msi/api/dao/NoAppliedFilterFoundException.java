package com.envista.msi.api.dao;

/**
 * Created by Sujit kumar on 27/04/2017.
 */
public class NoAppliedFilterFoundException extends RuntimeException {
    private Exception exception = null;
    public NoAppliedFilterFoundException() {
        super();
    }

    public NoAppliedFilterFoundException(String message) {
        super(message);
    }

    public NoAppliedFilterFoundException(String message, Exception exception) {
        super(message,exception);
        this.exception = exception;
    }

    public NoAppliedFilterFoundException(Exception exception) {
        super(exception);
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}
