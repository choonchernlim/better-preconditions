package com.github.choonchernlim.betterPreconditions.preconditions;

import com.github.choonchernlim.betterPreconditions.core.Matcher;
import com.github.choonchernlim.betterPreconditions.exception.BooleanFalsePreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.BooleanTruePreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.PreconditionException;

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
     * expect(null).toBeTrue().check();        // throws ObjectNullPreconditionException
     * expect(false).toBeTrue().check();       // throws BooleanFalsePreconditionException
     * expect(true).toBeTrue().check();        // ok
     *
     * expect(null).not().toBeTrue().check();  // throws ObjectNullPreconditionException
     * expect(true).not().toBeTrue().check();  // throws BooleanTruePreconditionException
     * expect(false).not().toBeTrue().check(); // ok
     * }
     * </pre>
     *
     * @return Current instance
     */
    public BooleanPreconditions toBeTrue() {
        return customMatcher(new Matcher<Boolean>() {
            @Override
            public boolean match(final Boolean value, final String label) {
                return expect(value, label).not().toBeNull().check();
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
