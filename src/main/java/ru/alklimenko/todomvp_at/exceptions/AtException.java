package ru.alklimenko.todomvp_at.exceptions;

public class AtException extends RuntimeException {
    static final long serialVersionUID = -4398329813670373492L;

    public AtException() {
        super();
    }

    public AtException(String message) {
        super(message);
    }

    public AtException(String message, Throwable cause) {
        super(message, cause);
    }

    public AtException(Throwable cause) {
        super(cause);
    }

    protected AtException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
