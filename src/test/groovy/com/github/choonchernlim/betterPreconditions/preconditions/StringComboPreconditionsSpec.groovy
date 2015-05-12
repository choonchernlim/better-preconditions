package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException
import spock.lang.Specification

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class StringComboPreconditionsSpec extends Specification {

    def "not.toBeNull.not.toBeBlank - null should throw ObjectNullPreconditionException"() {
        when:
        expect(null as String).
                not().toBeNull().
                not().toBeBlank().
                check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == 'String [ null ] must not be null'
    }

}
