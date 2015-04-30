package com.choonchernlim.betterPreconditions.preconditions

import com.choonchernlim.betterPreconditions.exception.StringBlankPreconditionException
import spock.lang.Specification
import spock.lang.Unroll

class StringBetterPreconditionsSpec extends Specification {

    @Unroll
    def "checkNotBlank - invalid label - #name"() {
        when:
        StringBetterPreconditions.checkNotBlank("Hello", name)

        then:
        thrown(StringBlankPreconditionException.class)

        where:
        name << [null, "", " "]
    }

    @Unroll
    def "checkNotBlank - invalid value - #val"() {
        when:
        StringBetterPreconditions.checkNotBlank(null, "Variable")

        then:
        thrown(StringBlankPreconditionException.class)

        where:
        val << [null, "", " "]
    }

    def "checkNotBlank - valid value"() {
        when:
        StringBetterPreconditions.checkNotBlank("Hello", "Variable")

        then:
        notThrown(StringBlankPreconditionException.class)
    }
}
