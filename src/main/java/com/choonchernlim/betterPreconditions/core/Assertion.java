package com.choonchernlim.betterPreconditions.core;

/**
 * This class executes the given matcher and throws appropriate exception if the matcher fails.
 *
 * @param <V> Value type
 */
public class Assertion<V> {
    /**
     * Matcher.
     */
    private final Matcher<V> matcher;

    /**
     * Value.
     */
    private final V value;

    /**
     * Label.
     */
    private final String label;

    /**
     * Whether the matcher is negated.
     */
    private final boolean isNegated;

    /**
     * Creates a new instance.
     *
     * @param matcher   Matcher
     * @param isNegated Whether the matcher is negated
     * @param value     Value
     * @param label     Label
     */
    public Assertion(Matcher<V> matcher, boolean isNegated, V value, String label) {
        this.matcher = matcher;
        this.isNegated = isNegated;
        this.value = value;
        this.label = label;
    }

    /**
     * Executes the matcher and throw appropriate exception if the matcher fails.
     */
    public void run() {
        if (isNegated) {
            if (matcher.match(value, label)) {
                throw matcher.getNegatedException(value, label);
            }
        }
        else if (!matcher.match(value, label)) {
            throw matcher.getException(value, label);
        }
    }
}
