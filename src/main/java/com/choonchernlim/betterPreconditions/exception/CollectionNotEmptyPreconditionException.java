package com.choonchernlim.betterPreconditions.exception;

import java.util.Collection;

/**
 * This exception is thrown if the collection is empty.
 */
public class CollectionNotEmptyPreconditionException extends PreconditionException {

    /**
     * Constructs a new exception with an error message.
     *
     * @param value Value
     * @param label Label
     */
    public CollectionNotEmptyPreconditionException(final Collection value, final String label) {
        super(String.format("%s [ size : %d ] must be empty", label, value.size()));
    }
}
