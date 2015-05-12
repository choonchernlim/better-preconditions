package com.github.choonchernlim.betterPreconditions.preconditions;

import com.github.choonchernlim.betterPreconditions.core.Matcher;
import com.github.choonchernlim.betterPreconditions.core.PreconditionException;
import com.github.choonchernlim.betterPreconditions.core.Preconditions;
import com.github.choonchernlim.betterPreconditions.exception.JodaTimeAfterPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.JodaTimeBeforePreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.JodaTimeEqualOrAfterPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.JodaTimeEqualOrBeforePreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.JodaTimeNotAfterPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.JodaTimeNotBeforePreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.JodaTimeNotEqualOrAfterPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.JodaTimeNotEqualOrBeforePreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.ObjectEqualPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.ObjectNotEqualPreconditionException;
import org.joda.time.base.BaseLocal;

public class JodaTimePreconditions extends Preconditions<JodaTimePreconditions, BaseLocal> {

    private static final String DEFAULT_EXPECTED_LABEL = "Expected Joda Time";
    private static final String DEFAULT_VALUE_LABEL = DEFAULT_EXPECTED_LABEL + " Label";

    /**
     * Package constructor.
     *
     * @param value Value
     * @param label Label
     */
    JodaTimePreconditions(final BaseLocal value, final String label) {
        super(value, label);
    }

    /**
     * Ensures given base local is equal to expected value. Override default implementation to return proper label.
     *
     * @see JodaTimePreconditions#toBeEqual(BaseLocal, String)
     */
    @Override
    public JodaTimePreconditions toBeEqual(final BaseLocal expectedValue) {
        return toBeEqual(expectedValue, DEFAULT_EXPECTED_LABEL);
    }

    /**
     * Ensures given base local is equal to expected value. Override default implementation and uses <code>isEquals()</code>
     * because Joda Time's <code>equals()</code> takes account of chronology, which is too strict.
     *
     * @param expectedValue Second value
     * @param expectedLabel Second label
     * @return Current instance
     */
    @Override
    public JodaTimePreconditions toBeEqual(final BaseLocal expectedValue, final String expectedLabel) {
        expectValueLabelToExist(expectedValue, expectedLabel, DEFAULT_VALUE_LABEL);

        return customMatcher(new Matcher<BaseLocal>() {
            @Override
            public boolean match(final BaseLocal givenValue, final String givenLabel) {
                expectNotNullAndSameType(givenValue, givenLabel, expectedValue, expectedLabel);

                return givenValue.isEqual(expectedValue);
            }

            @Override
            public PreconditionException getException(final BaseLocal givenValue, final String givenLabel) {
                return new ObjectNotEqualPreconditionException(givenValue, givenLabel, expectedValue, expectedLabel);
            }

            @Override
            public PreconditionException getNegatedException(final BaseLocal givenValue, final String givenLabel) {
                return new ObjectEqualPreconditionException(givenValue, givenLabel, expectedValue, expectedLabel);
            }
        });
    }

    /**
     * Ensures given base local is equal to or after expected value.
     *
     * @see JodaTimePreconditions#toBeEqualOrAfter(BaseLocal, String)
     */
    public JodaTimePreconditions toBeEqualOrAfter(final BaseLocal expectedValue) {
        return toBeEqualOrAfter(expectedValue, DEFAULT_EXPECTED_LABEL);
    }

    /**
     * Ensures given base local is equal to or after expected value.
     *
     * @param expectedValue Second value
     * @param expectedLabel Second label
     * @return Current instance
     */
    public JodaTimePreconditions toBeEqualOrAfter(final BaseLocal expectedValue, final String expectedLabel) {
        expectValueLabelToExist(expectedValue, expectedLabel, DEFAULT_VALUE_LABEL);

        return customMatcher(new Matcher<BaseLocal>() {
            @Override
            public boolean match(final BaseLocal givenValue, final String givenLabel) {
                expectNotNullAndSameType(givenValue, givenLabel, expectedValue, expectedLabel);

                return givenValue.isEqual(expectedValue) || givenValue.isAfter(expectedValue);
            }

            @Override
            public PreconditionException getException(final BaseLocal givenValue, final String givenLabel) {
                return new JodaTimeNotEqualOrAfterPreconditionException(givenValue,
                                                                        givenLabel,
                                                                        expectedValue,
                                                                        expectedLabel);
            }

            @Override
            public PreconditionException getNegatedException(final BaseLocal givenValue, final String givenLabel) {
                return new JodaTimeEqualOrAfterPreconditionException(givenValue,
                                                                     givenLabel,
                                                                     expectedValue,
                                                                     expectedLabel);
            }
        });
    }

    /**
     * Ensures given base local is after expected value.
     *
     * @see JodaTimePreconditions#toBeAfter(BaseLocal)
     */
    public JodaTimePreconditions toBeAfter(final BaseLocal expectedValue) {
        return toBeAfter(expectedValue, DEFAULT_EXPECTED_LABEL);
    }

    /**
     * Ensures given base local is after expected value.
     *
     * @param expectedValue Second value
     * @param expectedLabel Second label
     * @return Current instance
     */
    public JodaTimePreconditions toBeAfter(final BaseLocal expectedValue, final String expectedLabel) {
        expectValueLabelToExist(expectedValue, expectedLabel, DEFAULT_VALUE_LABEL);

        return customMatcher(new Matcher<BaseLocal>() {
            @Override
            public boolean match(final BaseLocal givenValue, final String givenLabel) {
                expectNotNullAndSameType(givenValue, givenLabel, expectedValue, expectedLabel);

                return givenValue.isAfter(expectedValue);
            }

            @Override
            public PreconditionException getException(final BaseLocal givenValue, final String givenLabel) {
                return new JodaTimeNotAfterPreconditionException(givenValue,
                                                                 givenLabel,
                                                                 expectedValue,
                                                                 expectedLabel);
            }

            @Override
            public PreconditionException getNegatedException(final BaseLocal givenValue, final String givenLabel) {
                return new JodaTimeAfterPreconditionException(givenValue,
                                                              givenLabel,
                                                              expectedValue,
                                                              expectedLabel);
            }
        });
    }

    /**
     * Ensures given base local is equal to or before expected value.
     *
     * @see JodaTimePreconditions#toBeEqualOrBefore(BaseLocal, String)
     */
    public JodaTimePreconditions toBeEqualOrBefore(final BaseLocal expectedValue) {
        return toBeEqualOrBefore(expectedValue, DEFAULT_EXPECTED_LABEL);
    }

    /**
     * Ensures given base local is equal to or before expected value.
     *
     * @param expectedValue Second value
     * @param expectedLabel Second label
     * @return Current instance
     */
    public JodaTimePreconditions toBeEqualOrBefore(final BaseLocal expectedValue, final String expectedLabel) {
        expectValueLabelToExist(expectedValue, expectedLabel, DEFAULT_VALUE_LABEL);

        return customMatcher(new Matcher<BaseLocal>() {
            @Override
            public boolean match(final BaseLocal givenValue, final String givenLabel) {
                expectNotNullAndSameType(givenValue, givenLabel, expectedValue, expectedLabel);

                return givenValue.isEqual(expectedValue) || givenValue.isBefore(expectedValue);
            }

            @Override
            public PreconditionException getException(final BaseLocal givenValue, final String givenLabel) {
                return new JodaTimeNotEqualOrBeforePreconditionException(givenValue,
                                                                         givenLabel,
                                                                         expectedValue,
                                                                         expectedLabel);
            }

            @Override
            public PreconditionException getNegatedException(final BaseLocal givenValue, final String givenLabel) {
                return new JodaTimeEqualOrBeforePreconditionException(givenValue,
                                                                      givenLabel,
                                                                      expectedValue,
                                                                      expectedLabel);
            }
        });
    }

    /**
     * Ensures given base local is before expected value.
     *
     * @see JodaTimePreconditions#toBeBefore(BaseLocal)
     */
    public JodaTimePreconditions toBeBefore(final BaseLocal expectedValue) {
        return toBeBefore(expectedValue, DEFAULT_EXPECTED_LABEL);
    }

    /**
     * Ensures given base local is before expected value.
     *
     * @param expectedValue Second value
     * @param expectedLabel Second label
     * @return Current instance
     */
    public JodaTimePreconditions toBeBefore(final BaseLocal expectedValue, final String expectedLabel) {
        expectValueLabelToExist(expectedValue, expectedLabel, DEFAULT_VALUE_LABEL);

        return customMatcher(new Matcher<BaseLocal>() {
            @Override
            public boolean match(final BaseLocal givenValue, final String givenLabel) {
                expectNotNullAndSameType(givenValue, givenLabel, expectedValue, expectedLabel);

                return givenValue.isBefore(expectedValue);
            }

            @Override
            public PreconditionException getException(final BaseLocal givenValue, final String givenLabel) {
                return new JodaTimeNotBeforePreconditionException(givenValue,
                                                                  givenLabel,
                                                                  expectedValue,
                                                                  expectedLabel);
            }

            @Override
            public PreconditionException getNegatedException(final BaseLocal givenValue, final String givenLabel) {
                return new JodaTimeBeforePreconditionException(givenValue,
                                                               givenLabel,
                                                               expectedValue,
                                                               expectedLabel);
            }
        });
    }


}
