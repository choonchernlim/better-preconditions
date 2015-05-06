package com.github.choonchernlim.betterPreconditions.core;

/**
 * Abstract class for all precondition exceptions.
 */
public class PreconditionException extends RuntimeException {

    /**
     * Constructs a new exception with an error message.
     *
     * @param error Error message
     */
    public PreconditionException(final String error) {
        super(error);
    }
}
