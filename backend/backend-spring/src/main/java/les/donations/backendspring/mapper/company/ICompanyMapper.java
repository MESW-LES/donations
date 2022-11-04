package les.donations.backendspring.mapper.company;

import les.donations.backendspring.dto.CompanyDTO;
import les.donations.backendspring.model.Company;
import org.springframework.stereotype.Component;

public interface ICompanyMapper {

    /**
     * Method that centers the process of instantiating a Company through is correspondent DTO
     * @param companyDTO the DTO to be converted
     * @return the company created
     * @throws IllegalArgumentException if there is an error in company data
     */
    Company dtoToModel(CompanyDTO companyDTO) throws IllegalArgumentException;
}
