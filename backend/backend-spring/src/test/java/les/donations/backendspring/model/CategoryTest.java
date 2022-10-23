package les.donations.backendspring.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @ParameterizedTest
    @NullAndEmptySource
    void setCodeWithANullAndEmptyValueTest(String code){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Category(code, "Name Test", "Description Test"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setNameWithANullAndEmptyValueTest(String name){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Category("Code Test", name, "Description Test"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setDescriptionWithANullAndEmptyValueTest(String description){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Category("Code Test", "Name Test", description));
    }

    @Test
    void createValidCategory(){
        Category category = new Category("Code Test", "Name Test", "Description Test");
        assertEquals("Code Test", category.getCode());
        assertEquals("Name Test", category.getName());
        assertEquals("Description Test", category.getDescription());
        assertTrue(category.isActive());
    }

}