package com.epam.exception;

public class PeriodicalException extends RuntimeException {
    private final Integer id;

    public PeriodicalException(String message) {
        this(message, null, null);
    }

    public PeriodicalException(String message, Integer id) {
        super(message);
        this.id = id;
    }

    public PeriodicalException(Exception e) {
        this(e.getMessage(), e);
    }

    public PeriodicalException(String message, Exception e) {
        this(message, null, e);
    }

    public PeriodicalException(String message, Integer id, Exception e) {
        super(message, e);
        this.id = id;
    }

    public int getUuid() {
        return id;
    }
}
