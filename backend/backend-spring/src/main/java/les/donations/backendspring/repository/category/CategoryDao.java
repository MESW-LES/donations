package les.donations.backendspring.repository.category;

import les.donations.backendspring.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, String>, CategoryDaoCustom {

    Category getCategoryByCodeAndActive(String code, boolean active);
}
