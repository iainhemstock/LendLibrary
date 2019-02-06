package com.iainhemstock.lendlibrary.domain.model.accounts;

import java.util.Objects;

public final class PersonProfile {
    private final FullName fullName;
    private final Address address;
    private final ContactDetails contactDetails;

    private PersonProfile(final Builder builder) {
        this.fullName = builder.fullName;
        this.address = builder.address;
        this.contactDetails = builder.contactDetails;
    }

    public PersonProfile(final PersonProfile copy) {
        this.fullName = copy.fullName;
        this.address = copy.address;
        this.contactDetails = copy.contactDetails;
    }

    public FullName getFullName() {
        return this.fullName;
    }

    public Address getAddress() {
        return this.address;
    }

    public ContactDetails getContactDetails() {
        return this.contactDetails;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonProfile that = (PersonProfile) o;
        return Objects.equals(fullName, that.fullName) &&
                Objects.equals(address, that.address) &&
                Objects.equals(contactDetails, that.contactDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, address, contactDetails);
    }

    public static class Builder {

        public static final String NULL_ARG_MSG = "argument was null";

        private FullName fullName;
        private Address address;
        private ContactDetails contactDetails;

        public Builder withFullName(final FullName fullName) {
            this.fullName = fullName;
            Objects.requireNonNull(this.fullName, NULL_ARG_MSG);
            return this;
        }

        public Builder withAddress(final Address address) {
            this.address = address;
            Objects.requireNonNull(this.address, NULL_ARG_MSG);
            return this;
        }

        public Builder withContactDetails(final ContactDetails contactDetails) {
            this.contactDetails = contactDetails;
            Objects.requireNonNull(this.contactDetails, NULL_ARG_MSG);
            return this;
        }

        public PersonProfile build() {
            return new PersonProfile(this);
        }
    }
}
