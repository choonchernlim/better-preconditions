package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.ObjectNotNullPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.StringBlankPreconditionException
import org.joda.time.LocalDate
import spock.lang.Specification

import static ObjectPreconditions.expect

class ObjectPreconditionsSpec extends Specification {

    def "toBeNull - null label should throw StringBlankPreconditionException"() {
        when:
        expect(new LocalDate(2015, 1, 1), null).toBeNull().check()

        then:
        def error = thrown(StringBlankPreconditionException.class)
        error.message == 'Label [ null ] must not be blank'
    }

    def "toBeNull - null should be ok"() {
        when:
        def actualValue = expect(null).toBeNull().check()

        then:
        actualValue == null
    }

    def "toBeNull - 2015-01-01 should throw ObjectNotNullPreconditionException"() {
        when:
        expect(new LocalDate(2015, 1, 1)).toBeNull().check()

        then:
        def error = thrown(ObjectNotNullPreconditionException.class)
        error.message == 'Object [ 2015-01-01 ] must be null'
    }

    def "toBeNull - 2015-01-01 should throw ObjectNotNullPreconditionException with label"() {
        when:
        expect(new LocalDate(2015, 1, 1), 'Surgery date').toBeNull().check()

        then:
        def error = thrown(ObjectNotNullPreconditionException.class)
        error.message == 'Surgery date [ 2015-01-01 ] must be null'
    }

    def "not.toBeNull - 2015-01-01 should be ok"() {
        when:
        def actualValue = expect(new LocalDate(2015, 1, 1)).not().toBeNull().check()

        then:
        actualValue == new LocalDate(2015, 1, 1)
    }

    def "not.toBeNull - null should throw ObjectNullPreconditionException"() {
        when:
        expect(null).not().toBeNull().check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == "Object [ null ] must not be null" as String
    }

    def "not.toBeNull - null should throw ObjectNullPreconditionException with label"() {
        when:
        expect(null, 'Surgery date').not().toBeNull().check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == "Surgery date [ null ] must not be null" as String
    }

}
