package com.choonchernlim.betterPreconditions.preconditions;

import com.choonchernlim.betterPreconditions.exception.ObjectNotNullPreconditionException;
import com.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException;

public class ObjectPreconditions {

    public static <T> T mustNotBeNull(final T reference, final String variableName) {

        if (reference == null) {
            throw new ObjectNullPreconditionException(String.format("%s must not be null", variableName));
        }

        return reference;
    }

    public static <T> T mustBeNull(final T reference, final String variableName) {

        if (reference != null) {
            throw new ObjectNotNullPreconditionException(String.format("%s must be null", variableName));
        }

        return null;
    }
}
