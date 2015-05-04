package com.choonchernlim.betterPreconditions.preconditions;

import com.choonchernlim.betterPreconditions.core.Assertion;
import com.choonchernlim.betterPreconditions.core.Evaluation;
import com.choonchernlim.betterPreconditions.exception.PreconditionException;
import com.google.common.collect.Lists;

import java.util.List;

public abstract class BetterPreconditions {

    protected final String value;
    protected final String label;
    private final List<Assertion> assertions;
    private boolean isNegated;

    public BetterPreconditions(final String value, final String label) {
        this.value = value;
        this.label = label;
        this.assertions = Lists.newArrayList();

        disableNegation();
    }

    protected final void enableNegation() {
        this.isNegated = true;
    }

    protected final void disableNegation() {
        this.isNegated = false;
    }

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

        disableNegation();

    }

    protected final void check() {
        for (Assertion assertion : assertions) {
            assertion.run();
        }
    }
}
