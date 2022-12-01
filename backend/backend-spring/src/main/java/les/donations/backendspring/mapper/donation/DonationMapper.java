package les.donations.backendspring.mapper.donation;

import les.donations.backendspring.dto.DonationDTO;
import les.donations.backendspring.dto.DonationProcessDTO;
import les.donations.backendspring.model.Donation;
import les.donations.backendspring.model.DonationImage;
import les.donations.backendspring.model.DonationProcess;
import les.donations.backendspring.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DonationMapper implements IDonationMapper {

    @Override
    public Donation dtoToModel(DonationDTO donationDTO) throws IllegalArgumentException{
        return new Donation(donationDTO.title, donationDTO.description);
    }

    @Override
    public DonationDTO modelToDTO(Donation donation) throws IllegalArgumentException {
        // donation information
        DonationDTO donationDTO = new DonationDTO().title(donation.getTitle()).description(donation.getDescription())
                .createdDate(StringUtils.convertDateToString(donation.getCreatedDate()))
                .donationImages(donation.getDonationImages().stream().map(DonationImage::getFileName).collect(Collectors.toList()));

        // gets the donation process
        DonationProcess donationProcess = donation.getDonationProcess();
        DonationProcessDTO donationProcessDTO = new DonationProcessDTO().status(donationProcess.getStatus().getDesignation())
                .donee(donationProcess.getDonee() != null ? donationProcess.getDonee().getCompany().getName() : null)
                .ongoingDate(donationProcess.getOngoingDate() != null ? StringUtils.convertDateToString(donationProcess.getOngoingDate()) : null);

        return donationDTO.donationProcess(donationProcessDTO);
    }
}
