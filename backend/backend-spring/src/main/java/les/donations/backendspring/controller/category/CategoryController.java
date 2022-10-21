package les.donations.backendspring.controller.category;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.dto.CategoryDTO;
import les.donations.backendspring.service.demo.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController implements ICategoryController{

    @Autowired
    private ICategoryService categoryService;

    @Override
    public ResponseEntity<ApiReturnMessage> createCategory(CategoryDTO categoryDTO) {
        System.out.println(categoryDTO.code);
        Object category;
        try {
            category = categoryService.addCategory(categoryDTO);
        } catch (IllegalArgumentException ex) {
            final ApiReturnMessage apiReturnExceptionMessage = new ApiReturnMessage(400 , ex.getMessage());
            return new ResponseEntity<>(apiReturnExceptionMessage, HttpStatus.BAD_REQUEST);
        }

        final ApiReturnMessage apiReturnMessage = new ApiReturnMessage(201, category);
        return new ResponseEntity<>(apiReturnMessage, HttpStatus.CREATED);
    }
}
