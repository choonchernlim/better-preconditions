package com.choonchernlim.betterPreconditions.exception;

public class StringBlankPreconditionException extends PreconditionException {

    public StringBlankPreconditionException(final String value, final String label) {
        super(String.format("%s [ %s ] must not be blank", label, value));
    }
}
