package les.donations.backendspring.mapper.donation;


import les.donations.backendspring.dto.DonationDTO;
import les.donations.backendspring.model.Donation;

public interface IDonationMapper {
    Donation dtoToModel(DonationDTO donationDTO) throws IllegalArgumentException;
    DonationDTO modelToDto(Donation donation) throws IllegalArgumentException;
}
