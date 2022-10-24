package les.donations.backendspring.controller.donee;

import les.donations.backendspring.api.ApiReturnMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoneeController implements IDoneeController {

    @Override
    public ResponseEntity<ApiReturnMessage> registerDonee() {
        System.out.println("POST Donee");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
