package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.NumberEqualPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.NumberNotEqualPreconditionException
import com.github.choonchernlim.betterPreconditions.exception.ObjectNotSameTypePreconditionException
import spock.lang.Specification
import spock.lang.Unroll

import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class NumberToBeEqualPreconditionsSpec extends Specification {

    @Unroll
    def "toBeEqual - #firstValue.class (#firstValue) == #secondValue.class (#secondValue) should be ok"() {
        when:
        def actualValue = expect(firstValue).toBeEqual(secondValue).check()

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
    }

    @Unroll
    def "toBeEqual - #firstValue.class (#firstValue) != #secondValue.class (#secondValue) should throw NumberNotEqualPreconditionException"() {
        when:
        expect(firstValue).toBeEqual(secondValue).check()

        then:
        def error = thrown(NumberNotEqualPreconditionException.class)
        error.message == "Number [ ${firstValue} ] must be equal to Expected Number [ ${secondValue} ]" as String

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
    def "toBeEqual - different types - #firstValue.class (#firstValue) == #secondValue.class (#secondValue) should throw ObjectNotSameTypePreconditionException"() {
        when:
        expect(firstValue).toBeEqual(secondValue).check()

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
    def "not.toBeEqual - #firstValue.class (#firstValue) != #secondValue.class (#secondValue) should be ok"() {
        when:
        def actualValue = expect(firstValue).not().toBeEqual(secondValue).check()

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
    def "not.toBeEqual - different types - #firstValue.class (#firstValue) != #secondValue.class (#secondValue) should throw ObjectNotSameTypePreconditionException"() {
        when:
        expect(firstValue).not().toBeEqual(secondValue).check()

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
    def "not.toBeEqual - #firstValue.class (#firstValue) != #secondValue.class (#secondValue) should throw NumberEqualPreconditionException"() {
        when:
        expect(firstValue).not().toBeEqual(secondValue).check()

        then:
        def error = thrown(NumberEqualPreconditionException.class)
        error.message == "Number [ ${firstValue} ] must not be equal to Expected Number [ ${secondValue} ]" as String

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
