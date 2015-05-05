package com.choonchernlim.betterPreconditions.preconditions

import com.choonchernlim.betterPreconditions.exception.CollectionEmptyPreconditionException
import com.choonchernlim.betterPreconditions.exception.CollectionNotEmptyPreconditionException
import com.choonchernlim.betterPreconditions.exception.ObjectNotNullPreconditionException
import com.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException
import spock.lang.Specification

import static com.choonchernlim.betterPreconditions.preconditions.CollectionPreconditions.expect

class CollectionPreconditionsSpec extends Specification {

    def "toBeNull - valid"() {
        when:
        def actualValue = expect(null).toBeNull().check()

        then:
        actualValue == null
    }

    def "toBeNull - invalid"() {
        when:
        expect([]).toBeNull().check()

        then:
        def error = thrown(ObjectNotNullPreconditionException.class)
        error.message == 'Collection [ [] ] must be null'
    }

    def "toBeEmpty - valid"() {
        when:
        def actualValue = expect([]).toBeEmpty().check()

        then:
        actualValue == []
    }

    def "toBeEmpty - invalid - null"() {
        when:
        expect(null).toBeEmpty().check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == "Collection [ null ] must not be null" as String
    }

    def "toBeEmpty - invalid - non empty list"() {
        when:
        expect([1, 2, 3]).toBeEmpty().check()

        then:
        def error = thrown(CollectionNotEmptyPreconditionException.class)
        error.message == "Collection [ size : 3 ] must be empty" as String
    }

    def "not.toBeEmpty - valid"() {
        when:
        def actualValue = expect([1, 2, 3,]).not().toBeEmpty().check()

        then:
        actualValue == [1, 2, 3]
    }

    def "not.toBeEmpty - invalid"() {
        when:
        expect([]).not().toBeEmpty().check()

        then:
        def error = thrown(CollectionEmptyPreconditionException.class)
        error.message == 'Collection [ size : 0 ] must not be empty'
    }

    def "not.toBeNull should throw exception and short circuit the assertions"() {
        when:
        expect(null, 'Cars').
                not().toBeNull().
                not().toBeEmpty().
                check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == 'Cars [ null ] must not be null'
    }

}
