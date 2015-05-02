package com.choonchernlim.betterPreconditions.preconditions;

import com.choonchernlim.betterPreconditions.exception.StringBlankPreconditionException;
import static com.choonchernlim.betterPreconditions.preconditions.ObjectPreconditions.mustNotBeNull;
import static com.choonchernlim.betterPreconditions.preconditions.StringPreconditions.mustNotBeBlank;
import static com.google.common.base.Strings.nullToEmpty;

public abstract class BetterPreconditions {

    protected static void checkVariableNameNotBlank(final String variableName) {
        if (nullToEmpty(variableName).trim().isEmpty()) {
            throw new StringBlankPreconditionException("Variable name cannot be blank");
        }
    }

    protected static void validate(final Object firstValue,
                                   final Object secondValue,
                                   final String firstVariableName,
                                   final String secondVariableName) {
        mustNotBeBlank(firstVariableName, "First variable name");
        mustNotBeBlank(secondVariableName, "Second variable name");
        mustNotBeNull(firstValue, firstVariableName);
        mustNotBeNull(secondValue, secondVariableName);
    }


}
