package com.github.choonchernlim.betterPreconditions.exception;

import com.github.choonchernlim.betterPreconditions.core.PreconditionException;

/**
 * This exception is thrown if the value is true.
 */
public class BooleanTruePreconditionException extends PreconditionException {

    /**
     * Constructs a new exception with an error message.
     *
     * @param value Value
     * @param label Label
     */
    public BooleanTruePreconditionException(final Boolean value, final String label) {
        super(String.format("%s [ %s ] must not be true", label, value));
    }
}
