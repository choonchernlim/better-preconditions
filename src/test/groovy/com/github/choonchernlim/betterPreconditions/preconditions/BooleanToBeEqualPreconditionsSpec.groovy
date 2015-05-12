package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.ObjectEqualPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.ObjectNotEqualPreconditionException
import spock.lang.Specification
import spock.lang.Unroll

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class BooleanToBeEqualPreconditionsSpec extends Specification {

    @Unroll
    def "toBeEqual - #firstValue == #secondValue should throw ObjectNotEqualPreconditionException"() {
        when:
        expect(firstValue).toBeEqual(secondValue).check()

        then:
        def error = thrown(ObjectNotEqualPreconditionException.class)
        error.message == "Boolean [ ${firstValue} ] must be equal to Expected Value [ ${secondValue} ]" as String


        where:
        firstValue | secondValue
        true       | false
        false      | true
    }

    @Unroll
    def "toBeEqual - #firstValue == #secondValue should be ok"() {
        when:
        def actualValue = expect(firstValue).toBeEqual(secondValue).check()

        then:
        actualValue == firstValue

        where:
        firstValue | secondValue
        true       | true
        false      | false
    }

    @Unroll
    def "not.toBeEqual - #firstValue != #secondValue should throw ObjectEqualPreconditionException"() {
        when:
        expect(firstValue).not().toBeEqual(secondValue).check()

        then:
        def error = thrown(ObjectEqualPreconditionException.class)
        error.message == "Boolean [ ${firstValue} ] must not be equal to Expected Value [ ${secondValue} ]" as String

        where:
        firstValue | secondValue
        true       | true
        false      | false
    }

    @Unroll
    def "not.toBeEqual - #firstValue != #secondValue should throw ObjectEqualPreconditionException with label"() {
        when:
        expect(firstValue, 'Blue Flag').not().toBeEqual(secondValue, 'Red Flag').check()

        then:
        def error = thrown(ObjectEqualPreconditionException.class)
        error.message == "Blue Flag [ ${firstValue} ] must not be equal to Red Flag [ ${secondValue} ]" as String

        where:
        firstValue | secondValue
        true       | true
        false      | false
    }

    @Unroll
    def "not.toBeEqual - #firstValue != #secondValue should be ok"() {
        when:
        def actualValue = expect(firstValue).not().toBeEqual(secondValue).check()

        then:
        actualValue == firstValue

        where:
        firstValue | secondValue
        true       | false
        false      | true
    }
}
