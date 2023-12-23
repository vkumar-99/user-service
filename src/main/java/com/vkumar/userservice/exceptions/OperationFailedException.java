package com.vkumar.userservice.exceptions;

public class OperationFailedException extends RuntimeException {

    public OperationFailedException() {
        super("Operation has failed !!!");
    }

    public OperationFailedException(String message) {
        super(message);
    }
}
