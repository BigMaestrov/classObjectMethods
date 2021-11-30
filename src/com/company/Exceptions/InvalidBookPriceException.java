package com.company.Exceptions;

public class InvalidBookPriceException  extends IllegalArgumentException{
    public InvalidBookPriceException() {
    }

    public InvalidBookPriceException(String s) {
        super(s);
    }

    public InvalidBookPriceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidBookPriceException(Throwable cause) {
        super(cause);
    }
}
