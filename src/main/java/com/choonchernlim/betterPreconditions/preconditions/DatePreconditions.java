package com.choonchernlim.betterPreconditions.preconditions;

import com.choonchernlim.betterPreconditions.core.Matcher;
import com.choonchernlim.betterPreconditions.exception.BaseLocalEqualPreconditionException;
import com.choonchernlim.betterPreconditions.exception.BaseLocalNotEqualPreconditionException;
import com.choonchernlim.betterPreconditions.exception.PreconditionException;
import org.joda.time.base.BaseLocal;

public class DatePreconditions extends BetterPreconditions<DatePreconditions, BaseLocal> {
    /**
     * Private constructor.
     *
     * @param value Value
     * @param label Label
     */
    private DatePreconditions(final BaseLocal value, final String label) {
        super(value, label);
    }

    /**
     * Returns new instance with default label.
     *
     * @param value Value
     * @return New instance
     */
    public static DatePreconditions expect(final BaseLocal value) {
        return expect(value, "BaseLocal");
    }

    /**
     * Returns new instance.
     *
     * @param value Value
     * @param label Label
     * @return New instance
     */
    public static DatePreconditions expect(final BaseLocal value, final String label) {
        return new DatePreconditions(value, label);
    }

    public DatePreconditions toBeEqual(final BaseLocal secondValue) {
        return toBeEqual(secondValue, "Second value");
    }

    /**
     * Ensures collection is empty.
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
     * @param secondValue Second value
     * @param secondLabel Second label
     * @return Current instance
     */
    public DatePreconditions toBeEqual(final BaseLocal secondValue, final String secondLabel) {
        return customMatcher(new Matcher<BaseLocal>() {
            @Override
            public boolean match(final BaseLocal firstValue, final String firstLabel) {
                return expect(firstValue, firstLabel).not().toBeNull().check().isEqual(secondValue);
            }

            @Override
            public PreconditionException getException(final BaseLocal firstValue, final String firstLabel) {
                return new BaseLocalNotEqualPreconditionException(firstValue, firstLabel, secondValue, secondLabel);
            }

            @Override
            public PreconditionException getNegatedException(BaseLocal firstValue, String firstLabel) {
                return new BaseLocalEqualPreconditionException(firstValue, firstLabel, secondValue, secondLabel);
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
