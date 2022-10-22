package les.donations.backendspring.repository.category;

import les.donations.backendspring.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, String> {

}
