package les.donations.backendspring.mapper.donor;

import les.donations.backendspring.dto.DonorDTO;
import les.donations.backendspring.model.Donor;

public interface IDonorMapper {
    /**
     * Converts a DTO to a modal
     * @param donorDTO, the information about the donor
     * @return the donor
     * @throws IllegalArgumentException if the information is wrong
     */
    Donor dtoToModel(final DonorDTO donorDTO) throws IllegalArgumentException;
    DonorDTO modelToDTO(final Donor donor);
}
