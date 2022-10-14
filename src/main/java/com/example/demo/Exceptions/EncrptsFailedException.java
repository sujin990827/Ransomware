package com.example.demo.Exceptions;

public class EncrptsFailedException extends DefaultException {
    private static final long serialVersionUID = 1L;
    public static final int ERRNO = 1;
    public static final String MESSAGE = "감염정보 등록 실패";

    public EncrptsFailedException() {
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