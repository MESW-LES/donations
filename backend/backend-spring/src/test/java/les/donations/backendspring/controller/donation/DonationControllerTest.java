package les.donations.backendspring.controller.donation;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.controller.donation.DonationController;
import les.donations.backendspring.dto.*;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.file.IFileManagement;
import les.donations.backendspring.service.donation.IDonationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class DonationControllerTest {

    @Mock
    private IDonationService donationService;
    @Mock
    private IFileManagement fileManagement;
    @InjectMocks
    private DonationController donationController;

    @BeforeEach
    public void setup() {
        //if we don't call below, we will get NullPointerException
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDonationsIllegalInformationTest() {
        when(donationService.getDonations(Mockito.any(Integer.class), Mockito.any(String.class))).
                thenThrow(new IllegalArgumentException());
        Assertions.assertThrows(IllegalArgumentException.class, () -> donationController.getDonations(new Integer(1), ""));
    }

    @Test
    void getCorrectDonationsTest() throws IllegalArgumentException {
        when(donationService.getDonations(Mockito.any(Integer.class), Mockito.any(String.class))).thenReturn(new PaginationDTO());
        ResponseEntity<ApiReturnMessage> result = donationController.getDonations(new Integer(1), "");
        Assertions.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void getDonationIllegalInformationTest() throws NotFoundEntityException {
        when(donationService.getDonation(Mockito.any(Long.class))).
                thenThrow(new IllegalArgumentException());
        Assertions.assertThrows(IllegalArgumentException.class, () -> donationController.getDonation(1L));
    }

    @Test
    void getDonationNotFoundTest() throws NotFoundEntityException {
        when(donationService.getDonation(Mockito.any(Long.class))).
                thenThrow(new NotFoundEntityException("The donation does not exist!"));
        ResponseEntity<ApiReturnMessage> result = donationController.getDonation(1L);
        Assertions.assertEquals(404, result.getStatusCodeValue());
        Assertions.assertEquals("The donation does not exist!", result.getBody().getErrorMessage());
    }

    @Test
    void getCorrectDonationTest() throws NotFoundEntityException {
        when(donationService.getDonation(Mockito.any(Long.class))).
                thenReturn(new DonationDTO());
        ResponseEntity<ApiReturnMessage> result = donationController.getDonation(1L);
        Assertions.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void registerDonationErrorSavingFilesTest() throws NotFoundEntityException, IOException {
        MultipartFile[] files = {};
        when(fileManagement.saveFiles(Mockito.any(MultipartFile[].class),Mockito.any(String.class))).
                thenThrow(new IOException());

        ResponseEntity<ApiReturnMessage> result = donationController.registerDonation(new DonationDTO(), files);
        Assertions.assertEquals(500, result.getStatusCodeValue());
        Assertions.assertEquals("An error occurred while reading the files!", result.getBody().getErrorMessage());
    }

    @Test
    void registerDonationNotFoundTest() throws NotFoundEntityException, IOException {
        MultipartFile[] files = {};
        List<FileDTO> fileDTOList = new ArrayList<>();
        when(fileManagement.saveFiles(Mockito.any(MultipartFile[].class),Mockito.any(String.class))).
                thenReturn(fileDTOList);
        when(donationService.registerDonation(Mockito.any(DonationDTO.class), Mockito.any(List.class))).
                thenThrow(new NotFoundEntityException("The donation does not exist!"));

        ResponseEntity<ApiReturnMessage> result = donationController.registerDonation(new DonationDTO(), files);
        Assertions.assertEquals(404, result.getStatusCodeValue());
        Assertions.assertEquals("The donation does not exist!", result.getBody().getErrorMessage());
    }

    @Test
    void registerDonationWithIllegalInformationTest() throws NotFoundEntityException, IOException {
        MultipartFile[] files = {};
        List<FileDTO> fileDTOList = new ArrayList<>();
        when(fileManagement.saveFiles(Mockito.any(MultipartFile[].class),Mockito.any(String.class))).
                thenReturn(fileDTOList);
        when(donationService.registerDonation(Mockito.any(DonationDTO.class), Mockito.any(List.class))).
                thenThrow(new IllegalArgumentException());

        ResponseEntity<ApiReturnMessage> result = donationController.registerDonation(new DonationDTO(), files);
        Assertions.assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    void registerCorrectDonationTest() throws NotFoundEntityException, IOException {
        MultipartFile[] files = {};
        List<FileDTO> fileDTOList = new ArrayList<>();
        when(fileManagement.saveFiles(Mockito.any(MultipartFile[].class),Mockito.any(String.class))).
                thenReturn(fileDTOList);
        DonationDTO donationDTO = new DonationDTO();
        when(donationService.registerDonation(Mockito.any(DonationDTO.class), Mockito.any(List.class))).
                thenReturn(donationDTO);

        ResponseEntity<ApiReturnMessage> result = donationController.registerDonation(new DonationDTO(), files);
        Assertions.assertEquals(201, result.getStatusCodeValue());
        Assertions.assertEquals(donationDTO, result.getBody().getMessage());
    }

    @Test
    void updateDonationNotFoundTest() throws NotFoundEntityException, IOException {
        Long donationId = 1L;
        DonationDTO donationDTO = new DonationDTO();
        when(donationService.updateDonation(donationId, donationDTO)).
                thenThrow(new NotFoundEntityException("The donation does not exist!"));

        ResponseEntity<ApiReturnMessage> result = donationController.updateDonation(donationId, donationDTO);
        Assertions.assertEquals(404, result.getStatusCodeValue());
        Assertions.assertEquals("The donation does not exist!", result.getBody().getErrorMessage());
    }

    @Test
    void updateDonationWithIllegalInformationTest() throws NotFoundEntityException, IOException {
        Long donationId = 1L;
        DonationDTO donationDTO = new DonationDTO();
        when(donationService.updateDonation(donationId, donationDTO)).
                thenThrow(new IllegalArgumentException());

        ResponseEntity<ApiReturnMessage> result = donationController.updateDonation(donationId, donationDTO);
        Assertions.assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    void updateCorrectDonationTest() throws NotFoundEntityException, IOException {
        Long donationId = 1L;
        DonationDTO donationDTO = new DonationDTO();
        when(donationService.updateDonation(donationId, donationDTO)).
                thenReturn(donationDTO);

        ResponseEntity<ApiReturnMessage> result = donationController.updateDonation(donationId, donationDTO);
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals(donationDTO, result.getBody().getMessage());
    }

    @Test
    void deleteDonationNotFoundTest() throws NotFoundEntityException, IOException {
        Long donationId = 1L;
        when(donationService.deleteDonation(donationId)).
                thenThrow(new NotFoundEntityException("The donation does not exist!"));

        ResponseEntity<ApiReturnMessage> result = donationController.deleteDonation(donationId);
        Assertions.assertEquals(404, result.getStatusCodeValue());
        Assertions.assertEquals("The donation does not exist!", result.getBody().getErrorMessage());
    }

    @Test
    void deleteDonationWithIllegalInformationTest() throws NotFoundEntityException, IOException {
        Long donationId = 1L;
        when(donationService.deleteDonation(donationId)).
                thenThrow(new IllegalArgumentException());

        ResponseEntity<ApiReturnMessage> result = donationController.deleteDonation(donationId);
        Assertions.assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    void deleteCorrectDonationTest() throws NotFoundEntityException, IOException {
        Long donationId = 1L;
        DonationDTO donationDTO = new DonationDTO();
        when(donationService.deleteDonation(donationId)).
                thenReturn(donationDTO);

        ResponseEntity<ApiReturnMessage> result = donationController.deleteDonation(donationId);
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals(donationDTO, result.getBody().getMessage());
    }

    @Test
    void requestDonationNotFoundTest() throws NotFoundEntityException, IOException {
        Long donationId = 1L;
        when(donationService.requestDonation(donationId)).
                thenThrow(new NotFoundEntityException("The donation does not exist!"));

        ResponseEntity<ApiReturnMessage> result = donationController.requestDonation(donationId);
        Assertions.assertEquals(404, result.getStatusCodeValue());
        Assertions.assertEquals("The donation does not exist!", result.getBody().getErrorMessage());
    }

    @Test
    void requestDonationWithIllegalInformationTest() throws NotFoundEntityException, IOException {
        Long donationId = 1L;
        when(donationService.requestDonation(donationId)).
                thenThrow(new IllegalArgumentException());

        ResponseEntity<ApiReturnMessage> result = donationController.requestDonation(donationId);
        Assertions.assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    void requestCorrectDonationTest() throws NotFoundEntityException, IOException {
        Long donationId = 1L;
        DonationDTO donationDTO = new DonationDTO();
        when(donationService.requestDonation(donationId)).
                thenReturn(donationDTO);

        ResponseEntity<ApiReturnMessage> result = donationController.requestDonation(donationId);
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals(donationDTO, result.getBody().getMessage());
    }

    @Test
    void decisionDonationNotFoundTest() throws NotFoundEntityException, IOException {
        Long donationId = 1L;
        DonationDecisionDTO decisionDTO = new DonationDecisionDTO();
        when(donationService.decisionDonation(donationId, decisionDTO)).
                thenThrow(new NotFoundEntityException("The donation does not exist!"));

        ResponseEntity<ApiReturnMessage> result = donationController.decisionDonation(donationId, decisionDTO);
        Assertions.assertEquals(404, result.getStatusCodeValue());
        Assertions.assertEquals("The donation does not exist!", result.getBody().getErrorMessage());
    }

    @Test
    void decisionCorrectDonationTest() throws NotFoundEntityException, IOException {
        Long donationId = 1L;
        DonationDecisionDTO decisionDTO = new DonationDecisionDTO();
        DonationDTO donationDTO = new DonationDTO();
        when(donationService.decisionDonation(donationId, decisionDTO)).
                thenReturn(donationDTO);

        ResponseEntity<ApiReturnMessage> result = donationController.decisionDonation(donationId, decisionDTO);
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals(donationDTO, result.getBody().getMessage());
    }
}