package com.choonchernlim.betterPreconditions.preconditions;

import com.choonchernlim.betterPreconditions.exception.BooleanFalsePreconditionException;
import com.choonchernlim.betterPreconditions.exception.BooleanTruePreconditionException;

public class BooleanBetterPreconditions extends BetterPreconditions {

    public static void checkTrue(final Boolean value, final String variableName) {
        checkVariableNameNotBlank(variableName);

        if (!value) {
            throw new BooleanFalsePreconditionException(String.format("%s must be TRUE", variableName));
        }
    }

    public static void checkFalse(final Boolean value, final String variableName) {
        checkVariableNameNotBlank(variableName);

        if (value) {
            throw new BooleanTruePreconditionException(String.format("%s must be FALSE", variableName));
        }
    }
}
