package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.ObjectNotNullPreconditionException
import spock.lang.Specification
import spock.lang.Unroll

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class BooleanToBeNullPreconditionsSpec extends Specification {

    def "toBeNull - null should be ok"() {
        when:
        def actualValue = expect(null as Boolean).toBeNull().check()

        then:
        actualValue == null
    }

    @Unroll
    def "toBeNull - #value should throw ObjectNotNullPreconditionException"() {
        when:
        expect(value).toBeNull().check()

        then:
        def error = thrown(ObjectNotNullPreconditionException.class)
        error.message == "Boolean [ ${value} ] must be null" as String

        where:
        value << [true, false]
    }

}
