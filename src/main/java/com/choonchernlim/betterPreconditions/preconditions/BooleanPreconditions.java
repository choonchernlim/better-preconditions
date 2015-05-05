package com.choonchernlim.betterPreconditions.preconditions;

import com.choonchernlim.betterPreconditions.core.Matcher;
import com.choonchernlim.betterPreconditions.exception.BooleanFalsePreconditionException;
import com.choonchernlim.betterPreconditions.exception.BooleanTruePreconditionException;
import com.choonchernlim.betterPreconditions.exception.PreconditionException;

/**
 * Boolean related preconditions.
 */
public class BooleanPreconditions extends BetterPreconditions<BooleanPreconditions, Boolean> {
    /**
     * Private constructor.
     *
     * @param value Value
     * @param label Label
     */
    private BooleanPreconditions(final Boolean value, final String label) {
        super(value, label);
    }

    /**
     * Returns new instance with default label.
     *
     * @param value Value
     * @return New instance
     */
    public static BooleanPreconditions expect(final Boolean value) {
        return expect(value, "Boolean");
    }

    /**
     * Returns new instance.
     *
     * @param value Value
     * @param label Label
     * @return New instance
     */
    public static BooleanPreconditions expect(final Boolean value, final String label) {
        return new BooleanPreconditions(value, label);
    }

    /**
     * Ensures boolean is true.
     * <pre>
     * {@code
     * expect(true).toBeTrue().check();        // ok
     * expect(false).toBeTrue().check();       // throws BooleanFalsePreconditionException
     * expect(null).toBeTrue().check();        // throws BooleanFalsePreconditionException
     *
     * expect(false).not().toBeTrue().check(); // ok
     * expect(null).not().toBeTrue().check();  // ok
     * expect(true).not().toBeTrue().check();  // throws BooleanTruePreconditionException
     * }
     * </pre>
     *
     * @return Current instance
     */
    public BooleanPreconditions toBeTrue() {
        return customMatcher(new Matcher<Boolean>() {
            @Override
            public boolean match(final Boolean value, final String label) {
                return value == Boolean.TRUE;
            }

            @Override
            public PreconditionException getException(final Boolean value, final String label) {
                return new BooleanFalsePreconditionException(value, label);
            }

            @Override
            public PreconditionException getNegatedException(final Boolean value, final String label) {
                return new BooleanTruePreconditionException(value, label);
            }
        });
    }
}
