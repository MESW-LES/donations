package les.donations.backendspring.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DonationTest {
    @ParameterizedTest
    @NullAndEmptySource
    void setTitleWithNullAndEmptyValueTest(String title){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Donation(title, "description"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setDescriptionWithNullAndEmptyValueTest(String description){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Donation("title", description));
    }

    @Test
    void createCorrectDonationTest(){
        Donation donation = new Donation("title", "description");
        assertEquals("title", donation.getTitle());
        assertEquals("description", donation.getDescription());
        assertTrue(donation.isActive());
        assertEquals(0, donation.getCategories().size());
        assertEquals(0, donation.getDonationImages().size());
    }
}
