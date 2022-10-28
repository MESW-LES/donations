package les.donations.backendspring.mapper.company;

import les.donations.backendspring.dto.CompanyDTO;
import les.donations.backendspring.model.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper implements ICompanyMapper {

    @Override
    public Company dtoToModel(CompanyDTO companyDTO) throws IllegalArgumentException{
        return new Company(companyDTO.name, companyDTO.description, companyDTO.taxNumber, companyDTO.phone);
    }
}
