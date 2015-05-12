package com.github.choonchernlim.betterPreconditions.exception;

import com.github.choonchernlim.betterPreconditions.core.PreconditionException;

/**
 * This exception is thrown if the first value is equal to or greater than second value.
 */
public class NumberEqualOrGreaterThanPreconditionException extends PreconditionException {

    /**
     * Constructs a new exception with an error message.
     *
     * @param firstValue  First value
     * @param firstLabel  First label
     * @param secondValue Second value
     * @param secondLabel Second label
     */
    public NumberEqualOrGreaterThanPreconditionException(final Number firstValue,
                                                         final String firstLabel,
                                                         final Number secondValue,
                                                         final String secondLabel) {
        super(String.format("%s [ %s ] must not be equal to or greater than %s [ %s ]",
                            firstLabel,
                            firstValue,
                            secondLabel,
                            secondValue));
    }
}
