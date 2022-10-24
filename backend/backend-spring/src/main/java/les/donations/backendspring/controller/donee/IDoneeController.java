package les.donations.backendspring.controller.donee;

import les.donations.backendspring.api.ApiReturnMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;


public interface IDoneeController {

    @PostMapping(value = "/donees", produces = "application/json", consumes = "application/json")
    ResponseEntity<ApiReturnMessage> registerDonee();
}
