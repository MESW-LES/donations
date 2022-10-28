package les.donations.backendspring.service.company;

import les.donations.backendspring.dto.CompanyDTO;
import les.donations.backendspring.model.Company;

import java.io.IOException;

public interface ICompanyService {

    Company createCompany(CompanyDTO companyDTO) throws IllegalArgumentException, IOException;

}
