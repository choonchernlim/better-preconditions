package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.JodaTimeBeforePreconditionException
import com.github.choonchernlim.betterPreconditions.exception.JodaTimeNotBeforePreconditionException
import com.github.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.StringBlankPreconditionException
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.LocalTime
import org.joda.time.base.BaseLocal
import spock.lang.Specification
import spock.lang.Unroll

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class JodaTimeToBeBeforePreconditionsSpec extends Specification {

    @Unroll
    def "toBeBefore - #firstValue < #secondValue should be ok"() {
        when:
        def actualValue = expect(firstValue).toBeBefore(secondValue).check()

        then:
        actualValue == firstValue

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 2)
        new LocalTime(7, 45)                | new LocalTime(7, 46)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 5)
        new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 2)
    }

    @Unroll
    def "toBeBefore - null < #secondValue should throw ObjectNullPreconditionException"() {
        when:
        expect(null as BaseLocal).toBeBefore(secondValue).check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == 'Joda Time [ null ] must not be null'

        where:
        secondValue << [new LocalDate(2015, 1, 1),
                        new LocalTime(7, 45),
                        new LocalDateTime(2015, 1, 2, 3, 4)]
    }

    @Unroll
    def "toBeBefore - #firstValue < null should throw ObjectNullPreconditionException"() {
        when:
        expect(firstValue).toBeBefore(null).check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == 'Expected Joda Time [ null ] must not be null'

        where:
        firstValue << [new LocalDate(2015, 1, 1),
                       new LocalTime(7, 45),
                       new LocalDateTime(2015, 1, 2, 3, 4)]
    }

    @Unroll
    def "toBeBefore - #firstValue < #secondValue - null expected label should throw StringBlankPreconditionException"() {
        when:
        expect(firstValue).toBeBefore(secondValue, null).check()

        then:
        def error = thrown(StringBlankPreconditionException.class)
        error.message == 'Expected Joda Time Label [ null ] must not be blank'

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 2)           | new LocalDate(2015, 1, 1)
        new LocalTime(7, 46)                | new LocalTime(7, 45)
        new LocalDateTime(2015, 1, 2, 3, 5) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "toBeBefore - #firstValue < #secondValue should throw JodaTimeNotBeforePreconditionException"() {
        when:
        expect(firstValue).toBeBefore(secondValue).check()

        then:
        def error = thrown(JodaTimeNotBeforePreconditionException.class)
        error.message == "Joda Time [ ${firstValue} ] must be before Expected Joda Time [ ${secondValue} ]" as String

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 2)           | new LocalDate(2015, 1, 2)
        new LocalTime(7, 46)                | new LocalTime(7, 46)
        new LocalDateTime(2015, 1, 2, 3, 5) | new LocalDateTime(2015, 1, 2, 3, 5)
    }

    @Unroll
    def "toBeBefore - #firstValue < #secondValue should throw JodaTimeNotBeforePreconditionException - with label"() {
        when:
        expect(firstValue, 'Start Value').toBeBefore(secondValue, 'End Value').check()

        then:
        def error = thrown(JodaTimeNotBeforePreconditionException.class)
        error.message == "Start Value [ ${firstValue} ] must be before End Value [ ${secondValue} ]" as String

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 2)           | new LocalDate(2015, 1, 2)
        new LocalTime(7, 46)                | new LocalTime(7, 46)
        new LocalDateTime(2015, 1, 2, 3, 5) | new LocalDateTime(2015, 1, 2, 3, 5)
    }

    @Unroll
    def "not.toBeBefore - #firstValue !< #secondValue should be ok"() {
        when:
        def actualValue = expect(firstValue).not().toBeBefore(secondValue).check()

        then:
        actualValue == firstValue

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 2)           | new LocalDate(2015, 1, 2)
        new LocalTime(7, 46)                | new LocalTime(7, 46)
        new LocalDateTime(2015, 1, 2, 3, 5) | new LocalDateTime(2015, 1, 2, 3, 5)
    }

    @Unroll
    def "not.toBeBefore - #firstValue !< #secondValue should throw JodaTimeBeforePreconditionException"() {
        when:
        expect(firstValue).not().toBeBefore(secondValue).check()

        then:
        def error = thrown(JodaTimeBeforePreconditionException.class)
        error.message == "Joda Time [ ${firstValue} ] must not be before Expected Joda Time [ ${secondValue} ]" as String

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 2)
        new LocalTime(7, 45)                | new LocalTime(7, 46)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 5)
    }

    @Unroll
    def "not.toBeBefore - #firstValue !< #secondValue should throw JodaTimeBeforePreconditionException - with label"() {
        when:
        expect(firstValue, 'Start Value').not().toBeBefore(secondValue, 'End Value').check()

        then:
        def error = thrown(JodaTimeBeforePreconditionException.class)
        error.message == "Start Value [ ${firstValue} ] must not be before End Value [ ${secondValue} ]" as String

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 2)
        new LocalTime(7, 45)                | new LocalTime(7, 46)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 5)
    }

    @Unroll
    def "toBeBefore - comparing two different types - #firstValue and #secondValue should throw ClassCastException"() {
        when:
        expect(firstValue).toBeBefore(secondValue).check()

        then:
        thrown(ClassCastException.class)

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDateTime(2015, 1, 2, 3, 4)
        new LocalTime(7, 45)                | new LocalDate(2015, 1, 1)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalTime(7, 45)
    }
}
