package com.github.choonchernlim.betterPreconditions.preconditions;

import com.github.choonchernlim.betterPreconditions.core.Matcher;
import com.github.choonchernlim.betterPreconditions.core.PreconditionException;
import com.github.choonchernlim.betterPreconditions.core.Preconditions;
import com.github.choonchernlim.betterPreconditions.exception.StringBlankPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.StringNotBlankPreconditionException;
import static com.google.common.base.Strings.nullToEmpty;

/**
 * String related preconditions.
 */
public class StringPreconditions extends Preconditions<StringPreconditions, String> {
    /**
     * Package constructor.
     *
     * @param value Value
     * @param label Label
     */
    StringPreconditions(final String value, final String label) {
        super(value, label);
    }

    /**
     * Ensures the string is blank.
     *
     * @return Current instance
     */
    public StringPreconditions toBeBlank() {
        return customMatcher(new Matcher<String>() {
            @Override
            public boolean match(final String value, final String label) {
                return nullToEmpty(value).trim().isEmpty();
            }

            @Override
            public PreconditionException getException(final String value, final String label) {
                return new StringNotBlankPreconditionException(value, label);
            }

            @Override
            public PreconditionException getNegatedException(final String value, final String label) {
                return new StringBlankPreconditionException(value, label);
            }
        });
    }
}
