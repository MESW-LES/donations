package les.donations.backendspring.repository.donation;

import les.donations.backendspring.model.Donation;

import java.util.List;

public interface DonationDaoCustom {

    List<Donation> getDonations(Integer donationProcessStatus);
}
