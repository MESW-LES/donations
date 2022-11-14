package les.donations.backendspring.service.donation;

import les.donations.backendspring.dto.CompanyDTO;
import les.donations.backendspring.dto.DonationDTO;
import les.donations.backendspring.model.Company;
import les.donations.backendspring.model.Donation;

import java.io.IOException;
import java.util.List;

public interface IDonationService {

    DonationDTO createDonation(DonationDTO donationDTO) throws IllegalArgumentException, IOException;

    List<DonationDTO> getDonations(Long id);

    DonationDTO getDonation(Long id);
}
