package com.choonchernlim.betterPreconditions.preconditions

import com.choonchernlim.betterPreconditions.exception.ObjectNotNullPreconditionException
import com.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException
import org.joda.time.LocalDate
import spock.lang.Specification

import static com.choonchernlim.betterPreconditions.preconditions.ObjectPreconditions.expect

class ObjectPreconditionsSpec extends Specification {

    def "toBeNull - valid"() {
        when:
        expect(null).toBeNull().check()

        then:
        notThrown(ObjectNotNullPreconditionException.class)
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
        expect(new LocalDate(2015, 1, 1)).not().toBeNull().check()

        then:
        notThrown(ObjectNullPreconditionException.class)
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
