package com.choonchernlim.betterPreconditions.preconditions

import com.choonchernlim.betterPreconditions.exception.ObjectNotNullPreconditionException
import com.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException
import com.choonchernlim.betterPreconditions.exception.StringBlankPreconditionException
import com.choonchernlim.betterPreconditions.exception.StringNotBlankPreconditionException
import spock.lang.Specification
import spock.lang.Unroll

import static com.choonchernlim.betterPreconditions.preconditions.StringPreconditions.expect

class StringPreconditionsSpec extends Specification {

    def "toBeNull - invalid"() {
        when:
        expect('Hello').toBeNull().check()

        then:
        def error = thrown(ObjectNotNullPreconditionException.class)
        error.message == 'String [ Hello ] must be null'
    }

    def "toBeNull - valid"() {
        when:
        def actualValue = expect('Hello').not().toBeNull().check()

        then:
        actualValue == 'Hello'
    }

    @Unroll
    def "toBeBlank - valid - #value"() {
        when:
        def actualValue = expect(value).toBeBlank().check()

        then:
        actualValue == value

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
        def actualValue = expect('Hello').not().toBeBlank().check()

        then:
        actualValue == 'Hello'
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

    def "not.toBeNull should throw exception and short circuit the assertions"() {
        when:
        expect(null).
                not().toBeNull().
                not().toBeBlank().
                check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == 'String [ null ] must not be null'
    }

}
