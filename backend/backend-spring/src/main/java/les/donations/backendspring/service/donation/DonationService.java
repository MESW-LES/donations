package les.donations.backendspring.service.donation;

import les.donations.backendspring.dto.*;
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
import java.util.stream.Collectors;

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

        return donationMapper.modelToDTO(donation);
    }

    @Override
    public DonationDTO updateDonation(Long donationId, DonationDTO donationDTO) throws IllegalArgumentException, NotFoundEntityException {

        // gets the donation by its id
        Donation donation = getDonationModel(donationId);

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

    @Override
    public PaginationDTO getDonations(Integer donationProcessStatus) {
        // gets the active donations with a specific status
        List<Donation> donations = donationDao.getDonations(donationProcessStatus);
        // converts them into DTOs
        List<ModelDTO> donationDTOs = donations.stream().map(donation -> donationMapper.modelToDTO(donation)).collect(Collectors.toList());

        return new PaginationDTO().results(donationDTOs).countResults(donations.size());
    }

    @Override
    public DonationDTO getDonation(Long donationId) throws NotFoundEntityException {
        // gets the donation by its id
        Donation donation = getDonationModel(donationId);
        return donationMapper.modelToDTO(donation);
    }

    @Override
    public DonationDTO deleteDonation(Long donationId) throws NotFoundEntityException, IllegalArgumentException {
        // gets the donation by its id
        Donation donation = getDonationModel(donationId);
        // gets the donation process
        DonationProcess donationProcess = donation.getDonationProcess();

        // if it is not possible to delete the donation
        if (!donationProcess.getStatus().isCanDeleteDonation()) {
            throw new IllegalArgumentException("The donation can't be deleted!");
        }

        // deactivate the donation
        donation.deactivate();
        return donationMapper.modelToDTO(donation);
    }

    @Override
    public DonationDTO requestDonation(Long donationId) throws NotFoundEntityException, IllegalArgumentException {
        // gets the donation by its id
        Donation donation = getDonationModel(donationId);
        // gets the donation process
        DonationProcess donationProcess = donation.getDonationProcess();

        // if the donation is not in created status it cant request the donation
        if(!donationProcess.getStatus().isCanEditDonation()){
            throw new IllegalArgumentException("The donation is not in a proper status to be requested!");
        }

        // change the donation process status
        donationProcess.toRequestedStatus(null);
        return donationMapper.modelToDTO(donation);
    }

    @Override
    public DonationDTO decisionDonation(Long donationId, DonationDecisionDTO donationDecisionDTO) throws NotFoundEntityException {
        // gets the donation
        Donation donation = getDonationModel(donationId);
        // change the donation process status
        donation.getDonationProcess().decide(donationDecisionDTO.decision);
        return donationMapper.modelToDTO(donation);
    }

    private Donation getDonationModel(Long donationId) throws NotFoundEntityException{
        Donation donation = donationDao.getReferenceById(donationId);
        // if the donation does not exist
        if(!donation.isActive()){
            throw new NotFoundEntityException("The donation does not exist!");
        }
        return donation;
    }
}
