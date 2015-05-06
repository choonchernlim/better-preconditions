package com.github.choonchernlim.betterPreconditions.preconditions;

import com.github.choonchernlim.betterPreconditions.core.Matcher;
import com.github.choonchernlim.betterPreconditions.exception.CollectionEmptyPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.CollectionNotEmptyPreconditionException;
import com.github.choonchernlim.betterPreconditions.exception.PreconditionException;

import java.util.Collection;

/**
 * Collection related preconditions.
 */
public class CollectionPreconditions extends BetterPreconditions<CollectionPreconditions, Collection> {
    /**
     * Private constructor.
     *
     * @param value Value
     * @param label Label
     */
    private CollectionPreconditions(final Collection value, final String label) {
        super(value, label);
    }

    /**
     * Returns new instance with default label.
     *
     * @param value Value
     * @return New instance
     */
    public static CollectionPreconditions expect(final Collection value) {
        return expect(value, "Collection");
    }

    /**
     * Returns new instance.
     *
     * @param value Value
     * @param label Label
     * @return New instance
     */
    public static CollectionPreconditions expect(final Collection value, final String label) {
        return new CollectionPreconditions(value, label);
    }

    /**
     * Ensures collection is empty.
     * <pre>
     * {@code
     * expect(null).toBeEmpty().check();                           // throws ObjectNullPreconditionException
     * expect(Lists.newArrayList(1, 2)).toBeEmpty().check();       // throws CollectionNotEmptyPreconditionException
     * expect(Lists.newArrayList()).toBeEmpty().check();           // ok
     *
     * expect(null).not().toBeEmpty().check();                     // throws ObjectNullPreconditionException
     * expect(Lists.newArrayList()).not().toBeEmpty().check();     // throws CollectionEmptyPreconditionException
     * expect(Lists.newArrayList(1, 2)).not().toBeEmpty().check(); // ok
     * }
     * </pre>
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
