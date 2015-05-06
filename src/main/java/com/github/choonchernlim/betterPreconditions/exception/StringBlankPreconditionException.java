package com.github.choonchernlim.betterPreconditions.exception;

import com.github.choonchernlim.betterPreconditions.core.PreconditionException;

/**
 * This exception is thrown if the value is not blank.
 */
public class StringBlankPreconditionException extends PreconditionException {

    /**
     * Constructs a new exception with an error message.
     *
     * @param value Value
     * @param label Label
     */
    public StringBlankPreconditionException(final String value, final String label) {
        super(String.format("%s [ %s ] must not be blank", label, value));
    }
}
