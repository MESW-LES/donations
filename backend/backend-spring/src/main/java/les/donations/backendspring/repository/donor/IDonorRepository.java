package les.donations.backendspring.repository.donor;

import les.donations.backendspring.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDonorRepository extends JpaRepository<Donor, Long> {

}
