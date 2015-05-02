package com.choonchernlim.betterPreconditions.preconditions

import com.choonchernlim.betterPreconditions.exception.StringBlankPreconditionException
import spock.lang.Specification
import spock.lang.Unroll

class StringPreconditionsSpec extends Specification {

    @Unroll
    def "mustNotBeBlank - invalid label - #name"() {
        when:
        StringPreconditions.mustNotBeBlank("Hello", name)

        then:
        thrown(StringBlankPreconditionException.class)

        where:
        name << [null, "", " "]
    }

    @Unroll
    def "mustNotBeBlank - invalid value - #val"() {
        when:
        StringPreconditions.mustNotBeBlank(null, "Variable")

        then:
        thrown(StringBlankPreconditionException.class)

        where:
        val << [null, "", " "]
    }

    def "mustNotBeBlank - valid value"() {
        when:
        StringPreconditions.mustNotBeBlank("Hello", "Variable")

        then:
        notThrown(StringBlankPreconditionException.class)
    }
}
