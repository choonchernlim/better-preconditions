package com.choonchernlim.betterPreconditions.exception;

/**
 * Abstract class for all precondition exceptions.
 */
public class PreconditionException extends RuntimeException {

    /**
     * Constructs a new exception with an error message.
     *
     * @param error Error message
     */
    public PreconditionException(String error) {
        super(error);
    }
}
