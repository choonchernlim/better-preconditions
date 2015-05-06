package com.github.choonchernlim.betterPreconditions.exception;

import com.github.choonchernlim.betterPreconditions.core.PreconditionException;

import java.util.Collection;

/**
 * This exception is thrown if the collection is not empty.
 */
public class CollectionEmptyPreconditionException extends PreconditionException {

    /**
     * Constructs a new exception with an error message.
     *
     * @param value Value
     * @param label Label
     */
    public CollectionEmptyPreconditionException(final Collection value, final String label) {
        super(String.format("%s [ size : %d ] must not be empty", label, value.size()));
    }
}
