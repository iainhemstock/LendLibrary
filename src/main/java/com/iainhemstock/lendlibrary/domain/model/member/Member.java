package com.iainhemstock.lendlibrary.domain.model.member;

import com.iainhemstock.lendlibrary.domain.shared.Entity;
import com.iainhemstock.lendlibrary.domain.shared.Id;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Member implements Entity {

    private MemberId memberId;
    private FullName fullName;
    private Address address;
    private ContactDetails contactDetails;

    public Member(final MemberId memberId, final FullName fullName,
                  final Address address, final ContactDetails contactDetails) {
        this.memberId = memberId;
        this.fullName = fullName;
        this.address = address;
        this.contactDetails = contactDetails;
        requireNonNull(this.memberId, "Member id is required");
        requireNonNull(this.fullName, "Full name is required");
        requireNonNull(this.address, "Address is required");
        requireNonNull(this.contactDetails, "Contact details are required");
    }

    public Member(Member copy) {
        this.memberId = copy.memberId;
        this.fullName = copy.fullName;
        this.address = copy.address;
        this.contactDetails = copy.contactDetails;
    }

    @Override
    public MemberId getId() {
        return this.memberId;
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

    public void renameTo(FullName updatedName) {
        this.fullName = updatedName;
    }

    public void relocateTo(final Address relocatedAddress) {
        this.address = relocatedAddress;
    }

    public void updateContactDetailsTo(ContactDetails updatedContactDetails) {
        this.contactDetails = updatedContactDetails;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", fullName=" + fullName +
                ", address=" + address +
                ", contactDetails=" + contactDetails +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        return Objects.equals(memberId, ((Member) o).memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, fullName, address, contactDetails);
    }
}
