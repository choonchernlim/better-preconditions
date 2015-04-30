package com.choonchernlim.betterPreconditions.preconditions

import com.choonchernlim.betterPreconditions.exception.NotBlankException
import spock.lang.Specification
import spock.lang.Unroll

class StringPreconditionsSpec extends Specification {

    @Unroll
    def "checkNotBlank - invalid label - #name"() {
        when:
        StringPreconditions.checkNotBlank("Hello", name)

        then:
        thrown(IllegalArgumentException.class)

        where:
        name << [null, "", " "]
    }

    @Unroll
    def "checkNotBlank - invalid value - #val"() {
        when:
        StringPreconditions.checkNotBlank(null, "Variable")

        then:
        thrown(NotBlankException.class)

        where:
        val << [null, "", " "]
    }

    def "checkNotBlank - valid value"() {
        when:
        StringPreconditions.checkNotBlank("Hello", "Variable")

        then:
        notThrown(NotBlankException.class)
    }
}
