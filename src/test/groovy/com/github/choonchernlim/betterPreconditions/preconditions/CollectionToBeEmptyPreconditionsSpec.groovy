package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.CollectionEmptyPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.CollectionNotEmptyPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException
import spock.lang.Specification

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class CollectionToBeEmptyPreconditionsSpec extends Specification {

    def "toBeEmpty - [] should be ok"() {
        when:
        def actualValue = expect([]).toBeEmpty().check()

        then:
        actualValue == []
    }

    def "toBeEmpty - null should throw ObjectNotNullPreconditionException"() {
        when:
        expect(null as Collection).toBeEmpty().check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == "Collection [ null ] must not be null" as String
    }

    def "toBeEmpty - [1, 2, 3] should throw CollectionNotEmptyPreconditionException"() {
        when:
        expect([1, 2, 3]).toBeEmpty().check()

        then:
        def error = thrown(CollectionNotEmptyPreconditionException.class)
        error.message == "Collection [ size : 3 ] must be empty" as String
    }

    def "not.toBeEmpty - [1, 2, 3,] should be ok"() {
        when:
        def actualValue = expect([1, 2, 3,]).not().toBeEmpty().check()

        then:
        actualValue == [1, 2, 3]
    }

    def "not.toBeEmpty - [] should throw CollectionEmptyPreconditionException"() {
        when:
        expect([]).not().toBeEmpty().check()

        then:
        def error = thrown(CollectionEmptyPreconditionException.class)
        error.message == 'Collection [ size : 0 ] must not be empty'
    }

}
