package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.JodaTimeEqualOrAfterPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.JodaTimeNotEqualOrAfterPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.StringBlankPreconditionException
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.LocalTime
import org.joda.time.base.BaseLocal
import spock.lang.Specification
import spock.lang.Unroll

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class JodaTimeToBeEqualOrAfterPreconditionsSpec extends Specification {

    @Unroll
    def "toBeEqualOrAfter - #firstValue == #secondValue should be ok"() {
        when:
        def actualValue = expect(firstValue).toBeEqualOrAfter(secondValue).check()

        then:
        actualValue == firstValue

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 1)
        new LocalTime(7, 45)                | new LocalTime(7, 45)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "toBeEqualOrAfter - #firstValue > #secondValue should be ok"() {
        when:
        def actualValue = expect(firstValue).toBeEqualOrAfter(secondValue).check()

        then:
        actualValue == firstValue

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 2)           | new LocalDate(2015, 1, 1)
        new LocalTime(7, 46)                | new LocalTime(7, 45)
        new LocalDateTime(2015, 1, 2, 3, 5) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "toBeEqualOrAfter - null >= #secondValue should throw ObjectNullPreconditionException"() {
        when:
        expect(null as BaseLocal).toBeEqualOrAfter(secondValue).check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == 'Joda Time [ null ] must not be null'

        where:
        secondValue << [new LocalDate(2015, 1, 1),
                        new LocalTime(7, 45),
                        new LocalDateTime(2015, 1, 2, 3, 4)]
    }

    @Unroll
    def "toBeEqualOrAfter - #firstValue >= null should throw ObjectNullPreconditionException"() {
        when:
        expect(firstValue).toBeEqualOrAfter(null).check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == 'Expected Joda Time [ null ] must not be null'

        where:
        firstValue << [new LocalDate(2015, 1, 1),
                       new LocalTime(7, 45),
                       new LocalDateTime(2015, 1, 2, 3, 4)]
    }

    @Unroll
    def "toBeEqualOrAfter - #firstValue == #secondValue - null expected label should throw StringBlankPreconditionException"() {
        when:
        expect(firstValue).toBeEqualOrAfter(secondValue, null).check()

        then:
        def error = thrown(StringBlankPreconditionException.class)
        error.message == 'Expected Joda Time Label [ null ] must not be blank'

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 1)
        new LocalTime(7, 45)                | new LocalTime(7, 45)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "toBeEqualOrAfter - #firstValue < #secondValue should throw JodaTimeNotEqualOrAfterPreconditionException"() {
        when:
        expect(firstValue).toBeEqualOrAfter(secondValue).check()

        then:
        def error = thrown(JodaTimeNotEqualOrAfterPreconditionException.class)
        error.message == "Joda Time [ ${firstValue} ] must be equal to or after Expected Joda Time [ ${secondValue} ]" as String

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 2)
        new LocalTime(7, 45)                | new LocalTime(7, 46)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 5)
    }

    @Unroll
    def "toBeEqualOrAfter - #firstValue < #secondValue should throw JodaTimeNotEqualOrAfterPreconditionException - with label"() {
        when:
        expect(firstValue, 'Start Value').toBeEqualOrAfter(secondValue, 'End Value').check()

        then:
        def error = thrown(JodaTimeNotEqualOrAfterPreconditionException.class)
        error.message == "Start Value [ ${firstValue} ] must be equal to or after End Value [ ${secondValue} ]" as String

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 2)
        new LocalTime(7, 45)                | new LocalTime(7, 46)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 5)
    }

    @Unroll
    def "not.toBeEqualOrAfter - #firstValue < #secondValue should be ok"() {
        when:
        def actualValue = expect(firstValue).not().toBeEqualOrAfter(secondValue).check()

        then:
        actualValue == firstValue

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 2)
        new LocalTime(7, 45)                | new LocalTime(7, 46)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 5)
    }

    @Unroll
    def "not.toBeEqualOrAfter - #firstValue == #secondValue should throw JodaTimeEqualOrAfterPreconditionException"() {
        when:
        expect(firstValue).not().toBeEqualOrAfter(secondValue).check()

        then:
        def error = thrown(JodaTimeEqualOrAfterPreconditionException.class)
        error.message == "Joda Time [ ${firstValue} ] must not be equal to or after Expected Joda Time [ ${secondValue} ]" as String

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 1)
        new LocalTime(7, 45)                | new LocalTime(7, 45)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "not.toBeEqualOrAfter - #firstValue == #secondValue should throw JodaTimeEqualOrAfterPreconditionException - with label"() {
        when:
        expect(firstValue, 'Start Value').not().toBeEqualOrAfter(secondValue, 'End Value').check()

        then:
        def error = thrown(JodaTimeEqualOrAfterPreconditionException.class)
        error.message == "Start Value [ ${firstValue} ] must not be equal to or after End Value [ ${secondValue} ]" as String

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 1)
        new LocalTime(7, 45)                | new LocalTime(7, 45)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "toBeEqualOrAfter - comparing two different types - #firstValue and #secondValue should throw ClassCastException"() {
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
