package les.donations.backendspring.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeographicAreaTest {
    @ParameterizedTest
    @NullAndEmptySource
    void setCityWithANullAndEmptyValueTest(String city){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GeographicArea(city, "parish", "municipality"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setParishWithANullAndEmptyValueTest(String parish){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GeographicArea("city", parish, "municipality"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setMunicipalityWithANullAndEmptyValueTest(String municipality){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new GeographicArea("city", "parish", municipality));
    }

    @Test
    void createValidGeographicCategory(){
        Category category = new Category("city", "parish", "municipality");
        assertEquals("city", category.getCode());
        assertEquals("parish", category.getName());
        assertEquals("municipality", category.getDescription());
        assertTrue(category.isActive());
    }
}
