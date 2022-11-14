package les.donations.backendspring.controller.donation;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.dto.DonationDTO;
import les.donations.backendspring.service.donation.IDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class DonationController implements IDonationController {

    @Autowired
    private IDonationService donationService;

    @Override
    public ResponseEntity getDonations(Long donorId) {
        HttpStatus httpStatus;
        List<DonationDTO> donationDTOS;
        try {
            donationDTOS = donationService.getDonations(donorId);
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<>(donationDTOS, httpStatus);
        } catch (IllegalArgumentException ex) {
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(ex.getMessage(), httpStatus);
        }

    }

    @Override
    public ResponseEntity<ApiReturnMessage> getDonation(Long donationId) {
        ApiReturnMessage apiReturnMessage;
        HttpStatus httpStatus;
        DonationDTO donationDTO;
        try {
            donationDTO = donationService.getDonation(donationId);
            httpStatus = HttpStatus.CREATED;
            apiReturnMessage = new ApiReturnMessage(httpStatus.value(), donationDTO);
        } catch (IllegalArgumentException ex) {
            httpStatus = HttpStatus.BAD_REQUEST;
            apiReturnMessage = new ApiReturnMessage(httpStatus.value(), ex.getMessage());
        }
        return new ResponseEntity<>(apiReturnMessage, httpStatus);
    }

    @Override
    public ResponseEntity<ApiReturnMessage> registerDonation() {
        System.out.println("POST Donation");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiReturnMessage> updateDonation(Long donationId) {
        System.out.println("PUT Donation");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiReturnMessage> deleteDonation(Long donationId) {
        System.out.println("DELETE Donation");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
