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
    public ResponseEntity<ApiReturnMessage> registerCategory(CategoryDTO categoryDTO) {
        ApiReturnMessage apiReturnMessage;
        HttpStatus httpStatus;
        try {
            categoryDTO = categoryService.registerCategory(categoryDTO);
            httpStatus = HttpStatus.CREATED;
            apiReturnMessage = new ApiReturnMessage(httpStatus.value(), categoryDTO);
        } catch (IllegalArgumentException ex) {
            httpStatus = HttpStatus.BAD_REQUEST;
            apiReturnMessage = new ApiReturnMessage(httpStatus.value(), ex.getMessage());
        }
        return new ResponseEntity<>(apiReturnMessage, httpStatus);
    }
}
