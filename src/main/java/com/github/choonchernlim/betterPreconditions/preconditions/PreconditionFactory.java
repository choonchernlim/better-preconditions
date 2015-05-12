package com.github.choonchernlim.betterPreconditions.preconditions;

import org.joda.time.base.BaseLocal;

import java.util.Collection;

/**
 * Returns new Precondition instances.
 */
public class PreconditionFactory {

    private PreconditionFactory() {
    }

    /**
     * Returns new BooleanPreconditions instance with default label.
     *
     * @param value Value
     * @return New instance
     */
    public static BooleanPreconditions expect(final Boolean value) {
        return expect(value, "Boolean");
    }

    /**
     * Returns new BooleanPreconditions instance.
     *
     * @param value Value
     * @param label Label
     * @return New instance
     */
    public static BooleanPreconditions expect(final Boolean value, final String label) {
        return new BooleanPreconditions(value, label);
    }

    /**
     * Returns new CollectionPreconditions instance with default label.
     *
     * @param value Value
     * @return New instance
     */
    public static CollectionPreconditions expect(final Collection value) {
        return expect(value, "Collection");
    }

    /**
     * Returns new CollectionPreconditions instance.
     *
     * @param value Value
     * @param label Label
     * @return New instance
     */
    public static CollectionPreconditions expect(final Collection value, final String label) {
        return new CollectionPreconditions(value, label);
    }

    /**
     * Returns new JodaTimePreconditions instance with default label.
     *
     * @param value Value
     * @return New instance
     */
    public static JodaTimePreconditions expect(final BaseLocal value) {
        return expect(value, "Joda Time");
    }

    /**
     * Returns new JodaTimePreconditions instance.
     *
     * @param value Value
     * @param label Label
     * @return New instance
     */
    public static JodaTimePreconditions expect(final BaseLocal value, final String label) {
        return new JodaTimePreconditions(value, label);
    }

    /**
     * Returns new ObjectPreconditions instance with default label.
     *
     * @param value Value
     * @return New instance
     */
    public static ObjectPreconditions expect(final Object value) {
        return expect(value, "Object");
    }

    /**
     * Returns new ObjectPreconditions instance.
     *
     * @param value Value
     * @param label Label
     * @return New instance
     */
    public static ObjectPreconditions expect(final Object value, final String label) {
        return new ObjectPreconditions(value, label);
    }

    /**
     * Returns new StringPreconditions instance with default label.
     *
     * @param value Value
     * @return New instance
     */
    public static StringPreconditions expect(final String value) {
        return expect(value, "String");
    }

    /**
     * Returns new StringPreconditions instance.
     *
     * @param value Value
     * @param label Label
     * @return New instance
     */
    public static StringPreconditions expect(final String value, final String label) {
        return new StringPreconditions(value, label);
    }

    /**
     * Returns new NumberPreconditions instance with default label.
     *
     * @param value Value
     * @return New instance
     */
    public static NumberPreconditions expect(final Number value) {
        return expect(value, "Number");
    }

    /**
     * Returns new NumberPreconditions instance.
     *
     * @param value Value
     * @param label Label
     * @return New instance
     */
    public static NumberPreconditions expect(final Number value, final String label) {
        return new NumberPreconditions(value, label);
    }
}
