package com.choonchernlim.betterPreconditions.exception;

public class ObjectNotNullPreconditionException extends PreconditionException {

    public ObjectNotNullPreconditionException(final Object value, final String label) {
        super(String.format("%s [ %s ] must be null", label, value));
    }
}
