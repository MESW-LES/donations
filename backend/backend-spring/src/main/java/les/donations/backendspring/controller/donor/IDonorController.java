package les.donations.backendspring.controller.donor;

import les.donations.backendspring.api.ApiReturnMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;


public interface IDonorController {

    @PostMapping(value = "/donors", produces = "application/json", consumes = "application/json")
    ResponseEntity<ApiReturnMessage> registerDonor();
}
