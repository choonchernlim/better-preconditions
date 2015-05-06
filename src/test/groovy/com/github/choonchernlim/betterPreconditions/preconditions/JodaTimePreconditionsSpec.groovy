package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.ObjectNotNullPreconditionException
import org.joda.time.LocalDate
import org.joda.time.base.BaseLocal
import spock.lang.Specification

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class JodaTimePreconditionsSpec extends Specification {

    def "toBeNull - null should be ok"() {
        when:
        def actualValue = expect(null as BaseLocal).toBeNull().check()

        then:
        actualValue == null
    }

    def "toBeNull - non-null should throw ObjectNotNullPreconditionException"() {
        when:
        expect(new LocalDate(2015, 1, 1)).toBeNull().check()

        then:
        def error = thrown(ObjectNotNullPreconditionException.class)
        error.message == 'Joda Time [ 2015-01-01 ] must be null'
    }
}
