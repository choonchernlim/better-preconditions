# Better Preconditions [![Build Status](https://travis-ci.org/choonchernlim/better-preconditions.svg?branch=master)](https://travis-ci.org/choonchernlim/better-preconditions)

Inspired by Guava Preconditions and Jasmine (a behavior-driven development framework for testing JavaScript code), 
Better Preconditions is a set of readable and testable Java preconditions.

## Why?

### Guava Preconditions Approach

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

### Better Preconditions Approach

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
    expect(registrationDate, "Restriction Date")
            .not().toBeNull()
            .toBeEqualOrAfter(LocalDate.now(), "Today's Date")
            .check();
}
```

* Short and succinct. Type only what's needed.
* If one of the precondition, a specific exception is thrown, which makes the API much easier to unit test.
* Error message includes value(s), for example, if the age is less than 50, the following error message is produced: 
    `Age [ 39 ] must be equal to or greater than Lower Age Limit [ 50 ]`.
* Method chaining makes the code very readable. 

## Maven Dependency

```xml
<dependency>
  <groupId>com.github.choonchernlim</groupId>
  <artifactId>better-preconditions</artifactId>
  <version>0.1.0</version>
</dependency>
```