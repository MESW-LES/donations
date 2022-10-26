package les.donations.backendspring.service.donor;

import les.donations.backendspring.dto.DonorDTO;
import les.donations.backendspring.mapper.donor.IDonorMapper;
import les.donations.backendspring.model.Donor;
import les.donations.backendspring.repository.donor.IDonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonorService implements IDonorService {

    @Autowired
    private IDonorRepository donorRepository;

    @Autowired
    private IDonorMapper donorMapper;

    @Override
    public DonorDTO addDonor(DonorDTO donorDTO) throws IllegalArgumentException {
        Donor donor = donorMapper.dtoToModel(donorDTO);
        try {
            donorRepository.saveAndFlush(donor);
            donorDTO.id = donor.getId();
        } catch (Exception ex) {
            throw new IllegalArgumentException("NIF or email already exists");
        }

        return donorDTO;
    }
}
