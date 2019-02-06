package com.iainhemstock.lendlibrary.domain.model.accounts;

import java.util.Objects;

public final class Address {

    private final Address1 address1;
    private final Address2 address2;
    private final City city;
    private final County county;
    private final Postcode postcode;

    private Address(final Builder builder) {
        this.address1 = builder.address1;
        this.address2 = builder.address2;
        this.city = builder.city;
        this.county = builder.county;
        this.postcode = builder.postcode;
    }

    public Address1 getAddress1() {
        return this.address1;
    }

    public Address2 getAddress2() {
        return this.address2;
    }

    public City getCity() {
        return this.city;
    }

    public County getCounty() {
        return this.county;
    }

    public Postcode getPostcode() {
        return this.postcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(address1, address.address1) &&
                Objects.equals(address2, address.address2) &&
                Objects.equals(city, address.city) &&
                Objects.equals(county, address.county) &&
                Objects.equals(postcode, address.postcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address1, address2, city, county, postcode);
    }

    public static class Builder {

        private static final String NULL_ARGUMENT_MESSAGE = "argument was null";
        private Address1 address1;
        private Address2 address2;
        private City city;
        private County county;
        private Postcode postcode;

        public Builder withAddress1(final Address1 address1) {
            this.address1 = address1;
            Objects.requireNonNull(this.address1, NULL_ARGUMENT_MESSAGE);
            return this;
        }

        public Builder withAddress2(final Address2 address2) {
            this.address2 = address2;
            Objects.requireNonNull(this.address2, NULL_ARGUMENT_MESSAGE);
            return this;
        }

        public Builder withCity(final City city) {
            this.city = city;
            Objects.requireNonNull(this.city, NULL_ARGUMENT_MESSAGE);
            return this;
        }

        public Builder withCounty(final County county) {
            this.county = county;
            Objects.requireNonNull(this.county, "argument was null");
            return this;
        }

        public Builder withPostcode(final Postcode postcode) {
            this.postcode = postcode;
            Objects.requireNonNull(this.postcode, "argument was null");
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
