package com.github.choonchernlim.betterPreconditions.exception;

/**
 * This exception is thrown if the value is blank.
 */
public class StringNotBlankPreconditionException extends PreconditionException {

    /**
     * Constructs a new exception with an error message.
     *
     * @param value Value
     * @param label Label
     */
    public StringNotBlankPreconditionException(final String value, final String label) {
        super(String.format("%s [ %s ] must be blank", label, value));
    }
}
