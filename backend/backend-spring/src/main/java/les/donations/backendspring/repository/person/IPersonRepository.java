package les.donations.backendspring.repository.person;

import les.donations.backendspring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<Person, Long> {
}
