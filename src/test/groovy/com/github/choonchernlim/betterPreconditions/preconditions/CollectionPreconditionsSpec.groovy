package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.*
import spock.lang.Specification

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class CollectionPreconditionsSpec extends Specification {

    def "toBeEqual - [1] != [2] should throw ObjectNotEqualPreconditionException"() {
        when:
        expect([1]).toBeEqual([2]).check()

        then:
        def error = thrown(ObjectNotEqualPreconditionException.class)
        error.message == 'Collection [ [1] ] must be equal to Expected Value [ [2] ]'
    }

    def "toBeEqual - [1] == [1] should be ok"() {
        when:
        def actualValue = expect([1]).toBeEqual([1]).check()

        then:
        actualValue
    }

    def "not.toBeEqual - [1] != [1] should throw ObjectEqualPreconditionException"() {
        when:
        expect([1]).not().toBeEqual([1]).check()

        then:
        def error = thrown(ObjectEqualPreconditionException.class)
        error.message == 'Collection [ [1] ] must not be equal to Expected Value [ [1] ]'
    }

    def "not.toBeEqual - [1] != [1] should throw ObjectEqualPreconditionException with label"() {
        when:
        expect([1], 'Apples').not().toBeEqual([1], 'Oranges').check()

        then:
        def error = thrown(ObjectEqualPreconditionException.class)
        error.message == 'Apples [ [1] ] must not be equal to Oranges [ [1] ]'
    }

    def "not.toBeEqual - [1] == [2] should be ok"() {
        when:
        def actualValue = expect([1]).not().toBeEqual([2]).check()

        then:
        actualValue
    }

    def "toBeNull - null should be ok"() {
        when:
        def actualValue = expect(null as Collection).toBeNull().check()

        then:
        actualValue == null
    }

    def "toBeNull - [] should throw ObjectNotNullPreconditionException"() {
        when:
        expect([]).toBeNull().check()

        then:
        def error = thrown(ObjectNotNullPreconditionException.class)
        error.message == 'Collection [ [] ] must be null'
    }

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
