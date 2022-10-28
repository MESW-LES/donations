package les.donations.backendspring.controller.donee;

import les.donations.backendspring.api.ApiReturnMessage;
import les.donations.backendspring.dto.DoneeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface IDoneeController {

    @PostMapping(value = "/donees", produces = "application/json", consumes = "application/json")
    ResponseEntity<ApiReturnMessage> registerDonee(@RequestBody DoneeDTO doneeDTO);
}
