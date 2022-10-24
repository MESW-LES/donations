package les.donations.backendspring.controller.donation;

import les.donations.backendspring.api.ApiReturnMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DonationController implements IDonationController {

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
