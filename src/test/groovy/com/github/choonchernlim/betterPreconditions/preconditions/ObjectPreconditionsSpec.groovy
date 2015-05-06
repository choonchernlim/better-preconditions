package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.ObjectNotNullPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException
import org.joda.time.LocalDate
import spock.lang.Specification

import static ObjectPreconditions.expect

class ObjectPreconditionsSpec extends Specification {

    def "toBeNull - valid"() {
        when:
        def actualValue = expect(null).toBeNull().check()

        then:
        actualValue == null
    }

    def "toBeNull - invalid"() {
        when:
        expect(new LocalDate(2015, 1, 1)).toBeNull().check()

        then:
        def error = thrown(ObjectNotNullPreconditionException.class)
        error.message == 'Object [ 2015-01-01 ] must be null'
    }

    def "toBeNull - invalid - with label"() {
        when:
        expect(new LocalDate(2015, 1, 1), 'Surgery date').toBeNull().check()

        then:
        def error = thrown(ObjectNotNullPreconditionException.class)
        error.message == 'Surgery date [ 2015-01-01 ] must be null'
    }

    def "not.toBeNull - valid"() {
        when:
        def actualValue = expect(new LocalDate(2015, 1, 1)).not().toBeNull().check()

        then:
        actualValue == new LocalDate(2015, 1, 1)
    }

    def "not.toBeNull - invalid"() {
        when:
        expect(null).not().toBeNull().check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == "Object [ null ] must not be null" as String
    }

    def "not.toBeNull - invalid - with label"() {
        when:
        expect(null, 'Surgery date').not().toBeNull().check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == "Surgery date [ null ] must not be null" as String
    }

}
