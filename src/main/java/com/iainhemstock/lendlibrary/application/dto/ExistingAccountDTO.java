package com.iainhemstock.lendlibrary.application.dto;

import java.util.Objects;

public class ExistingAccountDTO extends NewAccountDTO {

    private final String accountId;

    public ExistingAccountDTO(String accountId,
                              String firstName, String lastName,
                              String address1, String address2, String city, String county, String postcode,
                              String contactNumber, String email) {

        super(firstName, lastName, address1, address2, city, county, postcode, contactNumber, email);
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    @Override
    public String toString() {
        return "ExistingAccountDTO{" +
                "accountId='" + accountId + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExistingAccountDTO that = (ExistingAccountDTO) o;
        return Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }
}
