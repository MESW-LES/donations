package les.donations.backendspring.mapper.category;

import les.donations.backendspring.dto.CategoryDTO;
import les.donations.backendspring.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements ICategoryMapper {
    @Override
    public Category dtoToModel(final CategoryDTO categoryDTO) throws IllegalArgumentException{
        return new Category(categoryDTO.code, categoryDTO.name, categoryDTO.description);
    }

    @Override
    public CategoryDTO modelToDTO(final Category category){
        return new CategoryDTO().code(category.getCode()).name(category.getName()).description(category.getDescription());
    }
}
