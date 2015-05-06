package com.github.choonchernlim.betterPreconditions.preconditions;

import com.github.choonchernlim.betterPreconditions.core.Matcher;
import com.github.choonchernlim.betterPreconditions.exception.JodaTimeEqualOrAfterPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.JodaTimeEqualPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.JodaTimeNotEqualOrAfterPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.JodaTimeNotEqualPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.PreconditionException;
import org.joda.time.base.BaseLocal;

public class JodaTimePreconditions extends BetterPreconditions<JodaTimePreconditions, BaseLocal> {
    /**
     * Private constructor.
     *
     * @param value Value
     * @param label Label
     */
    private JodaTimePreconditions(final BaseLocal value, final String label) {
        super(value, label);
    }

    /**
     * Returns new instance with default label.
     *
     * @param value Value
     * @return New instance
     */
    public static JodaTimePreconditions expect(final BaseLocal value) {
        return expect(value, "Joda Time");
    }

    /**
     * Returns new instance.
     *
     * @param value Value
     * @param label Label
     * @return New instance
     */
    public static JodaTimePreconditions expect(final BaseLocal value, final String label) {
        return new JodaTimePreconditions(value, label);
    }

    /**
     * Ensures given base local is equal to expected value.
     *
     * @see JodaTimePreconditions#toBeEqual(BaseLocal, String)
     */
    public JodaTimePreconditions toBeEqual(final BaseLocal expectedValue) {
        return toBeEqual(expectedValue, "Expected Joda Time");
    }

    /**
     * Ensures given base local is equal to expected value.
     *
     * @param expectedValue Second value
     * @param expectedLabel Second label
     * @return Current instance
     */
    public JodaTimePreconditions toBeEqual(final BaseLocal expectedValue, final String expectedLabel) {
        expectValueLabelToExist(expectedValue, expectedLabel);

        return customMatcher(new Matcher<BaseLocal>() {
            @Override
            public boolean match(final BaseLocal givenValue, final String givenLabel) {
                return expect(givenValue, givenLabel).not().toBeNull().check().isEqual(expectedValue);
            }

            @Override
            public PreconditionException getException(final BaseLocal givenValue, final String givenLabel) {
                return new JodaTimeNotEqualPreconditionException(givenValue, givenLabel, expectedValue, expectedLabel);
            }

            @Override
            public PreconditionException getNegatedException(final BaseLocal givenValue, final String givenLabel) {
                return new JodaTimeEqualPreconditionException(givenValue, givenLabel, expectedValue, expectedLabel);
            }
        });
    }

    /**
     * Ensures given base local is equal or after the expected value.
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

    private void expectValueLabelToExist(BaseLocal expectedValue, String expectedLabel) {
        expectValueLabelToExist(expectedValue, expectedLabel, "Expected Joda Time Label");
    }


//    public static void mustBeEqualOrAfter(final LocalDate startDate,
//                                          final LocalDate endDate,
//                                          final String startDateVariableName,
//                                          final String endDateVariableName) {
//        // TODO
//    }
//
//    public static void mustBeAfter(final LocalDate startDate,
//                                   final LocalDate endDate,
//                                   final String startDateVariableName,
//                                   final String endDateVariableName) {
//        // TODO
//    }
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
