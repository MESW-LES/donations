package les.donations.backendspring.service.donation;

import les.donations.backendspring.dto.*;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.mapper.donation.IDonationMapper;
import les.donations.backendspring.mapper.donationImage.IDonationImageMapper;
import les.donations.backendspring.model.*;
import les.donations.backendspring.repository.category.CategoryDao;
import les.donations.backendspring.repository.donation.DonationDao;
import les.donations.backendspring.service.category.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class DonationServiceTest {

    @Mock
    private DonationDao donationDao;
    @Mock
    private CategoryDao categoryDao;
    @Mock
    private IDonationMapper donationMapper;
    @Mock
    private CategoryService categoryService;
    @Mock
    private IDonationImageMapper imageMapper;
    @InjectMocks
    private DonationService donationService;

    @BeforeEach
    public void setup() {
        //if we don't call below, we will get NullPointerException
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createDonationWithIllegalInformationTest(){
        DonationDTO donationDTO = new DonationDTO().id(1L).title("").description("").categoriesCode("1,2");
        when(donationMapper.dtoToModel(donationDTO)).thenThrow(new IllegalArgumentException("The title can't be null or empty!"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> donationService.registerDonation(donationDTO, new ArrayList<>()));
    }

    @Test
    void createDonationWithNotExistingCategoryCodeTest() throws NotFoundEntityException {
        DonationDTO donationDTO = new DonationDTO().id(1L).title("").description("").categoriesCode("1,2");
        when(donationMapper.dtoToModel(donationDTO)).thenReturn(new Donation("title", "description"));
        when(categoryService.getCategoryModel("1")).thenThrow(new NotFoundEntityException("The category with the code 1 does not exist!"));
        Assertions.assertThrows(NotFoundEntityException.class, () -> donationService.registerDonation(donationDTO, new ArrayList<>()));
    }

    @Test
    void createIllegalDonationTest() throws NotFoundEntityException {
        Donation donation = new Donation("title", "desc");
        DonationDTO donationDTO = new DonationDTO().id(1L).title("").description("").categoriesCode("1,2");
        when(donationMapper.dtoToModel(donationDTO)).thenReturn(donation);
        when(categoryService.getCategoryModel("1")).thenReturn(new Category("1", "name", "desc"));
        when(imageMapper.dtoToModel(new FileDTO())).thenReturn(new DonationImage("", "", "", "", 1L));
        when(donationDao.saveAndFlush(donation)).thenReturn(donation);
        when(donationMapper.modelToDTO(donation)).thenThrow(new IllegalArgumentException());
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                donationService.registerDonation(donationDTO, new ArrayList<>()));
    }

    @Test
    void updateDonationNotEditableTest() {
        DonationDTO donationDTO = new DonationDTO();
        Long donationId = 1L;
        Donation donation = new Donation("title", "desc");
        DonationProcess donationProcess = new DonationProcess(donation);
        donationProcess.setStatus(Status.REQUESTED);
        donation.setDonationProcess(donationProcess);

        when(donationDao.getReferenceById(donationId)).thenReturn(donation);
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                donationService.updateDonation(donationId, donationDTO));
    }

    @Test
    void updateDonationCategoryNotFoundTest() throws NotFoundEntityException {
        DonationDTO donationDTO = new DonationDTO().categoriesCode("1");
        Long donationId = 1L;
        Donation donation = new Donation("title", "desc");
        DonationProcess donationProcess = new DonationProcess(donation);
        donationProcess.setStatus(Status.CREATED);
        donation.setDonationProcess(donationProcess);

        when(donationDao.getReferenceById(donationId)).thenReturn(donation);
        when(categoryService.getCategoryModel("1")).thenThrow(new NotFoundEntityException(""));
        Assertions.assertThrows(NotFoundEntityException.class, () ->
                donationService.updateDonation(donationId, donationDTO));
    }

    @Test
    void updateCorrectDonationTest() throws NotFoundEntityException {
        Long donationId = 1L;
        DonationDTO donationDTO = new DonationDTO().id(1L).title("title1").description("desc1").categoriesCode("1");
        Donation donation = new Donation("title", "desc");
        DonationProcess donationProcess = new DonationProcess(donation);
        donationProcess.setStatus(Status.CREATED);
        donation.setDonationProcess(donationProcess);
        Category category = new Category("A", "test", "desc");
        donation.addCategory(category);

        when(donationDao.getReferenceById(donationId)).thenReturn(donation);
        when(categoryService.getCategoryModel("1")).thenReturn(category);
        DonationDTO returned = donationService.updateDonation(donationId, donationDTO);

        assertEquals(donationId, returned.id);
        assertEquals("title1", returned.title);
        assertEquals("desc1", returned.description);
    }

    @Test
    void getDonationsIllegalInformationTest() {
        Integer id = new Integer(1);
        Donation donation = new Donation("title", "desc");
        List<Donation> donations = Collections.singletonList(donation);
        when(donationDao.getDonations(id)).thenReturn(donations);
        when(donationMapper.modelToDTO(donation)).thenThrow(new IllegalArgumentException());

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                donationService.getDonations(id));
    }

    @Test
    void getCorrectDonationsTest() {
        Integer id = new Integer(1);
        Donation donation = new Donation("title", "desc");
        List<Donation> donations = Collections.singletonList(donation);
        when(donationDao.getDonations(id)).thenReturn(donations);

        DonationDTO donationDTO = new DonationDTO();
        List<ModelDTO> donationDTOs = Collections.singletonList(donationDTO);
        when(donationMapper.modelToDTO(donation)).thenReturn(donationDTO);
        when(categoryDao.findAll()).thenReturn(new ArrayList<>());

        PaginationDTO returned = donationService.getDonations(id);

        assertEquals(donationDTOs, returned.results);
        assertEquals(donations.size(), returned.countResults);
    }

    @Test
    void getNotFoundDonationTest() {
        Long donationId = 1L;
        Donation donation = new Donation("title", "desc");
        DonationProcess donationProcess = new DonationProcess(donation);
        donationProcess.setStatus(Status.REQUESTED);
        donation.setDonationProcess(donationProcess);
        Category category = new Category("A", "test", "desc");
        donation.addCategory(category);
        donation.setActive(false);

        when(donationDao.getReferenceById(donationId)).thenReturn(donation);
        Assertions.assertThrows(NotFoundEntityException.class, () ->
                donationService.getDonation(donationId));
    }

    @Test
    void getIlligalInformationDonationTest() {
        Long donationId = 1L;
        Donation donation = new Donation("title", "desc");
        DonationProcess donationProcess = new DonationProcess(donation);
        donationProcess.setStatus(Status.REQUESTED);
        donation.setDonationProcess(donationProcess);
        Category category = new Category("A", "test", "desc");
        donation.addCategory(category);

        when(donationDao.getReferenceById(donationId)).thenReturn(donation);
        when(donationMapper.modelToDTO(donation)).thenThrow(new IllegalArgumentException());
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                donationService.getDonation(donationId));
    }

    @Test
    void getCorrectDonationTest() throws NotFoundEntityException {
        Long donationId = 1L;
        Donation donation = new Donation("title", "desc");
        DonationProcess donationProcess = new DonationProcess(donation);
        donationProcess.setStatus(Status.REQUESTED);
        donation.setDonationProcess(donationProcess);
        Category category = new Category("A", "test", "desc");
        donation.addCategory(category);

        when(donationDao.getReferenceById(donationId)).thenReturn(donation);
        DonationDTO donationDTO = new DonationDTO().id(2L).title("title");
        when(donationMapper.modelToDTO(donation)).thenReturn(donationDTO);

        assertEquals(donationDTO,donationService.getDonation(donationId));
    }

    @Test
    void deleteNotDeletableDonationTest() {
        Long donationId = 1L;
        Donation donation = new Donation("title", "desc");
        DonationProcess donationProcess = new DonationProcess(donation);
        donationProcess.setStatus(Status.ONGOING);
        donation.setDonationProcess(donationProcess);
        Category category = new Category("A", "test", "desc");
        donation.addCategory(category);

        when(donationDao.getReferenceById(donationId)).thenReturn(donation);
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                donationService.deleteDonation(donationId));
    }

    @Test
    void deleteDonationWithIllegalInformationTest() {
        Long donationId = 1L;
        Donation donation = new Donation("title", "desc");
        DonationProcess donationProcess = new DonationProcess(donation);
        donationProcess.setStatus(Status.CREATED);
        donation.setDonationProcess(donationProcess);
        Category category = new Category("A", "test", "desc");
        donation.addCategory(category);

        when(donationDao.getReferenceById(donationId)).thenReturn(donation);
        when(donationMapper.modelToDTO(donation)).thenThrow(new IllegalArgumentException());
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                donationService.deleteDonation(donationId));
    }

    @Test
    void deleteCorrectDonationTest() throws NotFoundEntityException {
        Long donationId = 1L;
        Donation donation = new Donation("title", "desc");
        DonationProcess donationProcess = new DonationProcess(donation);
        donationProcess.setStatus(Status.CREATED);
        donation.setDonationProcess(donationProcess);
        Category category = new Category("A", "test", "desc");
        donation.addCategory(category);
        donation.setActive(true);

        when(donationDao.getReferenceById(donationId)).thenReturn(donation);
        DonationDTO donationDTO = new DonationDTO().id(1L).title("title").description("desc");
        when(donationMapper.modelToDTO(donation)).thenReturn(donationDTO);
        DonationDTO returned = donationService.deleteDonation(donationId);

        assertFalse(donation.isActive());
    }

    @Test
    void requestNotEditableDonationTest() {
        Long donationId = 1L;
        Donation donation = new Donation("title", "desc");
        DonationProcess donationProcess = new DonationProcess(donation);
        donationProcess.setStatus(Status.ONGOING);
        donation.setDonationProcess(donationProcess);
        Category category = new Category("A", "test", "desc");
        donation.addCategory(category);

        when(donationDao.getReferenceById(donationId)).thenReturn(donation);
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                donationService.requestDonation(donationId));
    }

    @Test
    void requestIllegalDonationTest() {
        Long donationId = 1L;
        Donation donation = new Donation("title", "desc");
        DonationProcess donationProcess = new DonationProcess(donation);
        donationProcess.setStatus(Status.CREATED);
        donation.setDonationProcess(donationProcess);
        Category category = new Category("A", "test", "desc");
        donation.addCategory(category);

        when(donationDao.getReferenceById(donationId)).thenReturn(donation);
        when(donationMapper.modelToDTO(donation)).thenThrow(new IllegalArgumentException());
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                donationService.requestDonation(donationId));
    }

    @Test
    void requestCorrectDonationTest() throws NotFoundEntityException {
        Long donationId = 1L;
        Donation donation = new Donation("title", "desc");
        DonationProcess donationProcess = new DonationProcess(donation);
        donationProcess.setStatus(Status.CREATED);
        donation.setDonationProcess(donationProcess);
        Category category = new Category("A", "test", "desc");
        donation.addCategory(category);
        donation.setActive(true);

        when(donationDao.getReferenceById(donationId)).thenReturn(donation);
        DonationDTO donationDTO = new DonationDTO().id(1L).title("title").description("desc");
        when(donationMapper.modelToDTO(donation)).thenReturn(donationDTO);
        DonationDTO returned = donationService.requestDonation(donationId);

        assertNull(donationProcess.getDonee());
    }

    @Test
    void decisionIllegalInformationDonationTest() {
        Long donationId = 1L;
        Donation donation = new Donation("title", "desc");
        DonationProcess donationProcess = new DonationProcess(donation);
        donationProcess.setStatus(Status.CREATED);
        donation.setDonationProcess(donationProcess);
        Category category = new Category("A", "test", "desc");
        donation.addCategory(category);

        DonationDecisionDTO decisionDTO = new DonationDecisionDTO().decision(true);

        when(donationDao.getReferenceById(donationId)).thenReturn(donation);
        when(donationMapper.modelToDTO(donation)).thenThrow(new IllegalArgumentException());

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                donationService.decisionDonation(donationId, decisionDTO));
    }

    @Test
    void decisionCorrectDonationTest() throws NotFoundEntityException {
        Long donationId = 1L;
        Donation donation = new Donation("title", "desc");
        DonationProcess donationProcess = new DonationProcess(donation);
        donationProcess.setStatus(Status.REQUESTED);
        donation.setDonationProcess(donationProcess);
        Category category = new Category("A", "test", "desc");
        donation.addCategory(category);

        DonationDTO donationDTO = new DonationDTO().id(2L).title("title");
        DonationDecisionDTO decisionDTO = new DonationDecisionDTO().decision(true);

        when(donationDao.getReferenceById(donationId)).thenReturn(donation);
        when(donationMapper.modelToDTO(donation)).thenReturn(donationDTO);

        DonationDTO returned = donationService.decisionDonation(donationId, decisionDTO);
        assertEquals(2L, returned.id);
        assertEquals("title", returned.title);
    }
}
