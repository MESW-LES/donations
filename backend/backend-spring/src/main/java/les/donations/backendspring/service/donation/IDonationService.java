package les.donations.backendspring.service.donation;

import les.donations.backendspring.dto.DonationDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;

public interface IDonationService {

    /**
     * Method that creates a donation and its donation process
     * @param donationDTO the donation's information
     * @return the inserted donations's information
     * @throws IllegalArgumentException in case any error exists in donation's information
     * @throws NotFoundEntityException in case any entity does not exist (category or address)
     */
    DonationDTO registerDonation(DonationDTO donationDTO) throws IllegalArgumentException, NotFoundEntityException;

}
