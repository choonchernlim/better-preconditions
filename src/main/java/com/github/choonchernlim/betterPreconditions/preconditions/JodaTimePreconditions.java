package com.github.choonchernlim.betterPreconditions.preconditions;

import com.github.choonchernlim.betterPreconditions.core.Matcher;
import com.github.choonchernlim.betterPreconditions.exception.JodaTimeEqualPreconditionException;
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
     * <pre>
     * {@code
     * TODO TBD!
     * expect(null).toBeEmpty().check();                           // throws ObjectNullPreconditionException
     * expect(Lists.newArrayList(1, 2)).toBeEmpty().check();       // throws BaseLocalNotEmptyPreconditionException
     * expect(Lists.newArrayList()).toBeEmpty().check();           // ok
     *
     * expect(null).not().toBeEmpty().check();                     // throws ObjectNullPreconditionException
     * expect(Lists.newArrayList()).not().toBeEmpty().check();     // throws BaseLocalEmptyPreconditionException
     * expect(Lists.newArrayList(1, 2)).not().toBeEmpty().check(); // ok
     * }
     * </pre>
     *
     * @param expectedValue Second value
     * @param expectedLabel Second label
     * @return Current instance
     */
    public JodaTimePreconditions toBeEqual(final BaseLocal expectedValue, final String expectedLabel) {
        expectValueLabelToExist(expectedValue, expectedLabel, "Expected Joda Time Label");

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


//    public static void mustBeEqual(final LocalDate startDate,
//                                   final LocalDate endDate,
//                                   final String startDateVariableName,
//                                   final String endDateVariableName) {
//        // TODO
//    }
//
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
