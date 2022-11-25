package les.donations.backendspring.controller.category;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.controller.IController;
import les.donations.backendspring.dto.CategoryDTO;
import les.donations.backendspring.dto.PaginationDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class CategoryController extends IController implements ICategoryController{

    @Autowired
    private ICategoryService categoryService;

    @Override
    public ResponseEntity<ApiReturnMessage> registerCategory(CategoryDTO categoryDTO) {
        try {
            categoryDTO = categoryService.registerCategory(categoryDTO);
            return created(categoryDTO);
        } catch (IllegalArgumentException ex) {
            return badRequest(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiReturnMessage> getCategories(Boolean onlyActive) {
        PaginationDTO categories = categoryService.getCategories(onlyActive);
        return ok(categories);
    }

    @Override
    public ResponseEntity<ApiReturnMessage> getCategory(String categoryCode) {
        try{
            // gets the category
            CategoryDTO categoryDTO = categoryService.getCategory(categoryCode);
            return ok(categoryDTO);

            // if the category does not exits
        }catch (NotFoundEntityException e){
            return notFound(e.getMessage());
        }
    }
}
