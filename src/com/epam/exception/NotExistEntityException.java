package com.epam.exception;

public class NotExistEntityException extends PeriodicalException {
    public NotExistEntityException(Long id) {
        super("THE ENTITY WITH ID = " + id + " ARE NOT EXIST !");
    }
}
