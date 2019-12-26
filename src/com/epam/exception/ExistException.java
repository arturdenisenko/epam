package com.epam.exception;

public class ExistException extends DAOException {
    public ExistException(String name) {
        super("Publisher " + name + " alredy exist", name);
    }
}
