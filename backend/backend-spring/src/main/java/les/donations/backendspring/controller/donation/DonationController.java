package les.donations.backendspring.controller.donation;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.controller.IController;
import les.donations.backendspring.dto.DonationDTO;
import les.donations.backendspring.exceptions.NotFoundEntityException;
import les.donations.backendspring.service.donation.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DonationController extends IController implements IDonationController{

    @Autowired
    private DonationService donationService;

    @Override
    public ResponseEntity<ApiReturnMessage> getDonations() {
        System.out.println("GET Donations");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiReturnMessage> getDonation(Long donationId) {
        System.out.println("GET Donation");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiReturnMessage> registerDonation(DonationDTO donationDTO) {
        try{
            // registers the donation
            donationDTO = donationService.registerDonation(donationDTO);
            return ok(donationDTO);

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
        System.out.println("DELETE Donation");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
