package com.github.choonchernlim.betterPreconditions.exception.booleanPreconditionException;

import com.github.choonchernlim.betterPreconditions.core.PreconditionException;

/**
 * This exception is thrown if the value is not true.
 */
public class BooleanFalsePreconditionException extends PreconditionException {

    /**
     * Constructs a new exception with an error message.
     *
     * @param value Value
     * @param label Label
     */
    public BooleanFalsePreconditionException(final Boolean value, final String label) {
        super(String.format("%s [ %s ] must be true", label, value));
    }
}
