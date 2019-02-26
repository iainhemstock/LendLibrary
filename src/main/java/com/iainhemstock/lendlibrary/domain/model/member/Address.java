package com.iainhemstock.lendlibrary.domain.model.member;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public final class Address {

    private final AddressLine1 addressLine1;
    private final AddressLine2 addressLine2;
    private final City city;
    private final County county;
    private final Postcode postcode;

    public Address(final AddressLine1 addressLine1, final AddressLine2 addressLine2,
                   final City city, final County county, final Postcode postcode) {

        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.county = county;
        this.postcode = postcode;
        requireNonNull(this.addressLine1, "Address line 1 is required");
        requireNonNull(this.addressLine2, "Address line 2 is required");
        requireNonNull(this.city, "City is required");
        requireNonNull(this.county, "County is required");
        requireNonNull(this.postcode, "Postcode is required");
    }

    public AddressLine1 getAddressLine1() {
        return this.addressLine1;
    }

    public AddressLine2 getAddressLine2() {
        return this.addressLine2;
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
    public String toString() {
        return "Address{" +
                "addressLine1=" + addressLine1 +
                ", addressLine2=" + addressLine2 +
                ", city=" + city +
                ", county=" + county +
                ", postcode=" + postcode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(addressLine1, address.addressLine1) &&
                Objects.equals(addressLine2, address.addressLine2) &&
                Objects.equals(city, address.city) &&
                Objects.equals(county, address.county) &&
                Objects.equals(postcode, address.postcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressLine1, addressLine2, city, county, postcode);
    }
}
