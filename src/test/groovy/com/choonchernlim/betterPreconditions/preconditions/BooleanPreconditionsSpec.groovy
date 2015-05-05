package com.choonchernlim.betterPreconditions.preconditions

import com.choonchernlim.betterPreconditions.exception.BooleanFalsePreconditionException
import com.choonchernlim.betterPreconditions.exception.BooleanTruePreconditionException
import com.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException
import spock.lang.Specification
import spock.lang.Unroll

import static com.choonchernlim.betterPreconditions.preconditions.BooleanPreconditions.expect

class BooleanPreconditionsSpec extends Specification {

    @Unroll
    def "toBeTrue - invalid - #value"() {
        when:
        expect(value).toBeTrue().check()

        then:
        def error = thrown(BooleanFalsePreconditionException.class)
        error.message == "Boolean [ ${value} ] must be true" as String

        where:
        value << [false, null]
    }

    @Unroll
    def "not.toBeTrue - valid - #value"() {
        when:
        def actualValue = expect(value).not().toBeTrue().check()

        then:
        actualValue == value

        where:
        value << [false, null]
    }

    def "toBeTrue - valid"() {
        when:
        def actualValue = expect(true).toBeTrue().check()

        then:
        actualValue
    }

    def "not.toBeTrue - invalid - with label"() {
        when:
        expect(true, 'Flag').not().toBeTrue().check()

        then:
        def error = thrown(BooleanTruePreconditionException.class)
        error.message == 'Flag [ true ] must not be true'
    }

    def "not.toBeNull should throw exception and short circuit the assertions"() {
        when:
        expect(null).
                not().toBeNull().
                not().toBeTrue().
                check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == 'Boolean [ null ] must not be null'
    }

}
