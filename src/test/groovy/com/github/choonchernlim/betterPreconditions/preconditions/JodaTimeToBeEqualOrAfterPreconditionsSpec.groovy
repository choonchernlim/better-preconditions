package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.JodaTimeEqualOrAfterPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.JodaTimeNotEqualOrAfterPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.StringBlankPreconditionException
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.LocalTime
import spock.lang.Specification
import spock.lang.Unroll

import static JodaTimePreconditions.expect

class JodaTimeToBeEqualOrAfterPreconditionsSpec extends Specification {

    @Unroll
    def "toBeEqualOrAfter - #type - #firstValue >= #secondValue should be ok"() {
        when:
        def actualValue = expect(firstValue).toBeEqualOrAfter(secondValue).check()

        then:
        actualValue == firstValue

        where:
        type            | firstValue                          | secondValue
        'LocalDate'     | new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 1)
        'LocalTime'     | new LocalTime(7, 45)                | new LocalTime(7, 45)
        'LocalDateTime' | new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "toBeEqualOrAfter - #type - #firstValue > #secondValue should be ok"() {
        when:
        def actualValue = expect(firstValue).toBeEqualOrAfter(secondValue).check()

        then:
        actualValue == firstValue

        where:
        type            | firstValue                          | secondValue
        'LocalDate'     | new LocalDate(2015, 1, 2)           | new LocalDate(2015, 1, 1)
        'LocalTime'     | new LocalTime(7, 46)                | new LocalTime(7, 45)
        'LocalDateTime' | new LocalDateTime(2015, 1, 2, 3, 5) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "toBeEqualOrAfter - #type - null >= #secondValue should throw ObjectNullPreconditionException"() {
        when:
        expect(null).toBeEqualOrAfter(secondValue).check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == 'Joda Time [ null ] must not be null'

        where:
        type            | secondValue
        'LocalDate'     | new LocalDate(2015, 1, 1)
        'LocalTime'     | new LocalTime(7, 45)
        'LocalDateTime' | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "toBeEqualOrAfter - #type - #firstValue >= null should throw ObjectNullPreconditionException"() {
        when:
        expect(firstValue).toBeEqualOrAfter(null).check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == 'Expected Joda Time [ null ] must not be null'

        where:
        type            | firstValue
        'LocalDate'     | new LocalDate(2015, 1, 1)
        'LocalTime'     | new LocalTime(7, 45)
        'LocalDateTime' | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "toBeEqualOrAfter - #type - #firstValue >= #secondValue - null expected label should throw StringBlankPreconditionException"() {
        when:
        expect(firstValue).toBeEqualOrAfter(secondValue, null).check()

        then:
        def error = thrown(StringBlankPreconditionException.class)
        error.message == 'Expected Joda Time Label [ null ] must not be blank'

        where:
        type            | firstValue                          | secondValue
        'LocalDate'     | new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 1)
        'LocalTime'     | new LocalTime(7, 45)                | new LocalTime(7, 45)
        'LocalDateTime' | new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "toBeEqualOrAfter - #type - #firstValue >= #secondValue should throw JodaTimeNotEqualOrAfterPreconditionException"() {
        when:
        expect(firstValue).toBeEqualOrAfter(secondValue).check()

        then:
        def error = thrown(JodaTimeNotEqualOrAfterPreconditionException.class)
        error.message == "Joda Time [ ${firstValue} ] must be equal to or after Expected Joda Time [ ${secondValue} ]" as String

        where:
        type            | firstValue                          | secondValue
        'LocalDate'     | new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 2)
        'LocalTime'     | new LocalTime(7, 45)                | new LocalTime(7, 46)
        'LocalDateTime' | new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 5)
    }

    @Unroll
    def "toBeEqualOrAfter - #type - #firstValue >= #secondValue should throw JodaTimeNotEqualOrAfterPreconditionException - with label"() {
        when:
        expect(firstValue, 'Start Value').toBeEqualOrAfter(secondValue, 'End Value').check()

        then:
        def error = thrown(JodaTimeNotEqualOrAfterPreconditionException.class)
        error.message == "Start Value [ ${firstValue} ] must be equal to or after End Value [ ${secondValue} ]" as String

        where:
        type            | firstValue                          | secondValue
        'LocalDate'     | new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 2)
        'LocalTime'     | new LocalTime(7, 45)                | new LocalTime(7, 46)
        'LocalDateTime' | new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 5)
    }

    @Unroll
    def "not.toBeEqualOrAfter - #type - #firstValue < #secondValue should be ok"() {
        when:
        def actualValue = expect(firstValue).not().toBeEqualOrAfter(secondValue).check()

        then:
        actualValue == firstValue

        where:
        type            | firstValue                          | secondValue
        'LocalDate'     | new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 2)
        'LocalTime'     | new LocalTime(7, 45)                | new LocalTime(7, 46)
        'LocalDateTime' | new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 5)
    }

    @Unroll
    def "not.toBeEqualOrAfter - #type - #firstValue >= #secondValue should throw JodaTimeEqualOrAfterPreconditionException"() {
        when:
        expect(firstValue).not().toBeEqualOrAfter(secondValue).check()

        then:
        def error = thrown(JodaTimeEqualOrAfterPreconditionException.class)
        error.message == "Joda Time [ ${firstValue} ] must not be equal to or after Expected Joda Time [ ${secondValue} ]" as String

        where:
        type            | firstValue                          | secondValue
        'LocalDate'     | new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 1)
        'LocalTime'     | new LocalTime(7, 45)                | new LocalTime(7, 45)
        'LocalDateTime' | new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "not.toBeEqualOrAfter - #type - #firstValue >= #secondValue should throw JodaTimeEqualOrAfterPreconditionException - with label"() {
        when:
        expect(firstValue, 'Start Value').not().toBeEqualOrAfter(secondValue, 'End Value').check()

        then:
        def error = thrown(JodaTimeEqualOrAfterPreconditionException.class)
        error.message == "Start Value [ ${firstValue} ] must not be equal to or after End Value [ ${secondValue} ]" as String

        where:
        type            | firstValue                          | secondValue
        'LocalDate'     | new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 1)
        'LocalTime'     | new LocalTime(7, 45)                | new LocalTime(7, 45)
        'LocalDateTime' | new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "toBeEqualOrAfter - comparing two different types - #firstValue >= #secondValue should throw ClassCastException"() {
        when:
        expect(firstValue).toBeEqualOrAfter(secondValue).check()

        then:
        thrown(ClassCastException.class)

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDateTime(2015, 1, 2, 3, 4)
        new LocalTime(7, 45)                | new LocalDate(2015, 1, 1)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalTime(7, 45)
    }
}
