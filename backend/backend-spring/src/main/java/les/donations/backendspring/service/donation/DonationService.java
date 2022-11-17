package les.donations.backendspring.service.donation;

import les.donations.backendspring.dto.DonationDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.mapper.donation.IDonationMapper;
import les.donations.backendspring.model.Category;
import les.donations.backendspring.model.Donation;
import les.donations.backendspring.repository.donation.DonationDao;
import les.donations.backendspring.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class DonationService implements IDonationService {

    @Autowired
    private IDonationMapper donationMapper;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private DonationDao donationDao;

    @Override
    public DonationDTO registerDonation(DonationDTO donationDTO) throws IllegalArgumentException, NotFoundEntityException {

        // converts donation dto to donation model
        Donation donation = donationMapper.dtoToModel(donationDTO);

        // gets the associated categories
        List<Category> categories = new ArrayList<>();
        for(String categoryCode : donationDTO.categoriesCode){
            categories.add(categoryService.getCategoryModel(categoryCode));
        }
        donation.setCategories(categories);

        // persists the donation
        donation = donationDao.saveAndFlush(donation);

        return donationDTO.id(donation.getId());
    }
}
