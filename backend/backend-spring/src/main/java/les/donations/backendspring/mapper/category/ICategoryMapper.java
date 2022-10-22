package les.donations.backendspring.mapper.category;

import les.donations.backendspring.dto.CategoryDTO;
import les.donations.backendspring.model.Category;

public interface ICategoryMapper {
    /**
     * Converts a DTO to a modal
     * @param categoryDTO, the information about the category
     * @return the category
     * @throws IllegalArgumentException if the information is wrong
     */
    Category dtoToModel(final CategoryDTO categoryDTO) throws IllegalArgumentException;
    CategoryDTO modelToDTO(final Category category);
}
