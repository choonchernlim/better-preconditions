package com.choonchernlim.betterPreconditions.preconditions;

import com.choonchernlim.betterPreconditions.core.Matcher;
import com.choonchernlim.betterPreconditions.exception.PreconditionException;
import com.choonchernlim.betterPreconditions.exception.StringBlankPreconditionException;
import com.choonchernlim.betterPreconditions.exception.StringNotBlankPreconditionException;
import static com.google.common.base.Strings.nullToEmpty;

/**
 * String related preconditions.
 */
public class StringPreconditions extends BetterPreconditions<StringPreconditions, String> {
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
     * Returns new instance with default label.
     *
     * @param value Value
     * @return New instance
     */
    public static StringPreconditions expect(final String value) {
        return expect(value, "String");
    }

    /**
     * Returns new instance.
     *
     * @param value Value
     * @param label Label
     * @return New instance
     */
    public static StringPreconditions expect(final String value, final String label) {
        return new StringPreconditions(value, label);
    }

    /**
     * Ensures the string is blank.
     * <pre>
     * {@code
     * expect(null).toBeBlank().check();          // ok
     * expect("").toBeBlank().check();            // ok
     * expect("   ").toBeBlank().check();         // ok
     * expect("Hello").toBeBlank().check();       // throws StringNotBlankPreconditionException
     *
     * expect(null).not().toBeBlank().check();    // throws StringBlankPreconditionException
     * expect("").not().toBeBlank().check();      // throws StringBlankPreconditionException
     * expect("   ").not().toBeBlank().check();   // throws StringBlankPreconditionException
     * expect("Hello").not().toBeBlank().check(); // ok
     * }
     * </pre>
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
