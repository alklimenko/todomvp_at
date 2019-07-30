package ru.alklimenko.todomvp_at.exceptions;

public class AtDriverException extends AtException {
    static final long serialVersionUID = -3720356023268967322L;

    public AtDriverException() {
        super();
    }

    public AtDriverException(String message) {
        super(message);
    }

    public AtDriverException(String message, Throwable cause) {
        super(message, cause);
    }

    public AtDriverException(Throwable cause) {
        super(cause);
    }

    protected AtDriverException(String message, Throwable cause,
                          boolean enableSuppression,
                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
