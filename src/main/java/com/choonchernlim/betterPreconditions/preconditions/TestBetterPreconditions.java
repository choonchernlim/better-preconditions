package com.choonchernlim.betterPreconditions.preconditions;

import com.choonchernlim.betterPreconditions.core.Assertion;
import com.choonchernlim.betterPreconditions.core.Evaluation;
import com.google.common.collect.Lists;

import java.util.List;

public abstract class TestBetterPreconditions {

    protected final String value;
    protected final String valueLabel;
    private final List<Assertion> assertions;
    private boolean isNegated;

    public TestBetterPreconditions(String value, String valueLabel) {
        this.value = value;
        this.valueLabel = valueLabel;
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
                                         final RuntimeException exception,
                                         final RuntimeException negatedException) {

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
                           protected RuntimeException getException() {
                               return exception;
                           }

                           @Override
                           protected RuntimeException getNegatedException() {
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
