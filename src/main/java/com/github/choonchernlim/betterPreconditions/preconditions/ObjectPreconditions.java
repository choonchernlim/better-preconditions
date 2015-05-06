package com.github.choonchernlim.betterPreconditions.preconditions;

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
     * Returns new instance with default label.
     *
     * @param value Value
     * @return New instance
     */
    public static ObjectPreconditions expect(final Object value) {
        return expect(value, "Object");
    }

    /**
     * Returns new instance.
     *
     * @param value Value
     * @param label Label
     * @return New instance
     */
    public static ObjectPreconditions expect(final Object value, final String label) {
        return new ObjectPreconditions(value, label);
    }
}
