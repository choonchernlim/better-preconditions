package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.NumberEqualOrLessThanPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.NumberNotEqualOrLessThanPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.ObjectNotSameTypePreconditionException
import spock.lang.Specification
import spock.lang.Unroll

import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class NumberToBeEqualOrLessThanPreconditionsSpec extends Specification {

    @Unroll
    def "toBeEqualOrLessThan - #firstValue.class (#firstValue) >= #secondValue.class (#secondValue) should be ok"() {
        when:
        def actualValue = expect(firstValue).toBeEqualOrLessThan(secondValue).check()

        then:
        actualValue == firstValue

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
    def "toBeEqualOrLessThan - #firstValue.class (#firstValue) != #secondValue.class (#secondValue) should throw NumberNotEqualOrLessThanPreconditionException"() {
        when:
        expect(firstValue).toBeEqualOrLessThan(secondValue).check()

        then:
        def error = thrown(NumberNotEqualOrLessThanPreconditionException.class)
        error.message == "Number [ ${firstValue} ] must be equal to or less than Expected Number [ ${secondValue} ]" as String

        where:
        firstValue           | secondValue
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
    def "toBeEqualOrLessThan - different types - #firstValue.class (#firstValue) >= #secondValue.class (#secondValue) should throw ObjectNotSameTypePreconditionException"() {
        when:
        expect(firstValue).toBeEqualOrLessThan(secondValue).check()

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
        0          | 1d
        0          | 1f
        0          | 1L
        0          | new Short((short) 1)
        0          | new Byte((byte) 1)
        0          | new BigInteger(1)
        0          | new BigDecimal(1)
        0          | new AtomicInteger(1)
        0          | new AtomicLong(1)
    }

    @Unroll
    def "not.toBeEqualOrLessThan - #firstValue.class (#firstValue) !>= #secondValue.class (#secondValue) should be ok"() {
        when:
        def actualValue = expect(firstValue).not().toBeEqualOrLessThan(secondValue).check()

        then:
        actualValue == firstValue

        where:
        firstValue           | secondValue
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
    def "not.toBeEqualOrLessThan - different types - #firstValue.class (#firstValue) !>= #secondValue.class (#secondValue) should throw ObjectNotSameTypePrecondition"() {
        when:
        expect(firstValue).not().toBeEqualOrLessThan(secondValue).check()

        then:
        def error = thrown(ObjectNotSameTypePreconditionException.class)
        error.message == "Expected Number [ ${secondValue} ] must be same type as Number [ ${firstValue} ]" as String

        where:
        firstValue | secondValue
        2          | 1d
        2          | 1f
        2          | 1L
        2          | new Short((short) 1)
        2          | new Byte((byte) 1)
        2          | new BigInteger(1)
        2          | new BigDecimal(1)
        2          | new AtomicInteger(1)
        2          | new AtomicLong(1)
    }

    @Unroll
    def "not.toBeEqualOrLessThan - #firstValue.class (#firstValue) !>= #secondValue.class (#secondValue) should throw NumberEqualOrLessThanPreconditionException"() {
        when:
        expect(firstValue).not().toBeEqualOrLessThan(secondValue).check()

        then:
        def error = thrown(NumberEqualOrLessThanPreconditionException.class)
        error.message == "Number [ ${firstValue} ] must not be equal to or less than Expected Number [ ${secondValue} ]" as String

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
