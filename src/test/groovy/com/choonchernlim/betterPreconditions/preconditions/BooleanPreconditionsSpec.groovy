package com.choonchernlim.betterPreconditions.preconditions

import com.choonchernlim.betterPreconditions.exception.BooleanFalsePreconditionException
import com.choonchernlim.betterPreconditions.exception.BooleanTruePreconditionException
import com.choonchernlim.betterPreconditions.exception.ObjectNotNullPreconditionException
import com.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException
import spock.lang.Specification

import static com.choonchernlim.betterPreconditions.preconditions.BooleanPreconditions.expect

class BooleanPreconditionsSpec extends Specification {

    def "toBeNull - invalid"() {
        when:
        expect(false).toBeNull().check()

        then:
        def error = thrown(ObjectNotNullPreconditionException.class)
        error.message == 'Boolean [ false ] must be null'
    }

    def "toBeNull - valid"() {
        when:
        def actualValue = expect(null).toBeNull().check()

        then:
        actualValue == null
    }

    def "toBeTrue - invalid - false"() {
        when:
        expect(false).toBeTrue().check()

        then:
        def error = thrown(BooleanFalsePreconditionException.class)
        error.message == "Boolean [ false ] must be true" as String
    }

    def "toBeTrue - invalid - null"() {
        when:
        expect(null).toBeTrue().check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == "Boolean [ null ] must not be null" as String
    }


    def "not.toBeTrue - valid - false"() {
        when:
        def actualValue = expect(false).not().toBeTrue().check()

        then:
        !actualValue
    }

    def "not.toBeTrue - valid"() {
        when:
        expect(null).not().toBeTrue().check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == "Boolean [ null ] must not be null" as String
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
