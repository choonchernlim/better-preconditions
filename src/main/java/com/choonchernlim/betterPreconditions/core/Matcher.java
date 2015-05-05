package com.choonchernlim.betterPreconditions.core;

import com.choonchernlim.betterPreconditions.exception.PreconditionException;

/**
 * Precondition matcher.
 *
 * @param <V> Value type
 */
public abstract class Matcher<V> {

    /**
     * Computes matching based on the given value.
     *
     * @param value Value
     * @param label Label
     * @return true if matches, otherwise false
     */
    public abstract boolean match(V value, String label);

    /**
     * Returns default exception if the matching fails.
     *
     * @param value Value
     * @param label Label
     * @return Exception
     */
    public abstract PreconditionException getException(V value, String label);

    /**
     * Returns negated exception if the matching fails.
     *
     * @param value Value
     * @param label Label
     * @return Exception
     */
    public abstract PreconditionException getNegatedException(V value, String label);
}
