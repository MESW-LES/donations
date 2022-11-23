package les.donations.backendspring.service.donation;

import les.donations.backendspring.dto.DonationDTO;
import les.donations.backendspring.dto.FileDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.mapper.donation.IDonationMapper;
import les.donations.backendspring.mapper.donationImage.IDonationImageMapper;
import les.donations.backendspring.model.Donation;
import les.donations.backendspring.model.DonationImage;
import les.donations.backendspring.model.DonationProcess;
import les.donations.backendspring.repository.donation.DonationDao;
import les.donations.backendspring.service.category.ICategoryService;
import les.donations.backendspring.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DonationService implements IDonationService {

    @Autowired
    private IDonationMapper donationMapper;
    @Autowired
    private IDonationImageMapper donationImageMapper;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private DonationDao donationDao;

    @Override
    public DonationDTO registerDonation(DonationDTO donationDTO, List<FileDTO> filesDTO) throws IllegalArgumentException, NotFoundEntityException {

        // converts donation dto to donation model
        Donation donation = donationMapper.dtoToModel(donationDTO);

        // gets the associated categories
        for(String categoryCode : donationDTO.categoriesCode.split(",")){
            donation.addCategory(categoryService.getCategoryModel(categoryCode.trim()));
        }

        // gets the associated donation images
        for(FileDTO fileDTO : filesDTO){
            DonationImage donationImage = donationImageMapper.dtoToModel(fileDTO);
            donationImage.setDonation(donation);
            donation.addDonationImage(donationImage);
        }

        // begins the donation process and associates with the donation
        DonationProcess donationProcess = new DonationProcess(donation);
        donation.setDonationProcess(donationProcess);

        // persists the donation
        donation = donationDao.saveAndFlush(donation);

        return donationDTO.id(donation.getId()).createdDate(StringUtils.convertDateToString(donation.getCreatedDate()));
    }

    @Override
    public DonationDTO updateDonation(Long donationId, DonationDTO donationDTO) throws IllegalArgumentException, NotFoundEntityException {

        // gets the donation by its id
        Donation donation = donationDao.getReferenceById(donationId);
        // if the donation does not exist
        if(!donation.isActive()){
            throw new NotFoundEntityException("The donation does not exist!");
        }

        // if the donation is not in a editable status (not in created status)
        if(!donation.getDonationProcess().getStatus().isCanEditDonation()){
            throw new IllegalArgumentException("The donation can't be edited!");
        }

        // removes the previous categories
        donation.clearCategories();
        // gets the associated categories
        for(String categoryCode : donationDTO.categoriesCode.split(",")){
            donation.addCategory(categoryService.getCategoryModel(categoryCode.trim()));
        }

        // updates the donation's information
        donation.setTitle(donationDTO.title);
        donation.setDescription(donationDTO.description);

        return donationDTO.id(donationId);
    }
}
