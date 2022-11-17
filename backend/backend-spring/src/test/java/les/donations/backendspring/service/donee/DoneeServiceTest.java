package les.donations.backendspring.service.donee;

import les.donations.backendspring.controller.donee.DoneeController;
import les.donations.backendspring.dto.CompanyDTO;
import les.donations.backendspring.dto.DoneeDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.model.Category;
import les.donations.backendspring.model.Company;
import les.donations.backendspring.model.Donee;
import les.donations.backendspring.repository.donee.DoneeDao;
import les.donations.backendspring.service.category.ICategoryService;
import les.donations.backendspring.service.company.ICompanyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class DoneeServiceTest {

    @Mock
    private DoneeDao doneeDao;
    @Mock
    private ICompanyService companyService;
    @Mock
    private ICategoryService categoryService;
    @InjectMocks
    private DoneeService doneeService;

    private Company company;
    private Category category;

    @BeforeEach
    public void setup() {
        //if we don't call below, we will get NullPointerException
        MockitoAnnotations.openMocks(this);
        company = new Company("name", "description", "taxNumber", 912345678L);
        category = new Category("code", "name", "description");
    }

    @Test
    void registerDoneeWithErrorsInCompanyInformationTest() throws IOException {
        // prepares the data
        CompanyDTO companyDTO = new CompanyDTO();
        DoneeDTO doneeDTO = new DoneeDTO().company(companyDTO);
        // mocks the service
        when(companyService.createCompany(companyDTO)).thenThrow(IllegalArgumentException.class);
        // calls the method
        Assertions.assertThrows(IllegalArgumentException.class, () -> doneeService.registerDonee(doneeDTO));
    }

    @Test
    void registerDoneeWithErrorsInDoneeInformationTest() throws IOException {
        // prepares the data
        CompanyDTO companyDTO = new CompanyDTO();
        DoneeDTO doneeDTO = new DoneeDTO().company(companyDTO);
        // mocks the service
        when(companyService.createCompany(companyDTO)).thenReturn(company);
        // calls the method
        Assertions.assertThrows(IllegalArgumentException.class, () -> doneeService.registerDonee(doneeDTO));
    }

    @Test
    void registerDoneeThatHasNonexistentCategoryTest() throws IOException, NotFoundEntityException {
        // prepares the data
        CompanyDTO companyDTO = new CompanyDTO();
        DoneeDTO doneeDTO = new DoneeDTO().company(companyDTO).password("123").categoryCodes(Collections.singletonList("CAT-A"));
        // mocks the service
        when(companyService.createCompany(companyDTO)).thenReturn(company);
        when(categoryService.getCategoryModel("CAT-A")).thenThrow(NotFoundEntityException.class);
        // calls the method
        Assertions.assertThrows(NotFoundEntityException.class, () -> doneeService.registerDonee(doneeDTO));
    }

    @Test
    void registerDoneeThatHasProblemsConnectingExternalAPITest() throws IOException {
        // prepares the data
        CompanyDTO companyDTO = new CompanyDTO();
        DoneeDTO doneeDTO = new DoneeDTO().company(companyDTO);
        // mocks the service
        when(companyService.createCompany(companyDTO)).thenThrow(IOException.class);
        // calls the method
        Assertions.assertThrows(IOException.class, () -> doneeService.registerDonee(doneeDTO));
    }

    @Test
    void registerDoneeSuccessfullyTest() throws IOException, NotFoundEntityException {
        // prepares the data
        Donee donee = new Donee("123", company);
        donee.setId(1L);
        CompanyDTO companyDTO = new CompanyDTO();
        DoneeDTO doneeDTO = new DoneeDTO().company(companyDTO).password("123").categoryCodes(Collections.singletonList("CAT-A"));
        // mocks the service
        when(companyService.createCompany(companyDTO)).thenReturn(company);
        when(categoryService.getCategoryModel("CAT-A")).thenReturn(category);
        when(doneeDao.saveAndFlush(Mockito.any(Donee.class))).thenReturn(donee);
        // calls the method
        DoneeDTO result = doneeService.registerDonee(doneeDTO);
        Assertions.assertEquals(1L, result.id);
    }

}