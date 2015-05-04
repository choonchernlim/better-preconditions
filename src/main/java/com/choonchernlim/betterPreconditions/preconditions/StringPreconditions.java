package com.choonchernlim.betterPreconditions.preconditions;

import com.choonchernlim.betterPreconditions.core.Evaluation;
import com.choonchernlim.betterPreconditions.exception.StringBlankPreconditionException;
import com.choonchernlim.betterPreconditions.exception.StringNotBlankPreconditionException;
import static com.google.common.base.Strings.nullToEmpty;

public class StringPreconditions extends BetterPreconditions {
    StringPreconditions(final String value, final String label) {
        super(value, label);
    }

    public static StringPreconditions expectString(final String value) {
        return expectString(value, "String");
    }

    public static StringPreconditions expectString(final String value, final String label) {
        return new StringPreconditions(value, label);
    }

    public StringPreconditions not() {
        enableNegation();
        return this;
    }

    public StringPreconditions toBeBlank() {
        addNewAssertion(new Evaluation() {
                            @Override
                            public boolean eval() {
                                return nullToEmpty(value).trim().isEmpty();
                            }
                        },
                        new StringNotBlankPreconditionException(value, label),
                        new StringBlankPreconditionException(value, label)
        );

        return this;
    }
}
