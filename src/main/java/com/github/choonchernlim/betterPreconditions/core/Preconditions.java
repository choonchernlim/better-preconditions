package com.github.choonchernlim.betterPreconditions.core;

import com.github.choonchernlim.betterPreconditions.exception.ObjectEqualPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.ObjectNotEqualPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.ObjectNotNullPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.ObjectNotSameTypePreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.ObjectSameTypePreconditionException;
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
     * Ensures given value is equal to expected value.
     *
     * @see Preconditions#toBeEqual(Object, String)
     */
    public C toBeEqual(final V expectedValue) {
        return toBeEqual(expectedValue, "Expected Value");
    }

    /**
     * Ensures given value is equal to expected value.
     *
     * @param expectedValue Second value
     * @param expectedLabel Second label
     * @return Current instance
     */
    @SuppressWarnings("unchecked")
    public C toBeEqual(final V expectedValue, final String expectedLabel) {
        expectValueLabelToExist(expectedValue, expectedLabel, "Expected Label");

        customMatcher(new Matcher<V>() {
            @Override
            public boolean match(final V givenValue, final String givenLabel) {
                return expect(givenValue, givenLabel).not().toBeNull().check().equals(expectedValue);
            }

            @Override
            public PreconditionException getException(final V givenValue, final String givenLabel) {
                return new ObjectNotEqualPreconditionException(givenValue, givenLabel, expectedValue, expectedLabel);
            }

            @Override
            public PreconditionException getNegatedException(final V givenValue, final String givenLabel) {
                return new ObjectEqualPreconditionException(givenValue, givenLabel, expectedValue, expectedLabel);
            }
        });

        return (C) this;
    }

    /**
     * Ensures given value is equal to expected value.
     *
     * @see Preconditions#toBeSameType(Object, String)
     */
    public C toBeSameType(final V expectedValue) {
        return toBeSameType(expectedValue, "Expected Value");
    }

    /**
     * Ensures given value has same class type as expected value.
     *
     * @param expectedValue Second value
     * @param expectedLabel Second label
     * @return Current instance
     */
    @SuppressWarnings("unchecked")
    public C toBeSameType(final V expectedValue, final String expectedLabel) {
        expectValueLabelToExist(expectedValue, expectedLabel, "Expected Label");

        customMatcher(new Matcher<V>() {
            @Override
            public boolean match(final V givenValue, final String givenLabel) {
                return expect(givenValue, givenLabel)
                        .not().toBeNull().check()
                        .getClass().equals(expectedValue.getClass());
            }

            @Override
            public PreconditionException getException(final V givenValue, final String givenLabel) {
                return new ObjectNotSameTypePreconditionException(givenValue, givenLabel, expectedValue, expectedLabel);
            }

            @Override
            public PreconditionException getNegatedException(final V givenValue, final String givenLabel) {
                return new ObjectSameTypePreconditionException(givenValue, givenLabel, expectedValue, expectedLabel);
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

    /**
     * Ensures value not null and same type.
     *
     * @param givenValue    Given label
     * @param givenLabel    Given label
     * @param expectedValue Expected value
     * @param expectedLabel Expected label
     */
    protected final void expectNotNullAndSameType(final V givenValue,
                                                  final String givenLabel,
                                                  final V expectedValue,
                                                  final String expectedLabel) {

        expect(expectedValue, expectedLabel)
                .not().toBeNull()
                .toBeSameType(givenValue, givenLabel)
                .check();
    }
}
