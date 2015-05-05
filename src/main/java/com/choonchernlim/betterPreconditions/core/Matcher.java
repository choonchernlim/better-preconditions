package com.choonchernlim.betterPreconditions.core;

import com.choonchernlim.betterPreconditions.exception.PreconditionException;

public abstract class Matcher<V> {

    public abstract boolean match(V value, String label);

    public abstract PreconditionException getException(V value, String label);

    public abstract PreconditionException getNegatedException(V value, String label);
}
