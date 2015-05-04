package com.choonchernlim.betterPreconditions.preconditions

import com.choonchernlim.betterPreconditions.exception.StringBlankPreconditionException
import com.choonchernlim.betterPreconditions.exception.StringNotBlankPreconditionException
import spock.lang.Specification
import spock.lang.Unroll

import static StringPreconditions.expectString

class StringPreconditionsSpec extends Specification {

    @Unroll
    def "toBeBlank - valid - #value"() {
        when:
        expectString(value).toBeBlank().check()

        then:
        notThrown(StringBlankPreconditionException.class)

        where:
        value << [null, '', ' ']
    }

    def "toBeBlank - invalid"() {
        when:
        expectString('Hello').toBeBlank().check()

        then:
        def error = thrown(StringNotBlankPreconditionException.class)
        error.message == 'String [ Hello ] must be blank'
    }

    def "toBeBlank - invalid - with label"() {
        when:
        expectString('Hello', 'Greeting').toBeBlank().check()

        then:
        def error = thrown(StringNotBlankPreconditionException.class)
        error.message == 'Greeting [ Hello ] must be blank'
    }


    def "not.toBeBlank - valid"() {
        when:
        expectString('Hello').not().toBeBlank().check()

        then:
        notThrown(StringBlankPreconditionException.class)
    }

    @Unroll
    def "not.toBeBlank - invalid - #value"() {
        when:
        expectString(value).not().toBeBlank().check()

        then:
        def error = thrown(StringBlankPreconditionException.class)
        error.message == "String [ ${value} ] must not be blank" as String

        where:
        value << [null, '', ' ']
    }

    @Unroll
    def "not.toBeBlank - invalid - with label - #value"() {
        when:
        expectString(value, 'Greeting').not().toBeBlank().check()

        then:
        def error = thrown(StringBlankPreconditionException.class)
        error.message == "Greeting [ ${value} ] must not be blank" as String

        where:
        value << [null, '', ' ']
    }

}
