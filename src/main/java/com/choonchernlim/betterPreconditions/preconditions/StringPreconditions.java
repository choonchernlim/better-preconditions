package com.choonchernlim.betterPreconditions.preconditions;

import com.choonchernlim.betterPreconditions.exception.NotBlankException;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.nullToEmpty;

public class StringPreconditions {

    public static void checkNotBlank(final String value, final String variableName) {
        checkArgument(!nullToEmpty(variableName).trim().isEmpty(), "Variable name cannot be blank");

        if (nullToEmpty(value).trim().isEmpty()) {
            throw new NotBlankException(String.format("%s cannot be blank", variableName));
        }
    }
}
