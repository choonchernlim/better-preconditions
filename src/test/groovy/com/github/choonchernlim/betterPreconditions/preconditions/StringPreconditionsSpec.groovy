package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.*
import spock.lang.Specification
import spock.lang.Unroll

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class StringPreconditionsSpec extends Specification {

    def "toBeEqual - 'Hello' != 'Hey' should throw ObjectNotEqualPreconditionException"() {
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

    def "not.toBeEqual - 'Hello' == 'Hey' should be ok"() {
        when:
        def actualValue = expect('Hello').not().toBeEqual('Hey').check()

        then:
        actualValue == 'Hello'
    }

    def "toBeNull - 'Hello' should throw ObjectNotNullPreconditionException"() {
        when:
        expect('Hello').toBeNull().check()

        then:
        def error = thrown(ObjectNotNullPreconditionException.class)
        error.message == 'String [ Hello ] must be null'
    }

    def "not.toBeNull - 'Hello' should be ok"() {
        when:
        def actualValue = expect('Hello').not().toBeNull().check()

        then:
        actualValue == 'Hello'
    }

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

    def "not.toBeNull.not.toBeBlank - null should throw ObjectNullPreconditionException"() {
        when:
        expect(null as String).
                not().toBeNull().
                not().toBeBlank().
                check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == 'String [ null ] must not be null'
    }

}
