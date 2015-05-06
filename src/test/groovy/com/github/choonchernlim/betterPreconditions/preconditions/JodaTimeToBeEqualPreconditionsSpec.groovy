package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.JodaTimeEqualPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.JodaTimeNotEqualPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.StringBlankPreconditionException
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.LocalTime
import spock.lang.Specification
import spock.lang.Unroll

import static JodaTimePreconditions.expect

class JodaTimeToBeEqualPreconditionsSpec extends Specification {

    @Unroll
    def "toBeEqual - #firstValue == #secondValue should be ok"() {
        when:
        def actualValue = expect(firstValue).toBeEqual(secondValue).check()

        then:
        actualValue == secondValue

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 1)
        new LocalTime(7, 45)                | new LocalTime(7, 45)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "toBeEqual - null == #secondValue should throw ObjectNullPreconditionException"() {
        when:
        expect(null).toBeEqual(secondValue).check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == 'Joda Time [ null ] must not be null'

        where:
        secondValue << [new LocalDate(2015, 1, 1),
                        new LocalTime(7, 45),
                        new LocalDateTime(2015, 1, 2, 3, 4)]
    }

    @Unroll
    def "toBeEqual - #firstValue == null should throw ObjectNullPreconditionException"() {
        when:
        expect(firstValue).toBeEqual(null).check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == 'Expected Joda Time [ null ] must not be null'

        where:
        firstValue << [new LocalDate(2015, 1, 1),
                       new LocalTime(7, 45),
                       new LocalDateTime(2015, 1, 2, 3, 4)]
    }

    @Unroll
    def "toBeEqual - #firstValue == #secondValue - null expected label should throw StringBlankPreconditionException"() {
        when:
        expect(firstValue).toBeEqual(secondValue, null).check()

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
    def "toBeEqual - #firstValue == #secondValue should throw JodaTimeNotEqualPreconditionException"() {
        when:
        expect(firstValue).toBeEqual(secondValue).check()

        then:
        def error = thrown(JodaTimeNotEqualPreconditionException.class)
        error.message == "Joda Time [ ${firstValue} ] must be equal to Expected Joda Time [ ${secondValue} ]" as String

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 2)
        new LocalTime(7, 45)                | new LocalTime(7, 46)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 5)
    }

    @Unroll
    def "toBeEqual - #firstValue == #secondValue should throw JodaTimeNotEqualPreconditionException - with label"() {
        when:
        expect(firstValue, 'Start Value').toBeEqual(secondValue, 'End Value').check()

        then:
        def error = thrown(JodaTimeNotEqualPreconditionException.class)
        error.message == "Start Value [ ${firstValue} ] must be equal to End Value [ ${secondValue} ]" as String

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 2)
        new LocalTime(7, 45)                | new LocalTime(7, 46)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 5)
    }

    @Unroll
    def "not.toBeEqual - #firstValue != #secondValue should be ok"() {
        when:
        def actualValue = expect(firstValue).not().toBeEqual(secondValue).check()

        then:
        actualValue == firstValue

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 2)
        new LocalTime(7, 45)                | new LocalTime(7, 46)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 5)
    }

    @Unroll
    def "not.toBeEqual - #firstValue != #secondValue should throw JodaTimeEqualPreconditionException"() {
        when:
        expect(firstValue).not().toBeEqual(secondValue).check()

        then:
        def error = thrown(JodaTimeEqualPreconditionException.class)
        error.message == "Joda Time [ ${firstValue} ] must not be equal to Expected Joda Time [ ${secondValue} ]" as String

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 1)
        new LocalTime(7, 45)                | new LocalTime(7, 45)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "not.toBeEqual - #firstValue != #secondValue should throw JodaTimeEqualPreconditionException - with label"() {
        when:
        expect(firstValue, 'Start Value').not().toBeEqual(secondValue, 'End Value').check()

        then:
        def error = thrown(JodaTimeEqualPreconditionException.class)
        error.message == "Start Value [ ${firstValue} ] must not be equal to End Value [ ${secondValue} ]" as String

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 1)
        new LocalTime(7, 45)                | new LocalTime(7, 45)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "toBeEqual - comparing two different types - #firstValue == #secondValue should throw ClassCastException"() {
        when:
        expect(firstValue).toBeEqual(secondValue).check()

        then:
        thrown(ClassCastException.class)

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDateTime(2015, 1, 2, 3, 4)
        new LocalTime(7, 45)                | new LocalDate(2015, 1, 1)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalTime(7, 45)
    }
}
