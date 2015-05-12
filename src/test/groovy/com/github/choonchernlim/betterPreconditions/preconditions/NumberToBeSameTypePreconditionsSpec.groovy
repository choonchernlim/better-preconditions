package com.github.choonchernlim.betterPreconditions.preconditions

import com.github.choonchernlim.betterPreconditions.exception.ObjectNotSameTypePreconditionException
import com.github.choonchernlim.betterPreconditions.exception.ObjectSameTypePreconditionException
import spock.lang.Specification
import spock.lang.Unroll

import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong

import static com.github.choonchernlim.betterPreconditions.preconditions.PreconditionFactory.expect

class NumberToBeSameTypePreconditionsSpec extends Specification {

    @Unroll
    def "toBeSameType - #firstValue.class == #secondValue.class should be ok"() {
        when:
        def actualValue = expect(firstValue).toBeSameType(secondValue).check()

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
    def "toBeSameType - #firstValue.class == #secondValue.class should throw ObjectNotSameTypePreconditionException"() {
        when:
        expect(firstValue).toBeSameType(secondValue).check()

        then:
        def error = thrown(ObjectNotSameTypePreconditionException.class)
        error.message == "Number [ ${firstValue} ] must be same type as Expected Value [ ${secondValue} ]" as String

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
    def "not.toBeSameType - #firstValue.class == #secondValue.class should be ok"() {
        when:
        def actualValue = expect(firstValue).not().toBeSameType(secondValue).check()

        then:
        actualValue == firstValue

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
    def "not.toBeSameType - #firstValue.class == #secondValue.class should throw ObjectSameTypePreconditionException"() {
        when:
        expect(firstValue).not().toBeSameType(secondValue).check()

        then:
        def error = thrown(ObjectSameTypePreconditionException.class)
        error.message == "Number [ ${firstValue} ] must not be same type as Expected Value [ ${secondValue} ]" as String

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
        new AtomicInteger(1) | new AtomicInteger(1)
        new AtomicLong(1)    | new AtomicLong(1)
    }


}
