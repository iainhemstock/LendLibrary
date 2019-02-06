package com.iainhemstock.lendlibrary.domain.model.accounts;

import java.util.Objects;

public final class ContactDetails {

    private final Telephone telephone;
    private final Email email;

    public ContactDetails(final Builder builder) {
        telephone = builder.telephone;
        this.email = builder.email;
    }

    public Telephone getTelephone() {
        return this.telephone;
    }

    public Email getEmail() {
        return this.email;
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

    public static class Builder {

        private Telephone telephone;
        private Email email;

        public Builder withTelephone(final Telephone telephone) {
            this.telephone = telephone;
            Objects.requireNonNull(this.telephone, "argument was null");
            return this;
        }

        public Builder withEmail(final Email email) {
            this.email = email;
            Objects.requireNonNull(this.email, "argument was null");
            return this;
        }

        public ContactDetails build() {
            return new ContactDetails(this);
        }
    }
}
