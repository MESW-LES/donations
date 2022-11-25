package les.donations.backendspring.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    @ParameterizedTest
    @NullAndEmptySource
    void setNameWithNullAndEmptyValueTest(String name){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Company(name, "description", "taxNumber", 912345678L));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setDescriptionWithNullAndEmptyValueTest(String description){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Company("name", description, "taxNumber", 912345678L));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setTaxNumberWithNullAndEmptyValueTest(String taxNumber){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Company("name", "description", taxNumber, 912345678L));
    }

    @Test
    void setPhoneWithNullValueTest(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Company("name", "description", "taxNumber", null));
    }

    @Test
    void setPhoneWithNoNineDigitsValueTest(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Company("name", "description", "taxNumber", 123L));
    }

}