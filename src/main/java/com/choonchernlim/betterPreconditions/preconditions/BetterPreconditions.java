package com.choonchernlim.betterPreconditions.preconditions;

import com.choonchernlim.betterPreconditions.core.Assertion;
import com.choonchernlim.betterPreconditions.core.Matcher;
import com.choonchernlim.betterPreconditions.exception.ObjectNotNullPreconditionException;
import com.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException;
import com.choonchernlim.betterPreconditions.exception.PreconditionException;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Abstract class for all preconditions.
 */
public abstract class BetterPreconditions<C, V> {

    /**
     * Value to be checked.
     */
    protected final V value;

    /**
     * Label for the value.
     */
    protected final String label;

    /**
     * All assertions to be executed.
     */
    private final List<Assertion> assertions;

    /**
     * Mutable field to indicate whether the upcoming assertion is negated.
     */
    private boolean isNegated;

    /**
     * Creates a new instance.
     *
     * @param value Value
     * @param label Label
     */
    protected BetterPreconditions(final V value, final String label) {
        this.value = value;
        this.label = label;
        this.assertions = Lists.newArrayList();
        this.isNegated = false;
    }

    /**
     * Enables negation.
     *
     * @return Current instance
     */
    @SuppressWarnings("unchecked")
    public final C not() {
        this.isNegated = true;
        return (C) this;
    }

    /**
     * Ensures the object is null.
     * <pre>
     * {@code
     * expect(null).toBeNull().check();          // ok
     * expect("Hello").toBeNull().check();       // throws ObjectNotNullPreconditionException
     *
     * expect(null).not().toBeNull().check();    // throws ObjectNullPreconditionException
     * expect("Hello").not().toBeNull().check(); // ok
     * }
     * </pre>
     *
     * @return Current instance
     */
    @SuppressWarnings("unchecked")
    public final C toBeNull() {
        customMatcher(new Matcher<V>() {
            @Override
            public boolean match(final V value, final String label) {
                return value == null;
            }

            @Override
            public PreconditionException getException(final V value, final String label) {
                return new ObjectNotNullPreconditionException(value, label);
            }

            @Override
            public PreconditionException getNegatedException(final V value, final String label) {
                return new ObjectNullPreconditionException(value, label);
            }
        });

        return (C) this;
    }

    /**
     * Adds new assertion.
     *
     * @param matcher Matcher
     * @return Current instance
     */
    @SuppressWarnings("unchecked")
    protected final C customMatcher(final Matcher<V> matcher) {
        assertions.add(new Assertion<V>(matcher, isNegated, value, label));

        // after creating a new assertion, reset the negation
        this.isNegated = false;

        return (C) this;
    }

    /**
     * Returns value if all assertions pass.
     */
    protected final V check() {
        for (Assertion assertion : assertions) {
            assertion.run();
        }

        return value;
    }
}
