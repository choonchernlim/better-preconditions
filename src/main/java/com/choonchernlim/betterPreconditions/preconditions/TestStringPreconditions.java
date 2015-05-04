package com.choonchernlim.betterPreconditions.preconditions;

import com.choonchernlim.betterPreconditions.core.Evaluation;
import com.choonchernlim.betterPreconditions.exception.StringBlankPreconditionException;
import com.choonchernlim.betterPreconditions.exception.StringNotBlankPreconditionException;
import static com.google.common.base.Strings.nullToEmpty;


public class TestStringPreconditions extends TestBetterPreconditions {

    TestStringPreconditions(String value, String variableName) {
        super(value, variableName);
    }

    public static TestStringPreconditions expect(final String value, final String valueLabel) {
        return new TestStringPreconditions(value, valueLabel);
    }

    public static void main(String[] args) {
        String name = "";

        expect(name, "Patient name")
                .not().toBeBlank()
                .check();
    }

    public TestStringPreconditions not() {
        enableNegation();
        return this;
    }

    public TestStringPreconditions toBeBlank() {
        addNewAssertion(new Evaluation() {
                            @Override
                            public boolean eval() {
                                return nullToEmpty(value).trim().isEmpty();
                            }
                        },
                        new StringNotBlankPreconditionException(String.format("%s must be blank", valueLabel)),
                        new StringBlankPreconditionException(String.format("%s must not be blank", valueLabel))
        );

        return this;
    }
}
