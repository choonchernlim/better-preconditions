package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.ObjectNotNullPreconditionException
import spock.lang.Specification

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class NumberPreconditionsSpec extends Specification {

    def "toBeNull - null should be ok"() {
        when:
        def actualValue = expect(null as Number).toBeNull().check()

        then:
        actualValue == null
    }

    def "toBeNull - non-null should throw ObjectNotNullPreconditionException"() {
        when:
        expect(1).toBeNull().check()

        then:
        def error = thrown(ObjectNotNullPreconditionException.class)
        error.message == 'Number [ 1 ] must be null'
    }

}
