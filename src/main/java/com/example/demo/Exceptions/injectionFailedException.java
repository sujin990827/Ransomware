package com.example.demo.Exceptions;

public class injectionFailedException extends DefaultException {
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 2;
    public static final String MESSAGE = "감염 키 등록 실패";

    public injectionFailedException() {
        super(ERRNO, MESSAGE);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public int getErrno() {
        return super.getErrno();
    }
}