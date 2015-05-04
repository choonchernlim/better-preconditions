package com.choonchernlim.betterPreconditions.exception;

public class StringNotBlankPreconditionException extends PreconditionException {

    public StringNotBlankPreconditionException(final String value, final String label) {
        super(String.format("%s [ %s ] must be blank", label, value));
    }
}
