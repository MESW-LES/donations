package les.donations.backendspring.mapper.donation;

import les.donations.backendspring.dto.DonationDTO;
import les.donations.backendspring.model.Donation;
import org.springframework.stereotype.Component;

@Component
public class DonationMapper implements IDonationMapper {

    @Override
    public Donation dtoToModel(DonationDTO donationDTO) throws IllegalArgumentException{
        return new Donation(donationDTO.title, donationDTO.description);
    }
}
