package com.example.demo.Exceptions;

public abstract class DefaultException extends Exception {
    private static final long serialVersionUID = 1L;

    protected int errno;

    public DefaultException(String e) {
        super(e);
    }

    public DefaultException(int errno, String e) {
        this(e);
        this.errno = errno;
    }

    public int getErrno() {
        return errno;
    }
}
