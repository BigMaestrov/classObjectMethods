package com.company.Exceptions;

public class BookIndexOutOfBoundsException extends IndexOutOfBoundsException{
    public BookIndexOutOfBoundsException() {
    }

    public BookIndexOutOfBoundsException(String s) {
        super(s);
    }
}
