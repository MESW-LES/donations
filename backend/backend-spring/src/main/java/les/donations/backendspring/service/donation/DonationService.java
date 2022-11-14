package les.donations.backendspring.service.donation;

import les.donations.backendspring.dto.DonationDTO;
import les.donations.backendspring.mapper.donation.IDonationMapper;
import les.donations.backendspring.model.Donation;
import les.donations.backendspring.repository.donation.IDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DonationService implements IDonationService {

    @Autowired
    private IDonationRepository donationRepository;

    @Autowired
    private IDonationMapper donationMapper;

    @Override
    public DonationDTO createDonation(DonationDTO donationDTO) throws IllegalArgumentException, IOException {
        return null;
    }

    @Override
    public List<DonationDTO> getDonations(Long id) {
        List<Donation> donations = donationRepository.findByDonorId(id);
        List<DonationDTO> donationDTOS = donations.stream()
                .map(donation -> donationMapper.modelToDto(donation)).collect(Collectors.toList());
        return donationDTOS;
    }

    @Override
    public DonationDTO getDonation(Long id) {
        Optional<Donation> donation = donationRepository.findById(id);
        if (donation.isPresent()) {
            return donationMapper.modelToDto(donation.get());
        }
        throw new IllegalArgumentException("Donation is not found");
    }
}
