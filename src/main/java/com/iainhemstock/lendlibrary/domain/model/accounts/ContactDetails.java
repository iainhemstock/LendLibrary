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
