package com.choonchernlim.betterPreconditions.core;

public class Assertion<V> {
    private final Matcher<V> matcher;
    private final V value;
    private final String label;
    private final boolean isNegated;

    public Assertion(Matcher<V> matcher, boolean isNegated, V value, String label) {
        this.matcher = matcher;
        this.isNegated = isNegated;
        this.value = value;
        this.label = label;
    }

    public void run() {
        if (isNegated) {
            if (matcher.match(value)) {
                throw matcher.getNegatedException(value, label);
            }
        }
        else if (!matcher.match(value)) {
            throw matcher.getException(value, label);
        }
    }
}
