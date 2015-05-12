package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.ObjectNotNullPreconditionException
import spock.lang.Specification

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class StringToBeNullPreconditionsSpec extends Specification {

    def "toBeNull - 'Hello' == null should throw ObjectNotNullPreconditionException"() {
        when:
        expect('Hello').toBeNull().check()

        then:
        def error = thrown(ObjectNotNullPreconditionException.class)
        error.message == 'String [ Hello ] must be null'
    }

    def "not.toBeNull - 'Hello' != null should be ok"() {
        when:
        def actualValue = expect('Hello').not().toBeNull().check()

        then:
        actualValue == 'Hello'
    }

}
