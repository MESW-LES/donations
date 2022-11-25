package les.donations.backendspring.repository.donee;

import les.donations.backendspring.model.Donee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoneeDao extends JpaRepository<Donee, String> {
}
