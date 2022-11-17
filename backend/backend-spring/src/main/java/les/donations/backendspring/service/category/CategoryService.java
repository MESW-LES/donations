package les.donations.backendspring.service.category;

import les.donations.backendspring.dto.CategoryDTO;
import les.donations.backendspring.dto.ModelDTO;
import les.donations.backendspring.dto.PaginationDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.mapper.category.ICategoryMapper;
import les.donations.backendspring.model.Category;
import les.donations.backendspring.repository.category.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ICategoryMapper categoryMapper;

    @Override
    public CategoryDTO registerCategory(CategoryDTO categoryDTO) throws IllegalArgumentException {
        if (categoryDao.findById(categoryDTO.code).isPresent()) {
            throw new IllegalArgumentException("The code of the category already exists");
        }
        Category category = categoryMapper.dtoToModel(categoryDTO);
        try {
            categoryDao.saveAndFlush(category);
        } catch (Exception ex) {
            throw new IllegalArgumentException("The name of the category already exists");
        }

        return categoryDTO;
    }

    @Override
    public Category getCategoryModel(String code) throws NotFoundEntityException {
        // gets the active category
        Category category = categoryDao.getCategoryByCodeAndActive(code, true);
        // if it does not exits
        if(category == null){
            throw new NotFoundEntityException("The category with the code " + code + " does not exist!");
        }
        return category;
    }

    @Override
    public CategoryDTO getCategory(String code) throws NotFoundEntityException {
        // gets the category
        Category category = getCategoryModel(code);
        // converts the category into DTO
        return categoryMapper.modelToDTO(category);
    }

    @Override
    public PaginationDTO getCategories(Boolean onlyActive) {
        // gets the categories
        List<Category> categories = categoryDao.getCategories(onlyActive);
        // converts them into DTO
        List<ModelDTO> categoriesDTO = categories.stream().map(category -> categoryMapper.modelToDTO(category)).collect(Collectors.toList());
        return new PaginationDTO().results(categoriesDTO).countResults(categories.size());
    }
}
