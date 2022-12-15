package les.donations.backendspring.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DonationProcessTest {

    @Test
    void toRequestedStatusNotEditableTest() {
        Donation donation = new Donation("title", "desc");
        DonationProcess process = new DonationProcess(donation);
        process.setStatus(Status.REQUESTED);
        Donee donee = new Donee();
        Assertions.assertThrows(IllegalArgumentException.class, () -> process.toRequestedStatus(donee));
    }

    @Test
    void toRequestedStatusCorrectTest() {
        Donation donation = new Donation("title", "desc");
        DonationProcess process = new DonationProcess(donation);
        Donee donee = new Donee();
        process.toRequestedStatus(donee);

        Assertions.assertEquals(Status.REQUESTED, process.getStatus());
        Assertions.assertEquals(donee, process.getDonee());
    }

    @Test
    void decideIllegalStatusTest() {
        Donation donation = new Donation("title", "desc");
        DonationProcess process = new DonationProcess(donation);

        Assertions.assertThrows(IllegalArgumentException.class, () -> process.decide(false));
    }

    @Test
    void decideFalseDecisionTest() {
        Donation donation = new Donation("title", "desc");
        Donee donee = new Donee();
        DonationProcess process = new DonationProcess(donation);
        process.setStatus(Status.REQUESTED);
        process.setDonee(donee);
        process.decide(false);

        Assertions.assertEquals(Status.CREATED, process.getStatus());
        Assertions.assertNull(process.getDonee());
    }

    @Test
    void decideTrueDecisionTest() {
        Donation donation = new Donation("title", "desc");
        Donee donee = new Donee();
        DonationProcess process = new DonationProcess(donation);
        process.setStatus(Status.REQUESTED);
        process.setDonee(donee);
        process.decide(true);

        Assertions.assertEquals(Status.ONGOING, process.getStatus());
    }
}
