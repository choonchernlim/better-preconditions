package com.choonchernlim.betterPreconditions.exception;

public class BooleanFalsePreconditionException extends PreconditionException {

    public BooleanFalsePreconditionException(final Boolean value, final String label) {
        super(String.format("%s [ %s ] must be true", label, value));
    }
}
