package com.envista.msi.api.dao;

/**
 * Created by Sujit kumar on 05/04/2017.
 */
public class DaoException extends RuntimeException {
    private Exception exception = null;
    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Exception exception) {
        super(message,exception);
        this.exception = exception;
    }

    public DaoException(Exception exception) {
        super(exception);
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}
