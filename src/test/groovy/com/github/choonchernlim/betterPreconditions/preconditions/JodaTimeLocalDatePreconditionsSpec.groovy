package com.github.choonchernlim.betterPreconditions.preconditions

import com.choonchernlim.betterPreconditions.exception.*
import com.github.choonchernlim.betterPreconditions.exception.*
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.LocalTime
import spock.lang.Specification
import spock.lang.Unroll

import static JodaTimePreconditions.expect

class JodaTimeLocalDatePreconditionsSpec extends Specification {

    def "toBeNull - null should be ok"() {
        when:
        def actualValue = expect(null).toBeNull().check()

        then:
        actualValue == null
    }

    def "toBeNull - non-null should throw ObjectNotNullPreconditionException"() {
        when:
        expect(new LocalDate(2015, 1, 1)).toBeNull().check()

        then:
        def error = thrown(ObjectNotNullPreconditionException.class)
        error.message == 'Joda Time [ 2015-01-01 ] must be null'
    }

    @Unroll
    def "toBeEqual - #type - #firstValue == #secondValue should be ok"() {
        when:
        def actualValue = expect(firstValue).toBeEqual(secondValue).check()

        then:
        actualValue == secondValue

        where:
        type            | firstValue                          | secondValue
        'LocalDate'     | new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 1)
        'LocalTime'     | new LocalTime(7, 45)                | new LocalTime(7, 45)
        'LocalDateTime' | new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "toBeEqual - #type - null == #secondValue should throw ObjectNullPreconditionException"() {
        when:
        expect(null).toBeEqual(secondValue).check()

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
    def "toBeEqual - #type - #firstValue == null should throw ObjectNullPreconditionException"() {
        when:
        expect(firstValue).toBeEqual(null).check()

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
    def "toBeEqual - #type - #firstValue == #secondValue - null expected label should throw StringBlankPreconditionException"() {
        when:
        expect(firstValue).toBeEqual(secondValue, null).check()

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
    def "toBeEqual - #type - #firstValue == #secondValue should throw JodaTimeNotEqualPreconditionException"() {
        when:
        expect(firstValue).toBeEqual(secondValue).check()

        then:
        def error = thrown(JodaTimeNotEqualPreconditionException.class)
        error.message == "Joda Time [ ${firstValue} ] must be equal to Expected Joda Time [ ${secondValue} ]" as String

        where:
        type            | firstValue                          | secondValue
        'LocalDate'     | new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 2)
        'LocalTime'     | new LocalTime(7, 45)                | new LocalTime(7, 46)
        'LocalDateTime' | new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 5)
    }

    @Unroll
    def "toBeEqual - #type - #firstValue == #secondValue should throw JodaTimeNotEqualPreconditionException - with label"() {
        when:
        expect(firstValue, 'Start Value').toBeEqual(secondValue, 'End Value').check()

        then:
        def error = thrown(JodaTimeNotEqualPreconditionException.class)
        error.message == "Start Value [ ${firstValue} ] must be equal to End Value [ ${secondValue} ]" as String

        where:
        type            | firstValue                          | secondValue
        'LocalDate'     | new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 2)
        'LocalTime'     | new LocalTime(7, 45)                | new LocalTime(7, 46)
        'LocalDateTime' | new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 5)
    }

    @Unroll
    def "not.toBeEqual - #type - #firstValue != #secondValue should be ok"() {
        when:
        def actualValue = expect(firstValue).not().toBeEqual(secondValue).check()

        then:
        actualValue == firstValue

        where:
        type            | firstValue                          | secondValue
        'LocalDate'     | new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 2)
        'LocalTime'     | new LocalTime(7, 45)                | new LocalTime(7, 46)
        'LocalDateTime' | new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 5)
    }

    @Unroll
    def "not.toBeEqual - #type - #firstValue != #secondValue should throw JodaTimeEqualPreconditionException"() {
        when:
        expect(firstValue).not().toBeEqual(secondValue).check()

        then:
        def error = thrown(JodaTimeEqualPreconditionException.class)
        error.message == "Joda Time [ ${firstValue} ] must not be equal to Expected Joda Time [ ${secondValue} ]" as String

        where:
        type            | firstValue                          | secondValue
        'LocalDate'     | new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 1)
        'LocalTime'     | new LocalTime(7, 45)                | new LocalTime(7, 45)
        'LocalDateTime' | new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 4)
    }

    @Unroll
    def "not.toBeEqual - #type - #firstValue != #secondValue should throw JodaTimeEqualPreconditionException - with label"() {
        when:
        expect(firstValue, 'Start Value').not().toBeEqual(secondValue, 'End Value').check()

        then:
        def error = thrown(JodaTimeEqualPreconditionException.class)
        error.message == "Start Value [ ${firstValue} ] must not be equal to End Value [ ${secondValue} ]" as String

        where:
        type            | firstValue                          | secondValue
        'LocalDate'     | new LocalDate(2015, 1, 1)           | new LocalDate(2015, 1, 1)
        'LocalTime'     | new LocalTime(7, 45)                | new LocalTime(7, 45)
        'LocalDateTime' | new LocalDateTime(2015, 1, 2, 3, 4) | new LocalDateTime(2015, 1, 2, 3, 4)
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
