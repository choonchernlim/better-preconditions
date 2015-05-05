package com.choonchernlim.betterPreconditions.preconditions;

import com.choonchernlim.betterPreconditions.core.Assertion;
import com.choonchernlim.betterPreconditions.core.Evaluation;
import com.choonchernlim.betterPreconditions.exception.ObjectNotNullPreconditionException;
import com.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException;
import com.choonchernlim.betterPreconditions.exception.PreconditionException;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Abstract class for all preconditions.
 */
public abstract class BetterPreconditions<K, T> {

    /**
     * Value to be checked.
     */
    protected final T value;

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

    public BetterPreconditions(final T value, final String label) {
        this.value = value;
        this.label = label;
        this.assertions = Lists.newArrayList();
        this.isNegated = false;
    }

    public abstract K not();

    public abstract K toBeNull();

    /**
     * Set flag to enable negation.
     */
    protected final K enableNegation(K instance) {
        this.isNegated = true;
        return instance;
    }

    /**
     * Set flag to disable negation.
     */
    private K disableNegation(K instance) {
        this.isNegated = false;
        return instance;
    }

    /**
     * Add new assertion.
     *
     * @param instance         Current instance
     * @param evaluation       What to be evaluated
     * @param exception        Exception for non-negated assertion
     * @param negatedException Exception for negated assertion
     */
    protected final K addAssertion(final K instance,
                                   final Evaluation evaluation,
                                   final PreconditionException exception,
                                   final PreconditionException negatedException) {

        assertions.add(new Assertion(isNegated) {
                           @Override
                           public void run() {
                               if (isNegated()) {
                                   if (evaluation.eval()) {
                                       throw getNegatedException();
                                   }
                               }
                               else if (!evaluation.eval()) {
                                   throw getException();
                               }
                           }

                           @Override
                           protected PreconditionException getException() {
                               return exception;
                           }

                           @Override
                           protected PreconditionException getNegatedException() {
                               return negatedException;
                           }
                       }

        );

        // after creating a new assertion, reset the negation
        return disableNegation(instance);
    }

    /**
     * Execute each assertion.
     */
    protected final void check() {
        for (Assertion assertion : assertions) {
            assertion.run();
        }
    }

    protected final K addToBeNullAssertion(K instance) {
        addAssertion(instance,
                     new Evaluation() {
                         @Override
                         public boolean eval() {
                             return value == null;
                         }
                     },
                     new ObjectNotNullPreconditionException(value, label),
                     new ObjectNullPreconditionException(value, label)
        );

        return instance;
    }
}
