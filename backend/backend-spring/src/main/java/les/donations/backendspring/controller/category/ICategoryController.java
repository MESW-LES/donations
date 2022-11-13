package les.donations.backendspring.controller.category;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.dto.CategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface ICategoryController {

    /**
     * Creates a category
     * @param categoryDTO, containing information about the category
     * @return a response with a code which represents if the operation was successful or not
     */
    @PostMapping(value = "/categories", produces = "application/json", consumes = "application/json")
    ResponseEntity<ApiReturnMessage> registerCategory(@RequestBody CategoryDTO categoryDTO);

    /**
     * Method that gets all (or the active ones) the categories in the system
     * @return a response with a code which represents if the operation was successful or not
     */
    @GetMapping(value = "/categories", produces = "application/json")
    ResponseEntity<ApiReturnMessage> getCategories(@RequestParam(value = "onlyActive", required = false) Boolean onlyActive);
}
