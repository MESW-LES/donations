package les.donations.backendspring.controller.category;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.dto.CategoryDTO;
import les.donations.backendspring.service.category.ICategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class CategoryControllerTest {

    @Mock
    private ICategoryService categoryService;
    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    public void setup() {
        //if we don't call below, we will get NullPointerException
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @MethodSource("provideMessageExceptions")
    void addCategoryWithIllegalInformation(String message){
        CategoryDTO categoryDTO = new CategoryDTO();
        // mock the service
        when(categoryService.addCategory(categoryDTO)).thenThrow(new IllegalArgumentException(message));
        // call the controller
        ResponseEntity<ApiReturnMessage> result = categoryController.createCategory(categoryDTO);
        Assertions.assertEquals(400, result.getStatusCodeValue());
        Assertions.assertEquals(message, result.getBody().getMessage().toString());
    }

    @Test
    void addCategoryWithValidInformation(){
        CategoryDTO categoryDTO = new CategoryDTO().code("CAT-A").name("Category A").description("Category A description");
        // mock the service
        when(categoryService.addCategory(categoryDTO)).thenReturn(categoryDTO);
        // call the controller
        ResponseEntity<ApiReturnMessage> result = categoryController.createCategory(categoryDTO);
        Assertions.assertEquals(201, result.getStatusCodeValue());
        Assertions.assertEquals(categoryDTO, result.getBody().getMessage());
    }

    private static Stream<Arguments> provideMessageExceptions(){
        return Stream.of(Arguments.of("The name can't be null or empty"), Arguments.of("The code of the category already exists"),
                Arguments.of("The name of the category already exists"));
    }

}