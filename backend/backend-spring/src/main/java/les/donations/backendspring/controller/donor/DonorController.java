package les.donations.backendspring.controller.donor;

import les.donations.backendspring.api.ApiReturnMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DonorController implements IDonorController {

    @Override
    public ResponseEntity<ApiReturnMessage> registerDonor() {
        System.out.println("POST Donor");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
