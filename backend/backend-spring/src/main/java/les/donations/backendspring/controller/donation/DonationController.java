package les.donations.backendspring.controller.donation;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.dto.DonationDTO;
import les.donations.backendspring.service.donation.IDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import les.donations.backendspring.controller.IController;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class DonationController extends IController implements IDonationController {

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
    public ResponseEntity<ApiReturnMessage> registerDonation(DonationDTO donationDTO) {
        try{
            // registers the donation
            donationDTO = donationService.registerDonation(donationDTO);
            return created(donationDTO);

            // if any entity does not exist
        }catch (NotFoundEntityException e){
            return notFound(e.getMessage());

            // if the information has any error
        }catch (IllegalArgumentException e){
            return badRequest(e.getMessage());
        }

    }

    @Override
    public ResponseEntity<ApiReturnMessage> updateDonation(Long donationId) {
        System.out.println("PUT Donation");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiReturnMessage> deleteDonation(Long donationId) {
        donationService.deleteDonation(donationId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
