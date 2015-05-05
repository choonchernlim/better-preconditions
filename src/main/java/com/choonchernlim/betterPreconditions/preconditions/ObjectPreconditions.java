package com.choonchernlim.betterPreconditions.preconditions;

/**
 * Object related preconditions.
 */
public class ObjectPreconditions extends BetterPreconditions<ObjectPreconditions, Object> {
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
    @Override
    public ObjectPreconditions not() {
        return enableNegation(this);
    }

    /**
     * Ensure the object is null.
     *
     * @return Current instance
     */
    @Override
    public ObjectPreconditions toBeNull() {
        return addToBeNullAssertion(this);
    }
}
