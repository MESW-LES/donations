package les.donations.backendspring.repository.category;

import les.donations.backendspring.model.Category;

import java.util.List;

public interface CategoryDaoCustom {

    List<Category> getCategories(Boolean onlyActive);
}
