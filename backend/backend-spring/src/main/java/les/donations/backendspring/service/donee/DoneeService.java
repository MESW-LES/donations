package les.donations.backendspring.service.donee;

import les.donations.backendspring.dto.DoneeDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.model.Company;
import les.donations.backendspring.model.Donee;
import les.donations.backendspring.model.GeographicArea;
import les.donations.backendspring.repository.donee.DoneeDao;
import les.donations.backendspring.service.category.ICategoryService;
import les.donations.backendspring.service.company.ICompanyService;
import les.donations.backendspring.service.geographicArea.IGeographicAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Transactional
@Service
public class DoneeService implements IDoneeService {

    @Autowired
    private DoneeDao doneeDao;
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IGeographicAreaService geographicAreaService;

    @Override
    public DoneeDTO registerDonee(DoneeDTO doneeDTO) throws IllegalArgumentException, IOException, NotFoundEntityException {
        // creates the company
        Company company = companyService.createCompany(doneeDTO.company);
        // creates the donee
        Donee donee = new Donee(doneeDTO.password, company);

        // associates the categories to the donee
        for(String categoryCode : doneeDTO.categoryCodes){
            donee.addCategory(categoryService.getCategoryModel(categoryCode));
        }

        //add geographic area
        for (Long areaId : doneeDTO.geographicAreaIds) {
            GeographicArea geographicArea = geographicAreaService.getGeographicAreaModel(areaId);
            donee.addGeographicArea(geographicArea);
            geographicArea.addDonee(donee);
        }

        // persists the donee
        donee = doneeDao.saveAndFlush(donee);

        // sends the email
        String companyEmail = donee.getCompany().getEmail();

        return doneeDTO.id(donee.getId());
    }
}
