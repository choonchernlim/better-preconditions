package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.*
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.LocalTime
import org.joda.time.base.BaseLocal
import spock.lang.Specification
import spock.lang.Unroll

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class JodaTimeToBeEqualOrBeforePreconditionsSpec extends Specification {

    @Unroll
    def "toBeEqualOrBefore - #firstValue <= #secondValue should be ok"() {
        when:
        def actualValue = expect(firstValue).toBeEqualOrBefore(secondValue).check()

        then:
        actualValue == firstValue

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 1)
        new LocalTime(7, 45)                | new LocalTime(7, 45)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 4)
        new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 2)
        new LocalTime(7, 45)                | new LocalTime(7, 46)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 5)
    }

    @Unroll
    def "toBeEqualOrBefore - null <= #secondValue should throw ObjectNullPreconditionException"() {
        when:
        expect(null as BaseLocal).toBeEqualOrBefore(secondValue).check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == 'Joda Time [ null ] must not be null'

        where:
        secondValue << [new LocalDate(2015, 1, 1),
                        new LocalTime(7, 45),
                        new LocalDateTime(2015, 1, 2, 3, 4)]
    }

    @Unroll
    def "toBeEqualOrBefore - #firstValue <= null should throw ObjectNullPreconditionException"() {
        when:
        expect(firstValue).toBeEqualOrBefore(null).check()

        then:
        def error = thrown(ObjectNullPreconditionException.class)
        error.message == 'Expected Joda Time [ null ] must not be null'

        where:
        firstValue << [new LocalDate(2015, 1, 1),
                       new LocalTime(7, 45),
                       new LocalDateTime(2015, 1, 2, 3, 4)]
    }

    @Unroll
    def "toBeEqualOrBefore - #firstValue <= #secondValue - null expected label should throw StringBlankPreconditionException"() {
        when:
        expect(firstValue).toBeEqualOrBefore(secondValue, null).check()

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
    def "toBeEqualOrBefore - #firstValue <= #secondValue should throw JodaTimeNotEqualOrBeforePreconditionException"() {
        when:
        expect(firstValue).toBeEqualOrBefore(secondValue).check()

        then:
        def error = thrown(JodaTimeNotEqualOrBeforePreconditionException.class)
        error.message == "Joda Time [ ${firstValue} ] must be equal to or before Expected Joda Time [ ${secondValue} ]" as String

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 2)           | new LocalDate(2015, 1, 1)
        new LocalTime(7, 46)                | new LocalTime(7, 45)
        new LocalDateTime(2015, 1, 2, 3, 5) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "toBeEqualOrBefore - #firstValue <= #secondValue should throw JodaTimeNotEqualOrBeforePreconditionException - with label"() {
        when:
        expect(firstValue, 'Start Value').toBeEqualOrBefore(secondValue, 'End Value').check()

        then:
        def error = thrown(JodaTimeNotEqualOrBeforePreconditionException.class)
        error.message == "Start Value [ ${firstValue} ] must be equal to or before End Value [ ${secondValue} ]" as String

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 2)           | new LocalDate(2015, 1, 1)
        new LocalTime(7, 46)                | new LocalTime(7, 45)
        new LocalDateTime(2015, 1, 2, 3, 5) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "not.toBeEqualOrBefore - #firstValue !<= #secondValue should be ok"() {
        when:
        def actualValue = expect(firstValue).not().toBeEqualOrBefore(secondValue).check()

        then:
        actualValue == firstValue

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 2)           | new LocalDate(2015, 1, 1)
        new LocalTime(7, 46)                | new LocalTime(7, 45)
        new LocalDateTime(2015, 1, 2, 3, 5) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "not.toBeEqualOrBefore - #firstValue !<= #secondValue should throw JodaTimeEqualOrBeforePreconditionException"() {
        when:
        expect(firstValue).not().toBeEqualOrBefore(secondValue).check()

        then:
        def error = thrown(JodaTimeEqualOrBeforePreconditionException.class)
        error.message == "Joda Time [ ${firstValue} ] must not be equal to or before Expected Joda Time [ ${secondValue} ]" as String

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 1)
        new LocalTime(7, 45)                | new LocalTime(7, 45)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "not.toBeEqualOrBefore - #firstValue !<= #secondValue should throw JodaTimeEqualOrBeforePreconditionException - with label"() {
        when:
        expect(firstValue, 'Start Value').not().toBeEqualOrBefore(secondValue, 'End Value').check()

        then:
        def error = thrown(JodaTimeEqualOrBeforePreconditionException.class)
        error.message == "Start Value [ ${firstValue} ] must not be equal to or before End Value [ ${secondValue} ]" as String

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 1)
        new LocalTime(7, 45)                | new LocalTime(7, 45)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "toBeEqualOrBefore - different types - #firstValue and #secondValue should throw ObjectNotSameTypePreconditionException"() {
        when:
        expect(firstValue).toBeEqualOrBefore(secondValue).check()

        then:
        def error = thrown(ObjectNotSameTypePreconditionException.class)
        error.message == "Expected Joda Time [ ${secondValue} ] must be same type as Joda Time [ ${firstValue} ]" as String

        where:
        firstValue                          | secondValue
        new LocalDate(2015, 1, 1)           | new LocalDateTime(2015, 1, 2, 3, 4)
        new LocalTime(7, 45)                | new LocalDate(2015, 1, 1)
        new LocalDateTime(2015, 1, 2, 3, 4) | new LocalTime(7, 45)
    }
}
