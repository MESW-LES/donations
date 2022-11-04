package les.donations.backendspring.service.donor;

import les.donations.backendspring.dto.DonorDTO;

import java.io.IOException;

public interface IDonorService {

    /**
     * Creates a donor
     * @param donorDTO, containing information about the donor
     * @return an information about the donor if the operation was successful
     * @throws IllegalArgumentException if the data is wrong, fields are not unique
     */
    DonorDTO registerDonor(DonorDTO donorDTO) throws IllegalArgumentException, IOException;
}
