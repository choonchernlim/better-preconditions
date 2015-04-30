package com.choonchernlim.betterPreconditions.preconditions;

import com.choonchernlim.betterPreconditions.exception.ObjectNotNullPreconditionException;
import com.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException;

public class ObjectBetterPreconditions extends BetterPreconditions {

    public static <T> T checkNotNull(final T reference, final String variableName) {
        checkVariableNameNotBlank(variableName);

        if (reference == null) {
            throw new ObjectNullPreconditionException(String.format("%s must not be null", variableName));
        }

        return reference;
    }

    public static <T> T checkNull(final T reference, final String variableName) {
        checkVariableNameNotBlank(variableName);

        if (reference != null) {
            throw new ObjectNotNullPreconditionException(String.format("%s must be null", variableName));
        }

        return null;
    }
}
