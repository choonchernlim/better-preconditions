package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.ObjectEqualPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.ObjectNotEqualPreconditionException
import spock.lang.Specification

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class StringToBeEqualPreconditionsSpec extends Specification {

    def "toBeEqual - 'Hello' == 'Hey' should throw ObjectNotEqualPreconditionException"() {
        when:
        expect('Hello').toBeEqual('Hey').check()

        then:
        def error = thrown(ObjectNotEqualPreconditionException.class)
        error.message == 'String [ Hello ] must be equal to Expected Value [ Hey ]'
    }

    def "toBeEqual - 'Hello' == 'Hello' should be ok"() {
        when:
        def actualValue = expect('Hello').toBeEqual('Hello').check()

        then:
        actualValue == 'Hello'
    }

    def "not.toBeEqual - 'Hello' != 'Hello' should throw ObjectEqualPreconditionException"() {
        when:
        expect('Hello').not().toBeEqual('Hello').check()

        then:
        def error = thrown(ObjectEqualPreconditionException.class)
        error.message == 'String [ Hello ] must not be equal to Expected Value [ Hello ]'
    }

    def "not.toBeEqual - 'Hello' != 'Hello' should throw ObjectEqualPreconditionException with label"() {
        when:
        expect('Hello', 'Polite Greeting').not().toBeEqual('Hello', 'Rude Greeting').check()

        then:
        def error = thrown(ObjectEqualPreconditionException.class)
        error.message == 'Polite Greeting [ Hello ] must not be equal to Rude Greeting [ Hello ]'
    }

    def "not.toBeEqual - 'Hello' != 'Hey' should be ok"() {
        when:
        def actualValue = expect('Hello').not().toBeEqual('Hey').check()

        then:
        actualValue == 'Hello'
    }

}
