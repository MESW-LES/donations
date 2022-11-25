package les.donations.backendspring.service.donation;

import les.donations.backendspring.dto.DonationDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;

import java.util.List;

public interface IDonationService {

    /**
     * Method that creates a donation and its donation process
     * @param donationDTO the donation's information
     * @return the inserted donations's information
     * @throws IllegalArgumentException in case any error exists in donation's information
     * @throws NotFoundEntityException in case any entity does not exist (category or address)
     */
    DonationDTO registerDonation(DonationDTO donationDTO) throws IllegalArgumentException, NotFoundEntityException;

    /**
     * Method that gets donations by donor id
     * @param id the donor id
     * @return the list of donations made by provided donor
     */
    List<DonationDTO> getDonations(Long id);

    /**
     * Method that gets a specific donations
     * @param id the donation id
     * @return the donation
     */
    DonationDTO getDonation(Long id);

    /**
     * Method that removes specific donation
     * @param id the donation id
     */
    void deleteDonation(Long id);
}
