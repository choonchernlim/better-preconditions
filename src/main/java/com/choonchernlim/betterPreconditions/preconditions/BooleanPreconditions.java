package com.choonchernlim.betterPreconditions.preconditions;

import com.choonchernlim.betterPreconditions.core.Evaluation;
import com.choonchernlim.betterPreconditions.exception.BooleanFalsePreconditionException;
import com.choonchernlim.betterPreconditions.exception.BooleanTruePreconditionException;

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
     * Return new instance with default label.
     *
     * @param value Value
     * @return New instance
     */
    public static BooleanPreconditions expect(final Boolean value) {
        return expect(value, "Boolean");
    }

    /**
     * Return new instance.
     *
     * @param value Value
     * @param label Label
     * @return New instance
     */
    public static BooleanPreconditions expect(final Boolean value, final String label) {
        return new BooleanPreconditions(value, label);
    }

    /**
     * Enable negation.
     *
     * @return Current instance
     */
    @Override
    public BooleanPreconditions not() {
        return enableNegation(this);
    }

    /**
     * Ensure the object is null.
     *
     * @return Current instance
     */
    @Override
    public BooleanPreconditions toBeNull() {
        return addToBeNullAssertion(this);
    }

    /**
     * Ensure boolean is true.
     *
     * @return Current instance
     */
    public BooleanPreconditions toBeTrue(final Boolean value, final String label) {
        return addAssertion(this,
                            new Evaluation() {
                                @Override
                                public boolean eval() {
                                    return value;
                                }
                            },
                            new BooleanFalsePreconditionException(value, label),
                            new BooleanTruePreconditionException(value, label)
        );
    }
}
