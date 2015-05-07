package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.*
import spock.lang.Specification
import spock.lang.Unroll

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class BooleanPreconditionsSpec extends Specification {

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

    def "toBeNull - null should be ok"() {
        when:
        def actualValue = expect(null as Boolean).toBeNull().check()

        then:
        actualValue == null
    }

    @Unroll
    def "toBeNull - #value should throw ObjectNotNullPreconditionException"() {
        when:
        expect(value).toBeNull().check()

        then:
        def error = thrown(ObjectNotNullPreconditionException.class)
        error.message == "Boolean [ ${value} ] must be null" as String

        where:
        value << [true, false]
    }

    def "toBeTrue - true == true should be ok"() {
        when:
        def actualValue = expect(true).toBeTrue().check()

        then:
        actualValue
    }

    def "toBeTrue - false == true should throw BooleanFalsePreconditionException"() {
        when:
        expect(false).toBeTrue().check()

        then:
        def error = thrown(BooleanFalsePreconditionException.class)
        error.message == "Boolean [ false ] must be true" as String
    }

    def "toBeTrue - null == true should throw ObjectNullPreconditionException"() {
        when:
        expect(null as Boolean).toBeTrue().check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == "Boolean [ null ] must not be null" as String
    }

    def "not.toBeTrue - false != true should be ok"() {
        when:
        def actualValue = expect(false).not().toBeTrue().check()

        then:
        !actualValue
    }

    def "not.toBeTrue - null != true should throw ObjectNullPreconditionException"() {
        when:
        expect(null as Boolean).not().toBeTrue().check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == "Boolean [ null ] must not be null" as String
    }

    def "not.toBeTrue - true != true should throw BooleanTruePreconditionException"() {
        when:
        expect(true).not().toBeTrue().check()

        then:
        def error = thrown(BooleanTruePreconditionException.class)
        error.message == 'Boolean [ true ] must not be true'
    }

    def "not.toBeNull.not.toBeTrue - null should throw ObjectNullPreconditionException"() {
        when:
        expect(null as Boolean, 'Flag').
                not().toBeNull().
                not().toBeTrue().
                check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == 'Flag [ null ] must not be null'
    }

}
