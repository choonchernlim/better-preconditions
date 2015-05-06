package com.github.choonchernlim.betterPreconditions.core;

import com.github.choonchernlim.betterPreconditions.exception.ObjectNotNullPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.StringBlankPreconditionException;
import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect;
import static com.google.common.base.Strings.nullToEmpty;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Abstract class for all preconditions.
 */
public abstract class Preconditions<C, V> {

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
    protected Preconditions(final V value, final String label) {
        // label is not empty... cannot use `expect(label).not().toBeBlank().check()` to prevent
        // infinite recursion calls.
        if (nullToEmpty(label).trim().isEmpty()) {
            throw new StringBlankPreconditionException(label, "Label");
        }

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
    public final C customMatcher(final Matcher<V> matcher) {
        assertions.add(new Assertion<V>(matcher, isNegated, value, label));

        // after creating a new assertion, reset the negation
        this.isNegated = false;

        return (C) this;
    }

    /**
     * Returns value if all assertions pass.
     */
    public final V check() {
        for (Assertion assertion : assertions) {
            assertion.run();
        }

        return value;
    }

    /**
     * Ensures value not be null and label not be blank.
     *
     * @param value        Value
     * @param label        Label
     * @param defaultLabel Default label
     */
    protected final void expectValueLabelToExist(final Object value, final String label, final String defaultLabel) {
        expect(label, defaultLabel).not().toBeBlank().check();
        expect(value, label).not().toBeNull().check();
    }
}
