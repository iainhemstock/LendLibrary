package com.iainhemstock.lendlibrary.domain.model.accounts;

import java.util.Objects;

public final class FullName {

    private final FirstName firstName;
    private final LastName lastName;

    private FullName(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

    public FirstName getFirstName() {
        return this.firstName;
    }

    public LastName getLastName() {
        return this.lastName;
    }

    public static class Builder {
        public static final String NULL_ARG_MSG = "argument was null";
        private FirstName firstName;
        private LastName lastName;

        public Builder withFirstName(final FirstName firstName) {
            this.firstName = firstName;
            Objects.requireNonNull(this.firstName, NULL_ARG_MSG);
            return this;
        }

        public Builder withLastName(final LastName lastName) {
            this.lastName = lastName;
            Objects.requireNonNull(this.lastName, NULL_ARG_MSG);
            return this;
        }

        public FullName build() {
            return new FullName(this);
        }
    }
}
