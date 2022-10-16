package les.donations.backendspring.repository.demo;

import les.donations.backendspring.model.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IDemoRepository extends JpaRepository<Demo, Long> {

    @Query("SELECT d FROM Demo d WHERE d.id = ?1")
    Demo getDemoById(Long id);
}
