package com.choonchernlim.betterPreconditions.exception;

/**
 * This exception is thrown if the value is not null.
 */
public class ObjectNotNullPreconditionException extends PreconditionException {

    /**
     * Constructs a new exception with an error message.
     *
     * @param value Value
     * @param label Label
     */
    public ObjectNotNullPreconditionException(final Object value, final String label) {
        super(String.format("%s [ %s ] must be null", label, value));
    }
}
