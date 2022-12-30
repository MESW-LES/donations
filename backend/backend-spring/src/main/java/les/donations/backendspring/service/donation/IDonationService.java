package les.donations.backendspring.service.donation;

import les.donations.backendspring.dto.DonationDTO;
import les.donations.backendspring.dto.DonationDecisionDTO;
import les.donations.backendspring.dto.FileDTO;
import les.donations.backendspring.dto.PaginationDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;

import java.util.List;

public interface IDonationService {

    /**
     * Method that creates a donation and its donation process
     * @param donationDTO the donation's information
     * @param filesDTO information about the donation's images
     * @return the inserted donations's information
     * @throws IllegalArgumentException in case any error exists in donation's information
     * @throws NotFoundEntityException in case any entity does not exist (category or address)
     */
    DonationDTO registerDonation(DonationDTO donationDTO, List<FileDTO> filesDTO) throws IllegalArgumentException, NotFoundEntityException;

    /**
     * Method that updates a donation
     * @param donationId the donation's identification
     * @param donationDTO the donation's information
     * @return the updated donation's information
     * @throws IllegalArgumentException in case any error exists in donation's information
     * @throws NotFoundEntityException in case any entity does not exist (donation, category or address)
     */
    DonationDTO updateDonation(Long donationId, DonationDTO donationDTO) throws IllegalArgumentException, NotFoundEntityException;

    /**
     * Method that gets the donations
     * @param donationProcessStatus the process status of a donation
     * @return the donations
     */
    PaginationDTO getDonations(Integer donationProcessStatus, String categoryCode);

    /**
     * Method that gets the information of a specific donation
     * @param donationId the donation identification
     * @return information about the donation
     */
    DonationDTO getDonation(Long donationId) throws NotFoundEntityException;

    /**
     * Method that removes specific donation
     * @param donationId the donation's identification
     * @throws NotFoundEntityException if the donation does not exist in the system
     * @throws IllegalArgumentException if the donation cant be deleted
     */
    DonationDTO deleteDonation(Long donationId) throws NotFoundEntityException, IllegalArgumentException;

    /**
     * Method that requests a donation to occur
     * @param donationId the donation identification
     * @return the donation in a specific status
     * @throws NotFoundEntityException if the donation does not exist
     * @throws IllegalArgumentException if the donation is not in proper status
     */
    DonationDTO requestDonation(Long donationId) throws NotFoundEntityException, IllegalArgumentException;

    /**
     * Method that decides if the donation is to proceed to ongoing or back to created status
     * @param donationId the donation identification
     * @param donationDecisionDTO the decision
     * @return the donation in a specific status
     * @throws NotFoundEntityException if the donation does not exist
     */
    DonationDTO decisionDonation(Long donationId, DonationDecisionDTO donationDecisionDTO) throws NotFoundEntityException;
}
