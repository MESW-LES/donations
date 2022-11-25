package les.donations.backendspring.service.category;

import les.donations.backendspring.dto.CategoryDTO;
import les.donations.backendspring.dto.PaginationDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.model.Category;
import les.donations.backendspring.model.Company;

import java.util.List;

public interface ICategoryService {

    /**
     * Creates a category
     * @param categoryDTO, containing information about the category
     * @return an information about the category if the operation was successful
     * @throws IllegalArgumentException if the data is wrong, code is not unique or name is not unique
     */
    CategoryDTO registerCategory(CategoryDTO categoryDTO) throws IllegalArgumentException;

    /**
     * Method that gets a category model by its code
     * @param code the category code
     * @return a Category model
     * @throws NotFoundEntityException in case any category has the specified code
     */
    Category getCategoryModel(String code) throws NotFoundEntityException;

    /**
     * Method that gets a category DTO by its code
     * @param code the category code
     * @return a Category DTO
     * @throws NotFoundEntityException in case any category has the specified code
     */
    CategoryDTO getCategory(String code) throws NotFoundEntityException;

    /**
     * Method that gets all the categories in the system (in Pagination structure)
     * @param onlyActive a flag that declares if it is necessary to get only the active ones or everything
     * @return information about the categories
     */
    PaginationDTO getCategories(Boolean onlyActive);
}
