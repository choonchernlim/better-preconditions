package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.JodaTimeAfterPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.JodaTimeNotAfterPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.ObjectNullPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.StringBlankPreconditionException
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.LocalTime
import org.joda.time.base.BaseLocal
import spock.lang.Specification
import spock.lang.Unroll

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class JodaTimeToBeAfterPreconditionsSpec extends Specification {

    @Unroll
    def "toBeAfter - #firstValue > #secondValue should be ok"() {
        when:
        def actualValue = expect(firstValue).toBeAfter(secondValue).check()

        then:
        actualValue == firstValue

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 2)           | new LocalDate(2015, 1, 1)
        new LocalTime(7, 46)                | new LocalTime(7, 45)
        new LocalDateTime(2015, 1, 2, 3, 5) | new LocalDateTime(2015, 1, 2, 3, 4)
        new LocalDate(2015, 1, 2)           | new LocalDate(2015, 1, 1)
    }

    @Unroll
    def "toBeAfter - null > #secondValue should throw ObjectNullPreconditionException"() {
        when:
        expect(null as BaseLocal).toBeAfter(secondValue).check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == 'Joda Time [ null ] must not be null'

        where:
        secondValue << [new LocalDate(2015, 1, 1),
                        new LocalTime(7, 45),
                        new LocalDateTime(2015, 1, 2, 3, 4)]
    }

    @Unroll
    def "toBeAfter - #firstValue > null should throw ObjectNullPreconditionException"() {
        when:
        expect(firstValue).toBeAfter(null).check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == 'Expected Joda Time [ null ] must not be null'

        where:
        firstValue << [new LocalDate(2015, 1, 1),
                       new LocalTime(7, 45),
                       new LocalDateTime(2015, 1, 2, 3, 4)]
    }

    @Unroll
    def "toBeAfter - #firstValue > #secondValue - null expected label should throw StringBlankPreconditionException"() {
        when:
        expect(firstValue).toBeAfter(secondValue, null).check()

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
    def "toBeAfter - #firstValue > #secondValue should throw JodaTimeNotAfterPreconditionException"() {
        when:
        expect(firstValue).toBeAfter(secondValue).check()

        then:
        def error = thrown(JodaTimeNotAfterPreconditionException.class)
        error.message == "Joda Time [ ${firstValue} ] must be after Expected Joda Time [ ${secondValue} ]" as String

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 2)           | new LocalDate(2015, 1, 2)
        new LocalTime(7, 46)                | new LocalTime(7, 46)
        new LocalDateTime(2015, 1, 2, 3, 5) | new LocalDateTime(2015, 1, 2, 3, 5)
    }

    @Unroll
    def "toBeAfter - #firstValue > #secondValue should throw JodaTimeNotAfterPreconditionException - with label"() {
        when:
        expect(firstValue, 'Start Value').toBeAfter(secondValue, 'End Value').check()

        then:
        def error = thrown(JodaTimeNotAfterPreconditionException.class)
        error.message == "Start Value [ ${firstValue} ] must be after End Value [ ${secondValue} ]" as String

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 2)           | new LocalDate(2015, 1, 2)
        new LocalTime(7, 46)                | new LocalTime(7, 46)
        new LocalDateTime(2015, 1, 2, 3, 5) | new LocalDateTime(2015, 1, 2, 3, 5)
    }

    @Unroll
    def "not.toBeAfter - #firstValue !> #secondValue should be ok"() {
        when:
        def actualValue = expect(firstValue).not().toBeAfter(secondValue).check()

        then:
        actualValue == firstValue

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 2)           | new LocalDate(2015, 1, 2)
        new LocalTime(7, 46)                | new LocalTime(7, 46)
        new LocalDateTime(2015, 1, 2, 3, 5) | new LocalDateTime(2015, 1, 2, 3, 5)
    }

    @Unroll
    def "not.toBeAfter - #firstValue !> #secondValue should throw JodaTimeAfterPreconditionException"() {
        when:
        expect(firstValue).not().toBeAfter(secondValue).check()

        then:
        def error = thrown(JodaTimeAfterPreconditionException.class)
        error.message == "Joda Time [ ${firstValue} ] must not be after Expected Joda Time [ ${secondValue} ]" as String

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 2)           | new LocalDate(2015, 1, 1)
        new LocalTime(7, 46)                | new LocalTime(7, 45)
        new LocalDateTime(2015, 1, 2, 3, 5) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "not.toBeAfter - #firstValue !> #secondValue should throw JodaTimeAfterPreconditionException - with label"() {
        when:
        expect(firstValue, 'Start Value').not().toBeAfter(secondValue, 'End Value').check()

        then:
        def error = thrown(JodaTimeAfterPreconditionException.class)
        error.message == "Start Value [ ${firstValue} ] must not be after End Value [ ${secondValue} ]" as String

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 2)           | new LocalDate(2015, 1, 1)
        new LocalTime(7, 46)                | new LocalTime(7, 45)
        new LocalDateTime(2015, 1, 2, 3, 5) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "toBeAfter - comparing two different types - #firstValue and #secondValue should throw ClassCastException"() {
        when:
        expect(firstValue).toBeAfter(secondValue).check()

        then:
        thrown(ClassCastException.class)

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDateTime(2015, 1, 2, 3, 4)
        new LocalTime(7, 45)                | new LocalDate(2015, 1, 1)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalTime(7, 45)
    }
}
