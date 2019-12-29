package com.epam.exception;

public class ExistEntityException extends PeriodicalException {
    public ExistEntityException(String name) {
        super("Entity with  " + name + " already exist");
    }
}
