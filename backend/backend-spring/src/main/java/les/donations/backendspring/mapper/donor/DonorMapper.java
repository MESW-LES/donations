package les.donations.backendspring.mapper.donor;

import les.donations.backendspring.dto.DonorDTO;
import les.donations.backendspring.model.Donor;
import org.springframework.stereotype.Component;

@Component
public class DonorMapper implements IDonorMapper{
    @Override
    public Donor dtoToModel(final DonorDTO donorDTO) throws IllegalArgumentException{
        return new Donor(donorDTO.id, donorDTO.firstName, donorDTO.lastName, donorDTO.nif, donorDTO.address,
                donorDTO.email, donorDTO.password);
    }

    @Override
    public DonorDTO modelToDTO(final Donor donor){
        return (new DonorDTO().id(donor.getId()).firstName(donor.getFirstName()).lastName(donor.getLastName())
                .nif(donor.getNif()).address(donor.getAddress()).email(donor.getEmail())).password(donor.getPassword());
    }
}
