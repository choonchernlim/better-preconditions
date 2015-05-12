package com.github.choonchernlim.betterPreconditions.preconditions;

import com.github.choonchernlim.betterPreconditions.core.Matcher;
import com.github.choonchernlim.betterPreconditions.core.PreconditionException;
import com.github.choonchernlim.betterPreconditions.core.Preconditions;
import com.github.choonchernlim.betterPreconditions.exception.NumberEqualOrGreaterThanPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.NumberEqualPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.NumberNotEqualOrGreaterThanPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.NumberNotEqualPreconditionException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class NumberPreconditions extends Preconditions<NumberPreconditions, Number> {

    private static final String DEFAULT_EXPECTED_LABEL = "Expected Number";
    private static final String DEFAULT_VALUE_LABEL = DEFAULT_EXPECTED_LABEL + " Label";

    protected NumberPreconditions(Number value, String label) {
        super(value, label);
    }

    /**
     * Ensures given base local is equal to or after expected value.
     *
     * @see NumberPreconditions#toBeEqual(Number, String)
     */
    @Override
    public NumberPreconditions toBeEqual(final Number expectedValue) {
        return toBeEqual(expectedValue, DEFAULT_EXPECTED_LABEL);
    }

    /**
     * Ensures given base local is equal to or after expected value.
     *
     * @param expectedValue Second value
     * @param expectedLabel Second label
     * @return Current instance
     */
    @Override
    public NumberPreconditions toBeEqual(final Number expectedValue, final String expectedLabel) {
        expectValueLabelToExist(expectedValue, expectedLabel, DEFAULT_VALUE_LABEL);

        return customMatcher(new Matcher<Number>() {
            @Override
            public boolean match(final Number givenValue, final String givenLabel) {
                expectNotNullAndSameType(givenValue, givenLabel, expectedValue, expectedLabel);

                return compareTo(givenValue, expectedValue) == 0;
            }

            @Override
            public PreconditionException getException(final Number givenValue, final String givenLabel) {
                return new NumberNotEqualPreconditionException(givenValue, givenLabel, expectedValue, expectedLabel);
            }

            @Override
            public PreconditionException getNegatedException(final Number givenValue, final String givenLabel) {
                return new NumberEqualPreconditionException(givenValue, givenLabel, expectedValue, expectedLabel);
            }
        });
    }

    /**
     * Ensures given base local is equal to or after expected value.
     *
     * @see NumberPreconditions#toBeEqualOrGreaterThan(Number, String)
     */
    public NumberPreconditions toBeEqualOrGreaterThan(final Number expectedValue) {
        return toBeEqualOrGreaterThan(expectedValue, DEFAULT_EXPECTED_LABEL);
    }

    /**
     * Ensures given base local is equal to or after expected value.
     *
     * @param expectedValue Second value
     * @param expectedLabel Second label
     * @return Current instance
     */
    public NumberPreconditions toBeEqualOrGreaterThan(final Number expectedValue, final String expectedLabel) {
        expectValueLabelToExist(expectedValue, expectedLabel, DEFAULT_VALUE_LABEL);

        return customMatcher(new Matcher<Number>() {
            @Override
            public boolean match(final Number givenValue, final String givenLabel) {
                expectNotNullAndSameType(givenValue, givenLabel, expectedValue, expectedLabel);

                return compareTo(givenValue, expectedValue) >= 0;
            }

            @Override
            public PreconditionException getException(final Number givenValue, final String givenLabel) {
                return new NumberNotEqualOrGreaterThanPreconditionException(givenValue,
                                                                            givenLabel,
                                                                            expectedValue,
                                                                            expectedLabel);
            }

            @Override
            public PreconditionException getNegatedException(final Number givenValue, final String givenLabel) {
                return new NumberEqualOrGreaterThanPreconditionException(givenValue,
                                                                         givenLabel,
                                                                         expectedValue,
                                                                         expectedLabel);
            }
        });
    }

    private int compareTo(Number givenValue, Number expectedValue) {
        if (givenValue instanceof Integer) {
            return ((Integer) givenValue).compareTo((Integer) expectedValue);
        }
        else if (givenValue instanceof Double) {
            return ((Double) givenValue).compareTo((Double) expectedValue);
        }
        else if (givenValue instanceof Float) {
            return ((Float) givenValue).compareTo((Float) expectedValue);
        }
        else if (givenValue instanceof Long) {
            return ((Long) givenValue).compareTo((Long) expectedValue);
        }
        else if (givenValue instanceof Short) {
            return ((Short) givenValue).compareTo((Short) expectedValue);
        }
        else if (givenValue instanceof Byte) {
            return ((Byte) givenValue).compareTo((Byte) expectedValue);
        }
        else if (givenValue instanceof BigInteger) {
            return ((BigInteger) givenValue).compareTo((BigInteger) expectedValue);
        }
        else if (givenValue instanceof BigDecimal) {
            return ((BigDecimal) givenValue).compareTo((BigDecimal) expectedValue);
        }
        else if (givenValue instanceof AtomicInteger) {
            return ((Integer) ((AtomicInteger) givenValue).get())
                    .compareTo(((AtomicInteger) expectedValue).get());
        }
        else if (givenValue instanceof AtomicLong) {
            return ((Long) ((AtomicLong) givenValue).get())
                    .compareTo(((AtomicLong) expectedValue).get());
        }

        throw new UnsupportedOperationException("Unsupported type: " + givenValue.getClass());
    }


//
//    public static void mustBeEqualOrGreaterThan(final Integer firstNumber,
//                                                final Integer secondNumber,
//                                                final String firstNumberVariableName,
//                                                final String secondNumberVariableName) {
//        // TODO
//    }
//
//    public static void mustBeGreater(final Integer firstNumber,
//                                     final Integer secondNumber,
//                                     final String firstNumberVariableName,
//                                     final String secondNumberVariableName) {
//        // TODO
//    }
//
//    public static void mustBeEqualOrLessThan(final Integer firstNumber,
//                                             final Integer secondNumber,
//                                             final String firstNumberVariableName,
//                                             final String secondNumberVariableName) {
//        // TODO
//    }
//
//    public static void mustBeLessThan(final Integer firstNumber,
//                                      final Integer secondNumber,
//                                      final String firstNumberVariableName,
//                                      final String secondNumberVariableName) {
//        // TODO
//    }


}
