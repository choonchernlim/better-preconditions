package com.choonchernlim.betterPreconditions.core;

public abstract class Assertion {
    private final boolean isNegated;

    public Assertion(boolean isNegated) {
        this.isNegated = isNegated;
    }

    public abstract void run();

    protected boolean isNegated() {
        return this.isNegated;
    }

    protected abstract RuntimeException getException();

    protected abstract RuntimeException getNegatedException();
}
