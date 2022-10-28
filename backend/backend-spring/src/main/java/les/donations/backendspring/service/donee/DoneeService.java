package les.donations.backendspring.service.donee;

import les.donations.backendspring.dto.DoneeDTO;
import les.donations.backendspring.model.Company;
import les.donations.backendspring.model.Donee;
import les.donations.backendspring.repository.donee.IDoneeRepository;
import les.donations.backendspring.service.company.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DoneeService implements IDoneeService {

    @Autowired
    private IDoneeRepository doneeRepository;
    @Autowired
    private ICompanyService companyService;

    @Override
    public DoneeDTO registerDonee(DoneeDTO doneeDTO) throws IllegalArgumentException, IOException {
        // creates the company
        Company company = companyService.createCompany(doneeDTO.company);
        // creates the donee
        Donee donee = new Donee(company);
        // persists the donee
        donee = doneeRepository.saveAndFlush(donee);

        // TODO: sends the email
        String companyEmail = donee.getCompany().getEmail();

        return doneeDTO.id(donee.getId());
    }
}
