package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.StringBlankPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.StringNotBlankPreconditionException
import spock.lang.Specification
import spock.lang.Unroll

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class StringToBeBlankPreconditionsSpec extends Specification {

    @Unroll
    def "toBeBlank - '#value' should be ok"() {
        when:
        def actualValue = expect(value).toBeBlank().check()

        then:
        actualValue == value

        where:
        value << ['', ' ']
    }

    def "toBeBlank - null should be ok"() {
        when:
        def actualValue = expect(null as String).toBeBlank().check()

        then:
        actualValue == null
    }

    def "toBeBlank - 'Hello' should throw StringNotBlankPreconditionException"() {
        when:
        expect('Hello').toBeBlank().check()

        then:
        def error = thrown(StringNotBlankPreconditionException.class)
        error.message == 'String [ Hello ] must be blank'
    }

    def "toBeBlank - 'Hello' should throw StringNotBlankPreconditionException with label"() {
        when:
        expect('Hello', 'Greeting').toBeBlank().check()

        then:
        def error = thrown(StringNotBlankPreconditionException.class)
        error.message == 'Greeting [ Hello ] must be blank'
    }

    def "not.toBeBlank - 'Hello' should be ok"() {
        when:
        def actualValue = expect('Hello').not().toBeBlank().check()

        then:
        actualValue == 'Hello'
    }

    @Unroll
    def "not.toBeBlank - '#value' should throw StringBlankPreconditionException"() {
        when:
        expect(value).not().toBeBlank().check()

        then:
        def error = thrown(StringBlankPreconditionException.class)
        error.message == "String [ ${value} ] must not be blank" as String

        where:
        value << ['', ' ']
    }

    def "not.toBeBlank - null should throw StringBlankPreconditionException"() {
        when:
        expect(null as String,).not().toBeBlank().check()

        then:
        def error = thrown(StringBlankPreconditionException.class)
        error.message == 'String [ null ] must not be blank'
    }

    @Unroll
    def "not.toBeBlank - '#value' should throw StringBlankPreconditionException with label"() {
        when:
        expect(value, 'Greeting').not().toBeBlank().check()

        then:
        def error = thrown(StringBlankPreconditionException.class)
        error.message == "Greeting [ ${value} ] must not be blank" as String

        where:
        value << ['', ' ']
    }

    def "not.toBeBlank - null should throw StringBlankPreconditionException with label"() {
        when:
        expect(null as String, 'Greeting').not().toBeBlank().check()

        then:
        def error = thrown(StringBlankPreconditionException.class)
        error.message == 'Greeting [ null ] must not be blank'
    }
}
