package com.github.choonchernlim.betterPreconditions.exception;

/**
 * This exception is thrown if the value is not null.
 */
public class ObjectNullPreconditionException extends PreconditionException {

    /**
     * Constructs a new exception with an error message.
     *
     * @param value Value
     * @param label Label
     */
    public ObjectNullPreconditionException(final Object value, final String label) {
        super(String.format("%s [ %s ] must not be null", label, value));
    }
}
