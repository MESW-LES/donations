package les.donations.backendspring.service.category;

import les.donations.backendspring.dto.CategoryDTO;
import les.donations.backendspring.mapper.category.ICategoryMapper;
import les.donations.backendspring.model.Category;
import les.donations.backendspring.repository.category.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ICategoryMapper categoryMapper;

    @Override
    public CategoryDTO registerCategory(CategoryDTO categoryDTO) throws IllegalArgumentException {
        if (categoryRepository.findById(categoryDTO.code).isPresent()) {
            throw new IllegalArgumentException("The code of the category already exists");
        }
        Category category = categoryMapper.dtoToModel(categoryDTO);
        try {
            categoryRepository.saveAndFlush(category);
        } catch (Exception ex) {
            throw new IllegalArgumentException("The name of the category already exists");
        }

        return categoryDTO;
    }
}
