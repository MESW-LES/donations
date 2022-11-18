package les.donations.backendspring.repository.donation;

import les.donations.backendspring.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationDao extends JpaRepository<Donation, Long> {
}
