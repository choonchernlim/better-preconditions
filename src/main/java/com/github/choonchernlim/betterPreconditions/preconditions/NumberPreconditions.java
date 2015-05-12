package com.github.choonchernlim.betterPreconditions.preconditions;

import com.github.choonchernlim.betterPreconditions.core.Matcher;
import com.github.choonchernlim.betterPreconditions.core.PreconditionException;
import com.github.choonchernlim.betterPreconditions.core.Preconditions;
import com.github.choonchernlim.betterPreconditions.exception.NumberEqualOrGreaterThanPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.NumberEqualOrLessThanPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.NumberEqualPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.NumberGreaterThanPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.NumberLessThanPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.NumberNotEqualOrGreaterThanPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.NumberNotEqualOrLessThanPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.NumberNotEqualPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.NumberNotGreaterThanPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.NumberNotLessThanPreconditionException;

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
     * Ensures given base local is equal to expected value.
     *
     * @see NumberPreconditions#toBeEqual(Number, String)
     */
    @Override
    public NumberPreconditions toBeEqual(final Number expectedValue) {
        return toBeEqual(expectedValue, DEFAULT_EXPECTED_LABEL);
    }

    /**
     * Ensures given base local is equal to expected value.
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
     * Ensures given base local is equal to or greater than expected value.
     *
     * @see NumberPreconditions#toBeEqualOrGreaterThan(Number, String)
     */
    public NumberPreconditions toBeEqualOrGreaterThan(final Number expectedValue) {
        return toBeEqualOrGreaterThan(expectedValue, DEFAULT_EXPECTED_LABEL);
    }

    /**
     * Ensures given base local is equal to or greater than expected value.
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

    /**
     * Ensures given base local is greater than expected value.
     *
     * @see NumberPreconditions#toBeGreaterThan(Number, String)
     */
    public NumberPreconditions toBeGreaterThan(final Number expectedValue) {
        return toBeGreaterThan(expectedValue, DEFAULT_EXPECTED_LABEL);
    }

    /**
     * Ensures given base local is greater than expected value.
     *
     * @param expectedValue Second value
     * @param expectedLabel Second label
     * @return Current instance
     */
    public NumberPreconditions toBeGreaterThan(final Number expectedValue, final String expectedLabel) {
        expectValueLabelToExist(expectedValue, expectedLabel, DEFAULT_VALUE_LABEL);

        return customMatcher(new Matcher<Number>() {
            @Override
            public boolean match(final Number givenValue, final String givenLabel) {
                expectNotNullAndSameType(givenValue, givenLabel, expectedValue, expectedLabel);

                return compareTo(givenValue, expectedValue) > 0;
            }

            @Override
            public PreconditionException getException(final Number givenValue, final String givenLabel) {
                return new NumberNotGreaterThanPreconditionException(givenValue,
                                                                     givenLabel,
                                                                     expectedValue,
                                                                     expectedLabel);
            }

            @Override
            public PreconditionException getNegatedException(final Number givenValue, final String givenLabel) {
                return new NumberGreaterThanPreconditionException(givenValue,
                                                                  givenLabel,
                                                                  expectedValue,
                                                                  expectedLabel);
            }
        });
    }

    /**
     * Ensures given base local is equal to or less than expected value.
     *
     * @see NumberPreconditions#toBeEqualOrLessThan(Number, String)
     */
    public NumberPreconditions toBeEqualOrLessThan(final Number expectedValue) {
        return toBeEqualOrLessThan(expectedValue, DEFAULT_EXPECTED_LABEL);
    }

    /**
     * Ensures given base local is equal to or less than expected value.
     *
     * @param expectedValue Second value
     * @param expectedLabel Second label
     * @return Current instance
     */
    public NumberPreconditions toBeEqualOrLessThan(final Number expectedValue, final String expectedLabel) {
        expectValueLabelToExist(expectedValue, expectedLabel, DEFAULT_VALUE_LABEL);

        return customMatcher(new Matcher<Number>() {
            @Override
            public boolean match(final Number givenValue, final String givenLabel) {
                expectNotNullAndSameType(givenValue, givenLabel, expectedValue, expectedLabel);

                return compareTo(givenValue, expectedValue) <= 0;
            }

            @Override
            public PreconditionException getException(final Number givenValue, final String givenLabel) {
                return new NumberNotEqualOrLessThanPreconditionException(givenValue,
                                                                         givenLabel,
                                                                         expectedValue,
                                                                         expectedLabel);
            }

            @Override
            public PreconditionException getNegatedException(final Number givenValue, final String givenLabel) {
                return new NumberEqualOrLessThanPreconditionException(givenValue,
                                                                      givenLabel,
                                                                      expectedValue,
                                                                      expectedLabel);
            }
        });
    }

    /**
     * Ensures given base local is less than expected value.
     *
     * @see NumberPreconditions#toBeLessThan(Number, String)
     */
    public NumberPreconditions toBeLessThan(final Number expectedValue) {
        return toBeLessThan(expectedValue, DEFAULT_EXPECTED_LABEL);
    }

    /**
     * Ensures given base local is less than expected value.
     *
     * @param expectedValue Second value
     * @param expectedLabel Second label
     * @return Current instance
     */
    public NumberPreconditions toBeLessThan(final Number expectedValue, final String expectedLabel) {
        expectValueLabelToExist(expectedValue, expectedLabel, DEFAULT_VALUE_LABEL);

        return customMatcher(new Matcher<Number>() {
            @Override
            public boolean match(final Number givenValue, final String givenLabel) {
                expectNotNullAndSameType(givenValue, givenLabel, expectedValue, expectedLabel);

                return compareTo(givenValue, expectedValue) < 0;
            }

            @Override
            public PreconditionException getException(final Number givenValue, final String givenLabel) {
                return new NumberNotLessThanPreconditionException(givenValue,
                                                                  givenLabel,
                                                                  expectedValue,
                                                                  expectedLabel);
            }

            @Override
            public PreconditionException getNegatedException(final Number givenValue, final String givenLabel) {
                return new NumberLessThanPreconditionException(givenValue,
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
        else {
            return ((Long) ((AtomicLong) givenValue).get())
                    .compareTo(((AtomicLong) expectedValue).get());
        }
    }


}
