package com.github.choonchernlim.betterPreconditions.preconditions;

import com.github.choonchernlim.betterPreconditions.core.Matcher;
import com.github.choonchernlim.betterPreconditions.core.PreconditionException;
import com.github.choonchernlim.betterPreconditions.core.Preconditions;
import com.github.choonchernlim.betterPreconditions.exception.JodaTimeAfterPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.JodaTimeEqualOrAfterPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.JodaTimeNotAfterPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.JodaTimeNotEqualOrAfterPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.ObjectEqualPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.ObjectNotEqualPreconditionException;
import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect;
import org.joda.time.base.BaseLocal;

public class JodaTimePreconditions extends Preconditions<JodaTimePreconditions, BaseLocal> {

    private static final String DEFAULT_EXPECTED_LABEL = "Expected Joda Time";

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
        expectValueLabelToExist(expectedValue, expectedLabel);

        return customMatcher(new Matcher<BaseLocal>() {
            @Override
            public boolean match(final BaseLocal givenValue, final String givenLabel) {
                return expect(givenValue, givenLabel).not().toBeNull().check().isEqual(expectedValue);
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
        return toBeEqualOrAfter(expectedValue, "Expected Joda Time");
    }

    /**
     * Ensures given base local is equal to or after expected value.
     *
     * @param expectedValue Second value
     * @param expectedLabel Second label
     * @return Current instance
     */
    public JodaTimePreconditions toBeEqualOrAfter(final BaseLocal expectedValue, final String expectedLabel) {
        expectValueLabelToExist(expectedValue, expectedLabel);

        return customMatcher(new Matcher<BaseLocal>() {
            @Override
            public boolean match(final BaseLocal givenValue, final String givenLabel) {
                expect(givenValue, givenLabel).not().toBeNull().check();

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
        return toBeAfter(expectedValue, "Expected Joda Time");
    }

    /**
     * Ensures given base local is after expected value.
     *
     * @param expectedValue Second value
     * @param expectedLabel Second label
     * @return Current instance
     */
    public JodaTimePreconditions toBeAfter(final BaseLocal expectedValue, final String expectedLabel) {
        expectValueLabelToExist(expectedValue, expectedLabel);

        return customMatcher(new Matcher<BaseLocal>() {
            @Override
            public boolean match(final BaseLocal givenValue, final String givenLabel) {
                expect(givenValue, givenLabel).not().toBeNull().check();

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

    private void expectValueLabelToExist(BaseLocal expectedValue, String expectedLabel) {
        expectValueLabelToExist(expectedValue, expectedLabel, "Expected Joda Time Label");
    }


//
//    public static void mustBeEqualOrBefore(final LocalDate startDate,
//                                           final LocalDate endDate,
//                                           final String startDateVariableName,
//                                           final String endDateVariableName) {
//        // TODO
//    }
//
//    public static void mustBeBefore(final LocalDate startDate,
//                                    final LocalDate endDate,
//                                    final String startDateVariableName,
//                                    final String endDateVariableName) {
//        // TODO
//    }
}
