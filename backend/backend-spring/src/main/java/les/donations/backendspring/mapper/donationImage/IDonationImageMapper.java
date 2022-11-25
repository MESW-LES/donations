package les.donations.backendspring.mapper.donationImage;

import les.donations.backendspring.dto.FileDTO;
import les.donations.backendspring.model.DonationImage;

public interface IDonationImageMapper {

    /**
     * Method that centers the process of instantiating a DonationImage through a FileDTO
     * @param fileDTO the DTO to be converted
     * @return the donation image created
     * @throws IllegalArgumentException if there is an error in donation image data
     */
    DonationImage dtoToModel(FileDTO fileDTO) throws IllegalArgumentException;
}
