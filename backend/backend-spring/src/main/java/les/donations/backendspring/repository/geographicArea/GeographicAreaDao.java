package les.donations.backendspring.repository.geographicArea;

import les.donations.backendspring.model.Category;
import les.donations.backendspring.model.GeographicArea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeographicAreaDao extends JpaRepository<GeographicArea, Long> {

}
