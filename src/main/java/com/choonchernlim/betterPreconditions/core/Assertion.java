package com.choonchernlim.betterPreconditions.core;

import com.choonchernlim.betterPreconditions.exception.PreconditionException;

public abstract class Assertion {
    private final boolean isNegated;

    public Assertion(boolean isNegated) {
        this.isNegated = isNegated;
    }

    public abstract void run();

    protected boolean isNegated() {
        return this.isNegated;
    }

    protected abstract PreconditionException getException();

    protected abstract PreconditionException getNegatedException();
}
