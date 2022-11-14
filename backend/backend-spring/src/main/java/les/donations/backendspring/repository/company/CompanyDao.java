package les.donations.backendspring.repository.company;

import les.donations.backendspring.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDao extends JpaRepository<Company, Long> {

    boolean existsCompanyByTaxNumber(String taxNumber);
}
