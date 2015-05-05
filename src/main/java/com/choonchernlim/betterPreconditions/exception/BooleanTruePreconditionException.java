package com.choonchernlim.betterPreconditions.exception;

public class BooleanTruePreconditionException extends PreconditionException {

    public BooleanTruePreconditionException(final Boolean value, final String label) {
        super(String.format("%s [ %s ] must not be true", label, value));
    }
}
