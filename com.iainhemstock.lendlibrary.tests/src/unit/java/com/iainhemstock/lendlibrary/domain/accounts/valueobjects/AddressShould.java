package com.iainhemstock.lendlibrary.domain.accounts.valueobjects;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public final class AddressShould {

    @Test
    public void throw_if_builder_receives_null_address1() {
        try {
            new Address.Builder().withAddress1(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void add_address1_to_address_using_builder() {
        Address1 address1 = Mockito.mock(Address1.class);
        final Address address = new Address.Builder().withAddress1(address1).build();
        assertThat(address.getAddress1(), is(equalTo(address1)));
    }

    @Test
    public void throw_if_builder_receives_null_address2() {
        try {
            new Address.Builder().withAddress2(null);
            fail("expected method under test to throw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void add_address2_to_address_using_builder() {
        final Address2 add2 = Mockito.mock(Address2.class);
        final Address address = new Address.Builder().withAddress2(add2).build();
        assertThat(address.getAddress2(), is(equalTo(add2)));
    }

    @Test
    public void throw_if_builder_receives_null_city() {
        try {
            new Address.Builder().withCity(null);
            fail("expected method under test to thorw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void add_city_to_address_using_builder() {
        final City city = Mockito.mock(City.class);
        final Address address = new Address.Builder().withCity(city).build();
        assertThat(address.getCity(), is(equalTo(city)));
    }

    @Test
    public void throw_if_builder_receives_null_county() {
        try {
            new Address.Builder().withCounty(null);
            fail("expected method under test to thorw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void add_county_to_address_using_builder() {
        final County county = Mockito.mock(County.class);
        final Address address = new Address.Builder().withCounty(county).build();
        assertThat(address.getCounty(), is(equalTo(county)));
    }

    @Test
    public void throw_if_builder_receives_null_postcode() {
        try {
            new Address.Builder().withPostcode(null);
            fail("expected method under test to thorw NullPointerException but it didn't");
        }
        catch (NullPointerException ex) {
            assertThat(ex.getMessage(), is(equalTo("argument was null")));
        }
    }

    @Test
    public void add_postcode_to_address_using_builder() {
        final Postcode postcode = Mockito.mock(Postcode.class);
        final Address address = new Address.Builder().withPostcode(postcode).build();
        assertThat(address.getPostcode(), is(equalTo(postcode)));
    }
}
