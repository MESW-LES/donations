package les.donations.backendspring.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {
    @ParameterizedTest
    @NullAndEmptySource
    void setFirstNameWithANullAndEmptyValueTest(String firstName){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Person(Long.valueOf("1"), firstName,
                "Smith", "123456789", "address", "person@gmail.com", "password"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setLastNameWithANullAndEmptyValueTest(String lastName){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Person(Long.valueOf("1"), "Anna",
                lastName, "123456789", "address", "person@gmail.com", "password"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setNifWithANullAndEmptyValueTest(String nif){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Person(Long.valueOf("1"), "Anna",
                "Smith", nif, "address", "person@gmail.com", "password"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setAddressWithANullAndEmptyValueTest(String address){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Person(Long.valueOf("1"), "Anna",
                "Smith", "123456789", address, "person@gmail.com", "password"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setEmailWithANullAndEmptyValueTest(String email){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Person(Long.valueOf("1"), "Anna",
                "Smith", "123456789", "address", email, "password"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setPasswordWithANullAndEmptyValueTest(String password){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Person(Long.valueOf("1"), "Anna",
                "Smith", "123456789", "address", "person@gmail.com", password));
    }

    @Test
    void createValidPerson(){
        Person person = new Person(Long.valueOf("1"), "Anna",
                "Smith", "123456789", "address", "person@gmail.com", "password");
        assertEquals("Anna", person.getFirstName());
        assertEquals("Smith", person.getLastName());
        assertEquals("123456789", person.getNif());
        assertEquals("address", person.getAddress());
        assertEquals("person@gmail.com", person.getEmail());
        assertEquals("password", person.getPassword());
    }
}
