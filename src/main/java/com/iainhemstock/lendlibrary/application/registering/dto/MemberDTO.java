package com.iainhemstock.lendlibrary.application.registering.dto;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class MemberDTO {
    private final String memberId;
    private final String firstName;
    private final String lastName;
    private final String addressLine1;
    private final String addressLine2;
    private final String city;
    private final String county;
    private final String postcode;
    private final String contactNumber;
    private final String email;

    public MemberDTO(String memberId,
                     String firstName, String lastName,
                     String addressLine1, String addressLine2, String city, String county, String postcode,
                     String contactNumber, String email) {

        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.county = county;
        this.postcode = postcode;
        this.contactNumber = contactNumber;
        this.email = email;
        requireNonNull(this.firstName, "First name is required");
        requireNonNull(this.lastName, "Last name is required");
        requireNonNull(this.lastName, "Address line 1 is required");
        requireNonNull(this.lastName, "Address line 2 is required");
        requireNonNull(this.lastName, "City is required is required");
        requireNonNull(this.lastName, "County is required is required");
        requireNonNull(this.lastName, "Postcode is required");
        requireNonNull(this.lastName, "Contact number is required");
        requireNonNull(this.lastName, "Email is required");
    }

    public String getMemberId() {
        return memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "memberId='" + memberId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", postcode='" + postcode + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null /*|| getClass() != o.getClass()*/)
            return false;
        MemberDTO that = (MemberDTO) o;
        return Objects.equals(memberId, that.memberId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(addressLine1, that.addressLine1) &&
                Objects.equals(addressLine2, that.addressLine2) &&
                Objects.equals(city, that.city) &&
                Objects.equals(county, that.county) &&
                Objects.equals(postcode, that.postcode) &&
                Objects.equals(contactNumber, that.contactNumber) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                memberId,
                firstName,
                lastName,
                addressLine1,
                addressLine2,
                city,
                county,
                postcode,
                contactNumber,
                email);
    }
}
