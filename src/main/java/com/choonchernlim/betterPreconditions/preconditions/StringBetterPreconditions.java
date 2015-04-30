package com.choonchernlim.betterPreconditions.preconditions;

import com.choonchernlim.betterPreconditions.exception.StringBlankPreconditionException;
import com.choonchernlim.betterPreconditions.exception.StringNotBlankPreconditionException;
import static com.choonchernlim.betterPreconditions.preconditions.BetterPreconditions.checkVariableNameNotBlank;
import static com.google.common.base.Strings.nullToEmpty;

public class StringBetterPreconditions {

    public static void checkNotBlank(final String value, final String variableName) {
        checkVariableNameNotBlank(variableName);

        if (nullToEmpty(value).trim().isEmpty()) {
            throw new StringBlankPreconditionException(String.format("%s must not be blank", variableName));
        }
    }

    public static void checkBlank(final String value, final String variableName) {
        checkVariableNameNotBlank(variableName);

        if (!nullToEmpty(value).trim().isEmpty()) {
            throw new StringNotBlankPreconditionException(String.format("%s must be blank", variableName));
        }
    }
}
