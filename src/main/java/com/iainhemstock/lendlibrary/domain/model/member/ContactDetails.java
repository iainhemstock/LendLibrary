package com.iainhemstock.lendlibrary.domain.model.member;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public final class ContactDetails {

    private final Telephone telephone;
    private final Email email;

    public ContactDetails(final Telephone telephone, final Email email) {
        this.telephone = telephone;
        this.email = email;
        requireNonNull(this.telephone, "Telephone is required");
        requireNonNull(this.email, "Email is required");
    }

    public Telephone getTelephone() {
        return this.telephone;
    }

    public Email getEmail() {
        return this.email;
    }

    @Override
    public String toString() {
        return "ContactDetails{" +
                "telephone=" + telephone +
                ", email=" + email +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDetails that = (ContactDetails) o;
        return Objects.equals(telephone, that.telephone) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telephone, email);
    }
}
