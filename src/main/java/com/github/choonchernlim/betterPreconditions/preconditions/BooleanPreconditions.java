package com.github.choonchernlim.betterPreconditions.preconditions;

import com.github.choonchernlim.betterPreconditions.core.Matcher;
import com.github.choonchernlim.betterPreconditions.core.PreconditionException;
import com.github.choonchernlim.betterPreconditions.core.Preconditions;
import com.github.choonchernlim.betterPreconditions.exception.BooleanFalsePreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.BooleanTruePreconditionException;
import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect;

/**
 * Boolean related preconditions.
 */
public class BooleanPreconditions extends Preconditions<BooleanPreconditions, Boolean> {
    /**
     * Package constructor.
     *
     * @param value Value
     * @param label Label
     */
    BooleanPreconditions(final Boolean value, final String label) {
        super(value, label);
    }

    /**
     * Ensures boolean is true.
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
