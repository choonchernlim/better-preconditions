package com.choonchernlim.betterPreconditions.exception;

public class ObjectNullPreconditionException extends PreconditionException {

    public ObjectNullPreconditionException(final Object value, final String label) {
        super(String.format("%s [ %s ] must not be null", label, value));
    }
}
