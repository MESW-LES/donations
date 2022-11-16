package les.donations.backendspring.service.donation;

import les.donations.backendspring.dto.DonationDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DonationService implements IDonationService {

    @Override
    public DonationDTO registerDonation(DonationDTO donationDTO) throws IllegalArgumentException, NotFoundEntityException {
        return null;
    }
}
