package com.polling.app.exception;

public class PollNotFoundException extends RuntimeException{
    public PollNotFoundException(String message) {
        super(message);
    }
}
