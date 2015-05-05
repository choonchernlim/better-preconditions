package com.choonchernlim.betterPreconditions.preconditions;

import com.choonchernlim.betterPreconditions.core.Evaluation;
import com.choonchernlim.betterPreconditions.exception.ObjectNotNullPreconditionException;
import com.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException;

/**
 * Object related preconditions.
 */
public class ObjectPreconditions extends BetterPreconditions<Object> {

    /**
     * Private constructor.
     *
     * @param value Value
     * @param label Label
     */
    private ObjectPreconditions(final Object value, final String label) {
        super(value, label);
    }

    /**
     * Return new instance with default label.
     *
     * @param value Value
     * @return New instance
     */
    public static ObjectPreconditions expect(final Object value) {
        return expect(value, "Object");
    }

    /**
     * Return new instance.
     *
     * @param value Value
     * @param label Label
     * @return New instance
     */
    public static ObjectPreconditions expect(final Object value, final String label) {
        return new ObjectPreconditions(value, label);
    }

    /**
     * Enable negation.
     *
     * @return Current instance
     */
    public ObjectPreconditions not() {
        enableNegation();
        return this;
    }

    /**
     * Ensure the object is null.
     *
     * @return Current instance
     */
    public ObjectPreconditions toBeNull() {
        addNewAssertion(new Evaluation() {
                            @Override
                            public boolean eval() {
                                return value == null;
                            }
                        },
                        new ObjectNotNullPreconditionException(value, label),
                        new ObjectNullPreconditionException(value, label)
        );

        return this;
    }
}
