package com.choonchernlim.betterPreconditions.preconditions;

import com.choonchernlim.betterPreconditions.exception.StringBlankPreconditionException;
import static com.google.common.base.Strings.nullToEmpty;

public abstract class BetterPreconditions {

    protected static void checkVariableNameNotBlank(final String variableName) {
        if (nullToEmpty(variableName).trim().isEmpty()) {
            throw new StringBlankPreconditionException("Variable name cannot be blank");
        }
    }

}
