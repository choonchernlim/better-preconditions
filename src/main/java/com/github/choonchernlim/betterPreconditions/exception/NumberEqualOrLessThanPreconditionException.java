package com.github.choonchernlim.betterPreconditions.exception;

import com.github.choonchernlim.betterPreconditions.core.PreconditionException;

/**
 * This exception is thrown if the first value is equal to or less than second value.
 */
public class NumberEqualOrLessThanPreconditionException extends PreconditionException {

    /**
     * Constructs a new exception with an error message.
     *
     * @param firstValue  First value
     * @param firstLabel  First label
     * @param secondValue Second value
     * @param secondLabel Second label
     */
    public NumberEqualOrLessThanPreconditionException(final Number firstValue,
                                                      final String firstLabel,
                                                      final Number secondValue,
                                                      final String secondLabel) {
        super(String.format("%s [ %s ] must not be equal to or less than %s [ %s ]",
                            firstLabel,
                            firstValue,
                            secondLabel,
                            secondValue));
    }
}
