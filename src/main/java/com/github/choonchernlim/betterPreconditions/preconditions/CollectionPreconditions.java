package com.github.choonchernlim.betterPreconditions.preconditions;

import com.github.choonchernlim.betterPreconditions.core.Matcher;
import com.github.choonchernlim.betterPreconditions.core.PreconditionException;
import com.github.choonchernlim.betterPreconditions.core.Preconditions;
import com.github.choonchernlim.betterPreconditions.exception.CollectionEmptyPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.CollectionNotEmptyPreconditionException;
import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect;

import java.util.Collection;

/**
 * Collection related preconditions.
 */
public class CollectionPreconditions extends Preconditions<CollectionPreconditions, Collection> {
    /**
     * Package constructor.
     *
     * @param value Value
     * @param label Label
     */
    CollectionPreconditions(final Collection value, final String label) {
        super(value, label);
    }

    /**
     * Ensures collection is empty.
     *
     * @return Current instance
     */
    public CollectionPreconditions toBeEmpty() {
        return customMatcher(new Matcher<Collection>() {
            @Override
            public boolean match(final Collection value, final String label) {
                return expect(value, label).not().toBeNull().check().isEmpty();
            }

            @Override
            public PreconditionException getException(final Collection value, final String label) {
                return new CollectionNotEmptyPreconditionException(value, label);
            }

            @Override
            public PreconditionException getNegatedException(Collection value, String label) {
                return new CollectionEmptyPreconditionException(value, label);
            }
        });
    }
}
