package les.donations.backendspring.service.category;

import les.donations.backendspring.dto.CategoryDTO;
import les.donations.backendspring.dto.PaginationDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.mapper.category.ICategoryMapper;
import les.donations.backendspring.model.Category;
import les.donations.backendspring.repository.category.CategoryDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class CategoryServiceTest {

    @Mock
    private CategoryDao categoryRepository;
    @Mock
    private ICategoryMapper categoryMapper;
    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    public void setup() {
        //if we don't call below, we will get NullPointerException
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerCategoryWithWrongPropertyInformationInformationTest(){
        CategoryDTO categoryDTO = new CategoryDTO().code("CAT-A");
        when(categoryRepository.findById(categoryDTO.code)).thenReturn(Optional.empty());
        when(categoryMapper.dtoToModel(categoryDTO)).thenThrow(new IllegalArgumentException("The name can't be null or empty"));

        try{
            categoryService.registerCategory(categoryDTO);
        }catch (IllegalArgumentException e){
            Assertions.assertEquals("The name can't be null or empty", e.getMessage());
        }
    }

    @Test
    void registerCategoryWithExistingCodeTest(){
        CategoryDTO categoryDTO = new CategoryDTO().code("CAT-A");
        when(categoryRepository.findById(categoryDTO.code)).thenReturn(Optional.of(new Category("code", "name", "description")));

        try{
            categoryService.registerCategory(categoryDTO);
        }catch (IllegalArgumentException e){
            Assertions.assertEquals("The code of the category already exists", e.getMessage());
        }
    }

    @Test
    void registerCategoryWithWExistingNameTest(){
        CategoryDTO categoryDTO = new CategoryDTO().code("CAT-A");
        when(categoryRepository.findById(categoryDTO.code)).thenReturn(Optional.empty());
        when(categoryMapper.dtoToModel(categoryDTO)).thenReturn(new Category("code", "name", "description"));
        when(categoryRepository.saveAndFlush(new Category("code", "name", "description"))).thenThrow(new IllegalArgumentException("The name of the category already exists"));

        try{
            categoryService.registerCategory(categoryDTO);
        }catch (IllegalArgumentException e){
            Assertions.assertEquals("The name of the category already exists", e.getMessage());
        }
    }

    @Test
    void registerCategoryWithValidInformationTest(){
        CategoryDTO categoryDTO = new CategoryDTO().code("CAT-A");
        when(categoryRepository.findById(categoryDTO.code)).thenReturn(Optional.empty());
        when(categoryMapper.dtoToModel(categoryDTO)).thenReturn(new Category("code", "name", "description"));
        when(categoryRepository.saveAndFlush(new Category("code", "name", "description"))).thenReturn(new Category("CAT-A", "Category A", "123"));

        CategoryDTO returned = categoryService.registerCategory(categoryDTO);
        assertEquals(categoryDTO, returned);
    }

    @Test
    void getCategoryButDoesNotExistTest(){
        when(categoryRepository.getCategoryByCodeAndActive("CAT-A", true)).thenReturn(null);
        assertThrows(NotFoundEntityException.class, () -> categoryService.getCategory("CAT-A"));
    }

    @Test
    void getCategoryTest() throws NotFoundEntityException {
        Category category = new Category("CAT-A", "Category A", "Description A");
        when(categoryRepository.getCategoryByCodeAndActive("CAT-A", true)).thenReturn(category);
        assertEquals(category, categoryService.getCategoryModel("CAT-A"));
    }

    @Test
    void getCategoriesTest(){
        Category categoryA = new Category("code A", "name", "description");
        Category categoryB = new Category("code B", "name", "description");
        List<Category> categories = new ArrayList<>();
        categories.add(categoryA);
        categories.add(categoryB);
        CategoryDTO categoryDTOA = new CategoryDTO().code("code A").name("name").description("description");
        CategoryDTO categoryDTOB = new CategoryDTO().code("code B").name("name").description("description");

        when(categoryRepository.getCategories(true)).thenReturn(categories);
        when(categoryMapper.modelToDTO(categoryA)).thenReturn(categoryDTOA);
        when(categoryMapper.modelToDTO(categoryB)).thenReturn(categoryDTOB);

        PaginationDTO result = categoryService.getCategories(true);
        Assertions.assertEquals(2, result.countResults);
        Assertions.assertEquals(categoryDTOA, result.results.get(0));
        Assertions.assertEquals(categoryDTOB, result.results.get(1));
    }
}