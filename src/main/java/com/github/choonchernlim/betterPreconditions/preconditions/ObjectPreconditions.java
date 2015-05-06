package com.github.choonchernlim.betterPreconditions.preconditions;

import com.github.choonchernlim.betterPreconditions.core.Preconditions;

/**
 * Object related preconditions.
 */
public class ObjectPreconditions extends Preconditions<ObjectPreconditions, Object> {
    /**
     * Package constructor.
     *
     * @param value Value
     * @param label Label
     */
    ObjectPreconditions(final Object value, final String label) {
        super(value, label);
    }
}
