package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.ObjectEqualPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.ObjectNotEqualPreconditionException
import spock.lang.Specification

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class CollectionToBeEqualPreconditionsSpec extends Specification {

    def "toBeEqual - [1] == [2] should throw ObjectNotEqualPreconditionException"() {
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

    def "not.toBeEqual - [1] != [2] should be ok"() {
        when:
        def actualValue = expect([1]).not().toBeEqual([2]).check()

        then:
        actualValue
    }

}
