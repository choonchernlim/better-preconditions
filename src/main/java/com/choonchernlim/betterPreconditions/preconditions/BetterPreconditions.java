package com.choonchernlim.betterPreconditions.preconditions;

import com.choonchernlim.betterPreconditions.core.Assertion;
import com.choonchernlim.betterPreconditions.core.Evaluation;
import com.choonchernlim.betterPreconditions.exception.PreconditionException;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Abstract class for all preconditions.
 */
public abstract class BetterPreconditions<T> {

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

        // disable negation by default
        disableNegation();
    }

    /**
     * Set flag to enable negation.
     */
    protected final void enableNegation() {
        this.isNegated = true;
    }

    /**
     * Set flag to disable negation.
     */
    protected final void disableNegation() {
        this.isNegated = false;
    }

    /**
     * Add new assertion.
     *
     * @param evaluation       What to be evaluated
     * @param exception        Exception for non-negated assertion
     * @param negatedException Exception for negated assertion
     */
    protected final void addNewAssertion(final Evaluation evaluation,
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
        disableNegation();
    }

    /**
     * Execute each assertion.
     */
    protected final void check() {
        for (Assertion assertion : assertions) {
            assertion.run();
        }
    }
}
