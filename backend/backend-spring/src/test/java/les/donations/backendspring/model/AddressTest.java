package les.donations.backendspring.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @ParameterizedTest
    @NullAndEmptySource
    void setStreetWithANullAndEmptyValueTest(String street){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Address(street, 2L, "123456"));
    }

    @Test
    void setHouseNumberWithANullAndEmptyValueTest(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Address("street", -1L, "123456"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setPostalCodeWithANullAndEmptyValueTest(String postalCode){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Address("street", 2L, postalCode));
    }

    @Test
    void createValidCategory(){
        Address address = new Address("street", 1L, "123456");
        assertEquals("street", address.getStreet());
        assertEquals(1L, address.getHouseNumber());
        assertEquals("123456", address.getPostalCode());
    }

}