package com.choonchernlim.betterPreconditions.preconditions;

import com.choonchernlim.betterPreconditions.core.Evaluation;
import com.choonchernlim.betterPreconditions.exception.StringBlankPreconditionException;
import com.choonchernlim.betterPreconditions.exception.StringNotBlankPreconditionException;
import static com.google.common.base.Strings.nullToEmpty;

/**
 * String related preconditions.
 */
public class StringPreconditions extends BetterPreconditions<String> {
    /**
     * Private constructor.
     *
     * @param value Value
     * @param label Label
     */
    private StringPreconditions(final String value, final String label) {
        super(value, label);
    }

    /**
     * Return new instance with default label.
     *
     * @param value Value
     * @return New instance
     */
    public static StringPreconditions expect(final String value) {
        return expect(value, "String");
    }

    /**
     * Return new instance.
     *
     * @param value Value
     * @param label Label
     * @return New instance
     */
    public static StringPreconditions expect(final String value, final String label) {
        return new StringPreconditions(value, label);
    }

    /**
     * Enable negation.
     *
     * @return Current instance
     */
    public StringPreconditions not() {
        enableNegation();
        return this;
    }

    /**
     * Ensure the string is blank.
     *
     * @return Current instance
     */
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
