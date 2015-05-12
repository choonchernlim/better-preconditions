package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException
import spock.lang.Specification

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class BooleanComboPreconditionsSpec extends Specification {

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
