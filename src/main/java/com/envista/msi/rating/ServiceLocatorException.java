package com.envista.msi.rating;

public class ServiceLocatorException extends Exception {
    private static final long serialVersionUID = 97945664101171507L;
    private Exception exception = null;

    public ServiceLocatorException() {
        super();
    }

    public ServiceLocatorException(String message) {
        super(message);
    }

    public ServiceLocatorException(String message, Exception exception) {
        super(message);
        this.exception = exception;
    }

    public ServiceLocatorException(Exception exception) {
        super();
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}