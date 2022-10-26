package les.donations.backendspring.service.donor;

import les.donations.backendspring.dto.DonorDTO;

public interface IDonorService {

    /**
     * Creates a donor
     * @param donorDTO, containing information about the donor
     * @return an information about the donor if the operation was successful
     * @throws IllegalArgumentException if the data is wrong, code is not unique or name is not unique
     */
    DonorDTO addDonor(DonorDTO donorDTO) throws IllegalArgumentException;
}
