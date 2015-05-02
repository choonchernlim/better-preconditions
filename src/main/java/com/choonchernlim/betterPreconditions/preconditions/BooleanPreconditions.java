package com.choonchernlim.betterPreconditions.preconditions;

import com.choonchernlim.betterPreconditions.exception.BooleanFalsePreconditionException;
import com.choonchernlim.betterPreconditions.exception.BooleanTruePreconditionException;

public class BooleanPreconditions extends BetterPreconditions {

    public static void mustBeTrue(final Boolean value, final String variableName) {
        checkVariableNameNotBlank(variableName);

        if (!value) {
            throw new BooleanFalsePreconditionException(String.format("%s must be TRUE", variableName));
        }
    }

    public static void mustBeFalse(final Boolean value, final String variableName) {
        checkVariableNameNotBlank(variableName);

        if (value) {
            throw new BooleanTruePreconditionException(String.format("%s must be FALSE", variableName));
        }
    }
}
