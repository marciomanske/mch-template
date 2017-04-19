package com.dminc.auth.exceptions;

@SuppressWarnings("serial")
public class UserPersistenceException extends Exception {

    public UserPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserPersistenceException(String message) {
        super(message);
    }

}
