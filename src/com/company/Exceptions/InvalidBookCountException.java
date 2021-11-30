package com.company.Exceptions;

public class InvalidBookCountException extends IllegalArgumentException{
    public InvalidBookCountException() {
    }

    public InvalidBookCountException(String s) {
        super(s);
    }

    public InvalidBookCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidBookCountException(Throwable cause) {
        super(cause);
    }
}
