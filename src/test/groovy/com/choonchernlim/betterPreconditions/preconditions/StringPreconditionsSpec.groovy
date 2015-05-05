package com.choonchernlim.betterPreconditions.preconditions

import com.choonchernlim.betterPreconditions.exception.StringBlankPreconditionException
import com.choonchernlim.betterPreconditions.exception.StringNotBlankPreconditionException
import spock.lang.Specification
import spock.lang.Unroll

import static com.choonchernlim.betterPreconditions.preconditions.StringPreconditions.expect

class StringPreconditionsSpec extends Specification {

    @Unroll
    def "toBeBlank - valid - #value"() {
        when:
        expect(value).toBeBlank().check()

        then:
        notThrown(StringNotBlankPreconditionException.class)

        where:
        value << [null, '', ' ']
    }

    def "toBeBlank - invalid"() {
        when:
        expect('Hello').toBeBlank().check()

        then:
        def error = thrown(StringNotBlankPreconditionException.class)
        error.message == 'String [ Hello ] must be blank'
    }

    def "toBeBlank - invalid - with label"() {
        when:
        expect('Hello', 'Greeting').toBeBlank().check()

        then:
        def error = thrown(StringNotBlankPreconditionException.class)
        error.message == 'Greeting [ Hello ] must be blank'
    }


    def "not.toBeBlank - valid"() {
        when:
        expect('Hello').not().toBeBlank().check()

        then:
        notThrown(StringBlankPreconditionException.class)
    }

    @Unroll
    def "not.toBeBlank - invalid - #value"() {
        when:
        expect(value).not().toBeBlank().check()

        then:
        def error = thrown(StringBlankPreconditionException.class)
        error.message == "String [ ${value} ] must not be blank" as String

        where:
        value << [null, '', ' ']
    }

    @Unroll
    def "not.toBeBlank - invalid - with label - #value"() {
        when:
        expect(value, 'Greeting').not().toBeBlank().check()

        then:
        def error = thrown(StringBlankPreconditionException.class)
        error.message == "Greeting [ ${value} ] must not be blank" as String

        where:
        value << [null, '', ' ']
    }

}
