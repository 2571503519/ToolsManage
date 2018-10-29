package com.jack.exception;

public class NameExistException extends  RuntimeException {

    public NameExistException(String message) {
        super(message);
    }
}
