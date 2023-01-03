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
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Person(firstName,
                "Smith", "123456789", "person@gmail.com"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setLastNameWithANullAndEmptyValueTest(String lastName){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Person("Anna",
                lastName, "123456789", "person@gmail.com"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setNifWithANullAndEmptyValueTest(String nif){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Person("Anna",
                "Smith", nif, "person@gmail.com"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setEmailWithANullAndEmptyValueTest(String email){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Person("Anna",
                "Smith", "123456789",  email));
    }

    @Test
    void createValidPerson(){
        Person person = new Person("Anna",
                "Smith", "123456789", "person@gmail.com");
        assertEquals("Anna", person.getFirstName());
        assertEquals("Smith", person.getLastName());
        assertEquals("123456789", person.getNif());
        assertEquals("person@gmail.com", person.getEmail());
    }
}
