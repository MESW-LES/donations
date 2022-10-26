package les.donations.backendspring.service.category;

import les.donations.backendspring.dto.CategoryDTO;
import les.donations.backendspring.model.Category;

public interface ICategoryService {

    /**
     * Creates a category
     * @param categoryDTO, containing information about the category
     * @return an information about the category if the operation was successful
     * @throws IllegalArgumentException if the data is wrong, code is not unique or name is not unique
     */
    CategoryDTO addCategory(CategoryDTO categoryDTO) throws IllegalArgumentException;
}
