package les.donations.backendspring.service.category;

import les.donations.backendspring.dto.CategoryDTO;
import les.donations.backendspring.mapper.category.ICategoryMapper;
import les.donations.backendspring.model.Category;
import les.donations.backendspring.repository.category.ICategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class CategoryServiceTest {

    @Mock
    private ICategoryRepository categoryRepository;
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
    void addCategoryWithWrongPropertyInformationInformation(){
        CategoryDTO categoryDTO = new CategoryDTO().code("CAT-A");
        when(categoryRepository.findById(categoryDTO.code)).thenReturn(Optional.empty());
        when(categoryMapper.dtoToModel(categoryDTO)).thenThrow(new IllegalArgumentException("The name can't be null or empty"));

        try{
            categoryService.addCategory(categoryDTO);
        }catch (IllegalArgumentException e){
            Assertions.assertEquals("The name can't be null or empty", e.getMessage());
        }
    }

    @Test
    void addCategoryWithExistingCode(){
        CategoryDTO categoryDTO = new CategoryDTO().code("CAT-A");
        when(categoryRepository.findById(categoryDTO.code)).thenReturn(Optional.of(new Category()));

        try{
            categoryService.addCategory(categoryDTO);
        }catch (IllegalArgumentException e){
            Assertions.assertEquals("The code of the category already exists", e.getMessage());
        }
    }

    @Test
    void addCategoryWithWExistingName(){
        CategoryDTO categoryDTO = new CategoryDTO().code("CAT-A");
        when(categoryRepository.findById(categoryDTO.code)).thenReturn(Optional.empty());
        when(categoryMapper.dtoToModel(categoryDTO)).thenReturn(new Category());
        when(categoryRepository.saveAndFlush(new Category())).thenThrow(new IllegalArgumentException("The name of the category already exists"));

        try{
            categoryService.addCategory(categoryDTO);
        }catch (IllegalArgumentException e){
            Assertions.assertEquals("The name of the category already exists", e.getMessage());
        }
    }

    @Test
    void addCategoryWithValidInformation(){
        CategoryDTO categoryDTO = new CategoryDTO().code("CAT-A");
        when(categoryRepository.findById(categoryDTO.code)).thenReturn(Optional.empty());
        when(categoryMapper.dtoToModel(categoryDTO)).thenReturn(new Category());
        when(categoryRepository.saveAndFlush(new Category())).thenReturn(new Category("CAT-A", "Category A", "123"));

        CategoryDTO returned = categoryService.addCategory(categoryDTO);
        assertEquals(categoryDTO, returned);
    }
}