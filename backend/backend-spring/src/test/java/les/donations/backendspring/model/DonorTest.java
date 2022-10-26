package les.donations.backendspring.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class DonorTest {

    @ParameterizedTest
    @NullAndEmptySource
    void setFirstNameWithANullAndEmptyValueTest(String firstName){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Donor(Long.valueOf("1"), firstName, "Last name test",
                "123456789", "Address Test","email@gmail.com", "password"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setLastNameWithANullAndEmptyValueTest(String lastName){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Donor(Long.valueOf("1"), "First Name", lastName,
                "123456789", "Address Test","email@gmail.com", "password"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setNifWithANullAndEmptyValueTest(String nif){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Donor(Long.valueOf("1"), "First Name", "Last name test",
                nif, "Address Test","email@gmail.com", "password"));
    }

    @Test
    void setInvalidNif() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Donor(Long.valueOf("1"), "First Name", "Last name test",
                "invalid nif", "Address Test","email@gmail.com", "password"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setAddressWithANullAndEmptyValueTest(String address){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Donor(Long.valueOf("1"), "First Name", "Last name test",
                "123456789", address,"email@gmail.com", "password"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setEmailWithANullAndEmptyValueTest(String email){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Donor(Long.valueOf("1"), "First Name", "Last name test",
                "123456789", "Address Test", email, "password"));
    }

    @Test
    void setInvalidEmail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Donor(Long.valueOf("1"), "First Name", "Last name test",
                "123456789", "Address Test","invalid_email.com", "password"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setPasswordWithANullAndEmptyValueTest(String password){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Donor(Long.valueOf("1"), "First Name", "Last name test",
                "123456789", "Address Test","email@gmail.com", password));
    }

    @Test
    void createValidCategory(){
        Donor donor = new Donor(Long.valueOf("1"), "First Name", "Last name test",
                "123456789", "Address Test","email@gmail.com", "password");
        assertEquals(Long.valueOf("1"), donor.getId());
        assertEquals("First Name", donor.getFirstName());
        assertEquals("Last name test", donor.getLastName());
        assertEquals("123456789", donor.getNif());
        assertEquals("Address Test", donor.getAddress());
        assertEquals("email@gmail.com", donor.getEmail());
        assertEquals("password", donor.getPassword());
    }

}
