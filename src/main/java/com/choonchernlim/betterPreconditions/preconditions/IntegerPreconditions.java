package com.choonchernlim.betterPreconditions.preconditions;

public class IntegerPreconditions extends BetterPreconditions {

    public static void mustBeEqual(final Integer firstNumber,
                                   final Integer secondNumber,
                                   final String firstNumberVariableName,
                                   final String secondNumberVariableName) {
        validate(firstNumber, secondNumber, firstNumberVariableName, secondNumberVariableName);

        // TODO
    }

    public static void mustBeEqualOrGreaterThan(final Integer firstNumber,
                                                final Integer secondNumber,
                                                final String firstNumberVariableName,
                                                final String secondNumberVariableName) {
        validate(firstNumber, secondNumber, firstNumberVariableName, secondNumberVariableName);
        // TODO
    }

    public static void mustBeGreater(final Integer firstNumber,
                                     final Integer secondNumber,
                                     final String firstNumberVariableName,
                                     final String secondNumberVariableName) {
        validate(firstNumber, secondNumber, firstNumberVariableName, secondNumberVariableName);

        // TODO
    }

    public static void mustBeEqualOrLessThan(final Integer firstNumber,
                                             final Integer secondNumber,
                                             final String firstNumberVariableName,
                                             final String secondNumberVariableName) {
        validate(firstNumber, secondNumber, firstNumberVariableName, secondNumberVariableName);

        // TODO
    }

    public static void mustBeLessThan(final Integer firstNumber,
                                      final Integer secondNumber,
                                      final String firstNumberVariableName,
                                      final String secondNumberVariableName) {
        validate(firstNumber, secondNumber, firstNumberVariableName, secondNumberVariableName);

        // TODO
    }


}
