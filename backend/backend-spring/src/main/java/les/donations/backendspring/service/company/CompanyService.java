package les.donations.backendspring.service.company;

import les.donations.backendspring.dto.CompanyDTO;
import les.donations.backendspring.mapper.company.ICompanyMapper;
import les.donations.backendspring.model.Company;
import les.donations.backendspring.repository.company.CompanyDao;
import les.donations.backendspring.external.ITaxNumberAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Transactional
@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private ITaxNumberAPI taxNumberAPI;
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private ICompanyMapper companyMapper;

    @Override
    public Company createCompany(CompanyDTO companyDTO) throws IllegalArgumentException, IOException {

        // checks if the company already exists
        if(companyDao.existsCompanyByTaxNumber(companyDTO.taxNumber)){
            throw new IllegalArgumentException("A company with the same tax number already exists!");
        }

        // checks the company and gets the associated email
        String email = taxNumberAPI.getEmailByCompanyTaxNumber(companyDTO.taxNumber);
        if(email == null){
            throw new IllegalArgumentException("The company does not have an associated email or is not valid!");
        }

        // creates the company
        Company company = companyMapper.dtoToModel(companyDTO);
        company.setEmail(email);
        return company;
    }
}
