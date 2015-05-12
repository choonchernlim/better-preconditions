package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.NumberEqualOrGreaterThanPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.NumberNotEqualOrGreaterThanPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.ObjectNotSameTypePreconditionException
import spock.lang.Specification
import spock.lang.Unroll

import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class NumberToBeEqualOrGreaterThanPreconditionsSpec extends Specification {

    @Unroll
    def "toBeEqualOrGreaterThan - #firstValue.class >= #secondValue.class should be ok"() {
        when:
        def actualValue = expect(firstValue).toBeEqualOrGreaterThan(secondValue).check()

        then:
        actualValue >= firstValue

        where:
        firstValue           | secondValue
        1                    | 1
        1d                   | 1d
        1f                   | 1f
        1L                   | 1L
        new Short((short) 1) | new Short((short) 1)
        new Byte((byte) 1)   | new Byte((byte) 1)
        new BigInteger(1)    | new BigInteger(1)
        new BigDecimal(1)    | new BigDecimal(1)
        new AtomicInteger(1) | new AtomicInteger(1)
        new AtomicLong(1)    | new AtomicLong(1)
        2                    | 1
        2d                   | 1d
        2f                   | 1f
        2L                   | 1L
        new Short((short) 2) | new Short((short) 1)
        new Byte((byte) 2)   | new Byte((byte) 1)
        new BigInteger(2)    | new BigInteger(1)
        new BigDecimal(2)    | new BigDecimal(1)
        new AtomicInteger(2) | new AtomicInteger(1)
        new AtomicLong(2)    | new AtomicLong(1)
    }

    @Unroll
    def "toBeEqualOrGreaterThan - #firstValue.class != #secondValue.class should throw NumberNotEqualOrGreaterThanPreconditionException"() {
        when:
        expect(firstValue).toBeEqualOrGreaterThan(secondValue).check()

        then:
        def error = thrown(NumberNotEqualOrGreaterThanPreconditionException.class)
        error.message == "Number [ ${firstValue} ] must be equal to or greater than Expected Number [ ${secondValue} ]" as String

        where:
        firstValue           | secondValue
        1                    | 2
        1d                   | 2d
        1f                   | 2f
        1L                   | 2L
        new Short((short) 1) | new Short((short) 2)
        new Byte((byte) 1)   | new Byte((byte) 2)
        new BigInteger(1)    | new BigInteger(2)
        new BigDecimal(1)    | new BigDecimal(2)
        new AtomicInteger(1) | new AtomicInteger(2)
        new AtomicLong(1)    | new AtomicLong(2)
    }

    @Unroll
    def "toBeEqualOrGreaterThan - different types - #firstValue.class >= #secondValue.class should throw ObjectNotSameTypePreconditionException"() {
        when:
        expect(firstValue).toBeEqualOrGreaterThan(secondValue).check()

        then:
        def error = thrown(ObjectNotSameTypePreconditionException.class)
        error.message == "Expected Number [ ${secondValue} ] must be same type as Number [ ${firstValue} ]" as String

        where:
        firstValue | secondValue
        1          | 1d
        1          | 1f
        1          | 1L
        1          | new Short((short) 1)
        1          | new Byte((byte) 1)
        1          | new BigInteger(1)
        1          | new BigDecimal(1)
        1          | new AtomicInteger(1)
        1          | new AtomicLong(1)
    }

    @Unroll
    def "not.toBeEqualOrGreaterThan - #firstValue.class !>= #secondValue.class should be ok"() {
        when:
        def actualValue = expect(firstValue).not().toBeEqualOrGreaterThan(secondValue).check()

        then:
        actualValue >= firstValue

        where:
        firstValue           | secondValue
        1                    | 2
        1d                   | 2d
        1f                   | 2f
        1L                   | 2L
        new Short((short) 1) | new Short((short) 2)
        new Byte((byte) 1)   | new Byte((byte) 2)
        new BigInteger(1)    | new BigInteger(2)
        new BigDecimal(1)    | new BigDecimal(2)
        new AtomicInteger(1) | new AtomicInteger(2)
        new AtomicLong(1)    | new AtomicLong(2)
    }

    @Unroll
    def "not.toBeEqualOrGreaterThan - different types - #firstValue.class !>= #secondValue.class should throw ObjectNotSameTypePreconditionE"() {
        when:
        expect(firstValue).not().toBeEqualOrGreaterThan(secondValue).check()

        then:
        def error = thrown(ObjectNotSameTypePreconditionException.class)
        error.message == "Expected Number [ ${secondValue} ] must be same type as Number [ ${firstValue} ]" as String

        where:
        firstValue | secondValue
        1          | 1d
        1          | 1f
        1          | 1L
        1          | new Short((short) 1)
        1          | new Byte((byte) 1)
        1          | new BigInteger(1)
        1          | new BigDecimal(1)
        1          | new AtomicInteger(1)
        1          | new AtomicLong(1)
    }

    @Unroll
    def "not.toBeEqualOrGreaterThan - #firstValue.class !>= #secondValue.class should throw NumberEqualOrGreaterThanPreconditionException"() {
        when:
        expect(firstValue).not().toBeEqualOrGreaterThan(secondValue).check()

        then:
        def error = thrown(NumberEqualOrGreaterThanPreconditionException.class)
        error.message == "Number [ ${firstValue} ] must not be equal to or greater than Expected Number [ ${secondValue} ]" as String

        where:
        firstValue           | secondValue
        1                    | 1
        1d                   | 1d
        1f                   | 1f
        1L                   | 1L
        new Short((short) 1) | new Short((short) 1)
        new Byte((byte) 1)   | new Byte((byte) 1)
        new BigInteger(1)    | new BigInteger(1)
        new BigDecimal(1)    | new BigDecimal(1)
        new AtomicInteger(1) | new AtomicInteger(1)
        new AtomicLong(1)    | new AtomicLong(1)
    }
}
