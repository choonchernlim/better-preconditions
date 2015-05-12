package com.github.choonchernlim.betterPreconditions.preconditions;

import com.github.choonchernlim.betterPreconditions.core.Preconditions;

public class NumberPreconditions<V extends Number> extends Preconditions<NumberPreconditions, V> {

    protected NumberPreconditions(V value, String label) {
        super(value, label);
    }


//
//    public static void mustBeEqualOrGreaterThan(final Integer firstNumber,
//                                                final Integer secondNumber,
//                                                final String firstNumberVariableName,
//                                                final String secondNumberVariableName) {
//        // TODO
//    }
//
//    public static void mustBeGreater(final Integer firstNumber,
//                                     final Integer secondNumber,
//                                     final String firstNumberVariableName,
//                                     final String secondNumberVariableName) {
//        // TODO
//    }
//
//    public static void mustBeEqualOrLessThan(final Integer firstNumber,
//                                             final Integer secondNumber,
//                                             final String firstNumberVariableName,
//                                             final String secondNumberVariableName) {
//        // TODO
//    }
//
//    public static void mustBeLessThan(final Integer firstNumber,
//                                      final Integer secondNumber,
//                                      final String firstNumberVariableName,
//                                      final String secondNumberVariableName) {
//        // TODO
//    }


}
