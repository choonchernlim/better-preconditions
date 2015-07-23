# Better Preconditions [![Build Status](https://travis-ci.org/choonchernlim/better-preconditions.svg?branch=master)](https://travis-ci.org/choonchernlim/better-preconditions)

Inspired by Guava Preconditions and Jasmine, Better Preconditions is a set of Java APIs for creating fluent, readable and testable Java preconditions.

## Maven Dependency

```xml
<dependency>
  <groupId>com.github.choonchernlim</groupId>
  <artifactId>better-preconditions</artifactId>
  <version>0.1.1</version>
</dependency>
```

## Feature Highlights

* Every precondition can be negated with `not()`:-

```java
expect(name, "Name").not().toBeBlank().check();
```

* Labels are optional (although they would provide more helpful error messages):-

```java
// this works
expect(endDate, "End Date").toBeAfter(startDate, "Start Date").check();

// this works too
expect(endDate).toBeAfter(startDate).check();
```

* Preconditions can be chained:-

```java
// age cannot be null and must be between 50 and 100, but not 55
expect(age, "Age")
    .not().toBeNull()
    .toBeEqualOrGreaterThan(50)
    .toBeEqualOrLessThan(100)
    .not().toBeEqual(55)
    .check();
```

* Each precondition throws a specific exception, which allows the preconditions to be easily unit tested:-

```java
// if null, throw ObjectNullPreconditionException
// if less than 50, throw NumberNotEqualOrGreaterThanPreconditionException
// if more than 100, throw NumberNotEqualOrLessThanPreconditionException
expect(age, "Age")
    .not().toBeNull() 
    .toBeEqualOrGreaterThan(50, "Lower Age Limit") 
    .toBeEqualOrLessThan(100, "Upper Age Limit")
    .check();
```

* Each thrown exception contains helpful error message for debugging purpose:-

```java
// if null, error message is "Age [ null ] must not be null"
// if less than 50, error message is "Age [ X ] must be equal to or greater than Lower Age Limit [ 50 ]"
// if more than 100, error message is "Age [ X ] must be equal to or less than Upper Age Limit [ 100 ]"
expect(age, "Age")
    .not().toBeNull() 
    .toBeEqualOrGreaterThan(50, "Lower Age Limit") 
    .toBeEqualOrLessThan(100, "Upper Age Limit")
    .check();
```

* Highly extensible. You can provide your own custom matcher if needed:-

```java
// name must not be blank and must start with "Cluster MEOW"
expect(name, "Name")
    .not().toBeBlank()
    .customMatcher(new Matcher<String>() {
        @Override
        public boolean match(String value, String label) {
            return name.startsWith("Cluster MEOW");
        }

        @Override
        public PreconditionException getException(String value, String label) {
            // if customMatcher(..) fails, throw this exception
            return new PreconditionException("Name must start be 'Cluster Meow'");
        }

        @Override
        public PreconditionException getNegatedException(String value, String label) {
            // if not().customMatcher(..) fails, throw this exception
            return new PreconditionException("Name must not start be 'Cluster Meow'");
        }
    })
    .check();
```

## Real World Example

Let's compare between Guava Preconditions and Better Preconditions.

### Guava Preconditions

```java
public void compute(final String name, final Integer age, final LocalDate registrationDate) {
    // if blank, throw IllegalArgumentException
    checkArgument(!nullToEmpty(name).trim().isEmpty(), "Name cannot be blank");

    // if null, throw NullPointerException
    checkNotNull(age, "Age cannot be null");

    // if less than 50 or more than 100, throw IllegalArgumentException
    checkArgument(age >= 50 && age <= 100, "Age must be between 50 and 100");

    // if null, throw NullPointerException
    checkNotNull(registrationDate, "Registration date cannot be null");

    final LocalDate dateToday = LocalDate.now();

    // if earlier than today, throw IllegalArgumentException
    checkArgument(registrationDate.isEqual(dateToday) || registrationDate.isAfter(dateToday),
                  "Registration date must be equal to or after today");
}
```

* Typing the error message for each precondition makes the code very messy. It gets even messier if we want to include the value(s) in the error message.
* Typically, most preconditions would throw either `IllegalArgumentException` or `IllegalArgumentException`, which makes the API a little confusing to unit test.
* If a method has a lot of preconditions, it becomes very unreadable. 

### Better Preconditions

```java
public void compute(final String name, final Integer age, final LocalDate registrationDate) {
    // if blank, throw StringBlankPreconditionException
    expect(name, "Name")
            .not().toBeBlank()
            .check();

    // if null, throw ObjectNullPreconditionException
    // if less than 50, throw NumberNotEqualOrGreaterThanPreconditionException
    // if more than 100, throw NumberNotEqualOrLessThanPreconditionException
    expect(age, "Age")
            .not().toBeNull()
            .toBeEqualOrGreaterThan(50, "Lower Age Limit")
            .toBeEqualOrLessThan(100, "Upper Age Limit")
            .check();

    // if null, throw ObjectNullPreconditionException
    // if earlier than today, throw JodaTimeNotEqualOrAfterPreconditionException
    expect(registrationDate, "Registration Date")
            .not().toBeNull()
            .toBeEqualOrAfter(LocalDate.now(), "Today's Date")
            .check();
}
```

* Short and succinct. Type only what's needed.
* If one of the preconditions fails, a specific exception is thrown, which makes the API much easier to unit test.
* Error message includes value(s), for example, if the age is less than 50, the following error message is produced: 
    `Age [ 39 ] must be equal to or greater than Lower Age Limit [ 50 ]`.
* Method chaining makes the code very readable. 

## Better Preconditions API

### Getting Started

* Always begin with `expect(var)` and end with `check()`.
* Every precondition can be negated with `not()` prefix.
* When chaining preconditions, the order of the preconditions is very important.

### Object

These preconditions apply to all variable types.

* `toBeEqual(expectedValue)`
* `toBeNull() `
* `toBeSameType(expectedValue)`

### Boolean

* `toBeTrue()`

### Collection

* `toBeEmpty()`

### Joda Time

These preconditions can be used on all `BaseLocal` subclasses: `LocalDate`, `LocalDateTime` and `LocalTime`.

* `toBeAfter(expectedValue)`
* `toBeBefore(expectedValue)`
* `toBeEqualOrAfter(expectedValue)`
* `toBeEqualOrBefore(expectedValue)`
          
### Number
          
These preconditions can be used on all `Number` subclasses: `AtomicInteger`, `AtomicLong`, `BigDecimal`, `BigInteger`, `Byte`, `Double`, `Float`, `Integer`, `Long`, `Short`.
              
* `toBeEqualOrGreaterThan(expectedValue)`
* `toBeEqualOrLessThan(expectedValue)`
* `toBeGreaterThan(expectedValue)`
* `toBeLessThan(expectedValue)`
                  
### String

* `toBeBlank()`

## Helpful Links

* [API Documentation](http://choonchernlim.github.io/better-preconditions/apidocs/index.html)
* [Test Cases](http://choonchernlim.github.io/better-preconditions/surefire-report.html) 
* [Test Coverage](http://choonchernlim.github.io/better-preconditions/jacoco/index.html)




