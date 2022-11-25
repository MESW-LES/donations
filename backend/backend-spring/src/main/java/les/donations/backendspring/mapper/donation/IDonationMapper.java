package les.donations.backendspring.mapper.donation;

import les.donations.backendspring.dto.DonationDTO;
import les.donations.backendspring.model.Donation;

public interface IDonationMapper {
    /**
     * Method that centers the process of instantiating a DonationDTO through is correspondent donation
     * @param donation the entity to be converted
     * @return the donationDTO created
     * @throws IllegalArgumentException if there is an error in donation data
     */
    DonationDTO modelToDto(Donation donation) throws IllegalArgumentException;

    /**
     * Method that centers the process of instantiating a Donation through is correspondent DTO
     * @param donationDTO the DTO to be converted
     * @return the donation created
     * @throws IllegalArgumentException if there is an error in donation data
     */
    Donation dtoToModel(DonationDTO donationDTO) throws IllegalArgumentException;
}
