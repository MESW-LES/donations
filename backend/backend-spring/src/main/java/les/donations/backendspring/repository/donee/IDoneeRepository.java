package les.donations.backendspring.repository.donee;

import les.donations.backendspring.model.Donee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoneeRepository extends JpaRepository<Donee, String> {
}
