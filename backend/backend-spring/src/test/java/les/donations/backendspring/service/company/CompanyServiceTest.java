package les.donations.backendspring.service.company;

import les.donations.backendspring.dto.CompanyDTO;
import les.donations.backendspring.external.ITaxNumberAPI;
import les.donations.backendspring.mapper.company.ICompanyMapper;
import les.donations.backendspring.model.Company;
import les.donations.backendspring.repository.company.CompanyDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class CompanyServiceTest {

    @Mock
    private ITaxNumberAPI taxNumberAPI;
    @Mock
    private CompanyDao companyDao;
    @Mock
    private ICompanyMapper companyMapper;
    @InjectMocks
    private CompanyService companyService;

    @BeforeEach
    public void setup() {
        //if we don't call below, we will get NullPointerException
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCompanyButTheTaxNumberAlreadyExistsTest(){
        when(companyDao.existsCompanyByTaxNumber("12345678")).thenReturn(true);
        Assertions.assertThrows(IllegalArgumentException.class, () -> companyService.createCompany(new CompanyDTO()
                .taxNumber("12345678")));
    }

    @Test
    void createCompanyButCantConsumeExternalAPITest() throws IOException {
        when(companyDao.existsCompanyByTaxNumber("12345678")).thenReturn(false);
        when(taxNumberAPI.getEmailByCompanyTaxNumber("12345678")).thenReturn(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> companyService.createCompany(new CompanyDTO()
                .taxNumber("12345678")));
    }

    @Test
    void createCompanyWithWrongInformationTest() throws IOException {
        CompanyDTO companyDTO = new CompanyDTO().taxNumber("12345678");
        when(companyDao.existsCompanyByTaxNumber("12345678")).thenReturn(false);
        when(taxNumberAPI.getEmailByCompanyTaxNumber("12345678")).thenReturn("test@gmail.com");
        when(companyMapper.dtoToModel(companyDTO)).thenThrow(IllegalArgumentException.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> companyService.createCompany(companyDTO));
    }

    @Test
    void createCompanyTest() throws IOException {
        Company company = new Company("name", "description", "taxNumber", 912345678L);
        CompanyDTO companyDTO = new CompanyDTO().taxNumber("12345678");
        when(companyDao.existsCompanyByTaxNumber("12345678")).thenReturn(false);
        when(taxNumberAPI.getEmailByCompanyTaxNumber("12345678")).thenReturn("test@gmail.com");
        when(companyMapper.dtoToModel(companyDTO)).thenReturn(company);
        Assertions.assertEquals(company, companyService.createCompany(companyDTO));
    }

}