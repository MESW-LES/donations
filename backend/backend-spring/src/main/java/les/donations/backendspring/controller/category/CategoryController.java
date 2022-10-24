package les.donations.backendspring.controller.category;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.dto.CategoryDTO;
import les.donations.backendspring.service.category.ICategoryService;
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
        Object message;
        HttpStatus httpStatus;
        try {
            message = categoryService.addCategory(categoryDTO);
            httpStatus = HttpStatus.CREATED;
        } catch (IllegalArgumentException ex) {
            message = ex.getMessage();
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        final ApiReturnMessage apiReturnMessage = new ApiReturnMessage(httpStatus.value(), message);
        return new ResponseEntity<>(apiReturnMessage, httpStatus);
    }
}
