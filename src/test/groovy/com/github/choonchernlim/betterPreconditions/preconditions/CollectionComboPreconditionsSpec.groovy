package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException
import spock.lang.Specification

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class CollectionComboPreconditionsSpec extends Specification {

    def "not.toBeNull.not.toBeEmpty - null should throw ObjectNullPreconditionException"() {
        when:
        expect(null as Collection, 'Cars').
                not().toBeNull().
                not().toBeEmpty().
                check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == 'Cars [ null ] must not be null'
    }

}
