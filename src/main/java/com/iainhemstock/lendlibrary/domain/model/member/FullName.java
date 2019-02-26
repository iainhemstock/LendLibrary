package com.iainhemstock.lendlibrary.domain.model.member;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public final class FullName {

    private final FirstName firstName;
    private final LastName lastName;

    public FullName(final FirstName firstName, final LastName lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        requireNonNull(this.firstName, "First name is required");
        requireNonNull(this.lastName, "Last name is required");
    }

    public FirstName getFirstName() {
        return this.firstName;
    }

    public LastName getLastName() {
        return this.lastName;
    }

    @Override
    public String toString() {
        return "FullName{" +
                "firstName=" + firstName +
                ", lastName=" + lastName +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullName fullName = (FullName) o;
        return Objects.equals(firstName, fullName.firstName) &&
                Objects.equals(lastName, fullName.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
