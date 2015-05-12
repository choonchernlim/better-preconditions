package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.NumberGreaterThanPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.NumberNotGreaterThanPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.ObjectNotSameTypePreconditionException
import spock.lang.Specification
import spock.lang.Unroll

import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class NumberToBeGreaterThanPreconditionsSpec extends Specification {

    @Unroll
    def "toBeGreaterThan - #firstValue.class (#firstValue) > #secondValue.class (#secondValue) should be ok"() {
        when:
        def actualValue = expect(firstValue).toBeGreaterThan(secondValue).check()

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
    def "toBeGreaterThan - #firstValue.class (#firstValue) != #secondValue.class (#secondValue) should throw NumberNotGreaterThanPreconditionException"() {
        when:
        expect(firstValue).toBeGreaterThan(secondValue).check()

        then:
        def error = thrown(NumberNotGreaterThanPreconditionException.class)
        error.message == "Number [ ${firstValue} ] must be greater than Expected Number [ ${secondValue} ]" as String

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
    def "toBeGreaterThan - different types - #firstValue.class (#firstValue) > #secondValue.class (#secondValue) should throw ObjectNotSameTypePreconditionException"() {
        when:
        expect(firstValue).toBeGreaterThan(secondValue).check()

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
    def "not.toBeGreaterThan - #firstValue.class (#firstValue) !> #secondValue.class (#secondValue) should be ok"() {
        when:
        def actualValue = expect(firstValue).not().toBeGreaterThan(secondValue).check()

        then:
        actualValue == firstValue

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
    def "not.toBeGreaterThan - different types - #firstValue.class (#firstValue) !> #secondValue.class (#secondValue) should throw ObjectNotSameTypePrecondition"() {
        when:
        expect(firstValue).not().toBeGreaterThan(secondValue).check()

        then:
        def error = thrown(ObjectNotSameTypePreconditionException.class)
        error.message == "Expected Number [ ${secondValue} ] must be same type as Number [ ${firstValue} ]" as String

        where:
        firstValue | secondValue
        1          | 2d
        1          | 2f
        1          | 2L
        1          | new Short((short) 2)
        1          | new Byte((byte) 2)
        1          | new BigInteger(2)
        1          | new BigDecimal(2)
        1          | new AtomicInteger(2)
        1          | new AtomicLong(2)
    }

    @Unroll
    def "not.toBeGreaterThan - #firstValue.class (#firstValue) !> #secondValue.class (#secondValue) should throw NumberGreaterThanPreconditionException"() {
        when:
        expect(firstValue).not().toBeGreaterThan(secondValue).check()

        then:
        def error = thrown(NumberGreaterThanPreconditionException.class)
        error.message == "Number [ ${firstValue} ] must not be greater than Expected Number [ ${secondValue} ]" as String

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
}
